<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>register page</title>
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
	<p style="margin-left:2.5%">Register page
	<form action="FrontController" method="post">
		<input type="hidden" name="command" value="register">
		<table style="with: 50%">

			<tr>
				<td>Email</td>
				<td><input type="email" name="email" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>Lastname</td>
				<td><input type="text" name="lastname" /></td>
			</tr>
			<tr>
				<td>Birthdate</td>
				<td><input type="date" name="birthdate" /></td>
			</tr>
			<tr>
				<td>Passport number</td>
				<td><input type="text" name="passport_number" /></td>
			</tr>
			<tr>
				<td>Passport expiry term</td>
				<td><input type="date" name="passport_term" /></td>
			</tr>
			<tr>
				<td>Driving licence number</td>
				<td><input type="text" name="d_licence_number" /></td>
			</tr>
			<tr>
				<td>Driving licence expiry term</td>
				<td><input type="date" name="d_licence_term" /></td>
			</tr>
			<tr>
				<td>Phone number</td>
				<td><input type="tel" name="phone_number" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Register" /></td> <td><h2 style="color: red"> ${RegisterErrorText} </h2></td>
			</tr>
		</table>
	</form>

	<p style="margin-left:2.5%">If you have already registered, go to <a href=FrontController?command=go_to_login_page>login page</a>.

</body>
</html>
