package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class ProcedureResultDTO {

  private static final int SUCCESS_CODE = 1;

  private int ERR_CD = -1;
  private String ERR_MSG = "";

  public boolean isSuccessCall() {
    return isProcedureCallSuccess();
  }

  protected boolean isProcedureCallSuccess() {
    // protected : 동일 패키지 또는 하위 클래스에서 접근 가능.
    return ERR_CD == SUCCESS_CODE;
  }

  protected boolean isProcedureCallFailure() {
    return !isProcedureCallSuccess();
  }
}
