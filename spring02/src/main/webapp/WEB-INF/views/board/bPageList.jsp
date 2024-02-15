<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Board Page List **</title>
<script>
"use strict"
//1. 검색조건 입력 후 버튼 클릭 시 실행
//=> 입력값들을 서버 전송 요청 처리 : location

//** self.location   
//1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
//	=> self.location="bcrilist?currPage=?????" : 해당 요청을 서버로 전달
//2) location 객체의 메서드
//	=> href, replace('...'), reload():새로고침
function searchDB(){
	self.location = 'bPageList' + '${pageMaker.makeQuery(1)}'
					+ '&searchType='+document.getElementById('searchType').value
					+ '&keyword='+document.getElementById('keyword').value;
}
//=> searchBtn을 클릭한 경우 : 검색조건 입력 후 첫 Page 요청
//	 이때는 서버에 searchType, keyword가 전달되기 이전이므로 
//	 searchType, keyword가 없는 makeQuery 메서드사용

//** JS 코드 내부에서 el Tag 사용시 주의사항
//=> JS 코드의 스트링 내에서 사용한 el Tag는 JSP가 처리해주므로   
//	 사용가능 하지만, 이 스크립트가 외부 문서인 경우에는 처리해주지 않으므로 주의
//	 이 코드를 외부문서로 작성하면 "${pageMaker.makeQuery(1)}" 이 글자 그대로 적용되어 404 발생

// 2. searchType을 '전체'로 변경하면 keyword는 clear 
function keywordlClear(){
	if(document.getElementById('searchType').value=='all')
		document.getElementById('keyword').value='';
}

//** Board Check_List
function checkClear(){
	//document.querySelectorAll('.clear').checked=false;
	// => nodeList를 반환하기 때문에 적용안됨
	
	let ck=document.querySelectorAll('.clear');
	for(let i=0; i<ck.length; i++){
		ck[i].checked=false;
	}
	return false; //reset의 기본 이벤트 제거
	
}//checkClear

// ** querySelector
// => css 선택자를 이용하여 첫번째 만난 요소 1개만 선택

// ** querySelectorAll 
// => css 선택자를 이용하여 해당하는 nodeList 를 반환
// => ","를 사용하면 여러 요소를 한번에 가져올 수 있음
//    querySelectorAll("#id,.class");
// => 그러므로 반복문과 이용됨


</script>
</head>
<body>
<h2>** Board List **</h2>
<hr>
<c:if test="${!empty requestScope.message}">${reuqestScope.message}<br><hr>
</c:if>
<hr>
<div id="serachBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
		<option value="all" ${pageMaker.cri.searchType=='all' ? 'selected' : ''}>전체</option>
		<option value="title" ${pageMaker.cri.searchType=='title' ? 'selected' : ''}>Title</option>
		<option value="content" ${pageMaker.cri.searchType=='content' ? 'selected' : ''}>Content</option>
		<option value="id" ${pageMaker.cri.searchType=='id' ? 'selected' : ''}>ID(작성자)</option>
		<option value="regdate" ${pageMaker.cri.searchType=='regdate' ? 'selected' : ''}>RegDate</option>
		<option value="tc" ${pageMaker.cri.searchType=='tc' ? 'selected' : ''}>Title or Content</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}">
	<button id="searchBtn" onclick="searchDB()">Search</button>
	<hr>
	<!-- CheckBox Test -->
	<form action="bCheckList" method="get">
		<b>ID : </b>
		<!-- check 의 선택한 값 유지를 위한 코드 -->
	      <c:set var="ck1" value="false" />
	      <c:set var="ck2" value="false" />
	      <c:set var="ck3" value="false" />
	      <c:set var="ck4" value="false" />
	      <c:set var="ck5" value="false" />
	      <c:set var="ck6" value="false" />
      <c:forEach  var="id" items="${pageMaker.cri.check}" >
         <c:if test="${id=='simsim916'}"> <c:set var="ck1" value="true" /> </c:if>
         <c:if test="${id=='agr4005'}"> <c:set var="ck2" value="true" /> </c:if>
         <c:if test="${id=='bamboo7'}"> <c:set var="ck3" value="true" /> </c:if>
         <c:if test="${id=='kso'}"> <c:set var="ck4" value="true" /> </c:if>
         <c:if test="${id=='admin'}"> <c:set var="ck5" value="true" /> </c:if>
         <c:if test="${id=='김수미'}"> <c:set var="ck6" value="true" /> </c:if>
      </c:forEach>
      <!-- ------------------------------------------------------------------------ -->
		
		<input type="checkbox" name="check" class="clear" value="simsim916" ${ck1 ? 'checked' : ''}>최문석&nbsp;
		<input type="checkbox" name="check" class="clear" value="agr4005" ${ck2 ? 'checked' : ''}>김수빈&nbsp;
		<input type="checkbox" name="check" class="clear" value="bamboo7" ${ck3 ? 'checked' : ''}>최승삼&nbsp;
		<input type="checkbox" name="check" class="clear" value="kso" ${ck4 ? 'checked' : ''}>김수옥&nbsp;
		<input type="checkbox" name="check" class="clear" value="admin" ${ck5 ? 'checked' : ''}>관리자&nbsp;
		<input type="checkbox" name="check" class="clear" value="김수미" ${ck6 ? 'checked' : ''}>김수미&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClear()"> <br>
	</form>
	<hr>
</div>

<table style="width: 100%">

<tr bgcolor="Khaki">
	<th>seq</th><th>id</th><th>title</th><th>content</th><th>regdate</th><th>조회수</th>
</tr>
<c:if test="${!empty sessionScope.loginID }">
	<c:forEach items="${ requestScope.apple}" var="list">
		<tr>
			<td>${list.seq }</td>
			<!-- 답글 등록 후 Title 출력 전에 들여쓰기 추가  -->
			<c:if test="${list.indent>0}">
				<c:forEach begin="1" end="${list.indent}">
					<span>&nbsp;&nbsp;</span>
				</c:forEach>
				<span style="color:blue;">re..</span>
			</c:if>
			<!-- 로그인 한 경우에만 글 내용 볼 수 있도록 -->
			<c:if test="${!empty loginID}"></c:if>
				<a href="detail?jCode=D&seq=${list.seq}">${list.title}</a>
			<c:if test="${empty loginID }">
			${list.title}
			</c:if>
			<td><a href="boardDetail?jCode=D&seq=${list.seq}">${list.title }</a></td>
			<td>${list.content }</td>
			<td>${list.regdate }</td>
			<td>${list.cnt }</td>
		</tr>
	</c:forEach>
</c:if>
<c:if test="${empty sessionScope.loginID }">
	<h2>로그인 하지 않으면 못 본다</h2>
</c:if>
</table>
<br>
<hr>

<div align="center">
<!-- ** Paging Block ** 
   => ver01: QueryString 수동 입력 -> 자동생성 makeQuery 메서드 적용
   => ver02: makeQuery 매서드 -> searchQuery 메서드로 변경
   1) FirstPage, Prev 
   => OLD버전
  	 	 <a href="bPageList?currPage=1 & rowsPerPage=5">FP</a>&nbsp;
  		 <a href="bPageList?currPage=${pageMaker.spageNo-1} & rowsPerPage=5">&LT;</a>&nbsp;&nbsp;
 -->
   <c:choose>
   		<c:when test="${pageMaker.prev && pageMaker.spageNo>1}">
   		<!-- ver01 : makeQuery 메서드 적용  -->	
	   		<%-- <a href="bPageList${pageMaker.makeQuery(1)}">FP</a>&nbsp;
   			<a href="bPageList${pageMaker.makeQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;--> --%>
   		<!-- ver02 : searchQuery 메서드로 변경 -->
	   		<a href="bPageList${pageMaker.searchQuery(1)}">FP</a>&nbsp;
   			<a href="bPageList${pageMaker.searchQuery(pageMaker.spageNo-1)}">&LT;</a>&nbsp;&nbsp;-->
     	</c:when>
     	<c:otherwise>
     		<font color="gray">FP&nbsp;&LT;&nbsp;&nbsp;</font>
     	</c:otherwise>
   </c:choose>	     
<!-- 2) Display PageNo 
	 => currPage 제외한 pageNo만 a Tag 적용
-->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i==pageMaker.cri.currPage}">
			<font color="orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i!=pageMaker.cri.currPage}">
			<a href="bPageList${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
	</c:forEach>
<!-- 3) Next, LastPage  -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo>0}">
		&nbsp;<a href="bPageList${pageMaker.searchQuery(pageMaker.epageNo+1)}">&GT;</a>
		&nbsp;<a href="bPageList${pageMaker.searchQuery(pageMaker.lastPageNo)}">LP</a>
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