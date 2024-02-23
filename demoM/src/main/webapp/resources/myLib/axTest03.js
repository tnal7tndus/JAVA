// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response(axmemberList.jsp)
//   - idbList(id별 boardList) : RTestController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// 1. List 출력 
// 1.1) Page response
// => response를 reasultArea1에 출력하기
// => 요청명: /member/aximlist
// => response: axmemberList.jsp
function axiMListf(){
	let resultHtml =
	`<table align center>
		<caption><h2>** Web02_MVC02 MemberList **</h2></cpation>
		<tr height=50><td bgcolor="yellow"><label for="id">ID</label></td>
			<td><input type="text" name="id" id="id"></td>
		</tr>
		<tr height=50><td bgcolor="yellow"><label for="name">Name</label></td>
			<td><input type="name" name="name" id="name"></td>
		</tr>
	</table>`
	
	document.getElementById('resultArea1').innerHTML=resultHtml
	
function axiMList(){
		
	let url="/member/aximlist";

	axios.get(
		url).then(response => {
			console.log("** response 성공 **");
			document.getElementById('resultArea1').innerHTML=response;
	}).catch(err => {
			alert("** response 실패 => " + err.message);
	});
	
	document.getElementById('resultArea2').innerHTML="";
	
}//axiMList