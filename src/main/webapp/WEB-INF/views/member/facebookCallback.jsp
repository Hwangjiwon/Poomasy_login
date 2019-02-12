<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Facebook callback</title>
</head>
<body>

	<h2>Facebook Login Success!!</h2>
	<c:if test="${not empty fResult}">


		<div>Facebook Login : ${fResult}</div>
		<div>Facebook Name : ${f_name}</div>
		<div>Facebook Id : ${f_id}</div>
		<div>Facebook Email : ${f_email}</div>
		<br>
		
		<button type ="button" onclick = "location.href='updateSnsMember'">MyPage</button>
		<a href="<c:url value='https://www.facebook.com/?stype=lo&jlou=AfcsQCdjEH83VH4_4v1m5frEkSzaU2IIMQoQSFS5quYTDXAxJipptLLx3Ii7mM_cZwX_cYbu642fAiz6X7QBAt4nFeMxAUO7it-492-tZ2Mj_g&smuh=17071&lh=Ac_3ZsQ_un1yuB7L'/>">SIGN_OUT</a>

	</c:if>
</body>
</html>