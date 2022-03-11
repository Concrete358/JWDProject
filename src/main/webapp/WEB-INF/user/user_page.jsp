<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${user.name}${user.lastname}</title>
<style>
table {
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
	<p style="margin-left:2.5%">USER PAGE
	<h2>${error_message}</h2>
	<table border="1" width="35%">
		<tr>
			<td>id</td>
			<td>${user.id}</td>
		</tr>
		<tr>
			<td>Name</td>
			<td>${user.name}</td>

		</tr>
		<tr>
			<td>Lastname</td>
			<td>${user.lastname}</td>
		</tr>
		<tr>
			<td>Email</td>
			<td>${user.email}</td>
		</tr>
		<tr>
			<td>Birth date</td>
			<td>${user.birthdate}</td>
		</tr>
		<tr>
			<td>Passport number</td>
			<td>${user.passportNumber}</td>

		</tr>
		<tr>
			<td>Passport term</td>
			<td>${user.passportTerm}</td>

		</tr>
		<tr>
			<td>Driving licence</td>
			<td>${user.licenceNumber}</td>

		</tr>
		<tr>
			<td>Driving licence term</td>
			<td>${user.licenceTerm}</td>
		</tr>
		<tr>
			<td>Phone number</td>
			<td>${user.phoneNumber}</td>
		</tr>
		<tr>
			<td>User role</td>
			<td>${user.role}</td>
		</tr>
	</table>
	<p></p>
	<table>
		<thead>
			<tr>
				<td>Change email</td>
			</tr>
		</thead>
		<tr>
			<td><form name="change_email" action="FrontController" method="post">
					<input type="email" name="new_email" placeholder="new email" required> 
					<input type="hidden" name="command" value="change_email"> 
					<input type="submit" value="change email"> ${change_email_message}
				</form></td>
		</tr>
	</table>
	<p>
	<table>
		<thead>
			<tr>
				<td>Change password</td>
			</tr>
		</thead>
		<tr>
			<td>
				<form name="change_password" action="FrontController" method="post">
					<input type="password" name="old_password"
						placeholder="old password" required> <input
						type="password" name="new_password" placeholder="new password"
						required> <input type="hidden" name="command"
						value="change_password"> <input type="submit"
						value="change password"> ${change_password_message}
				</form>
			</td>
		</tr>
	</table>

</body>
</html>