<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="ORDER.order_info" var="order_head"/>
<fmt:message key="order_number.order_info" var="order_number_text"/>
<fmt:message key="user_id.order_info" var="user_id_text"/>
<fmt:message key="client.order_list" var="client_lable"/>
<fmt:message key="car_id.order_info" var="car_id_text"/>
<fmt:message key="car.order_list" var="car_lable"/>
<fmt:message key="price.order_list" var="price_lable"/>
<fmt:message key="request_id.order_info" var="request_id_text"/>
<fmt:message key="start.user_requests" var="start_lable"/>
<fmt:message key="finish.user_requests" var="finish_lable"/>
<fmt:message key="rent_cost.order_list" var="rent_cost_lable"/>
<fmt:message key="damaged.order_list" var="damaged_lable"/>
<fmt:message key="repair_order_id.order_info" var="repair_order_id_text"/>
<fmt:message key="repair_cost.order_list" var="repair_cost_lable"/>
<fmt:message key="total_cost.order_list" var="total_cost_lable"/>
<fmt:message key="status.order_list" var="status_lable"/>
<fmt:message key="no_damage_message.order_list" var="no_damage_message"/>
<fmt:message key="damaged_message.order_list" var="damaged_message"/>
<fmt:message key="byn.common" var="BYN"/>
<fmt:message key="set_order_active.order_info" var="set_order_active_text"/>
<fmt:message key="claim_damage.order_info" var="claim_damage_text"/> 
<fmt:message key="close_order.order_info" var="close_order_text"/> 
<fmt:message key="activate_button.order_info" var="activate_button"/>
<fmt:message key="claim_button.order_info" var="claim_button"/>
<fmt:message key="close_button.order_info" var="close_button"/>
<c:if test="${not empty order_active_message}">
<fmt:message key="${order_active_message}" var="activate_message_text"/>
</c:if>
<c:if test="${not empty close_order_message}">
<fmt:message key="${close_order_message}" var="close_message_text"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>${order_head}&nbsp;${order.orderId}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p>
	<table border="1" width="25%">
		<tr>
			<td>${order_number_text}</td>
			<td>${order.orderId}</td>
		</tr>
		<tr>
			<td>${user_id_text}</td>
			<td>${order.userId}</td>
		</tr>
		<tr>
			<td>${client_lable}</td>
			<td>${order.userName}</td>
		</tr>
		<tr>
			<td>${car_id_text}</td>
			<td>${order.carId}</td>
		</tr>
		<tr>
			<td>${car_lable}</td>
			<td>${order.carName}</td>
		</tr>
		<tr>
			<td>${price_lable}</td>
			<td>${order.carPrice}</td>
		</tr>
		<tr>
			<td>${request_id_text}</td>
			<td>${order.requestId}</td>
		</tr>
		<tr>
			<td>${start_lable}</td>
			<td>${order.rentStartDate}</td>
		</tr>
		<tr>
			<td>${finish_lable}</td>
			<td>${order.rentFinishDate}</td>
		</tr>
		<tr>
			<td>${rent_cost_lable}</td>
			<td>${order.rentCost}</td>
		</tr>
		<tr>
			<td>${damaged_lable}</td>
			<td><c:choose>
					<c:when test="${order.damageStatus == 'false'}">
							${no_damage_message}
						</c:when>
					<c:when test="${order.damageStatus == 'true'}">
							${damaged_message}
						</c:when>
				</c:choose></td>
		</tr>
		<tr>
			<td>${repair_order_id_text}</td>
			<td><c:choose>
					<c:when test="${order.repairOrderId == 0}">
							${no_damage_message}
						</c:when>
					<c:otherwise>
							${order.repairOrderId}
						</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td>${repair_cost_lable}</td>
			<td><c:choose>
					<c:when test="${empty order.repairCost}">
							${no_damage_message}
						</c:when>
					<c:otherwise>
							${order.repairCost} ${BYN}
						</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td>${total_cost_lable}</td>
			<td>${order.totalCost}</td>
		</tr>
		<tr>
			<td>${status_lable}</td>
			<td>${order.orderStatus}</td>
		</tr>
	</table>
	<p>
	<h4 style="color: blue">${activate_message_text}</h4>
	<c:remove var="order_active_message" />
	<p>
	<h4 style="color: blue">${close_message_text}</h4>
	<c:remove var="close_order_message" />
	<table>
		<tr align="center">
			<td>${set_order_active_text}</td>
			<td>${claim_damage_text}</td>
			<td>${close_order_text}</td>
		</tr>
		<tr align="center">
			<td>
				<form action="FrontController" method="post">
					<input type="hidden" name="command" value="set_order_active">
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="order_id" value="${order.orderId}">
					<input type="hidden" name="car_status" value="2"> <input
						type="submit" value="${activate_button}">
				</form>
			</td>
			<td>
				<form action="FrontController" method="get">
					<input type="hidden" name="order_id" value="${order.orderId}">
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="command" value="go_to_repair_order_create_page"> 
					<input type="submit" value="${claim_button}">
				</form>
			</td>
			<td><form action="FrontController" method="post">
					<input type="hidden" name="command" value="close_order">
					<input type="hidden" name="current_page" value="${current_page}">
					<input type="hidden" name="order_id" value="${order.orderId}"> 
					<input type="submit" value="${close_button}">
				</form></td>
		</tr>
	</table>
<c:remove var="current_page" />	
</body>
</html>