<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** UpdateForm **</title>
</head>
<body>
<h2>** Web MCV2 UpdateForm **</h2>
<form action="/web02/mupdate" method="post">
<table>
	<tr height="20">
		<td bgcolor="Pink"><label for="id">I  D</label></td>
		<td><input type="text" name="id" id="id" value="${requestScope.detail.id}" readonly size="20"></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" value="${requestScope.detail.password}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="name">Name</label></td>
		<td><input type="text" name="name" id="name" value="${requestScope.detail.name}" size="20"></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="age">Age</label></td>
		<td><input type="text" name="age" id="age" value="${requestScope.detail.age}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="jno">Jno</label></td>
		<td><select name="jno" id="jno">
			<option value="1" ${requestScope.detail.jno==1 ? "selected":""}>1조: Business</option>
			<option value="2" ${requestScope.detail.jno==2 ? "selected":""}>2조: static</option>
			<option value="3" ${requestScope.detail.jno==3 ? "selected":""}>3조: 칭찬해조</option>
			<option value="4" ${requestScope.detail.jno==4 ? "selected":""}>4조: 카톡으로얘기하조</option>
			<option value="7" ${requestScope.detail.jno==7 ? "selected":""}>7조: 칠면조(관리팀)</option>
			</select>
		</td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="info">Info</label></td>
		<td><input type="text" name="info" id="info" value="${requestScope.detail.info}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="point">Point</label></td>
		<td><input type="text" name="point" id="point" value="${requestScope.detail.point}" size="20"></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="birthday">Birthday</label></td>
		<td><input type="date" name="birthday" id="birthday" value="${requestScope.detail.birthday}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="rid">추천인</label></td>
		<td><input type="text" name="rid" id="rid" value="${requestScope.detail.rid}" size="20"></td>
	</tr>
	<tr><td></td>
		<td><input type="submit" value="수정">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<br><hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>


&nbsp;<a href="/web02/home.jsp">Home</a>&nbsp;
&nbsp;<a href="">이전으로</a>&nbsp;
</body>
</html>

