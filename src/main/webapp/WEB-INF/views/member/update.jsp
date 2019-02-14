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

%>
	<h1>Update Info!!</h1>
	
		id : <%=userid%><br>
		
		<p>
		<form action = "/myapp/member/updateCallback" method="post">
			<input type = "hidden" id = "userid" name = "userid" value = "<%=userid%>">

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
