package me.chanjar.weixin.third.mini.api;

import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.third.mini.bean.WxSuieAccessToken;


/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public interface CpOpenConfigStorage {

  String getSuiteId();

  void setSuiteId(String SuiteId);

  String getSuiteSecret();
  String getSuiteTicket();
  void setSuiteSecret(String suiteSecret);

  String getSuiteToken();

  void setSuiteToken(String suiteToken);

  String getSuiteAesKey();

  void setSuiteAesKey(String suiteAesKey);


  void setSuiteTicket(String suiteTicket);

  String getSuiteAccessToken();

  boolean isSuiteAccessTokenExpired();

  void updateSuiteAccessTokent(WxSuieAccessToken suieAccessToken);
  String getJsapiTicket(String corpId);
  boolean isJsapiTicketExpired(String corpId);
  void expireJsapiTicket(String corpId);
  void updateJsapiTicket(String corpId, String jsapiTicket, int expiresInSeconds);
  boolean isAccessTokenExpired(String corpId);
  void expireAccessToken(String corpId);
  void updateAccessToken(String corpId, String accessToken, int expiresInSeconds);
  WxCpConfigStorage getWxCpConfigStorage(String corpId);
  String getAccessToken(String corpId);
  /**
   * 应该是线程安全的
   *
   * @param suiteAccessToken 新的accessToken值
   * @param expiresInSeconds     过期时间，以秒为单位
   */
  void updateSuiteAccessTokent(String suiteAccessToken, int expiresInSeconds);

  /**
   * 是否自动刷新token
   */
  boolean autoRefreshToken();

  Integer getAgentId(String corpId);
  void setAgentId(String corpId,Integer agentId);


  String getCpPermanentCode(String corpId);
  void setCpPermanentCode(String corpId,String cpPermanentCode);



}
