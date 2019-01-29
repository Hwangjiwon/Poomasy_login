<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Login!! 
</h1>

<P>  The time on the server is ${serverTime}. </P>

<a href="home">Home</a>
<a href="insert">Insert</a>
<br>
<a href="${url}">NaverLogin</a>
<a href="${kakao_url}">KakaoLogin</a>

</body>
</html>
