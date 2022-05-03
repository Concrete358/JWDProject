<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="list_of_cars_text.car_list" var="list_of_cars_text"/>
<fmt:message key="VIN.car" var="VIN"/>
<fmt:message key="NUMBER_PLATE.car" var="NUMBER_PLATE"/>
<fmt:message key="CAR_MAKER.car" var="CAR_MAKER"/>
<fmt:message key="MODEL.car" var="MODEL"/>
<fmt:message key="ENGINE_TYPE.car" var="ENGINE_TYPE"/>
<fmt:message key="DRIVE_TYPE.car" var="DRIVE_TYPE"/>
<fmt:message key="POWER.car" var="POWER"/>
<fmt:message key="price_per_day.common" var="price_per_day"/>
<fmt:message key="previous.common" var="previous"/>
<fmt:message key="next.common" var="next"/>
<fmt:message key="block_button.car_list" var="block_button"/>
<fmt:message key="activate_button.car_list" var="activate_button"/>
<fmt:message key="new_car_button.car_list" var="new_car_button"/>
<fmt:message key="edit_link.car_list" var="edit_link"/>
<fmt:message key="block_status_text.car_list" var="block_status_text"/>
<fmt:message key="active_text.car_list" var="active_text"/>
<fmt:message key="blocked_text.car_list" var="blocked_text"/>
<!DOCTYPE html>
<html>
<head>
<title>${list_of_cars_text}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p>${dao_error_message}</p>
	<table b width="90%">
		<thead>
			<tr>
				<td colspan="11">${list_of_cars_text}</td>
				<td  align="center"><form action="FrontController" method="GET">
				<input type="hidden" name="command" value="GO_TO_ADMIN_CAR_ADD_PAGE">
				<input type="submit" value="${new_car_button}" /></form></td>
			</tr>
			<tr align="center">
				<td>ID</td>
				<td>${CAR_MAKER}</td>
				<td>${MODEL}</td>
				<td>${VIN}</td>
				<td>${NUMBER_PLATE}</td>
				<td>${ENGINE_TYPE}</td>
				<td>${DRIVE_TYPE}</td>
				<td>${POWER}</td>
				<td>${price_per_day}</td>
				<td>${block_status_text}</td>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<c:forEach items="${listOfCars}" var="car">
		<fmt:message key="${car.engineType}.common" var="engineType"/>
		<fmt:message key="${car.drivenWheels}.common" var="drivenWheels"/>
			<tr align="center">
				<td>${car.id}</td>
				<td>${car.carMaker}</td>
				<td>${car.carModel}</td>
				<td>${car.vinNumber}</td>
				<td>${car.numberPlate}</td>
				<td>${engineType}</td>
				<td>${drivenWheels}</td>
				<td>${car.power}</td>
				<td>${car.pricePerDay}</td>
				<td><c:choose>
						<c:when test="${car.block == 'false'}">
							<h4 style="color: blue">${active_text}</h4>
						</c:when>
						<c:when test="${car.block == 'true'}">
							<h4 style="color: red">${blocked_text}</h4>
						</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${car.block == 'false'}">
							<form action="FrontController" method="post">
								<input type="hidden" name="command" value="change_car_block">
								<input type="hidden" name="current_page" value="${current_page}">
								<input type="hidden" name="car_id" value="${car.id}"> 
								<input type="hidden" name="block" value="true"> 
								<input type="submit" value="${block_button}">
							</form>
						</c:when>
						<c:when test="${car.block == 'true'}">
							<form action="FrontController" method="post">
								<input type="hidden" name="command" value="change_car_block">
								<input type="hidden" name="current_page" value="${current_page}">
								<input type="hidden" name="car_id" value="${car.id}"> 
								<input type="hidden" name="block" value="false"> <input
									type="submit" value="${activate_button}">
							</form>
						</c:when>
					</c:choose></td>
				<td><a
					href="FrontController?command=go_to_admin_car_edit_page&car_id=${car.id}">${edit_link}</a></td>
			</tr>
		</c:forEach>
	</table>
	<p style="margin-left: 2.5%">
		<c:set var="page" scope="page"
			value="FrontController?command=go_to_admin_car_list_page" />
		<c:if test="${listPage != 1}">
			<a
				href="${page}&recordsPerPage=${recordsPerPage}&listPage=${listPage-1}">${previous}</a>
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
				href="${page}&recordsPerPage=${recordsPerPage}&listPage=${listPage+1}">${next}</a>
		</c:if>
	<p style="margin-left: 2.5%">

</body>
</html>