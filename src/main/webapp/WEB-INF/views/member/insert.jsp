<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Insert Info!!
</h1>

<P>  The time on the server is ${serverTime}. </P>

<form action = "/myapp/member/insert" method="post">
	id<input type = "text" name ="userid" id = "userid" ><br>	
	password<input type = "password" name = "password" id = "password"><br>
	email<input type = "email" name = "email" id = "email"><br>
	name<input type = "text" name = "name" id = "name"><br>
	phone<input type = "text" name ="phone" id = "phone"><br>
	<input type = "submit" value = "SUBMIT">
</form>

<a href="home">Home</a>
<a href="login">Login</a>
</body>
</html>
