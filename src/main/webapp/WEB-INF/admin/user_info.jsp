<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="title.user_page" var="title_text"/>
<fmt:message key="name.user_page" var="name_text"/>
<fmt:message key="lastname.user_page" var="lastname_text"/>
<fmt:message key="email.user_page" var="email_text"/>
<fmt:message key="birth_date.user_page" var="birth_date_text"/>
<fmt:message key="pass_number.user_page" var="pass_number_text"/>
<fmt:message key="pass_term.user_page" var="pass_term_text"/>
<fmt:message key="licence_number.user_page" var="licence_number_text"/>
<fmt:message key="licence_term.user_page" var="licence_term_text"/>
<fmt:message key="phone.user_page" var="phone_text"/>
<fmt:message key="user_role.user_page" var="user_role_text"/>
<fmt:message key="change_email.user_page" var="change_email_text"/>
<fmt:message key="change_password.user_page" var="change_password_text"/>
<!DOCTYPE html>
<html>
<head>
<title>${user.name}&nbsp;${user.lastname}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left: 2.5%">${title_text}
	<h2>${error_message}</h2>
	<table border="1" width="30%">
		<tr>
			<td width="55%">ID</td>
			<td>${user.id}</td>
		</tr>
		<tr>
			<td>${name_text}</td>
			<td>${user.name}</td>
		</tr>
		<tr>
			<td>${lastname_text}</td>
			<td>${user.lastname}</td>
		</tr>
		<tr>
			<td>${email_text}</td>
			<td>${user.email}</td>
		</tr>
		<tr>
			<td>${birth_date_text}</td>
			<td>${user.birthdate}</td>
		</tr>
		<tr>
			<td>${pass_number_text}</td>
			<td>${user.passportNumber}</td>
		</tr>
		<tr>
			<td>${pass_term_text}</td>
			<td>${user.passportTerm}</td>
		</tr>
		<tr>
			<td>${licence_number_text}</td>
			<td>${user.licenceNumber}</td>
		</tr>
		<tr>
			<td>${licence_term_text}</td>
			<td>${user.licenceTerm}</td>
		</tr>
		<tr>
			<td>${phone_text}</td>
			<td>${user.phoneNumber}</td>
		</tr>
		<tr>
			<td>${user_role_text}</td>
			<td>${user.role}</td>
		</tr>
	</table>
</body>
</html>