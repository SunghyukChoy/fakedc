package my.likeaglow.fakedc.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO {

  private String MEM_ID;
  private String MEM_NAME;
  private String MEM_PASSWORD;
  private String EMAIL;
  private String PHONE_NUM;
  private String BIRTHDAY;
  private LocalDateTime RECENT_VISIT;
  private String INFO_OFFER;
  private String DBSTATUS;
  private String CREATE_USER;
  private LocalDateTime CREATE_TIME;
  private String UPDATE_USER;
  private LocalDateTime UPDATE_TIME;

}
