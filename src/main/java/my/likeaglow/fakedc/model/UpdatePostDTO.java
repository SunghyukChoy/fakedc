package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdatePostDTO extends ProcedureResultDTO {

  private long POST_ID;
  private String POST_TITLE;
  private String POST_CONTENT;
  private String POST_PASSWORD;
  private int POST_TYPE;

}
