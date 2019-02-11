<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
</head>
<body>
	<h1>Insert Info!!</h1>

	<P>The time on the server is ${serverTime}.</P>

	<form action="/myapp/member/insert" method="post">
		<p>
			id<input type="text" name="userid" id="userid"><br>
			<button type="button" class="idCheck">idCheck</button>
		</p>
		<p class="result">
			<span class="msg">Please id Check</span>
		</p>
		password<input type="password" name="password" id="password"><br>
		email<input type="email" name="email" id="email"><br>
		name<input type="text" name="name" id="name"><br> 
		phone<input	type="text" name="phone" id="phone"><br>
		<button type="submit" id="submit" disabled="disabled">Submit</button>
	</form>

	<a href="home">Home</a>
	<a href="login">Login</a>

	<script>
		$(".idCheck").click(function() {

			var query = {
				userid : $("#userid").val()
			};

			$.ajax({
				url : "/myapp/member/idCheck",
				type : "post",
				data : query,
				success : function(data) {

					if (data == 1) {
						$(".result .msg").text("Don't use this ID");
						$(".result .msg").attr("style", "color:#f00");

						$("#submit").attr("disabled", "disabled");
					} else {
						$(".result .msg").text("Success");
						$(".result .msg").attr("style", "color:#00f");

						$("#submit").removeAttr("disabled");
					}
				}
			});
		});

		$("#userid").keyup(function() {
			$(".result .msg").text("Please Check id");
			$(".result .msg").attr("style", "color:#000");

			$("#submit").attr("disabled", "disabled");

		});
	</script>
</body>
</html>
