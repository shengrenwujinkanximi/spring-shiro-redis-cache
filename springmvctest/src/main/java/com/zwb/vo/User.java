package com.zwb.vo;

public class User {
	private String userName;
	private String password;
	private boolean isRemeberMe;
	public boolean isRemeberMe() {
		return isRemeberMe;
	}
	public void setRemeberMe(boolean isRemeberMe) {
		this.isRemeberMe = isRemeberMe;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
