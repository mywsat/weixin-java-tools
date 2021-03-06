package me.chanjar.weixin.third.mini.api;


import me.chanjar.weixin.common.error.WxErrorException;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public interface CpOpenService {
  WxOpenSuiteService getWxOpenSuiteService();

  CpOpenConfigStorage getCpOpenConfigStorage();

  void setCpOpenConfigStorage(CpOpenConfigStorage wxOpenConfigStorage);

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的GET请求
   */
  String get(String url, String queryParam) throws WxErrorException;

  /**
   * 当本Service没有实现某个API的时候，可以用这个，针对所有微信API中的POST请求
   */
  String post(String url, String postData) throws WxErrorException;

}
