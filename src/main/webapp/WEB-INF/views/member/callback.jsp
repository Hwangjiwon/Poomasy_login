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
		<h4>ID : ${userid}</h4>
		<h4>EMAIL : ${email}</h4>
		<h4>NAME : ${name}</h4>
		<h4>PHONE : ${phone}</h4>
		<!--홈페이지 사인아웃 -->
		<a href="<c:url value='/member/logout'/>">LogOut</a>
	</c:if>


</body>
</html>