<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board List **</title>
</head>
<body>
<h2>** Board List **</h2>
	<table style="width: 100%">
	
	<tr bgcolor="Khaki">
		<th>seq</th>
		<th>id</th>
		<th>title</th>
		<th>content</th>
		<th>regdate</th>
		<th>조회수!</th>
	</tr>
	<c:if test="${!empty sessionScope.loginID }">
		<c:forEach items="${ requestScope.apple}" var="list">
			<tr>
				<td>${list.seq }</td>
				<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가  -->
				<c:if test="${list.indent>0}">
					<c:forEach begin="1" end="${list.indent}">
						<span>&nbsp;&nbsp;</span>
					</c:forEach>
					<span style="color:blue;">re..</span>
				</c:if>
				<!-- 로그인 한 경우에만 글 내용 볼 수 있도록 -->
				<c:if test="${!empty loginID}"></c:if>
					<a href="detail?jCode=D&seq=${list.seq}">${list.title}</a>
				<c:if test="${empty loginID }">
				${list.title}
				</c:if>
				<td><a href="boardDetail?jCode=D&seq=${list.seq}">${list.title }</a></td>
				<td>${list.content }</td>
				<td>${list.regdate }</td>
				<td>${list.cnt }</td>
			</tr>
		</c:forEach>
	</c:if>
	<c:if test="${empty sessionScope.loginID }">
		<h2>로그인 하지 않으면 못 본다</h2>
	</c:if>
	</table>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="boardInsert">게시글등록</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>