package aop04;

//** Aop 구현
//	 1단계 : 핵심적 관심사항과 공통적 관심사항 분리
//=> 핵심적 관심사항만 구현
//=> 공통적 관심사항(Aspect) 분리 : 별도의 클래스로 분리 -> MyAspect.java

public class Boy implements Programmer {

	@Override
	public String doStudying(int n) throws Exception {
		
		System.out.printf("열심히 회원관리를 %d개 만듭니다 => 핵심적 관심사항\n", n);
		
		//** Test를 위해 늘 성공으로 처리
		//=> 항상 false가 되도록
		//if (new Random().nextBoolean()) {
		if(1==2) {
			//실패
			throw new Exception("~~ Error 500 발생 => 예외발생");
		}
		return "취업성공 연봉 1억";
	}//doStudying
}//class
