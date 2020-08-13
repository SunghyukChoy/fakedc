package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostListDTO extends ProcedureResultDTO {

  private String BOARD_ID;
  private int SEARCH_TYPE;
  private int POST_TYPE;
  private String KEYWORD;
  private int PAGING_SIZE;
  private int PAGINE_INDEX;

}
