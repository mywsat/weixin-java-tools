package me.chanjar.weixin.mp.bean.membercard;

import lombok.Data;
import me.chanjar.weixin.common.util.ToStringUtils;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * <pre>
 * 拉取会员信息返回的结果
 *
 * 字段格式参考https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1451025283  6.2.1小节的步骤5
 * </pre>
 *
 * @author YuJian
 * @version 2017/7/9
 */
@Data
public class WxMpMemberCardUserInfoTicketResult implements Serializable {

  private static final long serialVersionUID = 9083772944412098311L;

  private String errorCode;

  private String errorMsg;



  private MemberCardUserInfo info;



  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  public static WxMpMemberCardUserInfoTicketResult fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(json, WxMpMemberCardUserInfoTicketResult.class);
  }
}

