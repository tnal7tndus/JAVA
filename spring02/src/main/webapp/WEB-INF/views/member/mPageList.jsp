<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Member Page List **</title>
<script>
"use strict"
// 1. 검색조건 입력 후 버튼클릭
// => 입력값들을 서버로 전송 요청 처리 : location
function searchDB(){
	self.location='mPageList'
		        + '?currPage=1&rowsPerPage=5'
		        +'&searchType='+document.getElementById('searchType').value
		        +'&keyword='+document.getElementById('keyword').value;
}
// 2. searchType을 '전체'로 변경하면 keyword는 clear 
function keywordlClear(){
	if(document.getElementById('searchType').value=='all')
		document.getElementById('keyword').value='';
}
//** Board Check_List
function checkClear(){
	
	let ck=document.querySelectorAll('.clear');
	for(let i=0; i<ck.length; i++){
		ck[i].checked=false;
	}
	return false; //reset의 기본 이벤트 제거
}//checkClear
</script>

</head>
<body>
<h2>** Member List **</h2>
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
		<input type="checkbox" name="check" class="clear" value="1" ${ck1 ? 'checked' : ''}>Business&nbsp;
		<input type="checkbox" name="check" class="clear" value="2" ${ck2 ? 'checked' : ''}>static&nbsp;
		<input type="checkbox" name="check" class="clear" value="3" ${ck3 ? 'checked' : ''}>칭찬해조&nbsp;
		<input type="checkbox" name="check" class="clear" value="4" ${ck4 ? 'checked' : ''}>카톡으로얘기하조&nbsp;
		<input type="checkbox" name="check" class="clear" value="7" ${ck5 ? 'checked' : ''}>칠면조&nbsp;
		<input type="submit" value="Search">&nbsp;
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
		<td><img alt="myImage" src="/spring02/resources/images/${v.uploadfile}" width="50" height="50"></td>
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
<!-- 1)  -->
   <c:choose>
   		<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
	   		<a href="${pageMaker.searchQuery(1)}">FP</a>&nbsp;
   			<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;-->
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
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>
		</c:when>
		<c:otherwise>
			<font color="gray">&nbsp;&GT;&nbsp;LP</font>
		</c:otherwise>
	</c:choose>
</div>
	
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="boardInsert">게시글등록</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>