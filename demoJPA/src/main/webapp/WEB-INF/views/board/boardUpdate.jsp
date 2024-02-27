<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board Update **</title>
</head>
<body>
<h2>** Board Update **</h2>
<form action="update" method="post">
<input type="hidden" name="cnt" value="${requestScope.apple.cnt}">
<table>
<tr>
	<td bgcolor="blue"><label for="title">title</label></td>
	<td><input type="text" name="title" id="title" size="20" value="${requestScope.apple.title}"></td>
	<td bgcolor="blue"><label for="content">content</label></td>
	<td><input type="text" name="content" id="content" size="20" value="${requestScope.apple.content}"></td>
	<td bgcolor="blue"><label for="seq">seq</label></td>
	<td><input type="text" name="seq" id="seq" size="20" value="${requestScope.apple.seq}"></td>
</tr>
</form>
<tr height="40">
	<td>
		<input type="submit" value="수정하기">&nbsp;&nbsp;
		<input type="reset" value="취소">
	</td>
</tr>
</table>
<br><hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>