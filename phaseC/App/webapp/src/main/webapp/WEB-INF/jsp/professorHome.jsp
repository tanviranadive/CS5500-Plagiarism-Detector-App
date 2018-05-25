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
<nav class="navbar navbar-inverse navbar-fixed-top">
<div class="row">
	<div class="col-sm-6">
		<a class="navbar-brand" href=""> <span
			class="glyphicon glyphicon-film pull-left"></span> <b class="tr-logo">Plagiarism
				Detector</b>
		</a>
	</div>
	<div class="col-sm-6">
		<a class="navbar-brand navbar-right" style="margin-right: 2px;"
			href="#">

			<button class="btn btn-danger" onClick="logout()">Logout</button>
		</a>

	</div>
</div>
</nav>
<script language="javascript" type="text/javascript">
function logout()
{
	 window.location = "/login";
}

function redirectHome()
{
	 window.location.href = "/user/${username}/professor/home";
}
</script>
</head>
<body>


	<div class="container" style="margin-top: 80px;">
		<h1 style="text-align: center;">Welcome Professor ${username}</h1>
		<div>
			<font color="red" role="alert"> ${errorMessage} </font>
		</div>
		<div class="list-group">
			<div class="list-group-item">
				<a href="${homeUrl}/assignment1">Assignment1</a>
			</div>
			<div class="list-group-item">
			<a href="${homeUrl}/assignment2">Assignment2</a>
			</div>
			<div class="list-group-item">
			<a href="${homeUrl}/assignment3">Assignment3</a>
			</div>
		</div>
	</div>
</body>
</html>