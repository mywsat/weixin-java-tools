package me.chanjar.weixin.third.mini.api.impl;

import me.chanjar.weixin.third.mini.bean.WxSuieAccessToken;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class ThirdMiniInRedisConfigStorage extends ThirdMiniSuieInMemoryConfigStorage {

  private final static String SUITE_TICKET_KEY = "mini_suite_ticket:";
  private final static String SUITE_ACCESS_TOKEN_KEY = "mini_suite_access_token:";


  private final static String ACCESS_TOKEN_KEY = "cp_mini_access_token:";
  private final static String JSAPI_TICKET_KEY = "cp_mini_jsapi_ticket:";
  private final static String PERMANENT_CODE_KEY = "cp_mini_permanent_code:";
  private final static String AGENTID_KEY = "cp_mini_agent:";

  protected final JedisPool jedisPool;
  private String suiteTicketKey;
  private String suiteAccessTokenKey;

  private String accessTokenKey;
  private String jsapiTicketKey;
  private String permanentCodeKey;
  private String agentIdKey;
  public ThirdMiniInRedisConfigStorage(JedisPool jedisPool) {
    this.jedisPool = jedisPool;
  }

  @Override
  public void setSuiteId(String suiteId) {
    super.setSuiteId(suiteId);
    suiteTicketKey = SUITE_TICKET_KEY.concat(suiteId);
    suiteAccessTokenKey = SUITE_ACCESS_TOKEN_KEY.concat(suiteId);
    accessTokenKey = ACCESS_TOKEN_KEY.concat(suiteId);
    permanentCodeKey = PERMANENT_CODE_KEY.concat(suiteId);
    jsapiTicketKey = JSAPI_TICKET_KEY.concat(suiteId);
    agentIdKey=AGENTID_KEY.concat(suiteId);
  }

  @Override
  public String getSuiteTicket() {
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.get(suiteTicketKey);
    }
  }

  @Override
  public void setSuiteTicket(String suiteTicket) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.set(suiteTicketKey, suiteTicket);
    }
  }
  @Override
  public  void setAgentId(String corpId,Integer agentId){
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.set(getKey(AGENTID_KEY, corpId), agentId+"");
    }
  }
  @Override
  public  Integer getAgentId(String corpId){
    try (Jedis jedis = jedisPool.getResource()) {
      String s = jedis.get(getKey(AGENTID_KEY, corpId));
      if(s!=null&&!"".equals(s)){
        int i = Integer.parseInt(s);
        return i;
      }
    }
    return null;
  }
  @Override
  public String getSuiteAccessToken() {
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.get(suiteAccessTokenKey);
    }
  }

  @Override
  public boolean isSuiteAccessTokenExpired() {
    try (Jedis jedis = jedisPool.getResource()) {
      return jedis.ttl(suiteAccessTokenKey) < 2;
    }
  }

  @Override
  public void updateSuiteAccessTokent(String suiteAccessToken, int expiresInSeconds) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.setex(suiteAccessTokenKey, expiresInSeconds - 200, suiteAccessToken);
    }
  }
  @Override
  public void updateSuiteAccessTokent(WxSuieAccessToken suieAccessToken){
    updateSuiteAccessTokent(suieAccessToken.getSuiteAccessToken(),suieAccessToken.getExpiresIn());
  }

  private String getKey(String prefix, String corpId) {
    return prefix.endsWith(":") ? prefix.concat(corpId) : prefix.concat(":").concat(corpId);
  }

  @Override
  public String getCpPermanentCode(String corpId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(getKey(permanentCodeKey, corpId));
    }
  }

  @Override
  public void setCpPermanentCode(String corpId, String cpPermanentCode) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.set(getKey(permanentCodeKey, corpId), cpPermanentCode);
    }
  }

  @Override
  public String getAccessToken(String corpId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(getKey(accessTokenKey, corpId));
    }
  }


  @Override
  public boolean isAccessTokenExpired(String corpId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(getKey(accessTokenKey, corpId)) < 2;
    }
  }

  @Override
  public void expireAccessToken(String corpId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(getKey(accessTokenKey, corpId), 0);
    }
  }

  @Override
  public void updateAccessToken(String corpId, String authorizerAccessToken, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(getKey(accessTokenKey, corpId), expiresInSeconds - 200, authorizerAccessToken);
    }
  }

  @Override
  public String getJsapiTicket(String appId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.get(getKey(this.jsapiTicketKey, appId));
    }
  }

  @Override
  public boolean isJsapiTicketExpired(String corpId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      return jedis.ttl(getKey(this.jsapiTicketKey, corpId)) < 2;
    }
  }

  @Override
  public void expireJsapiTicket(String corpId) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.expire(getKey(this.jsapiTicketKey, corpId), 0);
    }
  }

  @Override
  public void updateJsapiTicket(String corpId, String jsapiTicket, int expiresInSeconds) {
    try (Jedis jedis = this.jedisPool.getResource()) {
      jedis.setex(getKey(this.jsapiTicketKey, corpId), expiresInSeconds - 200, jsapiTicket);
    }
  }


}
