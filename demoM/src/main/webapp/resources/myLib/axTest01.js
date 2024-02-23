/**
// ** Ajax_REST API Login Test **
// 1. fetch
// => then 1단계  
//       * response 속성값 
//         response.status – HTTP 상태코드(예: 200)
//         response.ok - Boolean, HTTP 상태 코드가 200-299 사이이면 True
//         response.headers – HTTP 헤더를 담고있는 맵과 유사한 객체
//       * Body-reading 메서드
//         response.text() – 응답을 읽고 텍스트로 반환
//         response.json() – 응답을 JSON으로 구문 분석
//         response.formData() – 응답을 FormData객체로 반환
//         response.blob() – 응답을 Blob (유형이 있는 이진 데이터)으로 반환
//         response.arrayBuffer() – 응답을 ArrayBuffer (바이너리 데이터의 저수준 표현)로 반환
//         response.body - ReadableStream 객체이므로 본문을 청크별로 읽을 수 있다.

//=> catch: then 1단계에서 발생시킨 Error객체의 매개변수값을 인자의 message 속성으로 전달받아 처리함
         
// => Test1) Post요청: rsLogin(), rsLoginJJ: JSON -> Text, JSON
// => Test2) Get요청: rsDetail() -> selectOneJno 
// => Test3) Delete요청: rsDelete
 */
"use strict"
// Test1) rsLogin
// 1.1) form 출력 하기
function rsLoginf(){
	let resultHtml = 
	`<table align center>
		<caption><h3>** Ajax Login Form **</h3></cpation>
		<tr height=40><td bgcolor="aqua"><label for="id">ID</label></td>
			<td><input type="text" name="id" id="id"></td>
		</tr>
		<tr height=40><td bgcolor="aqua"><label for="password">Password</label></td>
			<td><input type="password" name="password" id="password"></td>
		</tr>
		<tr height=40>
			<td colspan="2"><span class="textlink" onclick="rsLogin()">rsLogin</span>&nbsp;&nbsp;
				<span class="textlink" onclick="rsLoginjj()">rsLoginJJ</span>&nbsp;&nbsp;
				<span class="textlink" onclick="axiLoginjj()">axiLoginJJ</span>&nbsp;&nbsp;
				<input type="reset" value="취소">
			</td>
		</tr>
	</table>`
	
	document.getElementById('resultArea1').innerHTML=resultHtml
}//rsLoginf

// 1.2) Login 기능 Service 요청처리
// => Ajax 요청, fetch 적용
// => @RestController, 계층적 uri 적용, Post 요청
// => request: JSON, response: Text 
function rsLogin(){
	let url="/rest/rslogin";
	
	fetch(url,{
		method:'Post',
		body:JSON.stringify({
			id:document.getElementById('id').value,
			password:document.getElementById('password').value
			}), //body완성
		headers:{'Content-type':'application/json'}
        // => POST 요청에서는 반드시 headers 형식 작성 
        //    (JSON 포맷을 사용함을 알려줘야함)				
	}).then(response => {
        // ** then 1단계
        // => status 확인 -> Error catch 블럭으로 또는 Response Body-reading Data return
        // => Response Body의 Data-reading & return.		
		if (!response.ok) throw new Error(response.status);
		// => Error 임을 인지시켜 catch 블럭으로 분기
        //   - fetch는 네트워크 장애등으로 HTTP요청을 할 수 없거나,
        //     url에 해당하는 페이지가 없는 경우에만 거부(reject)되어 catch로 분기하므로,
        //     .then절(1단계)에서 수동으로 HTTP 에러를 처리함.
        //     그러나 axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject)함.
	
		return response.text();
		// => 서버에서 Text 형식으로 보냈으므로 text() 메서드 사용
		//	  (Type별로 Body-reading method를 적용 함)
	}).then(responseData => {
		// ** then 2단계
        // => 1단계에서 return한 Data 처리
		alert(`** response Data => ${responseData}`)
		location.reload();	//새로고침
	}).catch(err => {
		console.error(`** Error => ${err.message}`);
		if(err.message =='502') alert('id 또는 password 오류입니다! 다시 입력하세요.');
		else alert(`System 오류입니다! 잠시 후 이용해주세요. status => ${err.message}`);
	});
}//rsLogin

// 1.3) rsLoginjj
// => request: JSON, response: JSON 
function rsLoginjj(){
	let url="/rest/rsloginjj";
	
	fetch(url,{
		method:'Post',
		body:JSON.stringify({
			id:document.getElementById('id').value,
			password:document.getElementById('password').value
			}), //body완성
		headers:{'Content-type':'application/json'}
	}).then(response => {
		if (!response.ok) throw new Error(response.status);
	
		return response.json();
		// => 서버에서 JSON 형식으로 보냈으므로 json() 메서드 사용
      	//    서버에서 UserDTO를 사용했으므로 사용시에 맴버변수명 주의(id, username..) 
	}).then(responseData => {
		alert(`** response Data: id=${responseData.id}, name=${responseData.username}`)
		location.reload();	//새로고침
	}).catch(err => {
		console.error(`** Error => ${err.message}`);
		if(err.message =='502') alert('id 또는 password 오류입니다! 다시 입력하세요.');
		else alert(`System 오류입니다! 잠시 후 이용해주세요. status => ${err.message}`);
	});
}//rsLoginjj

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// 2. Axios Login
// => 라이브러리 추가 (CDN 으로..   axTestForm.jsp에)
// => 서버요청은 위 "1.3) rsLoginJJ" 과 동일하게 rsloginjj 
// => JSON <-> JSON
// => Request
//   - data  : JSON Type 기본 (fetch 처럼 JSON.stringify 필요없음) 
//   - header: {'Content-Type': 'application/json'}  
// => Response
//   - then : 응답 Data는 매개변수.data로 접근가능, JSON Type 기본 (1단계로 모두 받음: fetch와 차이점))   
//   - catch
//     . axios는 상태코드가 2xx의 범위를 넘어가면 거부(reject) 되어 catch절로 분기함 
//       이때 catch절의 매개변수는 response 속성으로 error 내용 전체를 객체형태로 전달받음   
//     . error.response : error 내용 전체를 객체형태로 전달받음
//     . error.response.status : status 확인가능   
//     . error.message : 브라우져의 Error_Message, "Request failed with status code 415
function axiLoginjj(){
	let url="/rest/rsloginjj"
	
	axios({
		url:url,
		method:'Post',
		headers: {'Content-Type':'application/json'},
		data: {id:document.getElementById('id').value,
			   password:document.getElementById('password').value
		 	  }
	}).then(response => {
			alert(`** response.data => ${response.data}`);
			alert(`** response.id = ${response.data.id}, name=${response.data.username}`);
			location.reload();	//새로고침
	}).catch(err =>{
			console.error(`** err.response=${err.response},
							err.response.status=${err.response.status},
							err.message=${err.message}`);
			if(err.response.status=='502') alert(`id 또는 password 오류입니다! 다시 입력하세요.`);
			else alert(`System 오류입니다! 잠시 후 다시하세요. status=> ${err.message}`);
	});
}//axiLoginjj










