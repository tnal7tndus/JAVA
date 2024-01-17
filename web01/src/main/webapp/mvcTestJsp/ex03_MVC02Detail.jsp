<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** MyInfo **</title>
</head>
<body>
<h2>** MyInfo **</h2>

<table border="1" style="width:100%">
<tr bgcolor="Aquamarine">
	<th>Sno</th><th>Name</th><th>Age</th>
	<th>Jno</th><th>Info</th><th>Point</th>		
</tr>
<c:set value="${requestScope.detail}" var="v" />
<c:if test="${!empty requestScope.detail}">
	<tr height="100" style="text-align:center">
		<td>${v.sno}</td><td>${requestScope.detail.name}</td><td>${requestScope.detail.age}</td>
		<td>${v.jno}</td><td>${requestScope.detail.info}</td><td>${requestScope.detail.point}</td>
	</tr>
</c:if>
</table>
<hr>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>
</body>
</html>