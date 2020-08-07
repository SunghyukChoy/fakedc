package my.likeaglow.fakedc.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JspViewHelper {

  private JspViewHelper() {
  }

  public static final String DEFAULT_DATETIME_FORMAT = "yyyy.MM.dd HH:mm:ss";
  public static final DateTimeFormatter DEFAULT_DATETIME_PATTERN = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
  // DateTimeFormatter는 불변객체로 아래의 모든 메소드마다 객체 생성 시 메모리 소비가 심해짐

  public static String parseString(LocalDateTime dt, String pattern) {
    return dt.format(DateTimeFormatter.ofPattern(pattern));
  }

  public static String parseString(LocalDateTime dt) {
    return dt.format(DEFAULT_DATETIME_PATTERN);
  }

}
