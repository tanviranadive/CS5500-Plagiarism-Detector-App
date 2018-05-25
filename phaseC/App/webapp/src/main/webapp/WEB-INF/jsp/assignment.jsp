<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css">
    <script data-require="ui-bootstrap@*" data-semver="0.13.0" src="http://angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.13.0.min.js"></script>
    <title>Upload Page</title>
<script language="javascript" type="text/javascript">
function logout()
{
	 window.location = "/login";
}

function redirectHome()
{
	 window.location.href = "/user/${username}/student/home";
}
</script>
</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
<div class="row">
<div class="col-sm-6">
    <a class="navbar-brand" href="">
        <span class="glyphicon glyphicon-film pull-left"></span>
        <b class="tr-logo">Plagiarism Detector</b>
    </a>
    </div>
    <div class="col-sm-6" >
	    <a class="navbar-brand navbar-right" style="margin-right:2px;" href="#">
	       
	        <button class="btn btn-danger" onClick="logout()">Logout
	        </button>
	       </a>
	       
    </div>
    </div>
    </nav>
    
	<div class="container" style="margin-top: 80px;">
    <h1 align = "center">Upload your files</h1>
    <br>
    <div class ="container container-fluid">
    <div>
	<font color="red" role="alert">
        ${errormessage}
        </font>
    </div>
	    <form align ="center" method="post" enctype="multipart/form-data" >
	    
	    <div class ="form-group">
	    <div class="input-group input-file" name="myFile">
			<span class="input-group-btn">
	        <input name="myFile" id="myFile" type="file" class="form-control" multiple/>
	        </span>
	     </div>
	  		 
	  		<div class="row">
	         <button style="margin-top:20px;" type="submit" class="btn btn-block btn-primary">Submit</button>
	        </div>
	        <br>
	        <div class="row">
	         <input type="button" class="btn btn-block btn-success" name="button1" value="Home" onClick="redirectHome()">
	         </div>
		</div>
	    </form>
	</div>
</body>
</html>