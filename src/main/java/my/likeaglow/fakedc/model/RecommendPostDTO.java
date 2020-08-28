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
public class RecommendPostDTO extends ProcedureResultDTO {

  private long POST_ID;
  private String MEM_ID;
  private int IS_UNRECOMMEND; // 0 : 추천. 1 : 비추천.

}
