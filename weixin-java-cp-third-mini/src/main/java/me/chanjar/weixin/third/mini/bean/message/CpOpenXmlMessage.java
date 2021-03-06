package me.chanjar.weixin.third.mini.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import me.chanjar.weixin.common.util.xml.XStreamCDataConverter;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;


import me.chanjar.weixin.third.mini.api.ThirdMiniConfigStorage;
import me.chanjar.weixin.third.mini.util.xml.XStreamTransformer;
import me.chanjar.weixin.third.mini.util.CpOpenCryptUtil;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@XStreamAlias("xml")
@Data
public class CpOpenXmlMessage implements Serializable {
  private static final long serialVersionUID = -5641769554709507771L;

  @XStreamAlias("SuiteId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String suiteId;



  @XStreamAlias("InfoType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String infoType;
  @XStreamAlias("timeStamp")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String TimeStamp;
  @XStreamAlias("ChangeType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String changeType;


  @XStreamAlias("Name")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String name;

  @XStreamAlias("Department")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String department;

  @XStreamAlias("PreAuthCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String preAuthCode;

  @XStreamAlias("SuiteTicket")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String suiteTicket;
  @XStreamAlias("AuthCode")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authCode;
  @XStreamAlias("AuthCorpId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String authCorpId;

  @XStreamAlias("UserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String userID;

  @XStreamAlias("newUserID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String newUserID;
  @XStreamAlias("Mobile")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String mobile;
  public static String wxMpOutXmlMessageToEncryptedXml(WxCpXmlOutMessage message, ThirdMiniConfigStorage wxOpenConfigStorage){
    String plainXml = message.toXml();
    CpOpenCryptUtil pc = new CpOpenCryptUtil(wxOpenConfigStorage);
    return pc.encrypt(plainXml);
  }

  public static CpOpenXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    xml = xml.replace("</PicList><PicList>", "");
    return XStreamTransformer.fromXml(CpOpenXmlMessage.class, xml);
  }

  public static CpOpenXmlMessage fromXml(InputStream is) {
    return XStreamTransformer.fromXml(CpOpenXmlMessage.class, is);
  }

  /**
   * 从加密字符串转换
   *
   * @param encryptedXml        密文
   * @param cpOpenConfigStorage 配置存储器对象
   * @param timestamp           时间戳
   * @param nonce               随机串
   * @param msgSignature        签名串
   */
  public static CpOpenXmlMessage fromEncryptedXml(String encryptedXml,
                                                  ThirdMiniConfigStorage cpOpenConfigStorage, String timestamp, String nonce,
                                                  String msgSignature) {
    CpOpenCryptUtil cryptUtil = new CpOpenCryptUtil(cpOpenConfigStorage);
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce,
      encryptedXml);
    return fromXml(plainText);
  }

  public static WxCpXmlMessage fromEncryptedMpXml(String encryptedXml,
                                                  ThirdMiniConfigStorage wxOpenConfigStorage, String timestamp, String nonce,
                                                  String msgSignature) {
    CpOpenCryptUtil cryptUtil = new CpOpenCryptUtil(wxOpenConfigStorage);
    String plainText = cryptUtil.decrypt(msgSignature, timestamp, nonce,
      encryptedXml);
    return WxCpXmlMessage.fromXml(plainText);
  }

  public static CpOpenXmlMessage fromEncryptedXml(InputStream is,
                                                  ThirdMiniConfigStorage cpOpenConfigStorage, String timestamp, String nonce,
                                                  String msgSignature) {
    try {
      return fromEncryptedXml(IOUtils.toString(is, "UTF-8"), cpOpenConfigStorage,
        timestamp, nonce, msgSignature);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
