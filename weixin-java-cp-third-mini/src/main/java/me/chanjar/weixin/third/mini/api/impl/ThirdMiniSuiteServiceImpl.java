package me.chanjar.weixin.third.mini.api.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.URIUtil;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUser;

import me.chanjar.weixin.third.mini.bean.WxCpAccessToken;
import me.chanjar.weixin.third.mini.bean.WxCpPermanentCode;
import me.chanjar.weixin.third.mini.bean.WxSuieAccessToken;
import me.chanjar.weixin.third.mini.bean.auth.CpOpenUserInfo;
import me.chanjar.weixin.third.mini.bean.message.CpOpenXmlMessage;
import me.chanjar.weixin.third.mini.bean.result.MiniJscodeToSessionResult;
import me.chanjar.weixin.third.mini.util.json.CpOpenGsonBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.chanjar.weixin.third.mini.api.ThirdMiniConfigStorage;
import me.chanjar.weixin.third.mini.api.ThirdMiniervice;
import me.chanjar.weixin.third.mini.api.ThirdMiniSuiteService;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public class ThirdMiniSuiteServiceImpl implements ThirdMiniSuiteService {
  private static final Map<String, WxCpService> wxOpenCpServiceMap = new Hashtable<>();
  protected final Logger log = LoggerFactory.getLogger(this.getClass());
  private ThirdMiniervice ThirdMiniervice;
  private static Gson gson = new Gson();

  public ThirdMiniSuiteServiceImpl(ThirdMiniervice thirdMiniervice) {
    this.ThirdMiniervice = ThirdMiniervice;
  }

  @Override
  public WxCpService getWxCpServiceByCorpId(String corpId) {
    WxCpService wxCpService = wxOpenCpServiceMap.get(corpId);
    if (wxCpService == null) {
      synchronized (wxOpenCpServiceMap) {
        wxCpService = wxOpenCpServiceMap.get(corpId);
        if (wxCpService == null) {
          wxCpService = new WxOpenCpServiceImpl(this, corpId, getThirdMiniConfigStorage().getWxCpConfigStorage(corpId));

          wxOpenCpServiceMap.put(corpId, wxCpService);
        }
      }
    }
    return wxCpService;
  }

  @Override
  public ThirdMiniConfigStorage getThirdMiniConfigStorage() {
    return ThirdMiniervice.getThirdMiniConfigStorage();
  }

  public ThirdMiniervice getThirdMiniervice() {
    return ThirdMiniervice;
  }


  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getThirdMiniConfigStorage().getSuiteToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }



  @Override
  public String getSuiteAccessToken(boolean forceRefresh) throws WxErrorException {

    if (this.getThirdMiniConfigStorage().isSuiteAccessTokenExpired() || forceRefresh) {
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("suite_id", getThirdMiniConfigStorage().getSuiteId());
      jsonObject.addProperty("suite_secret", getThirdMiniConfigStorage().getSuiteSecret());
      jsonObject.addProperty("suite_ticket", getThirdMiniConfigStorage().getSuiteTicket());

      String responseContent = this.getThirdMiniervice().post(API_SUITE_TOKEN_URL, jsonObject.toString());
      WxSuieAccessToken wxSuieAccessToken = gson.fromJson(responseContent, WxSuieAccessToken.class);
      getThirdMiniConfigStorage().updateSuiteAccessTokent(wxSuieAccessToken);
      return wxSuieAccessToken.getSuiteAccessToken();
    }
    return this.getThirdMiniConfigStorage().getSuiteAccessToken();
  }

  @Override
  public MiniJscodeToSessionResult getJscodeTosession(String code) throws WxErrorException {
    String requestUrl = String.format(API_JSCODE_TO_SESSION, code);
    String response = this.get(requestUrl);
    Gson gson = CpOpenGsonBuilder.create();
    return gson.fromJson(response,MiniJscodeToSessionResult.class);
  }

  private String post(String uri, String postData) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + suiteAccessToken;
    return getThirdMiniervice().post(uriWithComponentAccessToken, postData);
  }
  private String postAccess(String uri, String postData) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + suiteAccessToken;
    return getThirdMiniervice().post(uriWithComponentAccessToken, postData);
  }
  private String get(String uri) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + suiteAccessToken;
    return getThirdMiniervice().get(uriWithComponentAccessToken, null);
  }
  private String getAccess(String uri) throws WxErrorException {
    String suiteAccessToken = getSuiteAccessToken(false);
    String uriWithComponentAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + suiteAccessToken;
    return getThirdMiniervice().get(uriWithComponentAccessToken, null);
  }
  @Override
  public String getPreAuthUrl(String redirectURI,String status) throws WxErrorException {
    String suiteId=getThirdMiniConfigStorage().getSuiteId();
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
    this.getThirdMiniConfigStorage().setCpPermanentCode(wxCpPermanentCode.getAuthCorpInfo().getCorpid(),wxCpPermanentCode.getPermanentCode());
    return wxCpPermanentCode;
  }

  @Override
  public String getAccessToken(String appid, boolean forceRefresh) throws WxErrorException {
    if(this.getThirdMiniConfigStorage().getWxCpConfigStorage(appid).isAccessTokenExpired() || forceRefresh) {
      String cpPermanentCode = getThirdMiniConfigStorage().getCpPermanentCode(appid);
      if(cpPermanentCode==null||cpPermanentCode.equals("")){
        log.error("第三方小程序永久授权码不存在，请及时初始化appid:[{}]",appid);
        throw new WxErrorException(null,null);

      }
      JsonObject jsonObject = new JsonObject();
      jsonObject.addProperty("auth_corpid",appid);
      jsonObject.addProperty("permanent_code", cpPermanentCode);
      String uri=API_ACCESS_TOKEN_URL;
      String url = uri + (uri.contains("?") ? "&" : "?") + "suite_access_token=" + this.getSuiteAccessToken(false);

      String responseContent = this.getThirdMiniervice().post(url, jsonObject.toString());
      WxCpAccessToken wxCpAccessToken = gson.fromJson(responseContent, WxCpAccessToken.class);
      getThirdMiniConfigStorage().updateAccessToken(appid,wxCpAccessToken.getAccessToken(),wxCpAccessToken.getExpiresIn());
    }
    return this.getThirdMiniConfigStorage().getAccessToken(appid);


  }




  @Override
  public WxCpPermanentCode route(final CpOpenXmlMessage wxMessage) throws WxErrorException {
    if (wxMessage == null) {
      throw new NullPointerException("message is empty");
    }
    if (StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "suite_ticket")) {
      getThirdMiniConfigStorage().setSuiteTicket(wxMessage.getSuiteTicket());
      return null;
    }
    if(StringUtils.equalsIgnoreCase(wxMessage.getInfoType(), "create_auth")){
      String authCode = wxMessage.getAuthCode();
      WxCpPermanentCode permanentCode = getPermanentCode(authCode);
      String appid=getThirdMiniConfigStorage().getSuiteId();
      Integer agentId=null;
      List<WxCpPermanentCode.AuthInfoBean.AgentBean> agents = permanentCode.getAuthInfo().getAgent();
      agentId=agents.get(0).getAgentid();
      String authCorpId = permanentCode.getAuthCorpInfo().getCorpid();
      getThirdMiniConfigStorage().setAgentId(authCorpId,agentId);
      getThirdMiniConfigStorage().setCpPermanentCode(authCorpId,permanentCode.getPermanentCode());
      return permanentCode;
    }

    return null;
  }

  @Override
  public boolean checkSignature(String appid, String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(getThirdMiniConfigStorage().getSuiteToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      this.log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String oauth2buildAuthorizationUrl(String redirectURI, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL,
      getThirdMiniConfigStorage().getSuiteId(), URIUtil.encodeURIComponent(redirectURI), scope, StringUtils.trimToEmpty(state) );
  }

}
