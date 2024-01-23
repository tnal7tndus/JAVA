package iocDI01_xml;

//** Test1. 절차지향
class SsTV{
	public void turnOn() {System.out.println("~~ SsTv turnOn ~~");}
	public void soundUp() {System.out.println("~~ SsTv soundUp ~~");}
	public void soundDown() {System.out.println("~~ SsTv soundDown ~~");}
	public void turnOff() {System.out.println("~~ SsTv turnOn ~~");}
}//SsTv

class LgTV{
	public void powerOn() {System.out.println("~~ LgTV powerOn ~~");}
	public void volumeUp() {System.out.println("~~ LgTV volumeUp ~~");}
	public void volumeDown() {System.out.println("~~ LgTV volumeDown ~~");}
	public void powerOff() {System.out.println("~~ LgTV pownerOff ~~");}
	
}//LgTV

//** Test2. 객체지향 : 다형성적용
//=> Interface, 구현을 강제 (메서드명의 동일한 규칙이 생김)
interface TV{
	void powerOn();
	void volumeUp();
	void volumeDown();
	void powerOff();
}
class SsTVi implements TV{
	public SsTVi() {System.out.println("~~ SsTVi 기본생성자 ~~");}
	public void powerOn() {System.out.println("~~ SsTvi pownerOff ~~");}
	public void volumeUp() {System.out.println("~~ SsTvi volumeDown ~~");}
	public void volumeDown() {System.out.println("~~ SsTvi volumeUp ~~");}
	public void powerOff() {System.out.println("~~ SsTvi powerOn ~~");}
	
}
class LgTVi implements TV{
	public LgTVi() { System.out.println("~~ LgTVi 기본생성자 ~~"); }
	public void powerOn() {System.out.println("~~ LgTVi pownerOff ~~");}
	public void volumeUp() {System.out.println("~~ LgTVi volumeDown ~~");}
	public void volumeDown() {System.out.println("~~ LgTVi volumeUp ~~");}
	public void powerOff() {System.out.println("~~ LgTVi powerOn ~~");}
	
}

//** Test3. Factory 패턴(IOC/DI 입문)
//=> getBean 메서드의 매개변수로 요청을 전달
class BeanFactory{
	public TV getBean(String tv) {
		if(tv != null && tv.equals("ss")) return new SsTVi();
		else if(tv != null && tv.equals("lg")) return new LgTVi();
		else return null;
	}//getBean
}//BeanFactory


public class TVUser01 {

	public static void main(String[] args) {
		//** Test1. 절차지향
		//=> TV 교체 필요 : 완전 재작성
		System.out.println("** Test1. 절차지향 **");
//		SsTV tv = new SsTV();
//		tv.turnOn();
//		tv.soundUp();
//		tv.soundDown();
//		tv.turnOff();
		
		LgTV tv = new LgTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
		
      // ** Test2. 객체지향
      // => 기본특징 : 캡슐화, 상속, 추상, 다형성(*)
      // => 다형성 적용(하나의 매서드로 다양한 )
      //   -> 관련성이 없는 두객체를 하나의 인터페이스로 묶어줌, 규칙성 부여
      //   -> 인터페이스에 정의된 메서드만 사용했다는 의미 (그러므로 교체가능)
      //   -> TV 교체 필요 : 우측의 클래스만 교체 (단, 소스코드수정-재컴파일 이 필요함)
		System.out.println("** Test2. 객체지향 **");
		TV tvi = new SsTVi(); //SsTVi(), LgTVi() 필요한거로 수정해서 사용
		tvi.powerOn();
		tvi.volumeUp();
		tvi.volumeDown();
		tvi.powerOff();
		
      // ** Test3. 소스코드 수정없이 실시간 객체 교체
      // => 객체를 생성해서 교체해줄 역할자가 필요 : Factory 패턴 (IOC/DI 입문)
      // => user 클래스의 요구사항(필요한 클래스, 의존성_Dependency) 을 Factory 에게 전달하는방법
      // => 3가지 : xml, @, JavaConfig (JavaCode)
		System.out.println("** Test3. Factory 패턴(IOC/DI 입문) **");
		BeanFactory bf = new BeanFactory();
		TV tvf = bf.getBean(args[0]);
		//=> 소스코드 수정없이 실시간으로 요청 전달 받음 : main의 매개변수 활용
		//	 main의 매개변수가 null인경우에는 args[0]의 값이 없기 때문에
		//	 java.lang.ArrayIndexOutOfBoundsException
		if(tvf != null) {
			tvf.powerOn();
			tvf.volumeUp();
			tvf.volumeDown();
			tvf.powerOff();
		}else System.out.println("** TV 선택 오류 **");
		
	}//main

}//class
