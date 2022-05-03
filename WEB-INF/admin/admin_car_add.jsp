<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="car_add_title.car_add" var="car_add_title"/>
<fmt:message key="add_button.car_add" var="add_button"/>
<fmt:message key="VIN.car" var="VIN"/>
<fmt:message key="NUMBER_PLATE.car" var="NUMBER_PLATE"/>
<fmt:message key="CAR_MAKER.car" var="CAR_MAKER"/>
<fmt:message key="MODEL.car" var="MODEL"/>
<fmt:message key="ENGINE_TYPE.car" var="ENGINE_TYPE"/>
<fmt:message key="DRIVE_TYPE.car" var="DRIVE_TYPE"/>
<fmt:message key="POWER.car" var="POWER"/>
<fmt:message key="price_per_day.common" var="price_per_day"/>
<fmt:message key="PETROL.common" var="PETROL_lable"/>
<fmt:message key="DIESEL.common" var="DIESEL_lable"/>
<fmt:message key="ELECTRIC.common" var="ELECTRIC_lable"/>
<fmt:message key="HYDROGEN.common" var="HYDROGEN_lable"/>
<fmt:message key="AWD.common" var="AWD_lable"/>
<fmt:message key="REAR.common" var="REAR_lable"/>
<fmt:message key="FRONT.common" var="FRONT_lable"/>
<!DOCTYPE html>
<html>
<head>
<title>${car_add_title}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left: 2.5%;">${car_add_title}</p>
	<form action="FrontController" method="post">
		<input type="hidden" name="command" value="create_car">
		<input type="hidden" name="current_page" value="${current_page}">
		<table style="with: 50%">

			<tr>
				<td>${VIN}</td>
				<td><input type="text" name="vin" required /></td>
			</tr>
			<tr>
				<td>${NUMBER_PLATE}</td>
				<td><input type="text" name="number_plate" required /></td>
			</tr>
			<tr>
				<td>${CAR_MAKER}</td>
				<td><input type="text" name="car_maker" required /></td>
			</tr>
			<tr>
				<td>${MODEL}</td>
				<td><input type="text" name="car_model" required /></td>
			</tr>
			<tr>
				<td>${ENGINE_TYPE}</td>
				<td><select name="engine_type" size="4" required>
						<option value="PETROL">${PETROL_lable}</option>
						<option value="DIESEL">${DIESEL_lable}</option>
						<option value="ELECTRIC">${ELECTRIC_lable}</option>
						<option value="HYDROGEN">${HYDROGEN_lable}</option>
				</select></td>
			</tr>
			<tr>
				<td>${DRIVE_TYPE}</td>
				<td><select name="drive_type" size="3" required>
						<option value="FRONT">${FRONT_lable}</option>
						<option value="REAR">${REAR_lable}</option>
						<option value="AWD">${AWD_lable}</option>
				</select></td>
			</tr>
			<tr>
				<td>${POWER}</td>
				<td><input type="number" name="power" min="1" max="1500"
					step="1" required /></td>
			</tr>
			<tr>
				<td>${price_per_day}</td>
				<td><input type="number" name="price" min="0.01" max="999999"
					step="0.01" required /></td>
			</tr>
			<tr>
				<td>${result_message}</td>
				<c:remove var="result_message"/>
				<td><input type="submit" value="${add_button}" /></td>
			</tr>
		</table>
	</form>
</body>
</html>