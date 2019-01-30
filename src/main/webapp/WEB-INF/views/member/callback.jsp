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
		<a href="<c:url value='/member/logout'/>">SIGN_OUT</a>
	</c:if>

	<!-- 네이버로그인 -->
	<c:if test="${not empty result}">

		<div>Naver Login : ${result}</div>
		<a href="<c:url value='http://nid.naver.com/nidlogin.logout'/>">SIGN_OUT</a>
		
		<p>
		from 네이버 개발자 센터

네이버 로그아웃에 대한 별도의 api가 없으며 사용자가 직접 네이버 서비스에서 로그아웃 하도록 처리하셔야 합니다. 
이유는 이용자 보호를 위해 정책상 네이버 이외의 서비스에서 네이버 로그아웃을 수행하는 것을 허용하지 않고 있는 점 양해 부탁드립니다.
		</p>
	</c:if>


</body>
</html>