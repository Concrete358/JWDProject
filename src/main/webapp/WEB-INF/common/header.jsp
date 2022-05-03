<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="home.header" var="home"/>
<fmt:message key="log_in.header" var="log_in"/>
<fmt:message key="log_out.header" var="log_out"/>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {
	margin: 0;
	font-family: Tahoma, Geneva, sans-serif;
}

.topnav {
	overflow: hidden;
	background-color: #54585d;
}

.topnav a {
	float: left;
	color: #f2f2f2;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 13px;
	font-weight: bold;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #04AA6D;
	color: white;
}

.topnav a.right {
	float: right;
}
</style>
</head>
<body>
	<div class="topnav">
		<a class="active" href="${path}/FrontController?command=go_to_main_page">${home}</a>
		<c:choose>
			<c:when test="${role == 'USER'}">
				<jsp:include page="/WEB-INF/menus/user_menu.jsp" />
			</c:when>
			<c:when test="${role == 'ADMIN'}">
				<jsp:include page="/WEB-INF/menus/admin_menu.jsp" />
			</c:when>
		</c:choose>

		<a class="right" href="${path}/FrontController?command=logout">${log_out}</a> 
		<a class="right" href="${path}/jsp/common/login.jsp">${log_in}</a>
	</div>
</body>
</html>