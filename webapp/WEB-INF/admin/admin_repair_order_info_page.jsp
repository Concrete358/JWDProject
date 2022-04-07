<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="repair_bill_text.repair_order" var="repair_bill_text"/>
<fmt:message key="edit_damage_text.repair_order" var="edit_damage_text"/>
<fmt:message key="text_2.repair_info" var="text_2"/>
<fmt:message key="text_3.repair_info" var="text_3"/>
<fmt:message key="edit.user_requests" var="edit_text"/>
<fmt:message key="repair_cost.order_list" var="repair_cost_lable"/>
<fmt:message key="text_2.repair_info" var="damage_description_text"/>
<fmt:message key="rent_order.repair_info" var="order_lable"/>
<fmt:message key="byn.common" var="BYN"/>
<c:if test="${not empty repair_order_edit_message}">
<fmt:message key="${repair_order_edit_message}" var="repair_order_edit_message_text"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>${repair_bill_text}</title>
<style>
table {
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
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p>
	<table border="1" width="30%">
		<thead>
			<tr>
				<td colspan="2" align="center">${repair_bill_text}</td>
			</tr>
		</thead>
		<tr>
			<td>№</td>
			<td>${order.repairOrderId}</td>
		</tr>
		<tr>
			<td>${order_lable}</td>
			<td>${order.orderId}</td>
		</tr>
		<tr>
			<td>${repair_cost_lable}</td>
			<td>${order.repairCost}</td>
		</tr>
		<tr>
			<td>${damage_description_text}</td>
			<td>${order.damageDescription}</td>
		</tr>
	</table>

	<p style="margin-left: 2.5%">${edit_damage_text}
	<form action="FrontController" method="post">
		<input type="hidden" name="current_page" value="${current_page}">
		<input type="hidden" name="command" value="repair_order_edit">
		<input type="hidden" name="repair_order_id"
			value="${order.repairOrderId}"> <input type="hidden"
			name="order_id" value="${order.orderId}">

		<table style="with: 40%">
			<tr>
				<td align="center">${text_2}</td>
				<td align="center">${text_3}</td>
			</tr>
			<tr>
				<td><textarea required name="damage_description"></textarea></td>
				<td><input required type="number" min="0.01" step="0.01"
					name="repair_cost"> ${BYN}</td>
			</tr>
			<tr>
				<td><input type="submit" value="${edit_text}" /></td>
			</tr>
		</table>
		<p style="margin-left: 2.5%">${repair_order_edit_message_text}
		<c:remove var="repair_order_edit_message"/>
	</form>
</body>
</html>