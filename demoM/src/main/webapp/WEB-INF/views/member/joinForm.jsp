<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_MCV2 JoinForm **</title>
<link rel="stylesheet" type="text/css"
	href="/resources/myLib/myStyle.css">
<script src="/resources/myLib/inCheck.js"></script>
<script>
"use strict"  // 엄격모드 (무조건 넣어줘야 함!!)

// ** ID 중복확인
// => UI 개선사항
// => 중복확인 버튼 추가
//    처음 : 중복확인버튼_enable / submit_disable
// => 중복확인 완료후 submit이 가능하도록
//    중복확인버튼_disable / submit_enable
// => 중복확인 기능 : function idDupCheck()
//    id입력값의 무결성점검 -> id 확인요청 -> 서버로 전송 -> id, selectOne 결과 -> response: 사용가능/불가능 
// => 서버측 : 컨트롤러에 idDupCheck 요청을 처리하는 매핑메서드, view_Page(팝업창) 작성 
function idDupCheck(){
	// 1) id입력값의 무결성점검
	if( !iCheck ){
		iCheck = idCheck();
	}else{
	// 2) 서버로 id 확인요청 -> 결과는 view_Page(팝업창)으로 
		let url = "idDupCheck?id="+document.getElementById('id').value;
		window.open(url,'_blank','width=400,height=300,resizable=yes,scrollbars=yes,toolbar=no,menubar=yes')
		// _blank : 새 창 열리게 하는 속성
	}	
}//idDupCheck



//** 화살표 함수
//=> 익명함수를 간단하게 표기
// function(){....}  
// () => {....}  

//** 입력값의 무결성 점검
//=> ID 중복확인, 입력값 확인 점검

//1) 모든항목  focusout 이벤트핸들러
// => 개별항목 점검확인하는 boolean Type 변수 (스위치변수) 
// => 개별항목 점검 function() 작성
//2) submit 진행전에 점검확인
// => 모든항목의 점검이 완료된 경우에만  submit 진행
// => function inCheck() 로 확인
// => submit 버튼의 onclick 리스너에 등록
//    ( submit 의 default 이벤트 고려 )
//----------------------------------------------------
//** 실습

// ** 입력값 무결성 확인
// 1) 전역변수 정의
// => 무결성 확인 결과를 위한 switch 변수
	let iCheck = false;
	let pCheck = false;
	let p2Check = false;
	let nCheck = false;
	let aCheck = false; //age 정수 확인
	let oCheck = false; //point 실수 확인
	let bCheck = false;
	
// 2) 개별적인 확인 코드
// => 이벤트 : focusout, keydown_EnterKey 적용
// => 오류가 없으면: switch 변수 값을 true로 변환, message 삭제
// => 오류가 있다면: switch 변수 값을 false로 변환, message 출력
// => 순서: Tag인식 -> Tag의 value 가져오기 -> 무결성확인

onload=function(){
	// => window.onload : window는 생략가능
    // => body의 Tag들을 인식가능한 상태일때 실행 되도록하기위함
	
	//=> ID
    // -> keydown_EnterKey에 포커스이동 적용
    // -> 제어문자의 ascii 코드 값(참고)
    //    esc=27, EnterKey=13, Space_Bar=32
 
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
    				document.getElementById('password2').focus();
    			}//if
    		});
    document.getElementById('password').addEventListener('focusout', ()=>{pCheck=pwCheck();});
    
    //=> Password2
    document.getElementById('password2').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('name').focus();
    			}//if
    		});
    document.getElementById('password2').addEventListener('focusout', ()=>{p2Check=pw2Check();});
    
    //=> Name
    document.getElementById('name').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('age').focus();
    			}//if
    		});
    document.getElementById('name').addEventListener('focusout', ()=>{nCheck=nmCheck();});
    
    //=> Age
    document.getElementById('age').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('jno').focus();
    			}//if
    		});
    document.getElementById('age').addEventListener('focusout', ()=>{aCheck=agCheck();});
    
    //=> Jno : Focus 이동만 해줌
    document.getElementById('jno').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('info').focus();
    			}//if
    		});
    
    //=> Info : Focus 이동만 해줌
    document.getElementById('info').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('point').focus();
    			}//if
    		});
    
    //=> Point
    document.getElementById('point').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('birthday').focus();
    			}//if
    		});
    document.getElementById('point').addEventListener('focusout', ()=>{oCheck=poCheck();});
    
    //=> Birthday
    document.getElementById('birthday').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('rid').focus();
    			}//if
    		});
    document.getElementById('birthday').addEventListener('focusout', ()=>{bCheck=bdCheck();});
    
    //=> Rid : Focus 이동만
    document.getElementById('rid').addEventListener('keydown',
    		(e)=>{
    			if(e.which==13){
    				e.preventDefault();
    				document.getElementById('submitTag').focus();
    			}//if
    		});
    
}//onload


// 3) submit 실행 여부 판단 & 실행
// => 모든항목의 무결성을 확인
// => 오류가 없으면 : return true
// => 오류가 1항목이라도 있으면 : return false

function inCheck(){
	if(!iCheck){document.getElementById('iMessage').innerHTML=' 필수입력, id를 확인하세요~~';}
	if(!pCheck){document.getElementById('pMessage').innerHTML=' 필수입력, PW를 확인하세요~~';}
	if(!p2Check){document.getElementById('p2Message').innerHTML=' 필수입력, PW2를 확인하세요~~';}
	if(!nCheck){document.getElementById('nMessage').innerHTML=' 필수입력, name을 확인하세요~~';}
	if(!aCheck){document.getElementById('aMessage').innerHTML=' 필수입력, age를 확인하세요~~';}
	if(!oCheck){document.getElementById('oMessage').innerHTML=' 필수입력, point를 확인하세요~~';}
	if(!bCheck){document.getElementById('bMessage').innerHTML=' 필수입력, birthday를 확인하세요~~';}

	if(iCheck && pCheck && p2Check && nCheck
			  && aCheck && oCheck && bCheck){
		//=>submit 확인
		if(confirm("가입 진행할까요?(Yes: 확인 / No: 취소)")){
			//=>submit 진행
			return true;
		}else{
			alert(" ** 가입이 취소되었습니다 ** ");
			return false;
		}//confirm
	}else{
		return false
	}//Check_조건
	
}//inCheck
</script>

</head>
<body>
<h2>** Spring_MCV2 JoinForm **</h2>
<!--  ** FileUpLoad Form **
=> form과 table Tag 사용시 주의사항 : form 내부에 table 사용해야함
   -> form 단위작업시 인식안됨
   -> JQ의 serialize, FormData의 append all 등 

=> method="Post" : 255byte 이상 대용량 전송 가능하므로

=> <form enctype="속성값">
   <form> 태그의 데이터 (input의 value)가 서버로 제출될때 해당 데이터가 인코딩되는 방법을 명시함
 
=> enctype="multipart/form-data" : 화일 upload를 가능하게 해줌 
   ** multipart/form-data는 파일업로드가 있는 입력양식요소에 사용되는 enctype 속성의 값중 하나이고, 
      multipart는 폼데이터가 여러 부분으로 나뉘어 서버로 전송되는 것을 의미
      이 폼이 제출될 때 이 형식을 서버에 알려주며, 
      multipart/form-data로 지정이 되어 있어야 서버에서 정상적으로 데이터를 처리할 수 있다    
-->


<form action="join" method="post" enctype="multipart/form-data">
<table>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="id">I  D</label></td>
		<td><input type="text" name="id" id="id" placeholder="영문, 숫자로 4~10글자 입력하세요" size="20">
			<button type="button" id="idDup" onclick="idDupCheck()">ID중복확인</button> 
			<br><span id="iMessage" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" placeholder="특수문자 필수" size="20"></input>
			<br><span id="pMessage" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="GreenYellow"><label for="password2">PW 확인</label></td>
		<td><input type="password" id="password2" placeholder="비밀번호 재입력" size="20"></input>
			<br><span id="p2Message" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="name">Name</label></td>
		<td><input type="text" name="name" id="name" size="20">
			<br><span id="nMessage" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="age">Age</label></td>
		<td><input type="text" name="age" id="age" size="20"></input>
			<br><span id="aMessage" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="jno">Jno</label></td>
		<td><select name="jno" id="jno">
			<option value="1">1조: Business</option>
			<option value="2">2조: static</option>
			<option value="3">3조: 칭찬해조</option>
			<option value="4">4조: 카톡으로얘기하조</option>
			<option value="7">7조: 칠면조(관리팀)</option>
			</select>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="info">Info</label></td>
		<td><input type="text" name="info" id="info" placeholder="자기소개 & 희망사항" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="point">Point</label></td>
		<td><input type="text" name="point" id="point" placeholder="숫자 입력" size="20">
			<br><span id="oMessage" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="birthday">Birthday</label></td>
		<td><input type="date" name="birthday" id="birthday" size="20"></input>
			<br><span id="bMessage" class="eMessage"></span>
		</td></tr>
	<tr height="20">
		<td bgcolor="MediumPurple"><label for="rid">추천인</label></td>
		<td><input type="text" name="rid" id="rid" size="20"></td>
	</tr>
	<tr height="20">
	<!-- ** File Upload 기능추가 -->
		<td bgcolor="MediumPurple"><label for="uploadfilef">Image</label></td>
		<td><img alt="MyImage" src="" width="100" height="100" class="select_img">
		<br>
		<input type="file" name="uploadfilef" id="uploadfilef" size="20"></td>
	</tr>
	
    <script>
        document.getElementById('uploadfilef').onchange=function(e){
         if(this.files && this.files[0]) {
            let reader = new FileReader;
            reader.readAsDataURL(this.files[0]);
             reader.onload = function(e) {
                // => jQuery를 사용하지 않는경우 
                document.getElementsByClassName('select_img')[0].src=e.target.result;
                
               //$(".select_img").attr("src", e.target.result)
               //            .width(70).height(90); 
               } // onload_function
          } // if   
        }; //change  
    </script>
	
	<tr><td></td>
		<td><input type="submit" id="submitTag" value="가입" onclick="return inCheck()" disabled="disabled">&nbsp;&nbsp;
		    <!-- => Tag 의 onclick 이벤트를 작성하고, onclick 이벤트핸들러가 가지고있던
                	기본동작인 submit을 선택적으로 진행되도록 해준다. 
                	- submit 진행 : default (또는 return true)
                	- submit 정지 : submit 이벤트를 무효화 해야함 (return false 또는 이벤트.preventDefault())  -->	
				<!--
				<button type="submit" onclick="return inCheck()">ButtonTest</button>
				<button type="button" onclick="inCheck()">ButtonTest2</button>
		          ** Button Test
	           		 => default : form 내부에서는  submit와  동일하게 작동됨 
	                    inCheck()의 return 값에 따라 (true 면) submit 진행됨 
	           		 => 단, type 속성을 선택하면 (button, reset, submit 등) 속성에 맞게 실행됨
	             		예) button을 선택하면 submit 은 실행되지않음
	             	  ** Enter_Key : form 내부에서는 누르면 submit이 진행됨
	             -->
             	  
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


&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>
