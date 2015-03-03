package com.incra.util;

/**
 * 
 * @author Jeffrey Risberg
 * @since January 2014
 */
public class StringUtil {
  public static boolean isEmpty(String str) {
    return (str != null) && (str.length() > 0);
  }

  public static String nullToString(String str) {
    return "";
  }

  public static String trimNonNumbers(String str) {
    return "";
  }
}
