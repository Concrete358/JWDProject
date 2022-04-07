<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="title.register" var="title_text"/>
<fmt:message key="name.user_page" var="name_text"/>
<fmt:message key="lastname.user_page" var="lastname_text"/>
<fmt:message key="email.user_page" var="email_text"/>
<fmt:message key="birth_date.user_page" var="birth_date_text"/>
<fmt:message key="pass_number.user_page" var="pass_number_text"/>
<fmt:message key="pass_term.user_page" var="pass_term_text"/>
<fmt:message key="licence_number.user_page" var="licence_number_text"/>
<fmt:message key="licence_term.user_page" var="licence_term_text"/>
<fmt:message key="phone.user_page" var="phone_text"/>
<fmt:message key="register_button.register" var="register_button"/>
<fmt:message key="login_page_link.register" var="login_page_link"/>
<fmt:message key="text1.register" var="text1"/>
<fmt:message key="password_title_text.common" var="password_title_text"/>
<fmt:message key="birthdate_title_text.common" var="birthdate_title_text"/>
<fmt:message key="document_term_title_text.common" var="document_term_title_text"/>
<fmt:message key="phone_number_title_text.common" var="phone_number_title_text"/>
<c:if test="${not empty RegisterErrorText}">
<fmt:message key="${RegisterErrorText}" var="RegisterErrorText_message"/>
</c:if>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title>${title_text}</title>
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
	<p style="margin-left: 2.5%">
	<form action="${path}/FrontController" method="post">
		<input type="hidden" name="command" value="register">
		<table style="with: 50%">

			<tr>
				<td>${email_text}</td>
				<td><input type="email" name="email" required pattern="^[-\w.]+@([A-z0-9][-A-z0-9]+\.)+[A-z]{2,4}$"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" required pattern="^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$" title="${password_title_text}"/></td>
			</tr>
			<tr>
				<td>${name_text}</td>
				<td><input type="text" name="name" required/></td>
			</tr>
			<tr>
				<td>${lastname_text}</td>
				<td><input type="text" name="lastname" required/></td>
			</tr>
			<tr>
				<td>${birth_date_text}</td>
				<td><input type="date" name="birthdate" required title="${birthdate_title_text}"/></td>
			</tr>
			<tr>
				<td>${pass_number_text}</td>
				<td><input type="text" name="passport_number" required/></td>
			</tr>
			<tr>
				<td>${pass_term_text}</td>
				<td><input type="date" name="passport_term" required title="${document_term_title_text}"/></td>
			</tr>
			<tr>
				<td>${licence_number_text}</td>
				<td><input type="text" name="d_licence_number" required/></td>
			</tr>
			<tr>
				<td>${licence_term_text}</td>
				<td><input type="date" name="d_licence_term" required title="${document_term_title_text}"/></td>
			</tr>
			<tr> 
				<td>${phone_text}</td>
				<td><input type="tel" name="phone_number" required title="${phone_number_title_text}" pattern="375(29|44|17|25|33)[1-9]\\d{6}"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="${register_button}" /></td>
				<td><h2 style="color: red">${RegisterErrorText_message}</h2></td>
				<c:remove var="RegisterErrorText"/>
			</tr>
		</table>
	</form>

	<p style="margin-left: 2.5%">
		${text1} <a
			href="${path}/FrontController?command=go_to_login_page">${login_page_link}</a>
</body>
</html>
