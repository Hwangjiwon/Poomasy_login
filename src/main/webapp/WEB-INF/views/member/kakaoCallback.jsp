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
	<!-- īī���α��� -->
	<c:if test="${not empty k_userInfo}">
		<div>Kakao Login : ${k_userInfo}</div>
		<div>Kakao id: ${k_id}</div>
		
		<a href="<c:url value='https://accounts.kakao.com/weblogin/account/connected_apps#pageConnectedAppDetail'/>">SIGN_OUT</a>
	</c:if>

	<script>
		if ('${k_userInfo}') {

			$("#k_id").text("���̵� : " + '${id}');
		}
	</script>
</body>
</html>