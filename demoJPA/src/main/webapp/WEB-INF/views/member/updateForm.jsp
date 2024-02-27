<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** UpdateForm **</title>
</head>
<body>
<h2>** Web MCV2 UpdateForm **</h2>
<form action="update" method="post" enctype="multipart/form-data">
<table>
	<tr height="20">
		<td bgcolor="Pink"><label for="id">I  D</label></td>
		<td><input type="text" name="id" id="id" value="${requestScope.apple.id}" readonly size="20"></td>
	</tr>
	<%-- PasswordEncoder 적용 후 분리함
	
	 <tr height="20">
		<td bgcolor="Pink"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" value="${requestScope.apple.password}" size="20"></input></td>
	</tr> --%>
	<tr height="20">
		<td bgcolor="Pink"><label for="name">Name</label></td>
		<td><input type="text" name="name" id="name" value="${requestScope.apple.name}" size="20"></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="age">Age</label></td>
		<td><input type="text" name="age" id="age" value="${requestScope.apple.age}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="jno">Jno</label></td>
		<td><select name="jno" id="jno">
			<option value="1" ${requestScope.apple.jno==1 ? "selected":""}>1조: Business</option>
			<option value="2" ${requestScope.apple.jno==2 ? "selected":""}>2조: static</option>
			<option value="3" ${requestScope.apple.jno==3 ? "selected":""}>3조: 칭찬해조</option>
			<option value="4" ${requestScope.apple.jno==4 ? "selected":""}>4조: 카톡으로얘기하조</option>
			<option value="7" ${requestScope.apple.jno==7 ? "selected":""}>7조: 칠면조(관리팀)</option>
			</select>
		</td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="info">Info</label></td>
		<td><input type="text" name="info" id="info" value="${requestScope.apple.info}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="point">Point</label></td>
		<td><input type="text" name="point" id="point" value="${requestScope.apple.point}" size="20"></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="birthday">Birthday</label></td>
		<td><input type="date" name="birthday" id="birthday" value="${requestScope.apple.birthday}" size="20"></input></td>
	</tr>
	<tr height="20">
		<td bgcolor="Pink"><label for="rid">추천인</label></td>
		<td><input type="text" name="rid" id="rid" value="${requestScope.apple.rid}" size="20"></td>
	</tr>
	<!-- Image Update 추가 
         => form Tag : method, enctype 확인
         => new Image를 선택하는 경우 -> uploadfilef 사용
         => new Image를 선택하지않는 경우 
            -> 본래 Image를 사용 -> uploadfile 값이 필요함 (hidden으로 보관)
   	-->   
	<tr height="20">
		<td bgcolor="Pink"><label for="uploadfilef">Image</label></td>
		<td><img alt="MyImage" width="100" height="100" class="select_img"
				src="/resources/uploadImages/${requestScope.apple.uploadfile}">
			<input type="hidden" name="uploadfile" id="uploadfile" value="${requestScope.apple.uploadfile}">
			<br>
			<input type="file" name="uploadfilef" id="uploadfilef" size="20">
		</td>
		
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
      
	</tr>
	<tr><td></td>
		<td><input type="submit" value="수정">&nbsp;&nbsp;
			<input type="reset" value="취소">
		</td>
	</tr>
</table>
</form>
<br><hr>
&nbsp;<a href="pwUpdate">비밀번호 수정</a>&nbsp;
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}
</c:if>
<br><hr>


&nbsp;<a href="/home">HOME</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>
