<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${car.carMaker}${car.carModel} INFO PAGE</title>
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
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left:2.5%">ORDER №${order.orderId}
	<table border="1" width="25%">
		<tr>
			<td>ID</td>
			<td>${order.orderId}</td>
		</tr>
		<tr>
			<td>User ID</td>
			<td>${order.userId}</td>
		</tr>
		<tr>
			<td>User Name</td>
			<td>${order.userName}</td>
		</tr>
		<tr>
			<td>Car ID</td>
			<td>${order.carId}</td>
		</tr>
		<tr>
			<td>Car model</td>
			<td>${order.carName}</td>
		</tr>
		<tr>
			<td>Car price</td>
			<td>${order.carPrice}</td>
		</tr>
		<tr>
			<td>Request ID</td>
			<td>${order.requestId}</td>
		</tr>
		<tr>
			<td>Rent start date</td>
			<td>${order.rentStartDate}</td>
		</tr>
		<tr>
			<td>Rent finish date</td>
			<td>${order.rentFinishDate}</td>
		</tr>
		<tr>
			<td>Rent cost</td>
			<td>${order.rentCost}</td>
		</tr>
		<tr>
			<td>Damage</td>
			<td><c:choose>
					<c:when test="${order.damageStatus == 'false'}">
							NO DAMAGE
						</c:when>
					<c:when test="${order.damageStatus == 'true'}">
							DAMAGED
						</c:when>
				</c:choose></td>
		</tr>
		<tr>
			<td>Repair order id</td>
			<td><c:choose>
					<c:when test="${order.repairOrderId == 0}">
							NO DAMAGE
						</c:when>
					<c:otherwise>
							${order.repairOrderId}
						</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td>Repair cost</td>
			<td><c:choose>
					<c:when test="${empty order.repairCost}">
							NO DAMAGE
						</c:when>
					<c:otherwise>
							${order.repairCost} BYN
						</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td>Total cost</td>
			<td>${order.totalCost}</td>
		</tr>
		<tr>
			<td>Order status</td>
			<td>${order.orderStatus}</td>
		</tr>
	</table>
	<p>
	<h4 style="color: blue">${order_active_message}</h4>
	<p>
	<h4 style="color: blue">${close_order_message}</h4>

	<table>
		<tr>
			<td>SET ORDER ACTIVE</td>
			<td>CLAIM DAMAGE</td>
			<td>CLOSE ORDER</td>
		</tr>
		<tr>
			<td align="center">
				<form action="FrontController" method="post">
					<input type="hidden" name="command" value="set_order_active">
					<input type="hidden" name="order_id" value="${order.orderId}">
					<input type="hidden" name="car_status" value="2"> <input
						type="submit" value="activate">
				</form>
			</td>
			<td>
				<form action="FrontController" method="get">
					<input type="hidden" name="order_id" value="${order.orderId}">
					<input type="hidden" name="command"
						value="go_to_repair_order_create_page"> <input
						type="submit" value="claim damage">
				</form>
			</td>
			<td><form action="FrontController" method="post">
					<input type="hidden" name="command" value="close_order"> <input
						type="hidden" name="order_id" value="${order.orderId}"> <input
						type="submit" value="close order">
				</form></td>
		</tr>
	</table>
</body>
</html>