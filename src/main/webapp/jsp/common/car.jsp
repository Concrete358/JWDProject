<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<fmt:message key="rent_cost.order_list" var="rent_cost"/>
<fmt:message key="byn.common" var="BYN"/>
<fmt:message key="hp.common" var="HP"/>
<fmt:message key="${car.engineType}.common" var="engineType"/>
<fmt:message key="${car.drivenWheels}.common" var="drivenWheels"/>
<fmt:message key="text_1.car" var="text_1"/>
<fmt:message key="RENT_START.common" var="text_2"/>
<fmt:message key="RENT_FINISH.common" var="text_3"/>
<fmt:message key="send_request.button" var="text_4"/>
<c:if test="${not empty RequestMessage}">
<fmt:message key="${RequestMessage}" var="request_message"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<style>
   <%@include file='/сss/style.css' %>
</style>
<title>${car.carMaker}&nbsp${car.carModel}</title>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p></p>
	<table border="1" width="30%">
		<thead>
			<tr>
				<td colspan=2 align="center">${car.carMaker}&nbsp${car.carModel}</td>
			</tr>
		</thead>
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
			<td>${car.power}${HP} (<ctg:HPtoKW powerHP="${car.power}"/>KW)</td>
		</tr>
		<tr>
			<td>${price_per_day}</td>
			<td>${car.pricePerDay} ${BYN}</td>
		</tr>
	</table>

	<div>
		<c:if test="${role == 'USER' || role == 'ADMIN'}">					
			<p style="margin-left: 2.5%">${text_1}</p>
			<form name="period" action="FrontController" method="post">
				<input type="hidden" name="command" value="request_generate">
				<input type="hidden" name="current_page" value="${current_page}">
				<input type="hidden" name="user_id" value="${user_id}"> 
				<input type="hidden" name="car_id" value="${car.id}"> 
				<input type="hidden" name="car_price" value="${car.pricePerDay}">
				<table style="width: 30%">
					<tr>
						<td>${text_2}</td>
						<td><input required type="date" name="rent_start_date" /></td>
					</tr>
					<tr>
						<td>${text_3}</td>
						<td><input required type="date" name="rent_finish_date" /></td>
					</tr>
					<tr><td>${rent_cost}</td><td>
					<span id="rent_cost_value"></span>
  					</td></tr>
					<tr>
						<td><input type="submit" value="${text_4}" /></td>
					</tr>
					<script>
    					let form = document.forms.period;
    					form.rent_start_date.onchange = calculate;
    					form.rent_finish_date.onchange = calculate;
    					function calculate() {
    						let result = Math.ceil((new Date(form.rent_finish_date.value) - new Date(form.rent_start_date.value))/(1000 * 60 * 60 * 24)) * ${car.pricePerDay};
    					if(isNaN(result) || result <= 0){
    						document.getElementById('rent_cost_value').innerHTML = "";
    						return;    						
    					}	
      						document.getElementById('rent_cost_value').innerHTML = result;
    					}
    				calculate();
  					</script>
  					
				</table>
			</form>
			<p style="margin-left: 2.5%">
				
				${request_message}
				<c:remove var="RequestMessage" />
		</c:if>
	</div>

	<div>
		<p style="margin-left: 2.5%">
			<jsp:include page="/jsp/common/cars_image_gallery.jsp" />
		</p>
	</div>
</body>
</html>