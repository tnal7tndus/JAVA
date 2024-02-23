/**
// ** Ajax_REST API Join Test **
// => axios
// => file_UpLoad가 포함된 formData 처리
// => joinForm 요청: MemberController -> 웹 Page return
// => join 요청: RTestController -> 결과 Text return
*/ 

// => Axios 메서드형식 적용 (00_AJAX(공유).pptx 16p)
/*   - GET   : axios.get( url[, config] )
     - POST  : axios.post( url, data[, config] )
     - PUT   : axios.put( url, data[, config] )
     - PATCH : axios.patch( url[, data[, config]] )
     - DELETE: axios.delete( url[, config] )    
*/
"use strict"

// 1) joinForm 요청
// => response: 웹 Page
function rsJoinf(){
	let url="/member/joinForm";
	axios.get(url
	).then(response => {
		console.log('** reponse : JoinForm 성공 **');
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err => {
		alert(`** response : JoinForm 실패!!! ** => ${err.message}`);
	});
	document.getElementById('resultArea2').innerHTML='';
}//rsJoinf


// ** form Tag의 input Data 처리방법
// => 직접 입력 : multipart 타입은 처리할 수 없음
//    data: { id:document.getElementById('id').value,
//              password:document.getElementById('password').value
//           } 
// => Form의 serialize() : jQuery만 적용됨
//       -> input Tag의 name과 id가 같아야 함.   
//       -> multipart 타입은 전송안됨. 
//          처리하지 못하는 값(예-> file Type)은 스스로 제외시킴 
// => 객체화 : multipart 타입은 처리할 수 없음
//       let myData = {
//        	    id:document.getElementById('id').value,
//         		password:document.getElementById('password').value
//         }

// => FormData 객체 활용 : JS의 내장객체
//      -> append 메서드 : multipart 타입 처리 불편
//      -> 생성자 매개변수 이용 : multipart 타입 포함 간편한 처리가능 

// 2) Join 처리
// => multipart 타입 포함
// => JS의 내장객체 FormData 사용
//      -> 요청_headers "Content-Type" 변경

function axiJoin(){
	// 2.1) Data 전송준비
	let formData = new FormData(document.getElementById('myform'));
	
	// 2.2) Axios 요청처리
	let url="/rest/rsjoin";
	axios.post(url, formData, {headers:{'Content-Type' : 'multipart/form-data'}
	}).then(response => {
		alert(`** join 성공 => ${response.data}`);
		location.reload();
	}).cathch(err => {
		if(err.response.status=='502')alert(' !! 입력 오류 !! 다시 입력하세요.');
		else alert(` !! 시스템 오류 !! 기다려주세요.=> ${err.message}`);
	});
	document.getElementById('resultArea2').innerHTML='';
}//axiJoin





