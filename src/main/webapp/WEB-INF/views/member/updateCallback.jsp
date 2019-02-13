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
	String email = request.getParameter("email");
	String name = request.getParameter("name");
	String phone = request.getParameter("phone");
	String gender = request.getParameter("gender");
	String age = request.getParameter("age");
%>
	<h1>Updated Info!!</h1>

	
		
		id : <%=userid%><br>
		email : <%=email%><br>
		name : <%=name%><br> 
		phone : <%=phone%><br>
		gender : <%=gender%><br>
		age : <%=age%><br>

	<a href="home">Home</a>
	<a href="login">Login</a>
</body>
</html>
