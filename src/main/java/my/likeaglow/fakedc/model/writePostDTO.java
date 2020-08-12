package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class writePostDTO extends ProcedureResultDTO {

  private long POST_ID;
  private String BOARD_ID;
  private String POST_TITLE;
  private String POST_CONTENT;
  private String POST_PASSWORD;
  private int POST_TYPE;
  private String CREATE_USER;

  /**
   * 입격받은 데이터들의 공백 여부 검사, 길이 검사
   * 
   * @return
   */
  public boolean isInvalid() {
    if (BOARD_ID.equals("") || POST_TITLE.equals("") || POST_CONTENT.equals("") || POST_PASSWORD.equals("")
        || POST_TYPE < 0 || CREATE_USER.equals("")) {
      return true;
    }
    if (BOARD_ID.length() > 20 || POST_TITLE.length() > 50 || POST_CONTENT.length() > 1024
        || POST_PASSWORD.length() > 12 || CREATE_USER.length() > 20) {
      return true;
      // 문자열의 유효성 검사는 디비에서 검사가 되지 않는다.
      // content 컬럼의 길이기 1024라면 이 길이를 넘었을 때 1024자까지만 저장되고 나머지는 잘린다.
    }
    return false;
  }

  /**
   * 쿼리 수행 결과 확인
   * 
   * @return
   */
  public boolean isWriteSuccess() {
    return isSuccessCall();
  }

}
