<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
<%
	String userid = request.getParameter("userid");
	String gender = request.getParameter("gender");
	String age = request.getParameter("age");
%>
	<h1>Updated Info!!</h1>

	
		
		<div>id : <%=userid%></div>
		<div>gender : <%=gender%></div>
		<div>age : <%=age%></div>

	<a href="<c:url value='/member/callback'/>">MyPage</a>
	<a href="<c:url value='/member/logout'/>">LogOut</a>
</body>
</html>
