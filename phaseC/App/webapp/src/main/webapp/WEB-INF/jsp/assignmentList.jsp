<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.io.*,java.util.* , javax.servlet.jsp.JspWriter"%>
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
<title>Assignment list</title>
<script language="javascript" type="text/javascript">
	function logout() {
		window.location = "/login";
	}

	function redirectHome() {
		window.location.href = "/user/${username}/professor/home";
	}
</script>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="row">
		<div class="col-sm-6">
			<a class="navbar-brand" href=""> <span
				class="glyphicon glyphicon-film pull-left"></span> <b
				class="tr-logo">Plagiarism Detector</b>
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
	<div>
		<font color="red" role="alert"> ${errorMessage} </font>
	</div>
	<div class="container" style="margin-top: 80px;">
		<div id="main">
  			<h1 style="text-align: center;"><input type="button" class="btn btn-primary pull-left" style="margin-left:5px; margin-right:5px;" onClick="redirectHome()"
					value="< Back" />
					Welcome Professor ${username}
			</h1> 
		</div>
		<h3 style="text-align: center;">Compare Student Submissions</h3>
		<br>
		<form method="POST">
			<div class="row">
				<div class="col-md-6 col-sm-12" align="center">
					<legend>Student1</legend>
					<c:forEach items="${files}" var="val">
						<div class="row">
							<label class="form-check-label"> <input type="radio"
								checked class="form-check-input" name="CompFile1" value="${val}">
								${val}<br>
							</label>
						</div>
					</c:forEach>
				</div>

				<div class="col-md-6 col-sm-12" align="center">
					<legend>Student2</legend>
					<c:forEach items="${files}" var="val">
						<div class="row">
							<label class="form-check-label"> <input type="radio"
								checked class="form-check-input" name="CompFile2" value="${val}">
								${val}<br>
							</label>
						</div>
					</c:forEach>
				</div>
			</div>
			<br> 
					<c:if test="${not empty files}">
		
		<div class="container" align="center">
			<fieldset class="form-group">
						<legend>Choose Strategy</legend>
						<div class="row">
							<div class="form-check col-md-4 col-sm-12">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="CompStrategy"
									value="SubSequence" checked> SubSequence<br>
								</label>
							</div>

							<div class="form-check col-md-4 col-sm-12">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="CompStrategy" value="Cosine">
									Cosine<br>
								</label>
							</div>
							<div class="form-check col-md-4 col-sm-12">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="CompStrategy" value="Combined">
									Combined<br>
								</label>
							</div>
						</div>
					</fieldset>
					</div>
					<br>
			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<label>Please enter email address to receive mail</label> <input
						type="text" name="email" placeholder="EmailId (Optional)" /> <input
						class="btn btn-warning" type="submit" value="Compare" />
				</div>
				<div class="col-sm-4"></div>
			</div>
			</c:if>
		</form>
	</div>
</body>
</html>