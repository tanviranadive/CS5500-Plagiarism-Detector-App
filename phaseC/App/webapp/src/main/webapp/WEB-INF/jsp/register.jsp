<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top"> <a
		class="navbar-brand" href=""> <span
		class="glyphicon glyphicon-film pull-left"></span> <b class="tr-logo">Plagiarism
			Detector</b>
	</a> </nav>
	<div style="margin-top: 50px; padding-top:5px;">
	<div class="container">
		<font color="red" role="alert"> ${message} </font>
	</div>
	</div>

	<form class="form-horizontal" method="POST">

		<div class="container tr-body-register-margin">

			<h1 style="text-align: center;">Register</h1>
			<input style="margin-top: 10px;" name="username" type="text"
				class="form-control" placeholder="username" /> <input
				style="margin-top: 10px;" name="password" type="password"
				class="form-control" placeholder="password" />

			<button style="margin-top: 10px;" type="submit"
				class="btn btn-primary btn-block">Register</button>

			<a href="/login" class="btn btn-danger btn-block">Cancel</a>

		</div>
</body>
</html>