package com.example.demo.service;

import com.example.demo.domain.UserDTO;
import com.example.demo.entity.User;

public interface UserService {

	
	default User dtotoEntity(UserDTO dto) {
		User entity = User.builder().id(dto.getId()).password(dto.getPassword()).level(dto.getLevel()).name(dto.getName())
				.phonenumber(dto.getPhonenumber()).address_code(dto.getAddress_code()).address1(dto.getAddress1()).address2(dto.getAddress2())
				.email(dto.getEmail()).email2(dto.getEmail2()).gender(dto.getGender()).birthdate(dto.getBirthdate())
				.point(dto.getPoint()).build();
		return entity;
	}

	
	
	default UserDTO entityToDTO(User entity) {
		UserDTO dto = UserDTO.builder().id(entity.getId()).password(entity.getPassword()).level(entity.getLevel()).name(entity.getName())
				.phonenumber(entity.getPhonenumber()).address_code(entity.getAddress_code()).address1(entity.getAddress1()).address2(entity.getAddress2())
				.email(entity.getEmail()).email2(entity.getEmail2()).gender(entity.getGender()).birthdate(entity.getBirthdate())
				.point(entity.getPoint()).build();
		return dto;
	}
	
	public User selectUser(UserDTO dto);
	
	public int insertUser(UserDTO dto);
	
//	=================================
	
}
