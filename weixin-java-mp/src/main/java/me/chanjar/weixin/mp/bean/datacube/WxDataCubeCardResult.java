package me.chanjar.weixin.mp.bean.datacube;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * Created by hasee on 2018/1/11.
 */
@Data
public class WxDataCubeCardResult extends WxDataCubeBaseResult {
  /**
   * ref_date : 2015-06-23
   * card_id : po8pktyDLmakNY2fn2VyhkiEPqGE
   * card_type : 3
   * view_cnt : 1
   * view_user : 1
   * receive_cnt : 1
   * receive_user : 1
   * verify_cnt : 0
   * verify_user : 0
   * given_cnt : 0
   * given_user : 0
   * expire_cnt : 0
   * expire_user : 0
   */

  @SerializedName("card_id")
  private String cardId;
  @SerializedName("card_type")
  private int cardType;
  @SerializedName("view_cnt")
  private int viewCnt;
  @SerializedName("view_user")
  private int viewUser;
  @SerializedName("receive_cnt")
  private int receiveCnt;
  @SerializedName("receive_user")
  private int receiveUser;
  @SerializedName("verify_cnt")
  private int verifyCnt;
  @SerializedName("verify_user")
  private int verifyUser;
  @SerializedName("given_cnt")
  private int givenCnt;
  @SerializedName("given_user")
  private int givenUser;
  @SerializedName("expire_cnt")
  private int expireCnt;
  @SerializedName("expire_user")
  private int expireUser;





  public static List<WxDataCubeCardResult> fromJson(String json) {
    return WxMpGsonBuilder.INSTANCE.create().fromJson(
      JSON_PARSER.parse(json).getAsJsonObject().get("list"),
      new TypeToken<List<WxDataCubeCardResult>>() {
      }.getType());
  }
}
