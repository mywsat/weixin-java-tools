package me.chanjar.weixin.third.mini.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @Author: hasee
 * @Description: ${description}
 * @Date: 2018/12/14 10:01
 */
@Data
public class MiniJscodeToSessionResult {
  @SerializedName("corpid")
  private String corpid;
  @SerializedName("userid")
  private String userid;
  @SerializedName("session_key")
  private String sessionKey;
  @SerializedName("errcode")
  private int errcode;
  @SerializedName("errmsg")
  private String errmsg;
}
