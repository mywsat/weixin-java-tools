package me.chanjar.weixin.mp.bean.membercard;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hasee on 2017/12/12.
 */
@Data
public class WxMpMemberCardAddResult implements Serializable {

  /**
   * errcode : 0
   * errmsg : ok
   * card_id : p1Pj9jr90_SQRaVqYI239Ka1erkI
   */

  private int errcode;
  private String errmsg;
  private String card_id;


}
