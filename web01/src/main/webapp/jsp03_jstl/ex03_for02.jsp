<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JSTL forEach begin, end, step Test **</title>
</head>
<body>
<h2>** JSTL forEach begin, end, step Test **</h2>
<pre>
=> 구간반복: StartIndex(begin), LastIndex(end), 증감값(step) 적용하기
=> step 의 default 값은 1
=> 실습 1)
   1 ~ 10 까지를 다음처럼 출력하세요 ~~
   -> 1, 2, 3, .....10         
   -> java의 예 : for (int i=1; i<11; i++) { ......  }   
=> 결과
</pre>
<b>
<c:forEach var="i" begin="1" end="10" step="1" varStatus="vs">
	${i}
	${vs.last ? "" : ", "}
</c:forEach>

<hr><pre>
=> 실습 2)
   1 ~ 10 중에서 짝수만, index, count 출력하기
   단, table 을 이용해서 출력하세요 ~~
   ex03_for01 의 table 과 비교해 보세요 ~~    
=> count : 반복횟수 
=> index : 배열등 index가 존재하는 경우에는 index 값을 출력
           반복자(iterator) 의 값   
           step 을 지정하지 않으면 1씩 증가     
</pre>
=> 결과1: step=1 <br>


=> 결과2: step=2 <br>



</b>

</body>
</html>