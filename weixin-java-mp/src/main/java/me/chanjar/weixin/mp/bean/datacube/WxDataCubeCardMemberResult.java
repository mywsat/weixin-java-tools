package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hasee on 2018/1/11.
 */
@Data
public class WxDataCubeCardMemberResult extends WxDataCubeBaseResult implements Serializable{
  /**
   * ref_date : 2015-06-23
   * view_cnt : 0
   * view_user : 0
   * receive_cnt : 0
   * receive_user : 0
   * active_user : 0
   * verify_cnt : 0
   * verify_user : 0
   * total_user : 86
   * total_receive_user : 95
   */

  @SerializedName("view_cnt")
  private int viewCnt;
  @SerializedName("view_user")
  private int viewUser;
  @SerializedName("receive_cnt")
  private int receiveCnt;
  @SerializedName("receive_user")
  private int receiveUser;
  @SerializedName("active_user")
  private int activeUser;
  @SerializedName("verify_cnt")
  private int verifyCnt;
  @SerializedName("verify_user")
  private int verifyUser;
  @SerializedName("total_user")
  private int totalUser;
  @SerializedName("total_receive_user")
  private int totalReceive_user;


  public static List<WxDataCubeCardMemberResult> fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("list"),
      new TypeToken<List<WxDataCubeCardMemberResult>>() {
      }.getType());
  }
}
