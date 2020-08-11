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

  public boolean isInvalid() {
    if (BOARD_ID.equals("") || POST_TITLE.equals("") || POST_CONTENT.equals("") || POST_PASSWORD.equals("")
        || CREATE_USER.equals("")) {
      return true;
    }
    return false;
  }

  public boolean isWriteSuccess() {
    return isSuccessCall();
  }

}
