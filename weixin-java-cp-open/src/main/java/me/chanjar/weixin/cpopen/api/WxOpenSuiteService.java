package me.chanjar.weixin.cpopen.api;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.cpopen.bean.WxCpPermanentCode;
import me.chanjar.weixin.cpopen.bean.auth.CpOpenUserInfo;


import me.chanjar.weixin.cpopen.bean.message.CpOpenXmlMessage;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public interface WxOpenSuiteService {

  String API_SUITE_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";
  String API_CREATE_PREAUTHCODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code";
  String API_PERMANENT_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code";
  String API_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token";
  String API_GET_AUTH_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_auth_info";
  String API_GET_JSAPI_TICKET="https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";

  String SUITE_INSTALL_PAGE_URL = "https://open.work.weixin.qq.com/3rdapp/install?suite_id=%s&pre_auth_code=%s&redirect_uri=%s&state=%s";

  String CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect";

  String OAUTH2_USER_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserinfo3rd?code=%s";
  String OAUTH2_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserdetail3rd";

  WxCpService getWxCpServiceByCorpId(String corpId);

  CpOpenConfigStorage getCpOpenConfigStorage();

  boolean checkSignature(String timestamp, String nonce, String signature);

  String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException;


  /**
   * 获取用户授权页URL（来路URL和成功跳转URL 的域名都需要为三方平台设置的 登录授权的发起页域名）
   */
  String getPreAuthUrl(String redirectURI,String status) throws WxErrorException;


  /**
   * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
   */
  WxCpPermanentCode getPermanentCode(String authCode) throws WxErrorException;
  String getAccessToken(String corpId,boolean forceRefresh) throws WxErrorException;
  WxCpPermanentCode route(CpOpenXmlMessage cpXmlMessage) throws WxErrorException;









  CpOpenUserInfo getUserTicket(String code) throws WxErrorException;

  WxCpUser oauth2getUserInfo(String userTicket) throws WxErrorException;

  boolean checkSignature(String corpId, String timestamp, String nonce, String signature);

  String oauth2buildAuthorizationUrl( String redirectURI, String scope, String state);

}
