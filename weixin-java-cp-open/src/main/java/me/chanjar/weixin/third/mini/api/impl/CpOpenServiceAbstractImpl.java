package me.chanjar.weixin.third.mini.api.impl;


import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

import me.chanjar.weixin.third.mini.api.CpOpenConfigStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import me.chanjar.weixin.third.mini.api.CpOpenService;
import me.chanjar.weixin.third.mini.api.WxOpenSuiteService;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public abstract class CpOpenServiceAbstractImpl<H, P> implements CpOpenService, RequestHttp<H, P> {
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  protected WxOpenSuiteService wxOpenSuiteService = new WxOpenSuiteServiceImpl(this);
  private CpOpenConfigStorage cpOpenConfigStorage;

  @Override
  public WxOpenSuiteService getWxOpenSuiteService() {
    return wxOpenSuiteService;
  }

  @Override
  public CpOpenConfigStorage getCpOpenConfigStorage() {
    return cpOpenConfigStorage;
  }

  public void setCpOpenConfigStorage(CpOpenConfigStorage cpOpenConfigStorage) {
    this.cpOpenConfigStorage = cpOpenConfigStorage;
  }

  protected synchronized <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    try {
      T result = executor.execute(uri, data);
      this.log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
//      /*
//       * 发生以下情况时尝试刷新access_token
//       * 40001 获取access_token时AppSecret错误，或者access_token无效
//       * 42001 access_token超时
//       * 40014 不合法的access_token，请开发者认真比对access_token的有效性（如是否过期），或查看是否正在为恰当的公众号调用接口
//       */
//      if (error.getErrorCode() == 42001 || error.getErrorCode() == 40001 || error.getErrorCode() == 40014) {
//        // 强制设置wxCpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
//        this.configStorage.expireAccessToken();
//        return execute(executor, uri, data);
//      }

      if (error.getErrorCode() != 0) {
        this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, data, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      this.log.error("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, data, e.getMessage());
      throw new RuntimeException(e);
    }
  }
}
