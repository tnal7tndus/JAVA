<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MCV2 LoginForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/mylib/myStyle.css">
</head>
<body>
<h2>** Spring_MCV2 LoginForm **</h2>
<form action="login" method="post">
<table>
	<tr height="20">
		<td bgcolor="aqua"><label for="id">I  D</label></td>
		<td><input type="text" name="id" id="id" size="20"></td>
	</tr>
	<tr height="20">
		<td bgcolor="aqua"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" size="20"></input></td>
	</tr>
	<tr><td></td>
		<td><input type="submit" value="로그인">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<hr>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<hr>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;



</body>
</html>