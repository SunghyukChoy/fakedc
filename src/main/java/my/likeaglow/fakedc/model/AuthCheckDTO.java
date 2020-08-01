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
public class AuthCheckDTO {
  // TODO: 로그인 시도에 필요한 정보를 위한 field 생성, getter, setter 지정 with Lombok
  private String MEM_ID;
  private String MEM_PASSWORD;
  private int ERR_CD;
  private String ERR_MSG;
}