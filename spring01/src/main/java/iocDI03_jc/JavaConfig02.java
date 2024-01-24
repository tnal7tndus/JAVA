package iocDI03_jc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

//** Java Bean Configuration class를 이용한 DI
//=> Test02 : 스피커 1개 사용 TV 
//   -> 생성자를 이용한 주입,
//   -> JC에서 xml 병행사용

//** JC 에서 xml 병행 사용하기 
//=> @ImportResource("iocDI03_jc/app09.xml")
//=> AiTVs 생성은 xml로 	

@ImportResource("iocDI03_jc/app09.xml")
@Configuration
public class JavaConfig02 {
	//1) 고전적 방법 (직접 new : 소스 재컴파일)
	@Bean
	//=> 컨테이너에 의해 메서드가 실행되어 생성된 인스턴스가 컨테이너에 return
	public TV sstv() {return new SsTVs();}
	
	
	//2) IOC/DI -> 생성자 주입
//	@Bean
//	public TV lgtv() {return new LgTVs(new Speaker(), "Blue", 999999);}
	
	//위와 같은 구조로 Speaker 생성자를 만들어서 사용하는 방법
	@Bean
	public TV lgtv() {return new LgTVs(sp(), "Blue", 999999);}
	public Speaker sp() {return new Speaker();}
	
	//** 3) IOC/DI : JC에서 xml 병행 사용
	//=> jc에서 생성 후 Speaker는 @Autowired로 전달받기
	//=> Speaker를 jc로 생성 후 @Bean의 적용에 따라 다른 결과
	@Bean
	public TV aitv() {return new AiTVs();}
	
}//class
