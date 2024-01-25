<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 MemberList **</title>
</head>
<body>
<h2>** Web02_MVC02 MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" sytle="width:100%">
<tr bgcolor="Lime">
	<th>ID</th><th>Password</th><th>Name</th><th>Age</th><th>Jno</th>
	<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th>		
</tr>
<c:if test="${!empty requestScope.banana}">
	<c:forEach var="v" items="${requestScope.banana}">
		<tr><td>${v.id}</td><td>${v.password}</td><td>${v.name}</td><td>${v.age}</td><td>${v.jno}</td>
		<td>${v.info}</td><td>${v.point}</td><td>${v.birthday}</td><td>${v.rid}</td></tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.banana}">
	<tr>
	<td colspan="9">~~ 출력 자료가 1건도 없습니다 .~~</td>
	</tr>
</c:if>
</table>
<hr>
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
&nbsp;<a href="home">HOME</a>&nbsp;


</body>
</html>