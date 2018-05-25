<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8" name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
<script data-require="ui-bootstrap@*" data-semver="0.13.0"
	src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.13.0.min.js"></script>
<title>Title</title>
<script language="javascript" type="text/javascript">
	function doSubmit() {
		window.location = "/register";
	}
</script>

<nav class="navbar navbar-inverse navbar-fixed-top"> <a
		class="navbar-brand" href=""> <span
		class="glyphicon glyphicon-film pull-left"></span> <b class="tr-logo">Plagiarism
			Detector</b>
	</a> </nav>
</head>
<body>
	<div class="row" align="center" style="margin-top:80px;">
	<h1>Login</h1>
	</div>
	


	<form class="form-horizontal" modelAttribute="userBean" method="POST">

		<div class="container" >
			<div>
		<font color="red" role="alert"> ${errorMessage} </font>
	</div>
			<input name="username" class="form-control" placeholder="username" />
			<input name="password" type="password" class="form-control"
				placeholder="password" />


			<button style="margin-top: 10px;" type="submit"
				class="btn btn-success btn-block">Login</button>

			<a href="/register" class="btn btn-block btn-danger">Register</a>

		</div>
	</form>

</body>
</html>