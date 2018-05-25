<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
		<h1 align="center">Plagiarism Result</h1>
		
		<p align="center">${message}</p>
		
		<table class="table">
 		<thead>
    		<tr>
      			<th scope="col">#</th>
      			<th scope="col">FileName1</th>
			    <th scope="col">FileName2</th>
			    <th scope="col">Similarity (%)</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${results}" var="compResult" varStatus="loop">
				<tr>
					<th scope="row">${loop.index+1}</th>
				    <td>${compResult.fileName1}</td>
				    <td>${compResult.fileName2}</td>
				    <td><fmt:formatNumber type="number" maxFractionDigits="2" value="${compResult.score}"/>%</td>
			   	</tr>
			</c:forEach>
		</tbody>
		</table>

		<br>
		<div class="col-sm-5"></div>
		<div class="col-sm-2" align="center">
			<input type="button" class="btn btn-primary" onClick="redirectHome()"
				value="home" />
		</div>
		<div class="col-sm-5"></div>
	</div>
	</div>
</body>
</html>