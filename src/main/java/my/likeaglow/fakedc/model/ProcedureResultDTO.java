package my.likeaglow.fakedc.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class ProcedureResultDTO {
	
	public static final int SUCCESS_CODE = 1;
	
	private int ERR_CD = -1;
	private String ERR_MSG = "";
	
	protected boolean isProcedureCallSuccess() {
		return ERR_CD == SUCCESS_CODE;
	}
	
	protected boolean isProcedureCallFailure() {
		return !isProcedureCallSuccess();
	}
}
