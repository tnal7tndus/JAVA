<%@page import="org.apache.jasper.tagplugins.jstl.core.Choose"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** home **</title>
</head>
<body>
<h2>** Dynamic Web Project **</h2>
<%-- <%
 if (session.getAttribute("loginName")!=null) { %>
<h3><%=session.getAttribute("loginName")%>님 안녕하세요~~</h3>
<% } else{%>
	<h3>로그인 후 사용하세요 ~~~</h3>
<% }
%> 
=> 아래 JSTL과 비교
--%>

<%-- 선생님 답
<c:if test="${!empty sessionScope.loginName}">
	<h3>${sessionScope.loginName}님 안녕하세요.</h3>
</c:if>
<c:if test="${!empty sessionScope.loginName}">
	<h3>로그인 후 사용하세요 ~~~</h3>
</c:if>
 --%>



<c:choose>
	<c:when test="${sessionScope.loginName!=null}">
		<h3>${sessionScope.loginName}님 안녕하세요.</h3>
	</c:when>
	<c:otherwise>
		<h3>로그인 후 사용하세요 ~~~</h3>
	</c:otherwise>
</c:choose>

<hr>
	<!--
	<form action="getpost" method="get">
		<input type="text" name="id" value="banana"> &nbsp;
		<input type="text" name="id" value="바나나">
		<input type="sumit" value="Test">
	 </form>
	 <hr>
	 -->
	 
	<form action="getpost" method="post">
		<input type="text" name="id" value="banana"> &nbsp;
		<input type="text" name="name" value="바나나">
		<input type="submit" value="Test">
	 </form>
 <hr>

	<!-- Get방식 -->
	<!-- <form action="hello" method="get">
		<input type="text" name="id"> <input type="submit"value="Test">
	<form action="#"><input type="submit" value="학생 조회"></input></form>
	-->

	<!-- Post방식 -->
	<!-- <form action="hello"method="post">
	<input type="text" name="id">
	<input type="submit" value="Test">
	</form>-->
<hr>
	<!-- ** 경로표기
		=> 절대경로 : / 로 시작, 프로젝트명부터 전체경로표기
			->		/ web01 / images / letsgo.png
			->		webapp 폴더는 생략 됨	
		=> 상대경로 : 현재위치에서 시작, / 로시작하면 안됨
			-> ./ : 현재위치, ../1단계 상위
			-> "./images/letsgo.png" =동일= "images/letsgo.png"
	 -->
	<img alt="" src="images/letsgo.png" width="300" height="200">
	<hr>
	&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
	&nbsp;<a href="/web01/logout"> Logout </a>&nbsp;<br>
	
	<%-- 선생님 답
<c:if test="${not empty sessionScope.loginName}">
&nbsp;<a href="#">MyInfo</a>&nbsp;
&nbsp;<a href="/web01/logout"> Logout </a>&nbsp;<br>
</c:if>
<c:if test="${!empty sessionScope.loginName}">
&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
&nbsp;<a href="#">JOIN</a>&nbsp;<br>
</c:if>
 --%>
 
<c:choose>
	<c:when test="${sessionScope.loginName!=null}">
		&nbsp;<a href="#">MyInfo</a>&nbsp;
		&nbsp;<a href="/web01/logout"> Logout </a>&nbsp;<br>
	</c:when>
	<c:otherwise>
		&nbsp;<a href="/web01/servletTestForm/flowEx04_LoginForm.jsp">Login</a>&nbsp;
		&nbsp;<a href="#">JOIN</a>&nbsp;<br>
	</c:otherwise>
</c:choose>
	
	
	&nbsp;<a href="/web01/hello"> HelloS </a>&nbsp;
	&nbsp;<a href="/web01/list"> M01ListS </a>&nbsp;
	&nbsp;<a href="/web01/life"> LifeCycle </a><br>
	&nbsp;<a href="/web01/servletTestForm/form01_Adder.html"> Adder </a>&nbsp;
	&nbsp;<a href="/web01/servletTestForm/form02_Radio.jsp"> Radio </a>&nbsp;
	&nbsp;<a href="/web01/servletTestForm/form03_Check.jsp"> Check </a>&nbsp;
	&nbsp;<a href="/web01/servletTestForm/form04_Select.jsp"> Select </a><br>
	&nbsp;<a href="/web01/flow01"> Flow01 </a>&nbsp;
	&nbsp;<a href="/web01/sessioni"> SessionI </a><br>
	&nbsp;<a href="/web01/jsp01/ex01_HelloJsp.jsp">HelloJ </a>&nbsp;
	&nbsp;<a href="/web01/jsp01/ex02_mvc01List.jsp">M01ListJ </a>&nbsp;
	&nbsp;<a href="/web01/list2">M02List</a>&nbsp;
	
	
	
</body>
</html>