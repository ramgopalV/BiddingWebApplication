package com.bid.bean;

public class LoginBean {
	private String username;
	private String password;
	
	public LoginBean() {
		username = "";
		password = "";
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return username;
	}
	public void setName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
