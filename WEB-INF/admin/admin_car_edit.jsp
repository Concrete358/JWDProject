<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="ctg" uri="customtags" %>
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="i18n.pagecontent" />

<fmt:message key="VIN.car" var="VIN" />
<fmt:message key="NUMBER_PLATE.car" var="NUMBER_PLATE" />
<fmt:message key="CAR_MAKER.car" var="CAR_MAKER" />
<fmt:message key="MODEL.car" var="MODEL" />
<fmt:message key="ENGINE_TYPE.car" var="ENGINE_TYPE" />
<fmt:message key="DRIVE_TYPE.car" var="DRIVE_TYPE" />
<fmt:message key="POWER.car" var="POWER" />
<fmt:message key="price_per_day.common" var="price_per_day" />
<fmt:message key="byn.common" var="BYN" />
<fmt:message key="hp.common" var="HP" />
<fmt:message key="${car.engineType}.common" var="engineType" />
<fmt:message key="${car.drivenWheels}.common" var="drivenWheels" />
<fmt:message key="PETROL.common" var="PETROL_lable" />
<fmt:message key="DIESEL.common" var="DIESEL_lable" />
<fmt:message key="ELECTRIC.common" var="ELECTRIC_lable" />
<fmt:message key="HYDROGEN.common" var="HYDROGEN_lable" />
<fmt:message key="AWD.common" var="AWD_lable" />
<fmt:message key="REAR.common" var="REAR_lable" />
<fmt:message key="FRONT.common" var="FRONT_lable" />
<fmt:message key="update_button.car_edit" var="update_button" />
<fmt:message key="new_value_text.car_edit" var="new_value_text" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${car.carMaker}${car.carModel}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left: 2.5%;">
	<h2>${error_message}</h2>
	<table border="1" width="25%">
		<tr>
			<td>ID</td>
			<td>${car.id}</td>
		</tr>
		<tr>
			<td>${VIN}</td>
			<td>${car.vinNumber}</td>
			<td><form id="change_VIN" action="FrontController" method="post">
					<input type="text" name="param_value"
						placeholder="${new_value_text}" required> <input
						type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="param_name" value="vin"> <input
						type="hidden" name="car_id" value="${car.id}">
				</form></td>
			<td><input form="change_VIN" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${NUMBER_PLATE}</td>
			<td>${car.numberPlate}</td>
			<td><form id="change_number" action="FrontController"
					method="post">
					<input type="text" name="param_value"
						placeholder="${new_value_text}" required
						pattern="^[0-9]{4}[ ][ABCEHIKMOPT]{2}[-][1-7]"> <input
						type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="param_name" value="number_plate">
					<input type="hidden" name="car_id" value="${car.id}">
				</form></td>
			<td><input form="change_number" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${CAR_MAKER}</td>
			<td>${car.carMaker}</td>
			<td><form id="change_car_maker" action="FrontController"
					method="post">
					<input type="text" name="param_value"
						placeholder="${new_value_text}" required pattern="^[a-zA-Z0-9 ]+$">
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}"> <input
						type="hidden" name="param_name" value="car_maker">
				</form></td>
			<td><input form="change_car_maker" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${MODEL}</td>
			<td>${car.carModel}</td>
			<td><form id="change_car_model" action="FrontController"
					method="post">
					<input type="text" name="param_value" placeholder="${new_value_text}" required pattern="^[a-zA-Z0-9 ]+$"> 
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}"> 
					<input type="hidden" name="param_name" value="car_model">
				</form></td>
			<td><input form="change_car_model" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${ENGINE_TYPE}</td>
			<td>${engineType}</td>
			<td><form id="change_engine_type" action="FrontController"
					method="post">
					<select name="param_value" size="4" required>
						<option value="PETROL">${PETROL_lable}</option>
						<option value="DIESEL">${DIESEL_lable}</option>
						<option value="ELECTRIC">${ELECTRIC_lable}</option>
						<option value="HYDROGEN">${HYDROGEN_lable}</option>
					</select> <input type="hidden" name="param_name" value="engine_type">
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="car_id" value="${car.id}"> <input
						type="hidden" name="command" value="change_car_parameter">
				</form></td>
			<td><input form="change_engine_type" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${DRIVE_TYPE}</td>
			<td>${drivenWheels}</td>
			<td><form id="change_drive_type" action="FrontController"
					method="get">
					<select name="param_value" size="3" required>
						<option value="FRONT">${FRONT_lable}</option>
						<option value="REAR">${REAR_lable}</option>
						<option value="AWD">${AWD_lable}</option>
					</select> <input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="car_id" value="${car.id}"> <input
						type="hidden" name="param_name" value="drive_type">
				</form></td>
			<td><input form="change_drive_type" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${POWER}</td>
			<td>${car.power}${HP} (<ctg:HPtoKW powerHP="${car.power}"/>KW)</td>
			<td><form id="change_car_power" action="FrontController"
					method="post">
					<input type="number" name="param_value"
						placeholder="${new_value_text}" required min="1" max="1500"
						step="1"> <input type="hidden" name="command"
						value="change_car_parameter"> <input type="hidden"
						name="current_page" value="${current_page}"> <input
						type="hidden" name="car_id" value="${car.id}"> <input
						type="hidden" name="param_name" value="power">
				</form></td>
			<td><input form="change_car_power" type="submit"
				value="${update_button}"></td>
		</tr>
		<tr>
			<td>${price_per_day}</td>
			<td>${car.pricePerDay}${BYN}</td>
			<td><form id="change_car_price" action="FrontController"
					method="post">
					<input type="number" name="param_value"
						placeholder="${new_value_text}" required min="1" max="1500"
						step="0.01"> <input type="hidden" name="command"
						value="change_car_parameter"> <input type="hidden"
						name="current_page" value="${current_page}"> <input
						type="hidden" name="car_id" value="${car.id}"> <input
						type="hidden" name="param_name" value="price">
				</form></td>
			<td><input form="change_car_price" type="submit"
				value="${update_button}"></td>
		</tr>
	</table>
</body>
</html>