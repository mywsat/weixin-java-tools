package me.chanjar.weixin.third.mini.api.impl;


import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.third.mini.api.WxOpenSuiteService;

/**
 * @author <a href="https://github.com/mywsat">mywsat</a>
 */
/* package */
class WxOpenCpServiceImpl extends WxCpServiceImpl {
  private WxOpenSuiteService wxOpenSuiteService;
  private WxCpConfigStorage wxCpConfigStorage;
  private String corpId;

  public WxOpenCpServiceImpl(WxOpenSuiteService wxOpenSuiteService, String corpId, WxCpConfigStorage wxCpConfigStorage) {
    this.wxOpenSuiteService = wxOpenSuiteService;
    this.corpId = corpId;
    this.wxCpConfigStorage = wxCpConfigStorage;
    initHttp();
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return wxCpConfigStorage;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    return wxOpenSuiteService.getAccessToken(corpId, forceRefresh);
  }





}
