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
	<p style="margin-left:2.5%">
	<table border="1" width="30%">
		<thead><tr><td colspan="2" align="center"> REPAIR ORDER INFO </td></tr></thead>
			<tr><td>№</td><td>${order.repairOrderId}</td></tr>
			<tr><td>ORDER</td><td>${order.orderId}</td></tr>
			<tr><td>REPAIR COST</td><td> ${order.repairCost} </td></tr>
			<tr><td>DAMAGE DESCRIPTION</td><td>${order.damageDescription}</td></tr>
	</table>

<p style="margin-left:2.5%">EDIT DAMAGE DESCRIPTION AND REPAIR COST
<form action="FrontController" method="post">
		<input type="hidden" name="command" value="repair_order_edit">
		<input type="hidden" name="repair_order_id" value="${order.repairOrderId}">
		<input type="hidden" name="order_id" value="${order.orderId}">
		
		<table style="with: 40%">
			<tr>
				<td align="center">DAMAGE DESCRIPTION</td>
				<td align="center">REPAIR COST</td>
			</tr>
			<tr>
			<td><textarea required name="damage_description"></textarea></td>
			<td><input required type="number" min="0.01" step="0.01" name="repair_cost"> BYN </td>	
			</tr>
			<tr>
				<td><input type="submit" value="edit"/></td>
			</tr>
			</table>
			<p style="margin-left:2.5%">${repair_order_edit_message}
</form>
	<p>
		<a href="FrontController?command=go_to_admin_repair_order_list_page">back</a>
	</p>
</body>
</html>