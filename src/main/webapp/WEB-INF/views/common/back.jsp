<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<script>
  <c:if test="${ alertMessage != null }">
    alert('${ alertMessage }');
  </c:if>
  /* Controller에서 mv 객체에 "alertMessage"를 key로 하여 value에 띄울 메세지를 담음 */
	/* 알림팡으로 바로 메세지를 보여주고 */
  history.back(-1);
  /* 뒤로 가기 */
</script>
</head>
</html>