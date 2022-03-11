<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Order list</title>
<style> table {
	margin-left:2.5%;
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
	<h2 align="right">${approve_message}</h2>
	<table cellpadding="1" border="1" width="95%">
		<thead>
		<tr><td colspan="13">LIST OF ORDERS</td></tr>
		<tr>
			<td>№</td>
			<td>PERSON</td>
			<td>CAR</td>
			<td>CAR PRICE</td>
			<td>REQUEST</td>
			<td>START</td>
			<td>FINISH</td>
			<td>RENT COST</td>
			<td>DAMAGED</td>
			<td>REPAIR ORDER</td>
			<td>REPAIR COST</td>
			<td>TOTAL COST</td>
			<td>STATUS</td>
		</tr>
		</thead>
		<c:forEach items="${listOfOrders}" var="order">
			<tr>
				<td>${order.orderId}</td>
				<td>${order.userName}</td>
				<td>${order.carName}</td>
				<td>${order.carPrice}</td>
				<td>${order.requestId}</td>
				<td>${order.rentStartDate}</td>
				<td>${order.rentFinishDate}</td>
				<td>${order.rentCost}</td>
				<td><c:choose>
						<c:when test="${order.damageStatus == 'false'}">
							<h4 style="color: blue">NO DAMAGE</h4>
						</c:when>
						<c:when test="${order.damageStatus == 'true'}">
							<h4 style="color: red">DAMAGED</h4>
						</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${order.repairOrderId == 0}">
							<h4 style="color: blue">NO DAMAGE</h4>
						</c:when>
						<c:otherwise>
							<h4>${order.repairOrderId}</h4>
						</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${empty order.repairCost}">
							<h4 style="color: blue">NO DAMAGE</h4>
						</c:when>
						<c:otherwise>
							<h4>${order.repairCost}</h4>
						</c:otherwise>
					</c:choose></td>
				<td>${order.totalCost}</td>
				<td>${order.orderStatus}</td>
			</tr>
		</c:forEach>
	</table>
	<p style="margin-left:2.5%">
	<c:set var="page" scope="page"
		value="FrontController?command=go_to_user_order_list_page" />
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