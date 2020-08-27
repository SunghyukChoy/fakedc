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
public class DeletePostDTO extends ProcedureResultDTO {

  private long POST_ID;
  private String MEM_ID;

}