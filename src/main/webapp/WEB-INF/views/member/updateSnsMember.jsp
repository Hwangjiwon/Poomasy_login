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

%>
	<h1>Update Info!!</h1>
	
		id : <%=sns_id%><br>
		
		<p>
		<form action = "/myapp/member/updateSnsMemberCallback" method="post">
			<input type = "hidden" id = "sns_id" name = "sns_id" value = "<%=sns_id%>">
			<input type = "hidden" id = "sns_type" name = "sns_type" value = "<%=sns_type%>">

			<input type = "radio" id="sns_gender" name="sns_gender" value="female"/>Female
			<input type = "radio" id="sns_gender" name="sns_gender" value="male"/>Male
			<br>
			phone : <input type = "text" id="sns_phone" name="sns_phone">
			<br>

		<button type="submit" id="submit">Submit</button>
	</form>

	<a href="home">Home</a>
	<a href="login">Login</a>
</body>
</html>
