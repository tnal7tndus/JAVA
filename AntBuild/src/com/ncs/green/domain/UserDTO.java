package com.ncs.green.domain;

public class UserDTO {

	private String id;
	private String name;
	private String loginTime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", password=" + name + ", loginTime=" + loginTime + "]";
	}
	
	
}//class
