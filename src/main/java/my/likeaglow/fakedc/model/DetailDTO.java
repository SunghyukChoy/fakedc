package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DetailDTO extends ProcedureResultDTO {

  private long POST_ID;

  /**
   * 쿼리 수행 결과 확인
   * 
   * @return
   */
  public boolean isDetailSuccess() {
    return isSuccessCall();
  }
}
