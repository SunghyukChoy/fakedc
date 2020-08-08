package my.likeaglow.fakedc.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostVO extends ProcedureResultDTO {
	
	private long POST_ID;
	private String BOARD_ID;
	private String POST_TITLE;
	private String POST_CONTENT;
	private String POST_PASSWORD;
	private int VIEW_COUNT;
	private int POST_TYPE;
	private int POST_RECOMMEND_COUNT;
	private int POST_UNRECOMMEND_COUNT;	
	private String CREATE_USER;
	private LocalDateTime CREATE_DATE;
	private String UPDATE_USER;
	private LocalDateTime UPDATE_DATE;
	
	
	public boolean isInvalid() {
		// TODO : ..
		return false;
	}

}
