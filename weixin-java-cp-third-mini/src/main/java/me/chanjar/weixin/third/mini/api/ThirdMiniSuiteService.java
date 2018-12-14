package me.chanjar.weixin.third.mini.api;


import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;
import me.chanjar.weixin.third.mini.bean.WxCpPermanentCode;
import me.chanjar.weixin.third.mini.bean.auth.CpOpenUserInfo;


import me.chanjar.weixin.third.mini.bean.message.CpOpenXmlMessage;
import me.chanjar.weixin.third.mini.bean.result.MiniJscodeToSessionResult;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public interface ThirdMiniSuiteService {

  String API_SUITE_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";
  String API_CREATE_PREAUTHCODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code";
  String API_JSCODE_TO_SESSION="https://qyapi.weixin.qq.com/cgi-bin/service/miniprogram/jscode2session?js_code=%s&grant_type=authorization_code";
  String API_PERMANENT_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code";
  String API_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token";
  String API_GET_AUTH_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_auth_info";
  String API_GET_JSAPI_TICKET="https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket";

  String SUITE_INSTALL_PAGE_URL = "https://open.work.weixin.qq.com/3rdapp/install?suite_id=%s&pre_auth_code=%s&redirect_uri=%s&state=%s";

  String CONNECT_OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s&connect_redirect=1#wechat_redirect";

  String OAUTH2_USER_TICKET = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserinfo3rd?code=%s";
  String OAUTH2_USER_INFO = "https://qyapi.weixin.qq.com/cgi-bin/service/getuserdetail3rd";

  WxCpService getWxCpServiceByCorpId(String corpId);

  ThirdMiniConfigStorage getThirdMiniConfigStorage();

  boolean checkSignature(String timestamp, String nonce, String signature);

  String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException;


  /**
   * 服务商服务器以code换取 用户唯一标识 userid 、用户所在企业corpid 和 会话密钥 session_key。
   */
  MiniJscodeToSessionResult getJscodeTosession(String code) throws WxErrorException;


  /**
   * 使用授权码换取公众号或小程序的接口调用凭据和授权信息
   */
  WxCpPermanentCode getPermanentCode(String authCode) throws WxErrorException;
  String getAccessToken(String corpId,boolean forceRefresh) throws WxErrorException;
  WxCpPermanentCode route(CpOpenXmlMessage cpXmlMessage) throws WxErrorException;

  boolean checkSignature(String corpId, String timestamp, String nonce, String signature);

  String oauth2buildAuthorizationUrl( String redirectURI, String scope, String state);

}
