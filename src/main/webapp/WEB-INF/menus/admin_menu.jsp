<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="req_list.admin_menu" var="req_list"/>
<fmt:message key="ord_list.admin_menu" var="ord_list"/>
<fmt:message key="rep_ord_list.admin_menu" var="rep_ord_list"/>
<fmt:message key="car_list.admin_menu" var="car_list"/>
<fmt:message key="admin_page.admin_menu" var="admin_page"/>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
	<nav>

		<a href="${path}/FrontController?command=go_to_admin_request_list_page"> ${req_list} </a> 
			<a href="${path}/FrontController?command=go_to_admin_order_list_page"> ${ord_list}  </a> 
			<a href="${path}/FrontController?command=go_to_admin_repair_order_list_page">${rep_ord_list} </a> 
			<a href="${path}/FrontController?command=go_to_admin_car_list_page">${car_list} </a> 
			<a href="${path}/FrontController?command=go_to_user_page">${admin_page} (${user_name}) </a>

	</nav>
</body>
</html>