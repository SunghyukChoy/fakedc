package my.likeaglow.fakedc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor // 인자가 없는 기본생성자를 자동으로 만들어 줌
@AllArgsConstructor // 필드의 순서대로 파라미터를 받는 생성자를 만들어줌.
public class LoginMemberDTO {

	private String MEM_ID;
	private String MEM_NAME;
	private int ERR_CD;
	private String ERR_MSG;

}