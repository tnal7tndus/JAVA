<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Jo Update Form **</title>
</head>
<body>
<h2>** Jo Update Form **</h2>
<form action="Update">
<table>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="jno">Jno</label></td>
		<td><select name="jno" id="jno">
			<option value="1">1조: Business</option>
			<option value="2">2조: static</option>
			<option value="3">3조: 칭찬해조</option>
			<option value="4">4조: 카톡으로얘기하조</option>
			<option value="7">7조: 칠면조(관리팀)</option>
			</select>
		</td>
	</tr>
	<tr height="20">
		<td bgcolor="skyblue"><label for="jname">JNAME</label></td>
		<td><input type="text" name="jname" id="jname" size="20" value="${requestScope.apple.jname}"></td>
	</tr>
	<tr height="20">
		<td bgcolor="skyblue"><label for="captain">CAPTAIN</label></td>
		<td><input type="text" name="captain" id="captain" size="20" value="${requestScope.apple.captain}"></td>
	</tr>
	<tr height="20">
		<td bgcolor="skyblue"><label for="project">PROJECT</label></td>
		<td><input type="text" name="project" id="project" size="20" value="${requestScope.apple.project}"></td>
	</tr>
	<tr height="20">
		<td bgcolor="skyblue"><label for="slogan">SLOGAN</label></td>
		<td><input type="text" name="slogan" id="slogan" size="20" value="${requestScope.apple.slogan}"></td>
	</tr>
	<tr><td></td>
		<td><input type="submit" value="수정하기">&nbsp;&nbsp;
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


&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>