<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="table_head.user_request_edit" var="table_head"/>
<fmt:message key="client.user_requests" var="client_lable"/>
<fmt:message key="car.user_requests" var="car_lable"/>
<fmt:message key="price.user_requests" var="price_lable"/>
<fmt:message key="order.user_requests" var="order_lable"/>
<fmt:message key="start.user_requests" var="start_lable"/>
<fmt:message key="finish.user_requests" var="finish_lable"/>
<fmt:message key="reviewed.user_requests" var="reviewed_lable"/>
<fmt:message key="approved.user_requests" var="approved_lable"/>
<fmt:message key="cost.user_requests" var="cost_lable"/>
<fmt:message key="reason.user_requests" var="reason_lable"/>
<fmt:message key="edit.user_requests" var="edit_lable"/>
<fmt:message key="text_1.user_request_edit" var="text1"/>
<fmt:message key="text_2.user_request_edit" var="text2"/>
<fmt:message key="no_order_message.user_requests" var="no_order_message"/>
<fmt:message key="unreviewed_message.user_requests" var="unreviewed_message"/>
<fmt:message key="reviewed_message.user_requests" var="reviewed_message"/>
<fmt:message key="approved_message.user_requests" var="approved_message"/>
<fmt:message key="rejected_message.user_requests" var="rejected_message"/>
<c:if test="${not empty request_edit_message}">
<fmt:message key="${request_edit_message}" var="request_edit_message_text"/>
</c:if>
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
	<p style="margin-left: 2.5%">
	<table border="1" width="30%">
		<thead>
			<tr>
				<td colspan="2" align="center">${table_head}</td>
			</tr>
		</thead>
		<tr>
			<td>№</td>
			<td>${request.requestId}</td>
		</tr>
		<tr>
			<td>${client_lable}</td>
			<td>${request.userName}</td>
		</tr>
		<tr>
			<td>${car_lable}</td>
			<td>${request.carName}</td>
		</tr>
		<tr>
			<td>${price_lable}</td>
			<td>${request.carPrice}</td>
		</tr>
		<tr>
			<td>${order_lable}</td>
			<td><c:choose>
					<c:when test="${request.orderId == 0}">
						<h4 style="color: blue">${no_order_message}</h4>
					</c:when>
					<c:otherwise>${request.orderId}</c:otherwise>
				</c:choose></td>
		</tr>
		<tr>
			<td>${start_lable}</td>
			<td>${request.rentStartDate}</td>
		</tr>
		<tr>
			<td>${finish_lable}</td>
			<td>${request.rentFinishDate}</td>
		</tr>
		<tr>
			<td>${reviewed_lable}</td>
			<td><c:choose>
					<c:when test="${request.reviewStatus == 'false'}">
						<h4 style="color: blue">${unreviewed_message}</h4>
					</c:when>
					<c:when test="${request.reviewStatus == 'true'}">
						<h4 style="color: green">${reviewed_message}</h4>
					</c:when>
				</c:choose></td>
		</tr>
		<tr>
			<td>${approved_lable}</td>
			<td><c:choose>
					<c:when test="${request.reviewStatus == 'false'}">
						<h4 style="color: blue">${unreviewed_message}</h4>
					</c:when>
					<c:when
						test="${request.reviewStatus == 'true' && request.requestApproveStatus=='true'}">
						<h4 style="color: green">${approved_message}</h4>
					</c:when>
					<c:when
						test="${request.reviewStatus == 'true' && request.requestApproveStatus=='false'}">
						<h4 style="color: red">${rejected_message}</h4>
					</c:when>
				</c:choose></td>
		</tr>
		<tr>
			<td>${cost_lable}</td>
			<td>${request.rentCost}</td>
		</tr>
		<tr>
			<td>${reason_lable}</td>
			<td>${request.rejectReason}</td>
		</tr>
	</table>
	<c:if test="${request.reviewStatus == 'true'}">
		<p style="margin-left: 2.5%">${text1}
	</c:if>
	<c:if test="${request.reviewStatus == 'false'}">
		<form action="FrontController" method="post">
			<input type="hidden" name="command" value="request_edit"> 
			<input type="hidden" name="current_page" value="${current_page}">
			<input type="hidden" name="request_id" value="${request.requestId}">
			<p style="margin-left: 2.5%">${text2}</p>
			<table style="with: 50%">
				<tr>
					<td>${start_lable}</td>
					<td><input required type="date" name="rent_start_date" /></td>
				</tr>
				<tr>
					<td>${finish_lable}</td>
					<td><input required type="date" name="rent_finish_date" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="${edit_lable}" /></td>
				</tr>
			</table>
		</form>
	</c:if>
	<p style="margin-left: 2.5%">${request_edit_message_text}
	<c:remove var="request_edit_message"/>
</body>
</html>