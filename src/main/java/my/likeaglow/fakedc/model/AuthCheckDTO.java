package my.likeaglow.fakedc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthCheckDTO extends ProcedureResultDTO {
  // TODO: 로그인 시도에 필요한 정보를 위한 field 생성, getter, setter 지정 with Lombok
  private String MEM_ID;
  private String MEM_PASSWORD;

  /**
   * 입력받은 아이디와 패스워드의 공백 여부 확인.
   * 
   * @param authCheckDTO
   * @return
   */
  public boolean isInvalidDTO() {
    return MEM_ID.equals("") || MEM_PASSWORD.equals("");
  }

  /**
   * 쿼리의 수행 결과 확인.
   * 
   * @return
   */
  public boolean isLoginSuccess() {
    return isSuccessCall();
  }

}