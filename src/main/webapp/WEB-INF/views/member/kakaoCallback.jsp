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
	<c:if test="${not empty k_userInfo}">
		<div>Kakao Login : ${k_userInfo}</div>
		<div>Kakao Id : ${k_id}</div>
		<div>Kakao name : ${k_name}</div>
		<br>
		
		<button type ="button" onclick = "location.href='updateSnsMember'">MyPage</button>
		<a href="<c:url value='https://accounts.kakao.com/weblogin/account/connected_apps#pageConnectedAppDetail'/>">SIGN_OUT</a>
	</c:if>

</body>
</html>