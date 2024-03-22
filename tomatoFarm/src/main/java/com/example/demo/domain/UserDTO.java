package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder
@Setter
public class UserDTO {

	private String id;
	private String password;
	private String level;
	private String username;
	private String phonenumber;
	private Integer address_code;
	private String address1;
	private String address2;
	private String email;
	private String email2;
	private Integer gender;
	private String birthdate;
	private Integer point;
	
}
