package aop02;

import java.util.Random;

public class Girl implements Programmer {
	
	@Override
	public void doStudying() throws Exception {
		
		System.out.println("열심히 회원관리를 만듭니다 => 핵심적 관심사항");
		
		//** Test를 위해 늘 실패로 처리
		//=> 항상 true가 되도록
		//if (new Random().nextBoolean()) {
		if(true) {
			//실패
			throw new Exception("~~ Error 404 발생 => 예외발생");
		}
	}//doStudying
}//class
