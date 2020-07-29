package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// @Getter, Setter : Getter, Setter 메소드 자동 생성. 라이브러리 lombok 이용.
@ToString
// @ToString : 객체에 담긴 값들을 한 번에 출력하기 위한 어노테이션
public class RegisterVO {
// 회원가입 시 데이터를 담을 객체
  private String MEM_ID;
  private String MEM_NAME;
  private String MEM_PASSWORD;
  private String EMAIL;
  private String PHONE_NUM;
  private String BIRTHDAY;
  private String INFO_OFFER; // `agree` or `disagree`
}
