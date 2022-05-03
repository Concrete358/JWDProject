<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="table_head.order_list" var="table_head"/>
<fmt:message key="client.order_list" var="client_lable"/>
<fmt:message key="car.order_list" var="car_lable"/>
<fmt:message key="price.order_list" var="price_lable"/>
<fmt:message key="request.order_list" var="request_lable"/>
<fmt:message key="start.order_list" var="start_lable"/>
<fmt:message key="finish.order_list" var="finish_lable"/>
<fmt:message key="rent_cost.order_list" var="rent_cost_lable"/>
<fmt:message key="damaged.order_list" var="damaged_lable"/>
<fmt:message key="repair_order.order_list" var="repair_order_lable"/>
<fmt:message key="repair_cost.order_list" var="repair_cost_lable"/>
<fmt:message key="total_cost.order_list" var="total_cost_lable"/>
<fmt:message key="status.order_list" var="status_lable"/>
<fmt:message key="previous.common" var="previous"/>
<fmt:message key="next.common" var="next"/>
<fmt:message key="no_damage_message.order_list" var="no_damage_message"/>
<fmt:message key="damaged_message.order_list" var="damaged_message"/>
<!DOCTYPE html>
<html>
<head>
<title>${table_head}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p>${dao_error_message}</p>
	<table cellpadding="1" border="1" width="95%">
		<thead>
			<tr >
				<td colspan="13">${table_head}</td>
			</tr>
			<tr align="center">
				<td>№</td>
				<td>${client_lable}</td>
				<td>${car_lable}</td>
				<td>${price_lable}</td>
				<td>${request_lable}</td>
				<td>${start_lable}</td>
				<td>${finish_lable}</td>
				<td>${rent_cost_lable}</td>
				<td>${damaged_lable}</td>
				<td>${repair_order_lable}</td>
				<td>${repair_cost_lable}</td>
				<td>${total_cost_lable}</td>
				<td>${status_lable}</td>
			</tr>
		</thead>
		<c:forEach items="${listOfOrders}" var="order">
			<tr align="center">
				<td>${order.orderId}</td>
				<td>${order.userName}</td>
				<td>${order.carName}</td>
				<td>${order.carPrice}</td>
				<td>${order.requestId}</td>
				<td>${order.rentStartDate}</td>
				<td>${order.rentFinishDate}</td>
				<td>${order.rentCost}</td>
				<td><c:choose>
						<c:when test="${order.damageStatus == 'false'}">
							<h4 style="color: blue">${no_damage_message}</h4>
						</c:when>
						<c:when test="${order.damageStatus == 'true'}">
							<h4 style="color: red">${damaged_message}</h4>
						</c:when>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${order.repairOrderId == 0}">
							<h4 style="color: blue">${no_damage_message}</h4>
						</c:when>
						<c:otherwise>
							<h4>${order.repairOrderId}</h4>
						</c:otherwise>
					</c:choose></td>
				<td><c:choose>
						<c:when test="${empty order.repairCost}">
							<h4 style="color: blue">${no_damage_message}</h4>
						</c:when>
						<c:otherwise>
							<h4>${order.repairCost}</h4>
						</c:otherwise>
					</c:choose></td>
				<td>${order.totalCost}</td>
				<td>${order.orderStatus}</td>
			</tr>
		</c:forEach>
	</table>
	<p style="margin-left: 2.5%">
		<c:set var="page" scope="page"
			value="FrontController?command=go_to_user_order_list_page" />
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
</body>
</html>