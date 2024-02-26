<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Axios MemberList **</title>
</head>
<body>
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="hotpink">
	<th>ID</th><th>Name</th><th>Age</th><th>Jno</th>
	<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th><th>Delete</th>				
</tr>
<c:if test="${!empty requestScope.apple}">
	<!-- ** idbList(id별 boardList) 
		=> 선택된 id를 function에 전달(매개변수를 활용)
		   idbList('apple')
	-->
	<c:forEach var="v" items="${requestScope.apple}">
		<tr><td onclick="idbList('${v.id}')">${v.id}</td><%-- <td>${v.password}</td> --%><td>${v.name}</td><td>${v.age}</td><td>${v.jno}</td>
		<td>${v.info}</td><td>${v.point}</td><td>${v.birthday}</td><td>${v.rid}</td>
		<td><img alt="myImage" src="/resources/images/${v.uploadfile}" width="50" height="50"></td>
	<!-- ** Delete 기능 추가
		=> 선택된 id를 function에 전달(매개변수를 활용)
		=> 결과는 성공/실패 여부만 전달 : RESTController로
		=> 성공 : Deleted로 변경, onclick 이벤트 해제 
				 이를 위해 Delete Tag를 function에서 인식할 수 있어야함.
		-->
		<td><span id="${v.id}" class="textlink" onclick="axiDelete('${v.id}')">Delete</span></td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.apple}">
	<tr>
	<td colspan="10">~~ 출력 자료가 1건도 없습니다 .~~</td>
	</tr>
</c:if>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<hr>
</table>
<hr>
&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;


</body>
</html>