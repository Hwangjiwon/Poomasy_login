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
	<!-- ���̹��α��� -->
	<c:if test="${not empty result}">

		<div>Naver Login : ${result}</div>
		<div>Naver id : ${n_id}</div>
		<div>Naver email : ${n_email}</div>
		<br>
		<a href="<c:url value='http://nid.naver.com/nidlogin.logout'/>">SIGN_OUT</a>

		<p>���̹� ������ ���� ���̹� �α׾ƿ��� ���� ������ api�� ������ ����ڰ� ���� ���̹� ���񽺿���
			�α׾ƿ� �ϵ��� ó���ϼž� �մϴ�. ������ �̿��� ��ȣ�� ���� ��å�� ���̹� �̿��� ���񽺿��� ���̹� �α׾ƿ��� �����ϴ�
			���� ������� �ʰ� �ִ� �� ���� ��Ź�帳�ϴ�.</p>
	</c:if>


</body>
</html>