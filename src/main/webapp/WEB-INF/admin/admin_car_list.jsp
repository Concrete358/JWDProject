<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Car list</title>
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
	<table b width="90%">
		<thead>
		<tr><td colspan="12">LIST OF CARS</td></tr>
		<tr align="center">
			<td>ID</td>
			<td>CAR MAKER</td>
			<td>CAR MODEL</td>
			<td> VIN </td>
			<td> NUMBER PLATE </td>
			<td>ENGINE TYPE</td>
			<td>DRIVE TYPE</td>
			<td>POWER</td>
			<td>PRICE</td>
			<td>BLOCK</td>
			<td></td>
			<td></td>
			</tr>
		</thead>
		<c:forEach items="${listOfCars}" var="car">
			<tr align="center">
				<td>${car.id}</td>
				<td>${car.carMaker}</td>
				<td>${car.carModel}</td>
				<td>${car.vinNumber}</td>
				<td>${car.numberPlate}</td>
				<td>${car.engineType}</td>
				<td>${car.drivenWheels}</td>
				<td>${car.power}</td>
				<td>${car.pricePerDay}</td>
				<td>
				<c:choose>
						<c:when test="${car.block == 'false'}">
							<h4 style="color: blue">ACTIVE</h4>
						</c:when>
						<c:when test="${car.block == 'true'}">
							<h4 style="color: red">BLOCKED</h4>
						</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${car.block == 'false'}">
							<form action="FrontController" method="post">
							<input type="hidden" name="command" value="change_car_block">
							<input type="hidden" name="car_id" value="${car.id}">
							<input type="hidden" name="block" value="true">
							<input type="submit" value="block">
							</form>
						</c:when>
						<c:when test="${car.block == 'true'}">
							<form action="FrontController" method="post">
							<input type="hidden" name="command" value="change_car_block">
							<input type="hidden" name="car_id" value="${car.id}">
							<input type="hidden" name="block" value="false">
							<input type="submit" value="activate">
							</form>
						</c:when>
					</c:choose></td>
				<td><a href = "FrontController?command=go_to_admin_car_edit_page&car_id=${car.id}"> edit </a></td>
			</tr>
		</c:forEach>
	</table>
	<p style="margin-left:2.5%">
	<c:set var="page" scope="page"
		value="FrontController?command=go_to_admin_car_list_page" />
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
	<p style="margin-left:2.5%">	
		<a href=FrontController?command=GO_TO_ADMIN_CAR_ADD_PAGE>add new car</a>
	


</body>
</html>