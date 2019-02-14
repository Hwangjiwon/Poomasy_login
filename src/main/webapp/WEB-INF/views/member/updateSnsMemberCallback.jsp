<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
<%
	String sns_id = request.getParameter("sns_id");
	String sns_type = request.getParameter("sns_type");
	String sns_phone = request.getParameter("sns_phone");

%>
	<h1>Updated Info!!</h1>
		
		<div>id : <%=sns_id%></div>
		<div>type : <%=sns_type%></div>
		<div>phone : <%=sns_phone%></div>
		
	<a href="<c:url value='home'/>">MyPage</a>
		
</body>
</html>
