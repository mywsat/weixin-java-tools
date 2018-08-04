package me.chanjar.weixin.cpopen.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 *
 */
public class CpOpenGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();

  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
