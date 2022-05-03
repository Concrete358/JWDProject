<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="${error_message}" var="error_message_text"/>

<!DOCTYPE html>
<html>
<head>
<title>error 500</title>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<h1>Internal Server Error (code:500)</h1>
	<p></p>
	<h2>I'll do my best for you not to get here.</h2>
	<h2 style="color: red">${error_message_text}</h2>
	<h2>Please try again your last action.</h2>
</body>
</html>