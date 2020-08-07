package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateMemberDTO {

  private String MEM_ID;
  private String MEM_NAME;
  private String MEM_PASSWORD;
  private String EMAIL;
  private String PHONE_NUM;
  private String BIRTHDAY;
  private String INFO_OFFER;
  private int ERR_CD;
  private String ERR_MSG;

}
