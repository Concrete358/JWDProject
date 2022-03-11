<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Request list</title>
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

	<p>${dao_error_message}</p>
	<h2 align="right">${approve_message}</h2>
	<h2 align="right">${cancel_message}</h2>
<table border="1" width="90%">	
	<thead>
		<tr><td colspan="13">LIST OF REQUESTS</td></tr>
		<tr><td>№</td>
		<td>PERSON</td>
		<td>CAR</td>
		<td>CAR PRICE</td>
		<td>ORDER</td>
		<td>START</td>
		<td>FINISH</td>
		<td>REVIEWED</td>
		<td>APPROVED</td>
		<td>RENT COST</td>
		<td>REJECT REASON</td>
		<td>CANCEL</td>
		<td>EDIT</td></tr></thead>
	<c:forEach items="${listOfRequests}" var="request">
		<tr>
		<td>${request.requestId}</td>
		<td>${request.userName}</td>
		<td>${request.carName}</td>
		<td>${request.carPrice}</td>
		<td>
		<c:choose>
			<c:when test="${request.orderId == 0}"><h4 style="color: blue">NO ORDER</h4></c:when>
			<c:otherwise>${request.orderId}</c:otherwise>
		</c:choose></td>
		<td>${request.rentStartDate}</td>
		<td>${request.rentFinishDate}</td>
		<td><c:choose>
			<c:when test="${request.reviewStatus == 'false'}"><h4 style="color: blue">UNREVIEWED</h4></c:when>
			<c:when test="${request.reviewStatus == 'true'}" ><h4 style="color: green">REVIEWED</h4></c:when>
		</c:choose></td>
		<td>
		<c:choose>
			<c:when test="${request.reviewStatus == 'false'}"><h4 style="color: blue">UNREVIEWED</h4></c:when>
			<c:when test="${request.reviewStatus == 'true' && request.requestApproveStatus=='true'}" ><h4 style="color: green">APPROVED</h4></c:when>
			<c:when test="${request.reviewStatus == 'true' && request.requestApproveStatus=='false'}"><h4 style="color: red">REJECTED</h4></c:when>
		</c:choose>
		</td>
		<td>${request.rentCost}</td>
		<td>${request.rejectReason}</td>
		<td><form action="FrontController" method="post">
			<input type="hidden" name="command" value="cancel_request">
			<input type="hidden" name="request_id" value="${request.requestId}">
			<input type="submit" value="cancel">
			</form></td>
		<td><a href="FrontController?command=go_to_request_edit_page&request_id=${request.requestId}">edit</a>
		</td>
		</tr>
	</c:forEach>
</table>
<p style="margin-left:2.5%">
<c:set var="page" scope="page" value="FrontController?command=go_to_user_request_list_page"/>
	<c:if test="${listPage != 1}">
		<a href="${page}&recordsPerPage=${recordsPerPage}&listPage=${listPage-1}">Previous</a>
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
		<a href="${page}&recordsPerPage=${recordsPerPage}&listPage=${listPage+1}">Next</a>
	</c:if>
</body>
</html>