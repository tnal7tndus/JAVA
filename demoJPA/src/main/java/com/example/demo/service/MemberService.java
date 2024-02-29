package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;


public interface MemberService {
	
    // ** Join
    List<MemberDTO> findMemberJoin();
	
	// jno별 Member 출력
	//=> JPARepository Method Naming 규약
	//** findByJno
	public List<Member> findByJno(int jno);
	
	// ** Password Update
	//=> @Query 적용
	public void updatePassword(String id, String password);
	
	//** selectList
	public List<Member> selectList();
	
	//** selectOne
	public Member selectOne(String id);
	
	//** insert, update
	public Member save(Member entity);
	
	//** Password_Update
	public Member pwUpdate(Member entity);
	
	//** delete
	public void deleteById(String id);
	
}
