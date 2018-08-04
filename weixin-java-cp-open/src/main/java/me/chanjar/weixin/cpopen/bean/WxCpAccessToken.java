package me.chanjar.weixin.cpopen.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hasee on 2018/3/24.
 */
public class WxCpAccessToken {
  /**
   * errcode : 0
   * errmsg : ok
   * access_token : xxxxxx
   * expires_in : 7200
   */

  @SerializedName("errcode")
  private int errcode;
  @SerializedName("errmsg")
  private String errmsg;
  @SerializedName("access_token")
  private String accessToken;
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

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }
}
