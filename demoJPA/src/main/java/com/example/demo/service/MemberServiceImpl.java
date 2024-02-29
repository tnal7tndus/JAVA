package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository repository;
	
    // ** Join
    @Override
    public List<MemberDTO> findMemberJoin() {
       return repository.findMemberJoin();
    }
	
	//** findByJno
	@Override
	public List<Member> findByJno(int jno) {
		return repository.findByJno(jno);
	}
	
	// ** Password Update
	//=> @Query 적용
	@Override
	public void updatePassword(String id, String password) {
		repository.updatePassword(id, password);
	}
	
	//** selectList
	@Override
	public List<Member> selectList() {
		return repository.findAll();
	}
	
	//** selectOne
	@Override
	public Member selectOne(String id) {
		
		Optional<Member> result = repository.findById(id);
		if(result.isPresent()) return result.get();
		else return null;
	}
	
	//** insert, update
	@Override
	public Member save(Member entity) {
		return repository.save(entity);
	}
	
	//** Password_Update
	@Override
	public Member pwUpdate(Member entity) {
		return null;
	}
	
	//** delete
	@Override
	public void deleteById(String id) {
		 repository.deleteById(id);
	
	}
	
}
