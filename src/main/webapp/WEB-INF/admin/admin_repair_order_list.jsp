<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Repair order list</title>
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
	<p>${dao_error_message}</p>
	<table border="1" width="45%">
		<thead>
		<tr><td colspan="4">LIST OF REPAIR ORDERS</td></tr>
		<tr>
			<td>№</td>
			<td>ORDER</td>
			<td>REPAIR COST</td>
			<td>DAMAGE DESCRIPTION</td>
		</tr>
		</thead>
		<c:forEach items="${listOfRepairOrders}" var="order">
			<tr>
				<td><a	href="FrontController?command=go_to_repair_order_info_page&repair_order_id=${order.repairOrderId}">${order.repairOrderId}</a></td>
				<td><a	href="FrontController?command=go_to_admin_order_page&order_id=${order.orderId}">${order.orderId}</a></td>
				<td> ${order.repairCost} </td>
				<td>${order.damageDescription}</td>
		</c:forEach>
	</table>
	<p style="margin-left:2.5%">
	<c:set var="page" scope="page"
		value="FrontController?command=go_to_admin_repair_order_list_page" />
	<c:if test="${listPage != 1}">
		<a
			href="${page}&recordsPerPage=${recordsPerPage}&listPage=${listPage-1}">Previous</a>
	</c:if>

	<c:forEach begin="1" end="${numberOfPages}" var="i">
		<c:choose>
			<c:when test="${i eq listPage}">
				<a href="${page}&recordsPerPage=${recordsPerPage}&listPage=${i}"><big>${i}</big></a>
			</c:when>
			<c:otherwise>
				<a href="${page}&recordsPerPage=${recordsPerPage}&listPage=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:if test="${listPage lt numberOfPages}">
		<a
			href="${page}&recordsPerPage=${recordsPerPage}&listPage=${listPage+1}">Next</a>
	</c:if>
</body>
</html>