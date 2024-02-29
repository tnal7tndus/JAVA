<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="/resources/mylib/myStyle.css">
</head>
<body>
<h2> ** Hello SprinBoot JPA !!! ** </h2>
<P>* Home_time: ${serverTime}. </P>
<hr>
<c:if test="${!empty sessionScope.loginName}">
	${sessionScope.loginName}님 안녕하세요 ~~<br>
</c:if>

<c:if test="${!empty requestScope.message}">
	<hr>=> ${requestScope.message}<br>
</c:if>

<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
	로그인 후 이용하세요 ~~<br>
</c:if>

<hr>
<img alt="mainImage" src="resources/images/aaa.gif" width="300" height="300">
<hr>

<!-- Login 전  -->
<c:if test="${empty sessionScope.loginID}">
	&nbsp;<a href="member/loginForm">LoginF</a>&nbsp;
	&nbsp;<a href="member/joinForm">JoinF</a>&nbsp;
</c:if>

<!-- Login 후  -->
<c:if test="${!empty sessionScope.loginID}">
	&nbsp;<a href="member/detail?jCode=D">내정보</a>&nbsp;
	&nbsp;<a href="member/detail?jCode=U">내정보수정</a>&nbsp;
	&nbsp;<a href="member/logout">Logout</a>&nbsp;
	&nbsp;<a href="member/delete">탈퇴</a>&nbsp;
</c:if>
<br><hr>
&nbsp;<a href ="member/memberList">MList</a>&nbsp;
&nbsp;<a href="member/mjoinList">mJoinList</a>&nbsp;
&nbsp;<a href ="jo/joList">JList</a>&nbsp;
&nbsp;<a href ="board/boardList">boardList</a>&nbsp;
&nbsp;<a href ="bcrypt">BCrypt</a><br>
&nbsp;<a href ="board/bPageList">BPage</a>&nbsp;
&nbsp;<a href ="member/mPageList">MPage</a>&nbsp;
&nbsp;<a href ="/axtestform">AjaxTest</a><br>
&nbsp;<a href ="/ginsert">GInsert</a>&nbsp;
&nbsp;<a href ="/glist">GList</a>&nbsp;
&nbsp;<a href ="/gupdate">GUpdate</a>&nbsp;
&nbsp;<a href ="/gpage">GPage</a>&nbsp;
</body>
</html>
