<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${car.carMaker} ${car.carModel}</title>
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
	<p></p>
	<table border="1" width="30%">
		<thead>
		<tr>
			<td colspan=2 align="center">${car.carMaker} ${car.carModel}</td>
		</tr>
		</thead>
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
		<p style="margin-left: 2.5%"> 
		<jsp:include page="/WEB-INF/common/cars_image_gallery.jsp"/>
	
	<c:if test="${role == 'USER' || role == 'ADMIN'}">	
	<p style="margin-left: 2.5%">CHOOSE DATES AND SEND RENT REQUEST</p>
	<form action="FrontController" method="post">
		<input type="hidden" name="command" value="request_generate">
		<input type="hidden" name="user_id" value="${user_id}">
		<input type="hidden" name="car_id" value="${car.id}">
		<input type="hidden" name="car_price" value="${car.pricePerDay}">
		<table style="with: 50%">
			<tr>
				<td>Rent start date</td>
				<td><input required type="date" name="rent_start_date" /></td>
			</tr>
			<tr>
				<td>Rent finish date</td>
				<td><input required type="date" name="rent_finish_date"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="send request" /></td>
			</tr>
			</table>
	</form>
	${RequestErrorText}
	<c:if test="${rent_request != null}">
	Your request has been submitted for review.
	To check status go to request list page.
	</c:if>
	<c:remove var="rent_request"/>
	</c:if>
</body>
</html>