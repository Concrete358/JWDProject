<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>ADD CAR PAGE</title>
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
<h3> CREATE NEW CAR </h3>
<form action="FrontController" method="post">
		<input type="hidden" name="command" value="create_car">
		<table style="with: 50%">

			<tr>
				<td>VIN</td>
				<td><input type="text" name="vin" required/></td>
			</tr>
			<tr>
				<td>NUMBER PLATE</td>
				<td><input type="text" name="number_plate" required/></td>
			</tr>
			<tr>
				<td>CAR MAKER</td>
				<td><input type="text" name="car_maker" required/></td>
			</tr>
			<tr>
				<td>CAR MODEL</td>
				<td><input type="text" name="car_model" required/></td>
			</tr>
			<tr>
				<td>ENGINE TYPE</td>
				<td><select name="engine_type" size="4" required>
				<option value="PETROL">PETROL</option>
				<option value="DIESEL">DIESEL</option>
				<option value="ELECTRIC">ELECTRIC</option>
				<option value="HYDROGEN">HYDROGEN</option>
				</select></td>
			</tr>
			<tr>
				<td>DRIVE TYPE</td>
				<td><select name="drive_type" size="3" required>
				<option value="FRONT">FRONT</option>
				<option value="REAR">REAR</option>
				<option value="AWD">AWD</option>
				</select></td>
			</tr>
			<tr>
				<td>POWER</td>
				<td><input type="number" name="power" min="1" max="1500" step="1" required/></td>
			</tr>
			<tr>
				<td>PRICE</td>
				<td><input type="number" name="price" min="0.01" max="999999" step="0.01" required/></td>
			</tr>
			<tr>
				<td>${result_message}</td><td><input type="submit" value="ADD"/></td> 
			</tr>
		</table>
	</form>
	<p>
		<a href=FrontController?command=go_to_admin_request_list_page>to
			request list</a>
	</p>
	<p>
		<a href=FrontController?command=go_to_admin_order_list_page>to
			order list</a>
	</p>
	<p>
		<a href=FrontController?command=go_to_main_page>main page</a>
	</p>
</body>
</html>