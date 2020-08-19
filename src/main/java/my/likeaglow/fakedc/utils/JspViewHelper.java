package my.likeaglow.fakedc.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class JspViewHelper {

  private JspViewHelper() {
  }

  public static final String DEFAULT_DATETIME_FORMAT = "yyyy.MM.dd HH:mm:ss";
  public static final DateTimeFormatter DEFAULT_DATETIME_PATTERN = DateTimeFormatter.ofPattern(DEFAULT_DATETIME_FORMAT);
  // DateTimeFormatter는 불변객체로 아래의 모든 메소드마다 객체 생성 시 메모리 소비가 심해짐
  public static final String DAY_DATETIME_FORMAT = "yyyy.MM.dd";
  public static final DateTimeFormatter DAY_DATETIME_PATTERN = DateTimeFormatter.ofPattern(DAY_DATETIME_FORMAT);

  public static String parseString(LocalDateTime dt, String pattern) {
    return dt.format(DateTimeFormatter.ofPattern(pattern));
  }

  /**
   * 시간은 "yyyy.MM.dd HH:mm:ss" 형식 String으로 출력
   * 
   * @param dt
   * @return
   */
  public static String parseString(LocalDateTime dt) {
    return dt.format(DEFAULT_DATETIME_PATTERN);
  }

  /**
   * 매개변수 시간으로부터 현재까지 얼마의 시간이 지났는지 출력
   * 
   * @param dt
   * @return
   */
  public static String getTimeDifference(LocalDateTime dt) {
    LocalDateTime currentTime = LocalDateTime.now();
    // 현재 시간으로 객체 생성

    long minuteDifference = ChronoUnit.MINUTES.between(dt, currentTime);
    // between() 메서드의 리턴 타입 long.
    // currentTime - dt 시간(분) 리턴
//    long hourDifferenct = ChronoUnit.HOURS.between(dt, currentTime);

    if (minuteDifference >= 1440) { // 하루(1440분) 이상 경과 시 "yyyy-MM-dd" 출력
      return dt.format(DAY_DATETIME_PATTERN);
    }
    if (minuteDifference >= 60) { // 한 시간 이상 경과 시 "x 시간 전" 출력
      long hourDifference = minuteDifference / 60;
      return hourDifference + " 시간 전";
    }
    if (minuteDifference < 60) { // 한 시간 이하 경과 시 "x 분 전" 출력
      return minuteDifference + " 분 전";
    }
    return dt.format(DAY_DATETIME_PATTERN);
  }
}
