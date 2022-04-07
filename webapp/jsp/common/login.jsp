<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="title.login" var="title"/>
<fmt:message key="info.login" var="info"/>
<fmt:message key="email.login" var="email_text"/>
<fmt:message key="password.login" var="password_text"/>
<fmt:message key="login_button.login" var="login_button"/>
<fmt:message key="text1.login" var="text1"/>
<fmt:message key="register_page_link.login" var="register_page_link"/>
<c:if test="${not empty loginErrorText}">
<fmt:message key="${loginErrorText}" var="error_message"/>
</c:if>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>${title}</title>
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
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p>
	<form action="${path}/FrontController" method="post">
		<input type="hidden" name="command" value="login">
		<table style="with: 50%">

			<tr>
				<td>${email_text}</td>
				<td><input type="email" name="email" required /></td>
			</tr>
			<tr>
				<td>${password_text}</td>
				<td><input type="password" name="password" required /></td>
			</tr>
			<tr>
				<td><input type="submit" value="${login_button}" /></td>
				<td><h2 style="color: red">${error_message}</h2></td>
				<c:remove var="loginErrorText"/>
			</tr>
		</table>
	</form>

	<p style="margin-left: 2.5%">${text1} <a href="${path}/FrontController?command=go_to_register_page">${register_page_link}</a>
</body>
</html>