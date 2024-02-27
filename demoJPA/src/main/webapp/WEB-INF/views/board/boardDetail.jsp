<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board Detail **</title>
</head>
<body>
<h2>** Board List **</h2>

<table border="1">
	<tr>
		<th>seq</th>
		<th>id</th>
		<th>title</th>
		<th>content</th>
		<th>regdate</th>
		<th>조회수</th>
	</tr>
 <c:set value="${requestScope.apple}" var="detail" /> 
	<tr>
		<td>${detail.seq}</td>
		<td>${detail.id}</td>
		<td>${detail.title}</td>
		<td>${detail.content}</td>
		<td>${detail.regdate}</td>
		<td>${detail.cnt}</td>
	</tr> 
</table>
<c:if test="${empty requestScope.apple}">
	<tr><td colspan="2"> ~~ 출력할 자료가 없습니다 ~~ </td></tr>
</c:if>
<c:if test="${!empty requestScope.message}">
${requestScope.message}<br>
</c:if>
<hr>
<!-- 로그인 한 경우에는 새 글 등록 -->
<c:if test="${!empty sessionScope.loginID}">
	&nbsp;<a href="boardInsert">새글등록</a>&nbsp;
	<!-- 댓글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
    	서버로 보내주어야함 (퀴리스트링으로 작성)    -->
	&nbsp;<a href="replyInsert?root=${apple.root}&step=${apple.step}&indent=${apple.indent}">답글등록</a>&nbsp;
</c:if>

<!-- 로그인id와 글쓴이id가 동일하면 수정, 삭제 가능 -->
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="boardUpdate?seq=${detail.seq}">게시글 수정</a>&nbsp;
&nbsp;<a href="delete?seq=${detail.seq}&root=${apple.root}">게시글 삭제</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>