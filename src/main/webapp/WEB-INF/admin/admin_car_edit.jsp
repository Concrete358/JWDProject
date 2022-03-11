<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>${car.carMaker} ${car.carModel} EDIT PAGE</title>
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
	<p style="margin-left: 2.5%;">${car.carMaker} ${car.carModel} EDIT PAGE
	<h2>${error_message}</h2>
	<table border="1" width="25%">
		<tr>
			<td>ID</td>
			<td>${car.id}</td>
		</tr>
		<tr>
			<td>VIN</td>
			<td>${car.vinNumber}</td>
			<td><form id="change_VIN" action="FrontController" method="post">
				<input type="text" name="param_value" placeholder="new VIN number" required> 
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="param_name" value="vin">
					<input type="hidden" name="car_id" value="${car.id}">
					</form>
			</td>
			<td><input form="change_VIN" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>NUMBER PLATE</td>
			<td>${car.numberPlate}</td>
			<td><form id="change_number" action="FrontController" method="post">
				<input type="text" name="param_value" placeholder="new number" required> 
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="param_name" value="number_plate">
					<input type="hidden" name="car_id" value="${car.id}">
					</form>
			</td>
			<td><input form="change_number" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>CAR MAKER</td>
			<td>${car.carMaker}</td>
			<td><form id="change_car_maker" action="FrontController" method="post">
				<input type="text" name="param_value" placeholder="new car maker" required> 
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}">
					<input type="hidden" name="param_name" value="car_maker"></form>
			</td>
			<td><input form="change_car_maker" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>MODEL</td>
			<td>${car.carModel}</td>
			<td><form id="change_car_model" action="FrontController" method="post">
				<input type="text" name="param_value" placeholder="new car model" required> 
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}">
					<input type="hidden" name="param_name" value="car_model"></form>
			</td>
			<td><input form="change_car_model" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>ENGINE TYPE</td>
			<td>${car.engineType}</td>
			<td><form id="change_engine_type" action="FrontController" method="post">
				<select name="param_value" size="4" required>
				<option value="PETROL">PETROL</option>
				<option value="DIESEL">DIESEL</option>
				<option value="ELECTRIC">ELECTRIC</option>
				<option value="HYDROGEN">HYDROGEN</option>
				</select>
					<input type="hidden" name="param_name" value="engine_type">
					<input type="hidden" name="car_id" value="${car.id}">
					<input type="hidden" name="command" value="change_car_parameter"></form>
			</td>
			<td><input form="change_engine_type" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>DRIVE TYPE</td>
			<td>${car.drivenWheels}</td>
				<td><form id="change_drive_type" action="FrontController" method="get">
				<select name="param_value" size="3" required>
				<option value="FRONT">FRONT</option>
				<option value="REAR">REAR</option>
				<option value="AWD">AWD</option>
				</select>
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}">
					<input type="hidden" name="param_name" value="drive_type">
					</form>
			</td>
			<td><input form="change_drive_type" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>POWER</td>
			<td>${car.power} HP</td>
			<td><form id="change_car_power" action="FrontController" method="post">
				<input type="number" name="param_value" placeholder="new power" required min="1" max="1500" step="1"> 
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}">
					<input type="hidden" name="param_name" value="power"></form>
			</td>
			<td><input form="change_car_power" type="submit" value="update">
				</td>
		</tr>
		<tr>
			<td>PRICE PER DAY</td>
			<td>${car.pricePerDay} BYN</td>
			<td><form id="change_car_price" action="FrontController" method="post">
				<input type="number" name="param_value" placeholder="new price" required> 
					<input type="hidden" name="command" value="change_car_parameter">
					<input type="hidden" name="car_id" value="${car.id}">
					<input type="hidden" name="param_name" value="price"></form>
			</td>
			<td><input form="change_car_price" type="submit" value="update">
				</td>
		</tr>
	</table>
</body>
</html>