<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring Boot Axios MemberPageList **</title>
<script>
"use strict"

// ** 검색 & 페이징 포함한 요청의 Ajax 처리
// => Ajax 요청 function 작성, url을 매개변수로 전달 : axiMListCri(url) 
// => Page 요청 : aTag -> span으로 변경하고 function 으로 처리 
// => Check 검색은 submit을 사용하기 때문에 적용하지 않음(주석처리)

// => Ajax 처리시에는 문서내부의 function이 인식 안되므로
//    searchDB(), keywordClear() 모두 axTest03.js 에 작성  

</script>
</head>
<body>
<h2>** Spring Boot Axios MemberPageList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">${reuqestScope.message}<br><hr>
</c:if>
<hr>
<div id="serachBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType=='all' ? 'selected' : ''}>전체</option>
		<option value="id" ${pageMaker.cri.searchType=='id' ? 'selected' : ''}>ID</option>
		<option value="name" ${pageMaker.cri.searchType=='name' ? 'selected' : ''}>Name</option>
		<option value="age" ${pageMaker.cri.searchType=='age' ? 'selected' : ''}>나이</option>
		<option value="birthday" ${pageMaker.cri.searchType=='birthday' ? 'selected' : ''}>Birthday</option>
		<option value="info" ${pageMaker.cri.searchType=='info' ? 'selected' : ''}>Info</option>
		<option value="rid" ${pageMaker.cri.searchType=='rid' ? 'selected' : ''}>추천인</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn" onclick="searchDB()">Search</button>
	<hr>
	<!-- CheckBox Test -->
	<form action="mCheckList" method="get">
		<b>Jno : </b>
	      <c:set var="ck1" value="false" />
	      <c:set var="ck2" value="false" />
	      <c:set var="ck3" value="false" />
	      <c:set var="ck4" value="false" />
	      <c:set var="ck5" value="false" />
	      <c:set var="ck6" value="false" />
      <c:forEach  var="id" items="${pageMaker.cri.check}" >
         <c:if test="${jno=='1'}"> <c:set var="ck1" value="true" /> </c:if>
         <c:if test="${jno=='2'}"> <c:set var="ck2" value="true" /> </c:if>
         <c:if test="${jno=='3'}"> <c:set var="ck3" value="true" /> </c:if>
         <c:if test="${jno=='4'}"> <c:set var="ck4" value="true" /> </c:if>
         <c:if test="${jno=='7'}"> <c:set var="ck5" value="true" /> </c:if>
      </c:forEach>
      <!-- ------------------------------------------------------------------------ -->
		<input type="checkbox" name="check clear" value="1" ${ck1 ? 'checked' : ''}>Business&nbsp;
		<input type="checkbox" name="check clear" value="2" ${ck2 ? 'checked' : ''}>static&nbsp;
		<input type="checkbox" name="check clear" value="3" ${ck3 ? 'checked' : ''}>칭찬해조&nbsp;
		<input type="checkbox" name="check clear" value="4" ${ck4 ? 'checked' : ''}>카톡으로얘기하조&nbsp;
		<input type="checkbox" name="check clear" value="7" ${ck5 ? 'checked' : ''}>칠면조&nbsp;
		<!-- <input type="submit" value="Search">&nbsp; -->
		<button type="button" onclick="axiMListCheck()">Check</button>
		<input type="reset" value="Clear" onclick="return checkClear()"> <br>
	</form>
	<hr> 
</div>

<table border="1" sytle="width:100%">
<tr bgcolor="Lime">
	<th>ID</th><!-- <th>Password</th> --><th>Name</th><th>Age</th><th>Jno</th>
	<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th>				
</tr>
<c:if test="${!empty requestScope.apple}">
	<c:forEach var="v" items="${requestScope.apple}">
		<tr><td>${v.id}</td><%-- <td>${v.password}</td> --%><td>${v.name}</td><td>${v.age}</td><td>${v.jno}</td>
		<td>${v.info}</td><td>${v.point}</td><td>${v.birthday}</td><td>${v.rid}</td>
		<td><img alt="myImage" src="resources/images/${v.uploadfile}" width="50" height="50"></td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty requestScope.apple}">
	<tr>
	<td colspan="9">~~ 출력 자료가 1건도 없습니다 .~~</td>
	</tr>
</c:if>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<hr>
</table>
<br>
<hr>
<div align="center">
<!-- 1)Prev, First  -->
   <c:choose>
   		<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
	   		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(1)}')">FP</span>&nbsp;
	   		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.spageNo-1)}')">&LT;</span>&nbsp;
     	</c:when>
     	<c:otherwise>
     		<font color="gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
     	</c:otherwise>
   </c:choose>	     
<!-- 2) Display PageNo 
-->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
	</c:forEach>
<!-- 3) Next, LastPage  -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
	   		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.epageNo+1)}')">&GT;</span>&nbsp;
	   		<span class="textlink" onclick="axiMListCri('${pageMaker.searchQuery(pageMaker.lastPageNo)}')">LP</span>&nbsp;
		</c:when>
		<c:otherwise>
			<font color="gray">&nbsp;&GT;&nbsp;LP</font>
		</c:otherwise>
	</c:choose>
</div>
	
&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="boardInsert">게시글등록</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>