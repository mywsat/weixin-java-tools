package me.chanjar.weixin.third.mini.bean.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class CpOpenQueryAuthResult implements Serializable {
  private static final long serialVersionUID = 2394736235020206855L;


  /**
   * CorpId : CORPID
   * DeviceId : DEVICEID
   * UserId : USERID
   * errcode : 0
   * errmsg : ok
   * expires_in : 7200
   * user_ticket : USER_TICKET
   */

  private String CorpId;
  private String DeviceId;
  private String UserId;
  private int errcode;
  private String errmsg;
  private int expiresIn;
  private String userTicket;


}
