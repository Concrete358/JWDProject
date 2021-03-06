<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="list_of_repair_orders_text.repair_order" var="list_of_repair_orders_text"/>
<fmt:message key="repair_cost.order_list" var="repair_cost_lable"/>
<fmt:message key="text_2.repair_info" var="damage_description_text"/>
<fmt:message key="rent_order.repair_info" var="order_lable"/>
<fmt:message key="previous.common" var="previous"/>
<fmt:message key="next.common" var="next"/>
<!DOCTYPE html>
<html>
<head>
<title>${list_of_repair_orders_text}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p>${dao_error_message}</p>
	<table border="1" width="45%">
		<thead>
			<tr>
				<td colspan="4">${list_of_repair_orders_text}</td>
			</tr>
			<tr>
				<td>№</td>
				<td>${order_lable}</td>
				<td>${repair_cost_lable}</td>
				<td>${damage_description_text}</td>
			</tr>
		</thead>
		<c:forEach items="${listOfRepairOrders}" var="order">
			<tr>
				<td><a
					href="FrontController?command=go_to_repair_order_info_page&repair_order_id=${order.repairOrderId}">${order.repairOrderId}</a></td>
				<td><a
					href="FrontController?command=go_to_admin_order_page&order_id=${order.orderId}">${order.orderId}</a></td>
				<td>${order.repairCost}</td>
				<td>${order.damageDescription}</td>
		</c:forEach>
	</table>
	<p style="margin-left: 2.5%">
		<c:set var="page" scope="page"
			value="FrontController?command=go_to_admin_repair_order_list_page" />
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