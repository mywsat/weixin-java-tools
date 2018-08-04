package me.chanjar.weixin.mp.bean.membercard;

import java.io.Serializable;

/**
 * Created by hasee on 2018/3/12.
 */
public class WxMpMemberCardGetUrlResult implements Serializable {
  /**
   * errcode : 0
   * errmsg : ok
   * url : https://mp.weixin.qq.com/bizmall/activatemembercard?action=preshow&&encrypt_card_id=3%2Bm9vVGt9YzgCphZE8Z28dUux7i0jgCUWu32QayFZsscPd7oyXGsHp9HoOjH9ML%2B&outer_str=guide&biz=MzU2ODQzMDM1MQ%3D%3D#wechat_redirect
   */

  private int errcode;
  private String errmsg;
  private String url;

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

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}
