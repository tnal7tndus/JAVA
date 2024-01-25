<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h2> ** Hello Spring !!! ** </h2>
<P>  The time on the server is ${serverTime}. </P>
<hr>
<c:if test="${!empty sessionScope.mName}">
    <h3>${sessionScope.mName}님 안녕하세요.</h3>
</c:if>
<c:if test="${empty sessionScope.mName}">
    <h3>로그인 후 이용해주세요.</h3>
</c:if>

<c:if test="${!empty requestScope.message}">
	<hr><h4>${requestScope.message}</h4>
</c:if>
<hr>
<!-- <img alt="" src="resources/images/white01.gif" width="300" height="200">  -->


&nbsp;<a href ="mlist">MList</a>&nbsp;
&nbsp;<a href ="mdetail">MDetail</a>&nbsp;
&nbsp;<a href ="mlistsp">MDListSp</a>&nbsp;
&nbsp;<a href ="mdetailsp">MDetailSp</a>&nbsp;

<hr>




</body>
</html>
