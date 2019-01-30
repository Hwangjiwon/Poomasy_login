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

	<!-- �Ϲݷα��� -->
	<c:if test="${not empty sessionScope.userid}">
		<h2>Login Success!!</h2>
		<h4>ID : ${userid}</h4>
		<h4>EMAIL : ${email}</h4>
		<h4>NAME : ${name}</h4>
		<h4>PHONE : ${phone}</h4>
		<!--Ȩ������ ���ξƿ� -->
		<a href="<c:url value='/member/logout'/>">SIGN_OUT</a>
	</c:if>

	<!-- ���̹��α��� -->
	<c:if test="${not empty result}">

		<div>Naver Login : ${result}</div>
		<a href="<c:url value='http://nid.naver.com/nidlogin.logout'/>">SIGN_OUT</a>
		
		<p>
		from ���̹� ������ ����

���̹� �α׾ƿ��� ���� ������ api�� ������ ����ڰ� ���� ���̹� ���񽺿��� �α׾ƿ� �ϵ��� ó���ϼž� �մϴ�. 
������ �̿��� ��ȣ�� ���� ��å�� ���̹� �̿��� ���񽺿��� ���̹� �α׾ƿ��� �����ϴ� ���� ������� �ʰ� �ִ� �� ���� ��Ź�帳�ϴ�.
		</p>
	</c:if>


</body>
</html>