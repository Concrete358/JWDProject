<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>login page</title>
<style> table {
	margin-left: 2.5%;
	border-collapse: collapse;
    font-family: Tahoma, Geneva, sans-serif;
}
table td {
	padding: 5px;
}
table thead td {
	background-color: #54585d;
	color: #ffffff;
	font-weight: bold;
	font-size: 13px;
	border: 1px solid #54585d;
}
table tbody td {
	color: #636363;
	font-size: 13px;
	border: 1px solid #dddfe1;
}
table tbody tr {
	background-color: #f9fafb;
}
table tbody tr:nth-child(odd) {
	background-color: #ffffff;
} 
</style>
</head>
<body>
<jsp:include page="/WEB-INF/common/header.jsp"/>
	<p style="margin-left:2.5%">Login page
	<form action="FrontController" method="post">
		<input type="hidden" name="command" value="login">
		<table style="with: 50%">

			<tr>
				<td>Email</td>
				<td><input type="email" name="email" required /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" required /></td>
			</tr>
			<tr>
				<td><input type="submit" value="login" /></td><td><h2 style="color: red"> ${loginErrorText} </h2></td>
			</tr>
		</table>
	</form>
	

	<p style="margin-left:2.5%"> If you are not registred, go to <a href=FrontController?command=go_to_register_page>register page</a>.


</body>
</html>