<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<title>error 403</title>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<h1>Forbidden (code:403)</h1>
	<p></p>
	<h2>I'll do my best for you not to get here.</h2>
	<h2 style="color: red">${error_message}</h2>
	<p></p>
</body>
</html>