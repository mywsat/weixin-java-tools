package me.chanjar.weixin.third.mini.api.impl;


import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.HttpType;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;
import me.chanjar.weixin.common.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * apache-http方式实现
 *
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public class ThirdMiniServiceApacheHttpClientImpl extends ThirdMiniServiceAbstractImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient = DefaultApacheHttpClientBuilder.get().build();
  private HttpHost httpProxy = null;

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
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

}
