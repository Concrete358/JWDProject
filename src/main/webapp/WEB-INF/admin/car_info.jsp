<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${car.carMaker} ${car.carModel} INFO PAGE</title>
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
	<p style="margin-left:2.5%">Car info page
	<h2>${error_message}</h2>
	<table cellpadding="1" border="1" width="95%">
		<tr>
			<td>ID</td>
			<td>${car.id}</td>
			<td rowspan="9"><img src="images/cars/${car.id}/1.jpg" style="width:100%"></td>
			<td rowspan="9"><img src="images/cars/${car.id}/2.jpg" style="width:100%"></td>
			<td rowspan="9"><img src="images/cars/${car.id}/3.jpg" style="width:100%"></td>
			<td rowspan="9"><img src="images/cars/${car.id}/4.jpg" style="width:100%"></td>
		</tr>
		<tr>
			<td>VIN</td>
			<td>${car.vinNumber}</td>
		</tr>
		<tr>
			<td>NUMBER PLATE</td>
			<td>${car.numberPlate}</td>
		</tr>
		<tr>
			<td>CAR MAKER</td>
			<td>${car.carMaker}</td>
		</tr>
		<tr>
			<td>MODEL</td>
			<td>${car.carModel}</td>
		</tr>
		<tr>
			<td>ENGINE TYPE</td>
			<td>${car.engineType}</td>
		</tr>
		<tr>
			<td>DRIVE TYPE</td>
			<td>${car.drivenWheels}</td>
		</tr>
		<tr>
			<td>POWER</td>
			<td>${car.power} HP</td>
		</tr>
		<tr>
			<td>PRICE PER DAY</td>
			<td>${car.pricePerDay} BYN</td>
		</tr>
	</table>
	
	<p style="margin-left:2.5%">Booking schedule</p>
<table cellpadding="1" border="1" width="35%">	
	<tr><td>ORDER №</td>
		<td>START DATE</td>
		<td>FINISH DATE</td>
	</tr>
	<c:forEach items="${order_list}" var="order">
		<tr>
		<td>${order.orderId}</td>
		<td>${order.rentStartDate}</td>
		<td>${order.rentFinishDate}</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>