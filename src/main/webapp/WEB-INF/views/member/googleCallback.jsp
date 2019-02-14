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
	<c:if test="${not empty sns_info}">
		<div>Google Profile : ${sns_info}</div>
		<div>Google Name : ${sns_name}</div>
		<div>Google Id : ${sns_id}</div>
		<br>
		<form action = "/myapp/member/updateSnsMember" method ="get">
		<input type="hidden" name = "sns_id" value="${sns_id}">
		<input type="hidden" name = "sns_type" value="google">
		<input type="submit" value="UpdateInfo">
		</form>
		<a href="<c:url value='https://myaccount.google.com/permissions?utm_source=google-account&utm_medium=web'/>">SIGN_OUT</a>
	</c:if>
	
</body>
</html>