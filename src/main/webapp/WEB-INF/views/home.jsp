<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="member/insert">Insert</a>
<a href="member/login">Login</a>
<a href="member/logout">Logout</a>
<a href="member/memberList">MemberList</a>
</body>
</html>
