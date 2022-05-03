<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="VIN.car" var="VIN"/>
<fmt:message key="NUMBER_PLATE.car" var="NUMBER_PLATE"/>
<fmt:message key="CAR_MAKER.car" var="CAR_MAKER"/>
<fmt:message key="MODEL.car" var="MODEL"/>
<fmt:message key="ENGINE_TYPE.car" var="ENGINE_TYPE"/>
<fmt:message key="DRIVE_TYPE.car" var="DRIVE_TYPE"/>
<fmt:message key="POWER.car" var="POWER"/>
<fmt:message key="price_per_day.common" var="price_per_day"/>

<fmt:message key="byn.common" var="BYN"/>
<fmt:message key="hp.common" var="HP"/>
<fmt:message key="${car.engineType}.common" var="engineType"/>
<fmt:message key="${car.drivenWheels}.common" var="drivenWheels"/>
<fmt:message key="booking_schedule_text.car_info" var="booking_schedule_text"/>
<fmt:message key="order.user_requests" var="text_1"/>
<fmt:message key="RENT_START.common" var="text_2"/>
<fmt:message key="RENT_FINISH.common" var="text_3"/>

<!DOCTYPE html>
<html>
<head>
<title>${car.carMaker}&nbsp;${car.carModel}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left: 2.5%"> <h2>${error_message}</h2>
	<table cellpadding="1" border="1" width="95%">
		<tr>
			<td>ID</td>
			<td>${car.id}</td>
			<td rowspan="9"><img src="images/cars/${car.id}/1.jpg"
				style="width: 100%"></td>
			<td rowspan="9"><img src="images/cars/${car.id}/2.jpg"
				style="width: 100%"></td>
			<td rowspan="9"><img src="images/cars/${car.id}/3.jpg"
				style="width: 100%"></td>
			<td rowspan="9"><img src="images/cars/${car.id}/4.jpg"
				style="width: 100%"></td>
		</tr>
		<tr>
			<td>${VIN}</td>
			<td>${car.vinNumber}</td>
		</tr>
		<tr>
			<td>${NUMBER_PLATE}</td>
			<td>${car.numberPlate}</td>
		</tr>
		<tr>
			<td>${CAR_MAKER}</td>
			<td>${car.carMaker}</td>
		</tr>
		<tr>
			<td>${MODEL}</td>
			<td>${car.carModel}</td>
		</tr>
		<tr>
			<td>${ENGINE_TYPE}</td>
			<td>${engineType}</td>
		</tr>
		<tr>
			<td>${DRIVE_TYPE}</td>
			<td>${drivenWheels}</td>
		</tr>
		<tr>
			<td>${POWER}</td>
			<td>${car.power} ${HP} (<ctg:HPtoKW powerHP="${car.power}"/>KW)</td>
		</tr>
		<tr>
			<td>${price_per_day}</td>
			<td>${car.pricePerDay} ${BYN}</td>
		</tr>
	</table>

	<p style="margin-left: 2.5%">${booking_schedule_text}</p>
	<table cellpadding="1" border="1" width="35%">
		<tr>
			<td>${text_1} №</td>
			<td>${text_2}</td>
			<td>${text_3}</td>
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