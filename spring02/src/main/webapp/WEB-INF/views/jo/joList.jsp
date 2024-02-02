<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoList Form **</title>
</head>
<body>
<h2>** JoList Form **</h2>

<table border="1" sytle="width:100%">
	<tr height="20" bgcolor="pink">
		<th>JNO</th><th>JNAME</th><th>CAPTAIN</th><th>PROJECT</th><th>SLOGAN</th><!-- <th>uploadfile</th> -->
	</tr>
	<c:if test="${!empty requestScope.apple}">
		<c:forEach var="v" items="${requestScope.apple}">
			<tr><td><a href="joDetail?jno=${v.jno}">${v.jno}</a></td><td>${v.jname}</td><td>${v.captain}</td>
			<td>${v.project}</td><td>${v.slogan}</td>
			<%-- <td><img alt="myImage" src="/spring02/resources/images/${v.uploadfile}" width="50" height="50"></td> --%>
			</tr>
		</c:forEach>
	</c:if>
</table>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>

&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="joInsert">조등록</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>