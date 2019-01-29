<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>MemberList</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>비밀번호</th>
				<th>이메일</th>
				<th>이름</th>
				<th>전화번호</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${memberList}" var="member">
				<tr>
					<td>${member.userid}</td>
					<td>${member.password}</td>
					<td>${member.email}</td>
					<td>${member.name}</td>
					<td>${member.phone}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<a href="home">Home</a>
</body>
</html>