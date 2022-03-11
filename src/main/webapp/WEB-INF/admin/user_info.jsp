<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>${user.name} ${user.lastname} - INFO</title>
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
	<p style="margin-left:2.5%">User info page
	<h2>${error_message}</h2>
	<table border="1" width="25%">
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
</body>
</html>