<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Callback</title>
</head>
<body>
	<h1>CallBack!</h1>

	<!-- 일반로그인 -->
	<c:if test="${not empty sessionScope.userid}">
		
		<h2>Login Success!!</h2>
		<div>ID : ${userid}</div>
		<div>EMAIL : ${email}</div>
		<div>NAME : ${name}</div>
		<div>PHONE : ${phone}</div>
		<!--홈페이지 사인아웃 -->
		<form action = "/myapp/member/update" method ="get">
		<input type="hidden" name = "userid" value="${userid}">
		<br>
		<input type="hidden" name = "email" value="${email}">
		<br>
		<input type="hidden" name = "name" value="${name}">
		<input type="hidden" name = "phone" value = "${phone}">
		<input type="submit" value="Mypage">
		</form>
		<a href="<c:url value='/member/logout'/>">LogOut</a>
	</c:if>


</body>
</html>