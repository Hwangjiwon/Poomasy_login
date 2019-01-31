<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:requestEncoding value="UTF-8" />
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>Login!!</h1>

	<div class="container">
		<div class="pg-opt">
			<h2>LOGIN</h2>
		</div>
		<div class="content">
			<c:if test="${empty sessionScope.userid}">
				<form action="<c:url value='/member/login'/>" method="post"
					class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2" for="id">ID</label>
						<div class="col-sm-8">
							<input type="text" name="userid" id="id" class="form-control"
								placeholder="ID" aria-describedby="basic-addon1">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2" for="pw">Password</label>
						<div class="col-sm-8">
							<input type="password" name="password" id="pw"
								class="form-control" placeholder="Password"
								aria-describedby="basic-addon1">
						</div>
					</div>
					<input type = "submit" value = "Login">
				</form>
			</c:if>
		
		</div>
	</div>


	<a href="home">Home</a>
	<a href="insert">Insert</a>
	<br>
	<a href="${n_url}">NaverLogin</a>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=32cf5047e2290a65b781450e52fab213&redirect_uri=http://localhost:8090/myapp/member/kakaoCallback&response_type=code">KakaoLogin</a>
	<a href="${g_url}">GoogleLogin</a>
	<a href="${f_url}">FacebookLogin</a>
</body>
</html>
