package iocDI03_jc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

//** Java Bean Configuration class를 이용한 DI
//=> Test04 : xml 을 이용해서 JC, @ 병행 처리 Test
//            그러므로 Bean 컨테이너는 GenericXmlApplicationContext 사용
//=> 스피커 2개 중 선택

public class TVUser11 {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너 생성
		AbstractApplicationContext sc = new
				GenericXmlApplicationContext("iocDI03_jc/app11.xml");

		// 2. 필요한 객체를 전달받고 서비스 실행
		System.out.println("** 1) 고전적 방법 (직접 new : 소스 재컴파일) **");
		TV sstv = (TV) sc.getBean("sstv");  //getBean = 만들어진 객체를 가지고 오는 메서드
		sstv.powerOn();
		sstv.volumeUp();
		sstv.volumeDown();
		sstv.powerOff();

		System.out.println("** 2) IOC/DI -> 생성자 주입 **");
		TV lgtv = (TV) sc.getBean("lgtv");
		lgtv.powerOn();
		lgtv.volumeUp();
		lgtv.volumeDown();
		lgtv.powerOff();

		System.out.println("** 3) IOC/DI -> setter 주입 **");
		TV aitv = (TV) sc.getBean("aitv");
		aitv.powerOn();
		aitv.volumeDown();
		aitv.powerOff();

		sc.close();
		
	}//main

}//class
