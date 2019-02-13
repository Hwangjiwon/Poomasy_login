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
%>
	<h1>Update Info!!</h1>
	
		id : <%=userid%><br>
		email : <%=email%><br>
		name : <%=name%><br> 
		phone : <%=phone%><br>
		
		<p>
		<form action = "/myapp/member/updateCallback" method="post">
			<input type = "hidden" id = "userid" name = "userid" value = "<%=userid%>">
			<input type = "hidden" id = "email" name = "email" value = "<%=email%>">
			<input type = "hidden" id = "named" name = "name" value = "<%=name%>">
			<input type = "hidden" id = "phone" name = "phone" value = "<%=phone%>">
		
			<input type = "radio" id="gender" name="gender" value="female"/>Female
			<input type = "radio" id="gender" name="gender" value="male"/>Male
			<br>
			age : <input type = "text" id="age" name="age">
			<br>

		<button type="submit" id="submit">Submit</button>
	</form>

	<a href="home">Home</a>
	<a href="login">Login</a>
</body>
</html>
