package com.ncs.spring02.service;

import java.util.List;

import com.ncs.spring02.domain.MemberDTO;

public interface MemberService {

	//** selectList
	public List<MemberDTO> selectList();
	
	//** selectOne
	public MemberDTO selectOne(String id);
	
	//** insert
	public int insert(MemberDTO dto);
	
	//** update
	public int update(MemberDTO dto);
	
	//** Password_Update
	public int pwUpdate(MemberDTO dto);
	
	//** delete
	public int delete(String id);
	
	//** selectJoList
	public List<MemberDTO> selectJoList(String jno);

}
