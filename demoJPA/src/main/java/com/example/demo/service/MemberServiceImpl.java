package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import mapperInterface.MemberMapper;
import pageTest.SearchCriteria;

//** Mybatis 적용
//=> CRUD 처리를 Mapper 를 이용
//=> DAO 대신 Mapper interface ->  ~Mapper.xml

//** Mybatis interface 방식으로 적용
//=> MemberDAO 대신 MemberMapper 사용
//=> MemberMapper의 인스턴스를 스프링이 생성해주고 이를 주입받아 실행함
// 	 (스프링이 생성해주는 동일한 타입의 클래스는 JUnit Test 로 확인가능, 추후 실습) 
//=> 단, 설정화일에 <mybatis-spring:scan base-package="mapperInterface"/> 반드시 추가해야함
//	 MemberDAO의 Sql구문 처리를 mapperInterface 사용으로 MemberMapper가 역할을 대신함

//=> SQL 구문 : xml 로작성 -> 이 화일을 Mapper 라고함
//=> Mapper 작성규칙
//  -> mapperInterface와 패키지명, 화일명이 동일해야함
//	-> 즉, Java Interface, Mapper, Mapper의 namespace 값(패키지와 화일명)이 모두 동일해야함
//	-> 그리고 해당 메서드는 Mapper의 xml 구문의 id 속성값으로 찾음

@Service
public class MemberServiceImpl implements MemberService {
	//** 전역변수 정의
	private final MemberRepository repository;
	
	
	//** selectList
	public List<Member> selectList(){
		return repository.findAll();
	}
	
	//** selectOne
	public MemberDTO selectOne(String id) {
		return repository.selectOne(id);
	}
	
	//** insert
	public int insert(MemberDTO dto) {
		return repository.insert(dto);
	}
	//** update
	public int update(MemberDTO dto) {
		return repository.update(dto);
	}
	
	//** Password_Update
	public int pwUpdate(MemberDTO dto) {
		return repository.pwUpdate(dto);
	}
	
	//** delete
	public int delete(String id) {
		return repository.delete(id);
	}
	
	//** selectJoList
	public List<MemberDTO> selectJoList(String jno) {
		return repository.selectJoList(jno);
	}
}
