package me.chanjar.weixin.third.mini.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxSuieAccessToken implements Serializable {

  /**
   * errcode : 0
   * errmsg : ok
   * suite_access_token : 61W3mEpU66027wgNZ_MhGHNQDHnFATkDa9-2llqrMBjUwxRSNPbVsMmyD-yq8wZETSoE5NQgecigDrSHkPtIYA
   * expires_in : 7200
   */

  @SerializedName("errcode")
  private int errcode;
  @SerializedName("errmsg")
  private String errmsg;
  @SerializedName("suite_access_token")
  private String suiteAccessToken;
  @SerializedName("expires_in")
  private int expiresIn;

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

  public String getSuiteAccessToken() {
    return suiteAccessToken;
  }

  public void setSuiteAccessToken(String suiteAccessToken) {
    this.suiteAccessToken = suiteAccessToken;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }
}
