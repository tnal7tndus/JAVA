<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** DeleteForm **</title>
</head>
<body>
<h2>** Web MCV2 DeleteForm **</h2>
<form action="mdelete" method="post">
	비밀번호 확인<input type="text" name="password" id="password">
	<input type="submit">
</form>

<c:if test="${requestScope.dMessage != null}">
=> ${requestScope.dMessage}
</c:if>
<hr>



</body>
</html>