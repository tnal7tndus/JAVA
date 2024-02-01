<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MCV2 LoginForm **</title>
<link rel="stylesheet" type="text/css" href="/spring02/resources/mylib/myStyle.css">
<script src="/spring02/resources/myLib/inCheck.js"></script>
<script>
"use script"
let iCheck=false;
let pCheck=false;
onload=function(){
	
	document.getElementById('id').focus();
    document.getElementById('id').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
   		            // => form 에서는
   	                // => enter 누르면 자동 submit 발생되므로 이를 제거함
    				document.getElementById('password').focus();
    			}//if
    		});
    //-> 무결성 점검
    document.getElementById('id').addEventListener('focusout', ()=>{iCheck=idCheck();});

    //=> Password
    document.getElementById('password').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('submitTag').focus();
   	                 // => password에서 입력후 Enter_Key 누르면 바로 submit 진행 되도록~~
   	                 //    type="submit"을 사용하는경우 정확하게 적용하기 어려워 적용하지 않음    
   	                 //if (!iCheck) iCheck=idCheck();
   	                 //else if (!pCheck) pCheck=pwCheck();
   	                 //else document.getElementById('myForm').submit();
    			}//if
    		});
    // -> 무결성 점검
    document.getElementById('password').addEventListener('focusout', ()=>{pCheck=pwCheck();});
    
}//onload

// 3) submit 실행 여부 판단 & 실행
// => 모든항목의 무결성을 확인
// => 오류가 없으면 : return true
// => 오류가 1항목이라도 있으면 : return false

function inCheck(){
	if(!iCheck){document.getElementById('iMessage').innerHTML=' 필수입력, id를 확인하세요~~';}
	if(!pCheck){document.getElementById('pMessage').innerHTML=' 필수입력, PW를 확인하세요~~';}
	if( iCheck && pCheck ){
		return true;
		}else{
			return false;
		}//Check_조건
}//inCheck
</script>
</head>
<body>
<h2>** Spring_MCV2 LoginForm **</h2>
<form action="login" method="post">
<table>
	<tr height="20">
		<td bgcolor="aqua"><label for="id">I  D</label></td>
		<td><input type="text" name="id" id="id" size="20"></td>
			<br><span id="iMessage" class="eMessage"></span>
	</tr>
	<tr height="20">
		<td bgcolor="aqua"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" size="20"></input></td>
		<br><span id="pMessage" class="eMessage"></span>
	</tr>
	<tr><td></td>
		<td><input type="submit" id="submitTag" value="로그인" onclick="return inCheck()">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<hr>

<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<hr>
&nbsp;<a href="/spring02/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;



</body>
</html>