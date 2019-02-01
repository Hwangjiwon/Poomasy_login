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
	<c:if test="${not empty g_userInfo}">
		<div>Google Profile : ${g_userInfo}</div>
		<div>Google Name : ${g_userName}</div>
		<div>Google Id : ${g_userId}</div>
		
		<a href="<c:url value='https://myaccount.google.com/permissions?utm_source=google-account&utm_medium=web'/>">SIGN_OUT</a>
	</c:if>
	
</body>
</html>