<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	* {
		margin: 0;
		padding: 0;
	}

	label {
		display:inline-block;
		width: 150px;
	}
	
	form > div {
		padding-bottom: 10px;
	}
</style>
</head>
<body>
	글쓰기 페이지

<!-- 
	숙제 #1
	작성자의 로그인 상태를 체크하여
	작성자가 로그인이 되어있으면 글쓴이 필드를 없게 처리하고
	자동으로 로그인 회원 ID가 들어가도록 컨트롤러 내부 메서드를 수정할 것
 -->
	<form method="post">
		<input type="hidden" name="BOARD_ID" value="${ vo.BOARD_ID }" />

		<div>
			<label>글종류</label> 
			<select name="POST_TYPE">
				<option>선택</option>
				<option value="1" ${ (vo.POST_TYPE == 1 ? "selected" : "") }>일반</option>
				<option value="2" ${ (vo.POST_TYPE == 2 ? "selected" : "") }>공지</option>
			</select>
		</div>
		<div>
			<label>제목</label> <input type="text" name="POST_TITLE"
				value="${ vo.POST_TITLE }" />
		</div>
		<div>
			<label>글쓴이</label> <input type="text" name="CREATE_USER"
				value="${ vo.CREATE_USER }" />
		</div>
		<div>
			<label>비밀번호</label> <input type="password" name="POST_PASSWORD"
				value="${ vo.POST_PASSWORD }" />
		</div>
		<div>
			<label>내용</label>
			<textarea name="POST_CONTENT">${ vo.POST_CONTENT }</textarea>
		</div>

		<button type="submit">제출</button>
		<button type="button" onclick="history.back(-1)">게시판으로</button>
	</form>
</body>
</html>