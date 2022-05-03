<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="req_list.user_menu" var="requests"/>
<fmt:message key="ord_list.user_menu" var="orders"/>
<fmt:message key="user_page.user_menu" var="user_page"/>

<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<nav>
		<a href="${path}/FrontController?command=GO_TO_USER_REQUEST_LIST_PAGE">${requests}</a> 
		<a href="${path}/FrontController?command=go_to_user_order_list_page">${orders}</a> 
		<a href="${path}/FrontController?command=go_to_user_page">${user_page} (${user_name})</a>
	</nav>
</body>
</html>