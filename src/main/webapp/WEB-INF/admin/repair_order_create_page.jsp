<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>REPAIR ORDER</title>
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
<p style="margin-left:2.5%">DESCRIBE DAMAGE AND REPAIR COST
<form action="FrontController" method="post">
		<input type="hidden" name="command" value="repair_order_create">
		<input type="hidden" name="order_id" value="${order_id}">
		
		<table style="with: 60%">
			<tr>
				<td align="center">DAMAGE DESCRIPTION</td>
				<td align="center">REPAIR COST</td>
			</tr>
			<tr>
			<td><textarea required name="damage_description"></textarea></td>
			<td><input required type="number" min="0.01" step="0.01" name="repair_cost"> BYN </td>	
			</tr>
			<tr>
				<td><input type="submit" value="create bill"/></td>
			</tr>
			</table>
			${repair_order_create_result}
</form>
	<p style="margin-left:2.5%">
		<a href="FrontController?command=go_to_admin_order_page&order_id=${order_id}">back</a>
</body>
</html>