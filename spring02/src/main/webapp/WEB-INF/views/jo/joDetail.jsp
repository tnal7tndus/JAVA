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
		<th>JNO</th><th>JNAME</th><th>CAPTAIN</th><th>PROJECT</th><th>SLOGAN</th><!-- <th>uploadfile</th> -->
	</tr>
	<c:if test="${!empty requestScope.apple}">
		<c:set var="v" value="${requestScope.apple}"/>
		<tr><td>${v.jno}</td><td>${v.jname}</td><td>${v.captain}</td>
		<td>${v.project}</td><td>${v.slogan}</td>
		<%-- <td><img alt="myImage" src="/spring02/resources/images/${v.uploadfile}" width="50" height="50"></td> --%>
		</tr>
	</c:if>
</table>

<hr>


<table border= "1" sytle="width:100%">
	<tr height ="20" bgcolor="yellow">
		<th>ID</th><th>Password</th><th>Name</th><th>Age</th>
		<th>Jno</th><th>Info</th><th>Point</th><th>Birthday</th><th>Rid</th>
	</tr>
	<c:if test="${!empty requestScope.banana}">
		<c:forEach var="s" items="${requestScope.banana}">
			<tr><td>${s.id}</td><td>${s.password}</td><td>${s.name}</td><td>${s.age}</td><td>${s.jno}</td>
			<td>${s.info}</td><td>${s.point}</td><td>${s.birthday}</td><td>${s.rid}</td></tr>
		</c:forEach>
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