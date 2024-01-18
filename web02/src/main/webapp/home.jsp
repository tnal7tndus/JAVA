<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>* home *</title>
</head>
<body>
<h2> Web02_MVC02 </h2>
<c:if test="${!empty sessionScope.loginName}">
    <h3>${sessionScope.loginName}님 안녕하세요.</h3>
</c:if>
<c:if test="${empty sessionScope.loginName}">
    <h3>로그인 후 이용해주세요.</h3>
</c:if>
<hr>
<form action="getpost" method ="get" >
    <input type="text" name="id" value="banana"> &nbsp;
    <input type="text" name="name" value="바나나">
    <input type="text" name="password" value="7">
    <input type="submit" value="Test">
    </form>
    <hr>
<!--  <form action="list">
    <input type ="submit" value="학생조회">
</form>-->
<hr>
<!-- ** 경로표기
    => 절대경로 : / 로시작, 프로젝트명부터 전체경로 표기
        -> /web01/images/letsgo.png
        -> webapp 폴더는 생략됨
    => 상대경로 : 현재위치에서 시작
        -> ./ : 현재위치, ../ : 1단계 상위
        -> "./images/letsgo.png", "images/letsgo.png" 동일
--> 
<img alt="" src="/web02/images/white01.gif" width="300" height="200">
<hr>
<c:if test="${!empty sessionScope.loginName}">
    &nbsp;<a href ="/web02/mdetail">MyInfo</a>&nbsp;
    &nbsp;<a href ="/web02/logout">Logout</a><br>
</c:if>
<c:if test="${empty sessionScope.loginName}">
    &nbsp;<a href ="/web02/member/loginForm.jsp">Login</a>&nbsp;
    &nbsp;<a href ="/web02/member/joinForm.jsp">Join</a><br>
</c:if>

&nbsp;<a href ="/web02/mlist">M02List</a>&nbsp;
</body>
</html>