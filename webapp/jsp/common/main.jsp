<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="title.main" var="title"/>
<fmt:message key="table_name.main" var="table_name"/>
<fmt:message key="price_per_day.common" var="price_per_day"/>
<fmt:message key="byn.common" var="BYN"/>
<fmt:message key="previous.common" var="previous"/>
<fmt:message key="next.common" var="next"/>

<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
<style>
table {
	margin-left: 2.5%;
	margin-right: 20%;
	border-collapse: collapse;
	font-family: Tahoma, Geneva, sans-serif;
}

table td {
	padding: 10px;
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
	border: 1px solid #54585d;
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
	<p>${dao_error_message}
	<table>
		<thead>
			<tr>
				<td colspan="5">${table_name}</td>
			</tr>
		</thead>
		<c:forEach items="${listOfCars}" var="car">
			<tr>
				<td width="30%"><a
					href="FrontController?command=go_to_car_page&id=${car.id}">
						${car.carMaker} ${car.carModel} - ${price_per_day} ${car.pricePerDay} ${BYN} </a></td>
				<td><img src="images/cars/${car.id}/1.jpg" style="width: 100%"></td>
				<td><img src="images/cars/${car.id}/2.jpg" style="width: 100%"></td>
				<td><img src="images/cars/${car.id}/3.jpg" style="width: 100%"></td>
			</tr>
		</c:forEach>
	</table>
	<p style="margin-left: 2.5%">
		<c:set var="page" scope="page"
			value="FrontController?command=go_to_main_page" />
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
		
		<p style="margin-left: 2.5%">
		<a href="FrontController?command=set_locale&language=ru_RU">RU</a>
		<a href="FrontController?command=set_locale&language=en_US">EN</a>
</body>
</html>