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

<form action = "/member/insertService" method="post">
	id<input type = "text" name ="userid"><br>
	password<input type = "password" name = "password"><br>
	email<input type = "email" name = "email"><br>
	name<input type = "text" name = "name"><br>
	phone<input type = "text" name ="phone"><br>
	<input type = "submit" value = "SUBMIT">
</form>

<a href="home">Home</a>
<a href="login">Login</a>
</body>
</html>
