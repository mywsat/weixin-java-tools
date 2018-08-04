package me.chanjar.weixin.cpopen.bean.auth;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */

public class CpOpenUserInfo implements Serializable {

  /**
   * errcode : 0
   * errmsg : ok
   * CorpId : CORPID
   * UserId : USERID
   * DeviceId : DEVICEID
   * user_ticket : USER_TICKET
   * expires_in : 7200
   */

  @SerializedName("errcode")
  private int errcode;
  @SerializedName("errmsg")
  private String errmsg;
  @SerializedName("CorpId")
  private String CorpId;
  @SerializedName("UserId")
  private String UserId;
  @SerializedName("DeviceId")
  private String DeviceId;
  @SerializedName("user_ticket")
  private String userTicket;
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

  public String getCorpId() {
    return CorpId;
  }

  public void setCorpId(String CorpId) {
    this.CorpId = CorpId;
  }

  public String getUserId() {
    return UserId;
  }

  public void setUserId(String UserId) {
    this.UserId = UserId;
  }

  public String getDeviceId() {
    return DeviceId;
  }

  public void setDeviceId(String DeviceId) {
    this.DeviceId = DeviceId;
  }

  public String getUserTicket() {
    return userTicket;
  }

  public void setUserTicket(String userTicket) {
    this.userTicket = userTicket;
  }

  public int getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(int expiresIn) {
    this.expiresIn = expiresIn;
  }
}
