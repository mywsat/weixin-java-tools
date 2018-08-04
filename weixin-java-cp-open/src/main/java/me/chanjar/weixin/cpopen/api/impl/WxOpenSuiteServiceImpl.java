package me.chanjar.weixin.cpopen.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.bean.result.WxError;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.cpopen.api.CpOpenConfigStorage;
import me.chanjar.weixin.cpopen.api.CpOpenService;
import me.chanjar.weixin.cpopen.api.WxOpenSuiteService;
import me.chanjar.weixin.cpopen.bean.WxCpAccessToken;
import me.chanjar.weixin.cpopen.bean.WxCpPermanentCode;
import me.chanjar.weixin.cpopen.bean.WxSuieAccessToken;
import me.chanjar.weixin.cpopen.bean.auth.CpOpenUserInfo;
import me.chanjar.weixin.cpopen.bean.message.CpOpenXmlMessage;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public class WxOpenSuiteServiceImpl implements WxOpenSuiteService {
  private static final Map<String, WxCpService> wxOpenCpServiceMap = new Hashtable<>();
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private CpOpenService cpOpenService;
  private static Gson gson = new Gson();

  public WxOpenSuiteServiceImpl(CpOpenService cpOpenService) {
    this.cpOpenService = cpOpenService;
  }

  @Override
  public WxCpService getWxCpServiceByCorpId(String corpId) {
    WxCpService wxCpService = wxOpenCpServiceMap.get(corpId);
    if (wxCpService == null) {
      synchronized (wxOpenCpServiceMap) {
        wxCpService = wxOpenCpServiceMap.get(corpId);
        if (wxCpService == null) {
          wxCpService = new WxOpenCpServiceImpl(this, corpId, getCpOpenConfigStorage().getWxCpConfigStorage(corpId));

          wxOpenCpServiceMap.put(corpId, wxCpService);
        }
      }
    }
    return wxCpService;
  }

  @Override
  public CpOpenConfigStorage getCpOpenConfigStorage() {
    return cpOpenService.getCpOpenConfigStorage();
  }

  public CpOpenService getCpOpenService() {
    return cpOpenService;
  }


  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getCpOpenConfigStorage().getSuiteToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }



  @Override
  public String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException {

    if (this.getCpOpenConfigStorage().isSuiteAccessTokenExpired() || forceRefresh) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("suite_id", getCpOpenConfigStorage().getSuiteId());
      jsonObject.addProperty("suite_secret", getCpOpenConfigStorage().getSuiteSecret());
      jsonObject.addProperty("suite_ticket", getCpOpenConfigStorage().getSuiteTicket());

      String responseContent = this.getCpOpenService().post(API_SUITE_TOKEN_URL, jsonObject.toString());
      WxSuieAccessToken wxSuieAccessToken = gson.fromJson(responseContent, WxSuieAccessToken.class);
      getCpOpenConfigStorage().updateSuiteAccessTokent(wxSuieAccessToken);
      return wxSuieAccessToken.getSuiteAccessToken();
    }
    return this.getCpOpenConfigStorage().getSuiteAccessToken();
  }

  private String post(String uri, String postData) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + suiteAccessToken;
    return getCpOpenService().post(uriWithComponentAccessToken, postData);
  }
  private String postAccess(String uri, String postData) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + suiteAccessToken;
    return getCpOpenService().post(uriWithComponentAccessToken, postData);
  }
  private String get(String uri) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + suiteAccessToken;
    return getCpOpenService().get(uriWithComponentAccessToken, null);
  }
  private String getAccess(String uri) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + suiteAccessToken;
    return getCpOpenService().get(uriWithComponentAccessToken, null);
  }
  @Override
  public String getPreAuthUrl(String redirectURI,String status) throws WxErrorException {
    String suiteId=getCpOpenConfigStorage().getSuiteId();
    String responseContent = get(API_CREATE_PREAUTHCODE_URL);
    JsonObject jsonObject = WxGsonBuilder.create().fromJson(responseContent, JsonObject.class);
    return String.format(SUITE_INSTALL_PAGE_URL, suiteId, jsonObject.get("pre_auth_code").getAsString(), URIUtil.encodeURIComponent(redirectURI),status);
  }

  @Override
  public WxCpPermanentCode getPermanentCode(String authCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("auth_code",authCode);
    String responseContent = post(API_PERMANENT_CODE_URL, jsonObject.toString());
    WxCpPermanentCode wxCpPermanentCode = gson.fromJson(responseContent, WxCpPermanentCode.class);
    this.getCpOpenConfigStorage().setCpPermanentCode(wxCpPermanentCode.getAuthCorpInfo().getCorpid(),wxCpPermanentCode.getPermanentCode());
    return wxCpPermanentCode;
  }

  @Override
  public String getAccessToken(String corpId, boolean forceRefresh) throws WxErrorException {
    if(this.getCpOpenConfigStorage().getWxCpConfigStorage(corpId).isAccessTokenExpired() || forceRefresh) {
      String cpPermanentCode = getCpOpenConfigStorage().getCpPermanentCode(corpId);
      if(cpPermanentCode==null||cpPermanentCode.equals("")){
        log.error(corpId+"永久授权码不存在，请及时初始化corpId:"+corpId);
        throw new WxErrorException(null,null);

      }
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("auth_corpid",corpId);
      jsonObject.addProperty("permanent_code", cpPermanentCode);
      String uri=API_ACCESS_TOKEN_URL;
      String url = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + this.getSuiteAccessToken(false);

      String responseContent = this.getCpOpenService().post(url, jsonObject.toString());
      WxCpAccessToken wxCpAccessToken = gson.fromJson(responseContent, WxCpAccessToken.class);
      getCpOpenConfigStorage().updateAccessToken(corpId,wxCpAccessToken.getAccessToken(),wxCpAccessToken.getExpiresIn());
    }
    return this.getCpOpenConfigStorage().getAccessToken(corpId);


  }



  @Override
  public CpOpenUserInfo getUserTicket(String code) throws WxErrorException {
    String url=String.format(OAUTH2_USER_TICKET,code);
    String responseContent = getAccess(url);
    CpOpenUserInfo cpOpenUserInfo = gson.fromJson(responseContent, CpOpenUserInfo.class);
    return cpOpenUserInfo;
  }

  @Override
  public WxCpUser oauth2getUserInfo(String userTicket) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_ticket",userTicket);
    String responseContent = postAccess(OAUTH2_USER_INFO, jsonObject.toString());
    WxCpUser wxCpUser = WxCpUser.fromJson(responseContent);
    return wxCpUser;
  }

  @Override
  public WxCpPermanentCode route(final CpOpenXmlMessage wxMessage) throws WxErrorException {
    if (wxMessage == null) {
      throw new NullPointerException("message is empty");
    }
    if (StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "suite_ticket")) {
      getCpOpenConfigStorage().setSuiteTicket(wxMessage.getSuiteTicket());
      return null;
    }
    if(StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "create_auth")){
      String authCode = wxMessage.getAuthCode();
      WxCpPermanentCode permanentCode = getPermanentCode(authCode);
      String appid=getCpOpenConfigStorage().getSuiteId();
      Integer agentId=null;
      List<WxCpPermanentCode.AuthInfoBean.AgentBean> agents = permanentCode.getAuthInfo().getAgent();
      agentId=agents.get(0).getAgentid();
      String authCorpId = permanentCode.getAuthCorpInfo().getCorpid();
      getCpOpenConfigStorage().setAgentId(authCorpId,agentId);
      getCpOpenConfigStorage().setCpPermanentCode(authCorpId,permanentCode.getPermanentCode());
      return permanentCode;
    }

    return null;
  }








  @Override
  public boolean checkSignature(String appid, String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getCpOpenConfigStorage().getSuiteToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }

  }



  @Override
  public String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL,
      getCpOpenConfigStorage().getSuiteId(), URIUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state) );
  }

}
