<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Request page</title>
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
	 <p style="margin-left:2.5%">
	<table border="1" width="30%">	
	<thead><tr><td colspan="2" align="center">REQUEST INFO</td></tr></thead>
		<tr><td>№</td><td>${request.requestId}</td></tr>
		<tr><td>PERSON</td><td>${request.userName}</td></tr>
		<tr><td>CAR</td><td>${request.carName}</td></tr>
		<tr><td>CAR PRICE</td><td>${request.carPrice}</td></tr>
		<tr><td>ORDER</td><td><c:choose>
			<c:when test="${request.orderId == 0}"><h4 style="color: blue">NO ORDER</h4></c:when>
			<c:otherwise>${request.orderId}</c:otherwise>
		</c:choose></td></tr>
		<tr><td>START</td><td>${request.rentStartDate}</td></tr>
		<tr><td>FINISH</td><td>${request.rentFinishDate}</td></tr>
		<tr><td>REVIEWED</td><td><c:choose>
			<c:when test="${request.reviewStatus == 'false'}"><h4 style="color: blue">UNREVIEWED</h4></c:when>
			<c:when test="${request.reviewStatus == 'true'}" ><h4 style="color: green">REVIEWED</h4></c:when>
		</c:choose></td></tr>
		<tr><td>APPROVED</td><td><c:choose>
			<c:when test="${request.reviewStatus == 'false'}"><h4 style="color: blue">UNREVIEWED</h4></c:when>
			<c:when test="${request.reviewStatus == 'true' && request.requestApproveStatus=='true'}" ><h4 style="color: green">APPROVED</h4></c:when>
			<c:when test="${request.reviewStatus == 'true' && request.requestApproveStatus=='false'}"><h4 style="color: red">REJECTED</h4></c:when>
		</c:choose></td></tr>
		<tr><td>RENT COST</td><td>${request.rentCost}</td></tr>
		<tr><td>REJECT REASON</td><td>${request.rejectReason}</td></tr>
	</table>
	<c:if test="${request.reviewStatus == 'true'}">
	<p style="margin-left:2.5%">NOTE: Reviewed requests can't be edited.
	</c:if>
	<c:if test="${request.reviewStatus == 'false'}">	
	<form action="FrontController" method="post">
		<input type="hidden" name="command" value="request_edit">
		<input type="hidden" name="request_id" value="${request.requestId}">
		<p style="margin-left:2.5%"> CHOOSE NEW DATES </p>
		<table style="with: 50%">
			<tr>
				<td>Rent start date</td>
				<td><input required type="date" name="rent_start_date" /></td>
			</tr>
			<tr>
				<td>Rent finish date</td>
				<td><input required type="date" name="rent_finish_date"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="edit request" /></td>
			</tr>
			</table>
	</form>
	</c:if>
	 <p style="margin-left:2.5%">${request_edit_message}

</body>
</html>