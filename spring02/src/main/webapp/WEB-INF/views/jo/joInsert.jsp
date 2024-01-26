<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>JO Insert Form</h2>

<form action="Insert">
	Jno: <input type="text" name="jno" id="id"><br>
	Jname: <input type="text" name="jname" id="jname"><br>
	Captain: <input type="text" name="captain" id="captain"><br>
	Project: <input type="text" name="project" id="project"><br>
	Slogan: <input type="text" name="slogan" id="slogan"><br>
	<button>등록</button>
	
</form>
<br>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>

</body>
</html>