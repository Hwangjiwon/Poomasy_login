<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Mypage</title>
</head>
<body>
	<h1>Add Your Info!!</h1>
	
	<form action="/myapp/member/updateSnsMember" method="post">
		Id : ${sns_id}
		<br> 
		<label for="sns_gender">gender : </label> 
		<input type="radio" name="sns_gender" value="male" checked="checked">Male 
		<input type="radio" name="sns_gender" value="female">Female
		<br> phone: <input type="text" name="sns_phone" id="sns_phone">
		<br>
		<button type="submit" id="submit">Submit</button>
	</form>

</body>
</html>
