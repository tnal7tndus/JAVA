<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board Insert **</title>
</head>
<body>
<h2>** 새로운 게시글 작성 **</h2>

<form action="insert">
	id: <input type="text" name="id" id="id"><br>
	title: <input type="text" name="title" id="title"><br>
	content: <input type="text" name="content" id="content"><br>
	<button>등록</button>
</form>
<br>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>
</body>
</html>