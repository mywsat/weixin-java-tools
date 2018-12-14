package me.chanjar.weixin.third.mini.api.impl;


import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.util.http.apache.ApacheHttpClientBuilder;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.third.mini.api.ThirdMiniConfigStorage;
import me.chanjar.weixin.third.mini.bean.WxSuieAccessToken;


import java.io.File;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于内存的微信配置provider，在实际生产环境中应该将这些配置持久化
 *建议使用redis 此类中大部分方法不可用
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public class ThirdMiniSuieInMemoryConfigStorage implements ThirdMiniConfigStorage {
  private String suiteId;
  private String suiteSecret;
  private String suiteToken;
  private String suiteAesKey;
  private String suiteTicket;
  private String suiteAccessToken;
  private long suiteExpiresTime;


  private Map<String, Token> cpPermanentCodes = new Hashtable<>();
  private Map<String, Token> accessTokens = new Hashtable<>();
  private Map<String, Token> jsapiTickets = new Hashtable<>();


  @Override
  public boolean autoRefreshToken() {
    return true;
  }

  @Override
  public Integer getAgentId(String corpId) {
    return null;
  }

  @Override
  public void setAgentId(String corpId, Integer agentId) {

  }


  @Override
  public String getCpPermanentCode(String corpId) {
    return getTokenString(cpPermanentCodes,corpId);
  }

  @Override
  public void setCpPermanentCode(String corpId,String cpPermanentCode) {
    updateToken(cpPermanentCodes, corpId, cpPermanentCode, null);
  }

  private String getTokenString(Map<String, Token> map, String key) {
    Token token = map.get(key);
    if (token == null || (token.expiresTime != null && System.currentTimeMillis() > token.expiresTime)) {
      return null;
    }
    return token.token;
  }

  private void expireToken(Map<String, Token> map, String key) {
    Token token = map.get(key);
    if (token != null) {
      token.expiresTime = 0L;
    }
  }

  private void updateToken(Map<String, Token> map, String key, String tokenString, Integer expiresInSeconds) {
    Token token = map.get(key);
    if (token == null) {
      token = new Token();
      map.put(key, token);
    }
    token.token = tokenString;
    if (expiresInSeconds != null) {
      token.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
    }
  }








  @Override
  public void updateSuiteAccessTokent(WxSuieAccessToken suieAccessToken) {

  }

  @Override
  public String getJsapiTicket(String corpId) {
    return null;
  }

  @Override
  public boolean isJsapiTicketExpired(String corpId) {
    return false;
  }

  @Override
  public void expireJsapiTicket(String corpId) {

  }

  @Override
  public void updateJsapiTicket(String corpId, String jsapiTicket, int expiresInSeconds) {

  }


  @Override
  public boolean isAccessTokenExpired(String corpId) {
    return getTokenString(accessTokens, corpId) == null;
  }

  @Override
  public void expireAccessToken(String corpId) {

  }

  @Override
  public void updateAccessToken(String corpId, String accessToken, int expiresInSeconds) {

  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage(String corpId) {
    return new WxOpenCpConfigStorage(this, corpId);
  }

  @Override
  public String getAccessToken(String corpId) {
    return null;
  }

  @Override
  public void updateSuiteAccessTokent(String suiteAccessToken, int expiresInSeconds) {

  }


  public void setSuiteAccessToken(String suiteAccessToken) {
    this.suiteAccessToken = suiteAccessToken;
  }

  public long getSuiteExpiresTime() {
    return suiteExpiresTime;
  }

  public void setSuiteExpiresTime(long suiteExpiresTime) {
    this.suiteExpiresTime = suiteExpiresTime;
  }

  private static class Token {
    private String token;
    private Long expiresTime;
  }

  private static class WxOpenCpConfigStorage implements WxCpConfigStorage {
    private ThirdMiniConfigStorage cpOpenConfigStorage;
    private String corpId;
    private Lock accessTokenLock = new ReentrantLock();

    private WxOpenCpConfigStorage(ThirdMiniConfigStorage cpOpenConfigStorage, String corpId) {
      this.cpOpenConfigStorage = cpOpenConfigStorage;
      this.corpId = corpId;
    }

    @Override
    public String getAccessToken() {
      return cpOpenConfigStorage.getAccessToken(corpId);
    }

    @Override
    public boolean isAccessTokenExpired() {
      return cpOpenConfigStorage.isAccessTokenExpired(corpId);
    }

    @Override
    public void expireAccessToken() {
      cpOpenConfigStorage.expireAccessToken(corpId);
    }

    @Override
    public void updateAccessToken(WxAccessToken accessToken) {
      cpOpenConfigStorage.updateAccessToken(corpId,accessToken.getAccessToken(),accessToken.getExpiresIn());
    }

    @Override
    public void updateAccessToken(String accessToken, int expiresIn) {
      cpOpenConfigStorage.updateAccessToken(corpId,accessToken,expiresIn);
    }

    @Override
    public String getJsapiTicket() {
      return cpOpenConfigStorage.getJsapiTicket(corpId);
    }

    @Override
    public boolean isJsapiTicketExpired() {
      return cpOpenConfigStorage.isJsapiTicketExpired(corpId);
    }

    @Override
    public void expireJsapiTicket() {
       cpOpenConfigStorage.expireJsapiTicket(corpId);
    }

    @Override
    public void updateJsapiTicket(String jsapiTicket, int expiresInSeconds) {
      cpOpenConfigStorage.updateJsapiTicket(corpId,jsapiTicket,expiresInSeconds);
    }

    @Override
    public String getCorpId() {
      return corpId;
    }

    @Override
    public String getCorpSecret() {
      return null;
    }

    @Override
    public Integer getAgentId() {
      Integer agentId = cpOpenConfigStorage.getAgentId(corpId);
      return  agentId;
    }

    @Override
    public String getToken() {
      return cpOpenConfigStorage.getSuiteToken();
    }

    @Override
    public String getAesKey() {
      return cpOpenConfigStorage.getSuiteAesKey();
    }

    @Override
    public long getExpiresTime() {
      return 0;
    }

    @Override
    public String getOauth2redirectUri() {
      return null;
    }

    @Override
    public String getHttpProxyHost() {
      return null;
    }

    @Override
    public int getHttpProxyPort() {
      return 0;
    }

    @Override
    public String getHttpProxyUsername() {
      return null;
    }

    @Override
    public String getHttpProxyPassword() {
      return null;
    }

    @Override
    public File getTmpDirFile() {
      return null;
    }

    @Override
    public ApacheHttpClientBuilder getApacheHttpClientBuilder() {
      return null;
    }
  }
  @Override
  public String getSuiteId() {
    return suiteId;
  }

  @Override
  public void setSuiteId(String suiteId) {
    this.suiteId = suiteId;
  }

  @Override
  public String getSuiteSecret() {
    return suiteSecret;
  }

  @Override
  public void setSuiteSecret(String suiteSecret) {
    this.suiteSecret = suiteSecret;
  }

  @Override
  public String getSuiteToken() {
    return suiteToken;
  }

  @Override
  public void setSuiteToken(String suiteToken) {
    this.suiteToken = suiteToken;
  }

  @Override
  public String getSuiteAesKey() {
    return suiteAesKey;
  }

  @Override
  public void setSuiteAesKey(String suiteAesKey) {
    this.suiteAesKey = suiteAesKey;
  }
  @Override
  public String getSuiteTicket() {
    return suiteTicket;
  }
  @Override
  public void setSuiteTicket(String suiteTicket) {
    this.suiteTicket = suiteTicket;
  }

  @Override
  public String getSuiteAccessToken() {
    return suiteAccessToken;
  }

  @Override
  public boolean isSuiteAccessTokenExpired() {
    return System.currentTimeMillis() >suiteExpiresTime;
  }

}
