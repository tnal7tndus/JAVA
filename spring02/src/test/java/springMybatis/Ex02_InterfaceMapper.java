package springMybatis;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.MemberDAO;

import mapperInterface.MemberMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class Ex02_InterfaceMapper {

    // ** Interface Mapper 설정
    // => Controller -> Service -> (DAO) -> interface Mapper : xml의 sql구문을 이용해서 DB처리
	@Autowired
	MemberMapper mapper;
	// => 성공: MemberMapper mapper = new MemberMapper구현객체 ;
    //    -> 구현객체 생성부터는 Spring과 Mybatis가 규칙에 의해 처리해줌 
    //    -> 규칙: 패키지명과 클래스명을 interface, mapper xml, xml의 namespace 모두 동일하게 해줌.
    //           이를 위한 경로 설정 
    //           <mybatis-spring:scan base-package="mapperInterface"/>  
	
	@Autowired
	MemberDAO dao;
	
	@Autowired
	MemberDTO dto;
	
	@Test
	//** mapper 동작 Test
    // => getClass().getName() : 실제동작하는 클래스(MemberMapper의 구현객체)의 이름 확인가능
    //    이를 통해 우리는 Mapper interface만 작성했지만, 
    //    내부적으로는 동일한 타입의 클래스가 만들어졌음을 알 수 있다.  
	public void mapperTest() {
		assertNotNull(mapper);
		System.out.println("** MemberMapper Interface 구현객체 => " + mapper.getClass().getName());
		System.out.println("** dto 인스턴스의 동작하는 클래스명 => " + dto.getClass().getName());
	}
	
	//** mapper의 메서드 Test
	//=> Mybatis 사용시 주의사항
	//	-> 참조형 매개변수 사용시 매개변수 주소를 공유하지 않음 주의
	//	   selectDTO(MemberDTO dto) 형식
	//	-> 매개변수는 Type은 무관하지만 개수는 1개만 사용 가능(기본규칙)
	//	   그러므로 주로 객체형으로 사용하지만, 복수의 매개변수를 사용하려면 @Param을 이용할 수 있음
	//	-> xml 대신 @ 으로 Sql 구현 가능
	
	//1) selectOne
	@Test
	public void selectOne() {
		String id = "yellow";	 //yellow, black
		dto = mapper.selectOne(id);
		System.out.println("** selectOne => " + dto);
		assertNotNull(dto);
	}
	
	//2) selectDTO(MemberDTO dto) 형식
	//=> MemberDAO와 Mybatis 비교
	//	 참조형 매개변수 사용시 Mybatis는 매개변수 주소를 공유하지 않음 주의
	@Test
	public void selectDTO() {
		
		//2.1) MemberDAO 적용시
		MemberDTO dto1 = new MemberDTO();
		dto1.setId("yellow");
		dao.selectDTO(dto1);
		System.out.println("** MemberDAO selectDTO() => " + dto1);
		
		//2.2) Mybatis 적용시
		MemberDTO dto2 = new MemberDTO();
		mapper.selectDTO(dto2); 
		//dto2=mapper.selectDTO(dto2);
		System.out.println("** selectDTO() => " + dto2);
	}
	
	@Test
	//3) 복수의 매개변수 사용 Test
	//=> Mybatis에서 2개 이상의 매개변수 처리
	//=> Mapper interface에서 @Param 적용가능
	//=> selectParam (id, jno)
	public void paramTest() {
		dto = mapper.selectParam("yellow", 7);
		System.out.println("** Mybatis Param Test => " +dto);
		assertNotNull(dto);
	}
	
	
	
}//class
