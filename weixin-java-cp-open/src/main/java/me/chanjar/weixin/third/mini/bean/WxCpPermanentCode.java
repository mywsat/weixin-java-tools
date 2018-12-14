package me.chanjar.weixin.third.mini.bean;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
public class WxCpPermanentCode implements Serializable {

  /**
   * access_token : xxxxxx
   * auth_corp_info : {"corp_agent_max":30,"corp_full_name":"full_name","corp_industry":"IT服务","corp_name":"name","corp_scale":"1-50人","corp_square_logo_url":"yyyyy","corp_sub_industry":"计算机软件/硬件/信息服务","corp_type":"verified","corp_user_max":50,"corp_wxqrcode":"zzzzz","corpid":"xxxx","subject_type":1,"verified_end_time":1431775834}
   * auth_info : {"agent":[{"agentid":1,"appid":1,"name":"NAME","privilege":{"allow_party":[1,2,3],"allow_tag":[1,2,3],"allow_user":["zhansan","lisi"],"extra_party":[4,5,6],"extra_tag":[4,5,6],"extra_user":["wangwu"],"level":1},"round_logo_url":"xxxxxx","square_logo_url":"yyyyyy"},{"agentid":2,"appid":5,"name":"NAME2","round_logo_url":"xxxxxx","square_logo_url":"yyyyyy"}]}
   * auth_user_info : {"avatar":"http://xxx","email":"xxxx@aaa.com","mobile":"1234567890","name":"xxx","userid":"aa"}
   * errcode : 0
   * errmsg : ok
   * expires_in : 7200
   * permanent_code : xxxx
   */

  @SerializedName("access_token")
  private String accessToken;
  @SerializedName("auth_corp_info")
  private AuthCorpInfoBean authCorpInfo;
  @SerializedName("auth_info")
  private AuthInfoBean authInfo;
  @SerializedName("auth_user_info")
  private AuthUserInfoBean authUserInfo;
  @SerializedName("errcode")
  private int errcode;
  @SerializedName("errmsg")
  private String errmsg;
  @SerializedName("expires_in")
  private int expiresIn;
  @SerializedName("permanent_code")
  private String permanentCode;

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public AuthCorpInfoBean getAuthCorpInfo() {
    return authCorpInfo;
  }

  public void setAuthCorpInfo(AuthCorpInfoBean authCorpInfo) {
    this.authCorpInfo = authCorpInfo;
  }

  public AuthInfoBean getAuthInfo() {
    return authInfo;
  }

  public void setAuthInfo(AuthInfoBean authInfo) {
    this.authInfo = authInfo;
  }

  public AuthUserInfoBean getAuthUserInfo() {
    return authUserInfo;
  }

  public void setAuthUserInfo(AuthUserInfoBean authUserInfo) {
    this.authUserInfo = authUserInfo;
  }

  public int getErrcode() {
    return errcode;
  }

  public void setErrcode(int errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }

  public String getPermanentCode() {
    return permanentCode;
  }

  public void setPermanentCode(String permanentCode) {
    this.permanentCode = permanentCode;
  }

  public static class AuthCorpInfoBean {
    /**
     * corp_agent_max : 30
     * corp_full_name : full_name
     * corp_industry : IT服务
     * corp_name : name
     * corp_scale : 1-50人
     * corp_square_logo_url : yyyyy
     * corp_sub_industry : 计算机软件/硬件/信息服务
     * corp_type : verified
     * corp_user_max : 50
     * corp_wxqrcode : zzzzz
     * corpid : xxxx
     * subject_type : 1
     * verified_end_time : 1431775834
     */

    @SerializedName("corp_agent_max")
    private int corpAgentMax;
    @SerializedName("corp_full_name")
    private String corpFullName;
    @SerializedName("corp_industry")
    private String corpIndustry;
    @SerializedName("corp_name")
    private String corpName;
    @SerializedName("corp_scale")
    private String corpScale;
    @SerializedName("corp_square_logo_url")
    private String corpSquareLogoUrl;
    @SerializedName("corp_sub_industry")
    private String corpSubIndustry;
    @SerializedName("corp_type")
    private String corpType;
    @SerializedName("corp_user_max")
    private int corpUserMax;
    @SerializedName("corp_wxqrcode")
    private String corpWxqrcode;
    @SerializedName("corpid")
    private String corpid;
    @SerializedName("subject_type")
    private int subjectType;
    @SerializedName("verified_end_time")
    private int verifiedEndTime;

    public int getCorpAgentMax() {
      return corpAgentMax;
    }

    public void setCorpAgentMax(int corpAgentMax) {
      this.corpAgentMax = corpAgentMax;
    }

    public String getCorpFullName() {
      return corpFullName;
    }

    public void setCorpFullName(String corpFullName) {
      this.corpFullName = corpFullName;
    }

    public String getCorpIndustry() {
      return corpIndustry;
    }

    public void setCorpIndustry(String corpIndustry) {
      this.corpIndustry = corpIndustry;
    }

    public String getCorpName() {
      return corpName;
    }

    public void setCorpName(String corpName) {
      this.corpName = corpName;
    }

    public String getCorpScale() {
      return corpScale;
    }

    public void setCorpScale(String corpScale) {
      this.corpScale = corpScale;
    }

    public String getCorpSquareLogoUrl() {
      return corpSquareLogoUrl;
    }

    public void setCorpSquareLogoUrl(String corpSquareLogoUrl) {
      this.corpSquareLogoUrl = corpSquareLogoUrl;
    }

    public String getCorpSubIndustry() {
      return corpSubIndustry;
    }

    public void setCorpSubIndustry(String corpSubIndustry) {
      this.corpSubIndustry = corpSubIndustry;
    }

    public String getCorpType() {
      return corpType;
    }

    public void setCorpType(String corpType) {
      this.corpType = corpType;
    }

    public int getCorpUserMax() {
      return corpUserMax;
    }

    public void setCorpUserMax(int corpUserMax) {
      this.corpUserMax = corpUserMax;
    }

    public String getCorpWxqrcode() {
      return corpWxqrcode;
    }

    public void setCorpWxqrcode(String corpWxqrcode) {
      this.corpWxqrcode = corpWxqrcode;
    }

    public String getCorpid() {
      return corpid;
    }

    public void setCorpid(String corpid) {
      this.corpid = corpid;
    }

    public int getSubjectType() {
      return subjectType;
    }

    public void setSubjectType(int subjectType) {
      this.subjectType = subjectType;
    }

    public int getVerifiedEndTime() {
      return verifiedEndTime;
    }

    public void setVerifiedEndTime(int verifiedEndTime) {
      this.verifiedEndTime = verifiedEndTime;
    }
  }

  public static class AuthInfoBean {
    @SerializedName("agent")
    private List<AgentBean> agent;

    public List<AgentBean> getAgent() {
      return agent;
    }

    public void setAgent(List<AgentBean> agent) {
      this.agent = agent;
    }

    public static class AgentBean {
      /**
       * agentid : 1
       * appid : 1
       * name : NAME
       * privilege : {"allow_party":[1,2,3],"allow_tag":[1,2,3],"allow_user":["zhansan","lisi"],"extra_party":[4,5,6],"extra_tag":[4,5,6],"extra_user":["wangwu"],"level":1}
       * round_logo_url : xxxxxx
       * square_logo_url : yyyyyy
       */

      @SerializedName("agentid")
      private int agentid;
      @SerializedName("appid")
      private String appid;
      @SerializedName("name")
      private String name;
      @SerializedName("privilege")
      private PrivilegeBean privilege;
      @SerializedName("round_logo_url")
      private String roundLogoUrl;
      @SerializedName("square_logo_url")
      private String squareLogoUrl;

      public int getAgentid() {
        return agentid;
      }

      public void setAgentid(int agentid) {
        this.agentid = agentid;
      }

      public String getAppid() {
        return appid;
      }

      public void setAppid(String appid) {
        this.appid = appid;
      }

      public String getName() {
        return name;
      }

      public void setName(String name) {
        this.name = name;
      }

      public PrivilegeBean getPrivilege() {
        return privilege;
      }

      public void setPrivilege(PrivilegeBean privilege) {
        this.privilege = privilege;
      }

      public String getRoundLogoUrl() {
        return roundLogoUrl;
      }

      public void setRoundLogoUrl(String roundLogoUrl) {
        this.roundLogoUrl = roundLogoUrl;
      }

      public String getSquareLogoUrl() {
        return squareLogoUrl;
      }

      public void setSquareLogoUrl(String squareLogoUrl) {
        this.squareLogoUrl = squareLogoUrl;
      }

      public static class PrivilegeBean {
        /**
         * allow_party : [1,2,3]
         * allow_tag : [1,2,3]
         * allow_user : ["zhansan","lisi"]
         * extra_party : [4,5,6]
         * extra_tag : [4,5,6]
         * extra_user : ["wangwu"]
         * level : 1
         */

        @SerializedName("level")
        private int level;
        @SerializedName("allow_party")
        private List<Integer> allowParty;
        @SerializedName("allow_tag")
        private List<Integer> allowTag;
        @SerializedName("allow_user")
        private List<String> allowUser;
        @SerializedName("extra_party")
        private List<Integer> extraParty;
        @SerializedName("extra_tag")
        private List<Integer> extraTag;
        @SerializedName("extra_user")
        private List<String> extraUser;

        public int getLevel() {
          return level;
        }

        public void setLevel(int level) {
          this.level = level;
        }

        public List<Integer> getAllowParty() {
          return allowParty;
        }

        public void setAllowParty(List<Integer> allowParty) {
          this.allowParty = allowParty;
        }

        public List<Integer> getAllowTag() {
          return allowTag;
        }

        public void setAllowTag(List<Integer> allowTag) {
          this.allowTag = allowTag;
        }

        public List<String> getAllowUser() {
          return allowUser;
        }

        public void setAllowUser(List<String> allowUser) {
          this.allowUser = allowUser;
        }

        public List<Integer> getExtraParty() {
          return extraParty;
        }

        public void setExtraParty(List<Integer> extraParty) {
          this.extraParty = extraParty;
        }

        public List<Integer> getExtraTag() {
          return extraTag;
        }

        public void setExtraTag(List<Integer> extraTag) {
          this.extraTag = extraTag;
        }

        public List<String> getExtraUser() {
          return extraUser;
        }

        public void setExtraUser(List<String> extraUser) {
          this.extraUser = extraUser;
        }
      }
    }
  }

  public static class AuthUserInfoBean {
    /**
     * avatar : http://xxx
     * email : xxxx@aaa.com
     * mobile : 1234567890
     * name : xxx
     * userid : aa
     */

    @SerializedName("avatar")
    private String avatar;
    @SerializedName("email")
    private String email;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("name")
    private String name;
    @SerializedName("userid")
    private String userid;

    public String getAvatar() {
      return avatar;
    }

    public void setAvatar(String avatar) {
      this.avatar = avatar;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getMobile() {
      return mobile;
    }

    public void setMobile(String mobile) {
      this.mobile = mobile;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public String getUserid() {
      return userid;
    }

    public void setUserid(String userid) {
      this.userid = userid;
    }
  }
}
