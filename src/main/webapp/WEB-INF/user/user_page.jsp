<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<c:if test="${not empty change_email_message}">
<fmt:message key="${change_email_message}" var="change_email_message_text"/>
</c:if>
<c:if test="${not empty change_password_message}">
<fmt:message key="${change_password_message}" var="change_password_message_text"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>${user.name} ${user.lastname}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left: 2.5%">${title_text}
	<h2>${error_message}</h2>
	<table border="1" width="35%">
		<tr>
			<td>ID</td>
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
	<p></p>
	<table>
		<thead>
			<tr>
				<td>${change_email_text}</td>
			</tr>
		</thead>
		<tr>
			<td><form name="${change_email_text}" action="FrontController"
					method="post">
					<input type="email" name="new_email" placeholder="new email" required> 
						<input type="hidden" name="command" value="change_email"> 
						<input type="hidden" name="current_page" value="${current_page}">
						<input type="submit" value="${change_email_text}"> ${change_email_message_text}
						<c:remove var="change_email_message"/>
				</form></td>
		</tr>
	</table>
	<p>
	<table>
		<thead>
			<tr>
				<td>${change_password_text}</td>
			</tr>
		</thead>
		<tr>
			<td>
				<form name="change_password" action="FrontController" method="post">
					<input type="password" name="old_password"
						placeholder="old password" required> <input
						type="password" name="new_password" placeholder="new password"
						required> <input type="hidden" name="command"
						value="change_password">
						<input type="hidden" name="current_page" value="${current_page}">
						<input type="submit" value="${change_password_text}"> ${change_password_message_text}
						<c:remove var="change_password_message"/>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>