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
	<c:if test="${not empty sns_info}">

		<div>Naver Login : ${sns_info}</div>
		<div>Naver id : ${sns_id}</div>
		<div>Naver email : ${sns_email}</div>
		<br>
		<form action="/myapp/member/updateSnsMember" method="get">
			<input type="hidden" name="sns_id" value="${sns_id}"> <input
				type="hidden" name="sns_type" value="naver"> <input
				type="submit" value="UpdateInfo">
		</form>
		<a href="<c:url value='http://nid.naver.com/nidlogin.logout'/>">SIGN_OUT</a>

		<p>���̹� ������ ���� ���̹� �α׾ƿ��� ���� ������ api�� ������ ����ڰ� ���� ���̹� ���񽺿��� �α׾ƿ� �ϵ���
			ó���ϼž� �մϴ�. ������ �̿��� ��ȣ�� ���� ��å�� ���̹� �̿��� ���񽺿��� ���̹� �α׾ƿ��� �����ϴ� ���� ������� �ʰ�
			�ִ� �� ���� ��Ź�帳�ϴ�.</p>
	</c:if>


</body>
</html>