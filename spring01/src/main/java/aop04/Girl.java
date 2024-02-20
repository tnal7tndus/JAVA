package aop04;

public class Girl implements Programmer {
	
	@Override
	public String doStudying(int n) throws Exception {
		
		System.out.printf("열심히 회원관리를 %d개 만듭니다 => 핵심적 관심사항\n" , n);
		
		//** Test를 위해 늘 실패로 처리
		//=> 항상 true가 되도록
		//if (new Random().nextBoolean()) {
		if(true) {
			//실패
			throw new Exception("~~ Error 404 발생 => 예외발생");
		}
		return "취업성공 연봉 1억";
	}//doStudying
}//class
