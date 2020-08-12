package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMemberDTO extends ProcedureResultDTO {

  private String MEM_ID;
  private String MEM_NAME;
  private String MEM_PASSWORD;
  private String EMAIL;
  private String PHONE_NUM;
  private String BIRTHDAY;
  private String INFO_OFFER;

  /**
   * 입력받은 데이터들의 공백 여부 검사
   * 
   * @return
   */
  public boolean isInvalidDTO() {
    return MEM_ID.equals("") || MEM_NAME.equals("") || MEM_PASSWORD.equals("") || EMAIL.equals("")
        || BIRTHDAY.equals("") || PHONE_NUM.equals("") || INFO_OFFER.equals("");
  }

  /**
   * 쿼리 수행 결과 확인
   * 
   * @return
   */
  public boolean isUpdateSuccess() {
    return isSuccessCall();
  }
}
