package com.example.demo.repository;


import org.springframework.stereotype.Repository;

import com.example.demo.domain.UserDTO;
import com.example.demo.entity.User;

@Repository
public interface UserRepository{
	
	/* 🎃🎃🎃🎃🎃🎃 검수 전 🎃🎃🎃🎃🎃🎃 */

	
	
	/* 🎃🎃🎃🎃🎃🎃 수미 🎃🎃🎃🎃🎃🎃 */
	
	public User selectUser(UserDTO dto);
	
	public int insertUser(UserDTO dto);
}
