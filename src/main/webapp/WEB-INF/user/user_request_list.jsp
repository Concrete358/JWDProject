<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="table_head.user_requests" var="table_head"/>
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
<fmt:message key="cancel.user_requests" var="cancel_lable"/>
<fmt:message key="previous.common" var="previous"/>
<fmt:message key="next.common" var="next"/>
<fmt:message key="no_order_message.user_requests" var="no_order_message"/>
<fmt:message key="unreviewed_message.user_requests" var="unreviewed_message"/>
<fmt:message key="reviewed_message.user_requests" var="reviewed_message"/>
<fmt:message key="approved_message.user_requests" var="approved_message"/>
<fmt:message key="rejected_message.user_requests" var="rejected_message"/>
<c:if test="${not empty cancel_message}">
<fmt:message key="${cancel_message}" var="cancel_message_text"/>
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

	<p>${dao_error_message}</p>
	<h2 align="right">${cancel_message_text}</h2>
	<table border="1" width="90%" >
		<thead>
			<tr >
				<td colspan="13">${table_head}</td>
			</tr>
			<tr align="center">
				<td>№</td>
				<td>${client_lable}</td>
				<td width="12%">${car_lable}</td>
				<td>${price_lable}</td>
				<td>${order_lable}</td>
				<td>${start_lable}</td>
				<td>${finish_lable}</td>
				<td width="8%">${reviewed_lable}</td>
				<td>${approved_lable}</td>
				<td width="10%">${cost_lable}</td>
				<td width="12%">${reason_lable}</td>
				<td></td>
				<td></td>
			</tr>
		</thead>
		<c:forEach items="${listOfRequests}" var="request">
			<tr align="center">
				<td>${request.requestId}</td>
				<td>${request.userName}</td>
				<td>${request.carName}</td>
				<td>${request.carPrice}</td>
				<td><c:choose>
						<c:when test="${request.orderId == 0}">
							<h4 style="color: blue">${no_order_message}</h4>
						</c:when>
						<c:otherwise>${request.orderId}</c:otherwise>
					</c:choose></td>
				<td>${request.rentStartDate}</td>
				<td>${request.rentFinishDate}</td>
				<td><c:choose>
						<c:when test="${request.reviewStatus == 'false'}">
							<h4 style="color: blue">${unreviewed_message}</h4>
						</c:when>
						<c:when test="${request.reviewStatus == 'true'}">
							<h4 style="color: green">${reviewed_message}</h4>
						</c:when>
					</c:choose></td>
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
				<td>${request.rentCost}</td>
				<td>${request.rejectReason}</td>
				<td><form action="FrontController" method="post">
						<input type="hidden" name="command" value="cancel_request">
						<input type="hidden" name="current_page" value="${current_page}">
						<input type="hidden" name="request_id"
							value="${request.requestId}"> <input type="submit"
							value="${cancel_lable}">
					</form></td>
				<td><a
					href="FrontController?command=go_to_request_edit_page&request_id=${request.requestId}">${edit_lable}</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<p style="margin-left: 2.5%">
		<c:set var="page" scope="page"
			value="FrontController?command=go_to_user_request_list_page" />
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
			<c:remove var="cancel_message"/>
</body>
</html>