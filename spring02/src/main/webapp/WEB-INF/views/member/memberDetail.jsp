<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** My Info Detail **</title>
</head>
<body>
<h2>** My Info **</h2>

<table border="1">
<tr bgcolor="yellow">

	<th>Name</th><th>Age</th><th>Jno</th><th>Info</th>
	<th>Point</th><th>Birthday</th><th>Rid</th><th>uploadfile</th>
</tr>
<c:set value="${requestScope.apple}" var="v" />
	<tr>
		<td>${v.name}</td><td>${v.age}</td><td>${v.jno}</td><td>${v.info}</td>
		<td>${v.point}</td><td>${v.birthday}</td><td>${v.rid}</td>
		<td><img alt="myImage" src="/spring02/resources/images/${v.uploadfile}" width="50" height="50"></td>
	</tr>
</table>
<hr>
<h3><a href='javascript:history.go(-1)'>이전으로</a></h3>
<hr>



</body>
</html>