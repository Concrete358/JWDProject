<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>main</title>
<style> table {
	margin-left: 2.5%;
	margin-right: 20%;
	border-collapse: collapse;
    font-family: Tahoma, Geneva, sans-serif;
}
table td {
	padding: 10px;
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
	border: 1px solid #54585d;
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
	<table> 
	<thead><tr><td colspan="5">OUR CARS</td></tr></thead>
	<c:forEach items="${listOfCars}" var="car">
	<tr >
	<td width="30%" ><a href="FrontController?command=go_to_car_page&id=${car.id}">
		${car.carMaker} ${car.carModel} ( ${car.power}HP ) - ${car.drivenWheels} - price per day ${car.pricePerDay} BYN
		</a>
	</td>
	<td><img src="images/cars/${car.id}/1.jpg" style="width:100%" ></td>
	<td><img src="images/cars/${car.id}/2.jpg" style="width:100%" ></td>
	<td><img src="images/cars/${car.id}/3.jpg" style="width:100%" ></td>
	<td> <img src="images/cars/${car.id}/4.jpg" style="width:100%"></td>
	</tr>
	</c:forEach>
	</table>

</body>
</html>