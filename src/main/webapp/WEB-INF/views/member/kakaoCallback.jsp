<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<!-- 카카오로그인 -->
	<c:if test="${not empty sns_info}">
		<div>Kakao Login : ${sns_info}</div>
		<div>Kakao Id : ${sns_id}</div>
		<div>Kakao name : ${sns_name}</div>
		<br>
		
		<form action = "/myapp/member/updateSnsMember" method ="get">
		<input type="hidden" name = "sns_id" value="${sns_id}">
		<input type="hidden" name = "sns_type" value="kakao">
		<input type="submit" value="UpdateInfo">
		</form>

		<a href="<c:url value='https://accounts.kakao.com/weblogin/account/connected_apps#pageConnectedAppDetail'/>">SIGN_OUT</a>
	</c:if>

</body>
</html>