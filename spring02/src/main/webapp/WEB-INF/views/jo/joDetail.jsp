<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Detail Form **</title>
</head>
<body>
<h2>** Jo Detail Form **</h2>

<table border="1" sytle="width:100%">
	<tr height="20" bgcolor="pink">
		<th>JNO</th><th>JNAME</th><th>CAPTAIN</th><th>PROJECT</th><th>SLOGAN</th>
	</tr>
	<c:if test="${!empty requestScope.apple}">
		<c:set var="v" value="${requestScope.apple}"/>
		<tr><td>${v.jno}</td><td>${v.jname}</td><td>${v.captain}</td>
		<td>${v.project}</td><td>${v.slogan}</td></tr>
	</c:if>
</table>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>

&nbsp;<a href="joInsert?jno=${requestScope.apple.jno }">조등록</a>&nbsp;
&nbsp;<a href="joUpdate?jno=${requestScope.apple.jno}">조수정</a>&nbsp;
&nbsp;<a href="joDelete?jno=${requestScope.apple.jno}">조삭제</a>&nbsp;
<br><hr>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>