package me.chanjar.weixin.cp.api.impl;


import me.chanjar.weixin.common.WxType;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public class WxCpServiceApacheHttpClientImpl extends WxCpServiceAbstractImpl<CloseableHttpClient, HttpHost> {
  protected CloseableHttpClient httpClient;
  protected HttpHost httpProxy;

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.APACHE_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {

    if (this.configStorage.isAccessTokenExpired() || forceRefresh) {
      synchronized (this.globalAccessTokenRefreshLock) {
        if (this.configStorage.isAccessTokenExpired()) {
          String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?"
            + "&corpid=" + this.configStorage.getCorpId()
            + "&corpsecret=" + this.configStorage.getCorpSecret();
          try {
            HttpGet httpGet = new HttpGet(url);
            if (this.httpProxy != null) {
              RequestConfig config = RequestConfig.custom()
                .setProxy(this.httpProxy).build();
              httpGet.setConfig(config);
            }
            String resultContent = null;
            try (CloseableHttpClient httpclient = getRequestHttpClient();
                 CloseableHttpResponse response = httpclient.execute(httpGet)) {
              resultContent = new BasicResponseHandler().handleResponse(response);
            } finally {
              httpGet.releaseConnection();
            }
            WxError error = WxError.fromJson(resultContent, WxType.CP);
            if (error.getErrorCode() != 0) {
              throw new WxErrorException(error);
            }
            WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
            this.configStorage.updateAccessToken(
              accessToken.getAccessToken(), accessToken.getExpiresIn());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    WxCpConfigStorage wxCpConfigStorage = getWxCpConfigStorage();
    ApacheHttpClientBuilder apacheHttpClientBuilder = wxCpConfigStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(wxCpConfigStorage.getHttpProxyHost())
      .httpProxyPort(wxCpConfigStorage.getHttpProxyPort())
      .httpProxyUsername(wxCpConfigStorage.getHttpProxyUsername())
      .httpProxyPassword(wxCpConfigStorage.getHttpProxyPassword());

    if (wxCpConfigStorage.getHttpProxyHost() != null && wxCpConfigStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(wxCpConfigStorage.getHttpProxyHost(), wxCpConfigStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }

}
