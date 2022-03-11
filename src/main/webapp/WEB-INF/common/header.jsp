<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<a class="active" href=FrontController?command=go_to_main_page>Home</a>
<c:choose>
<c:when test="${role == 'USER'}">
<jsp:include page="/WEB-INF/menus/user_menu.jsp"/>	
 </c:when>
<c:when test="${role == 'ADMIN'}">
<jsp:include page="/WEB-INF/menus/admin_menu.jsp"/>
 </c:when>	
</c:choose>

<a class="right" href="FrontController?command=logout">log out</a>
<a class="right" href="FrontController?command=go_to_login_page">log in</a>


</div>
</body>
</html>