package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
// @Getter, Setter : Getter, Setter 메소드 자동 생성. 라이브러리 lombok 이용.
@ToString
// @ToString : 객체에 담긴 값들을 한 번에 출력하기 위한 어노테이션
public class RegisterDTO extends ProcedureResultDTO {
  // 회원가입 시 데이터를 담을 객체
  // MemberVO를 쓰지 않고 DTO 객체를 따로 만들어 쓰는 이유는 각 클래스를 목적에 맞는 용도로만 쓰기 위해서임.
  // 같은 필드를 사용한다고 해서 다른 목적으로 만든 클래스를 쓰지 말것.
  private String MEM_ID;
  private String MEM_NAME;
  private String MEM_PASSWORD;
  private String EMAIL;
  private String PHONE_NUM;
  private String BIRTHDAY;
  private String INFO_OFFER; // 'Y' or 'N'

  // DTO 객체에 변수들의 값만이 아닌 객체의 상태를 저장한다.
  /**
   * 저장하기 전 유효한 저장 상태인지 점검한다
   * 
   * @return
   */
  public boolean isInvalidDTO() {
    if (MEM_ID.equals("") || MEM_NAME.equals("") || MEM_PASSWORD.equals("") || EMAIL.equals("") || BIRTHDAY.equals("")
        || PHONE_NUM.equals("") || INFO_OFFER.equals("")) {
      return true;
    }
    if (MEM_ID.length() > 20 || MEM_NAME.length() > 30 || MEM_PASSWORD.length() > 100 || EMAIL.length() > 50
        || PHONE_NUM.length() > 20 || BIRTHDAY.length() > 10 || INFO_OFFER.length() > 10) {
      return true;
    }
    return false;
  }

  public boolean isRegisterSuccess() {
    return isSuccessCall();
  }
}
