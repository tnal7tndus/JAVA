<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Delete Form **</title>
</head>
<body>
<h2>** Jo Delete Form **</h2>
<form action="Delete">
	
</form>

<c:if test="${requestScope.dMessage != null}">
=> ${requestScope.dMessage}
</c:if>
<hr>
</body>
</html>