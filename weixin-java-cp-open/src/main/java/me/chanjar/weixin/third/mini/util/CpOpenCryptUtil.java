package me.chanjar.weixin.third.mini.util;


import me.chanjar.weixin.third.mini.api.CpOpenConfigStorage;
import org.apache.commons.codec.binary.Base64;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class CpOpenCryptUtil extends me.chanjar.weixin.common.util.crypto.WxCryptUtil {
  /**
   * 构造函数
   *
   * @param cpOpenConfigStorage
   */
  public CpOpenCryptUtil(CpOpenConfigStorage cpOpenConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    String encodingAesKey = cpOpenConfigStorage.getSuiteAesKey();
    String token = cpOpenConfigStorage.getSuiteToken();
    String appId = cpOpenConfigStorage.getSuiteId();

    this.token = token;
    this.appidOrCorpid = appId;
    this.aesKey = Base64.decodeBase64(encodingAesKey + "=");
  }
}
