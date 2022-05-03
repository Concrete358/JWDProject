<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="i18n.pagecontent"/>

<fmt:message key="title.repair_info" var="title_text"/>
<fmt:message key="text_1.repair_info" var="text_1"/>
<fmt:message key="text_2.repair_info" var="text_2"/>
<fmt:message key="text_3.repair_info" var="text_3"/>
<fmt:message key="claim_button.order_info" var="claim_button"/>
<fmt:message key="byn.common" var="BYN"/>
<c:if test="${not empty repair_order_create_result}">
<fmt:message key="${repair_order_create_result}" var="repair_order_create_message"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<title>${title_text}</title>
<style>
   <%@include file='/сss/style.css' %>
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/common/header.jsp" />
	<p style="margin-left: 2.5%">${text_1}
	<form action="FrontController" method="post">
		<input type="hidden" name="command" value="repair_order_create">
		<input type="hidden" name="order_id" value="${order_id}">
		<input type="hidden" name="current_page" value="${current_page}">
		<table style="with: 60%">
			<tr>
				<td align="center">${text_2}</td>
				<td align="center">${text_3}</td>
			</tr>
			<tr>
				<td><textarea required name="damage_description"></textarea></td>
				<td><input required type="number" min="0.01" step="0.01"
					name="repair_cost"> ${BYN}</td>
			</tr>
			<tr>
				<td><input type="submit" value="${claim_button}" /></td>
			</tr>
		</table>
		${repair_order_create_message}
		<c:remove var="repair_order_create_result"/>
	</form>
</body>
</html>