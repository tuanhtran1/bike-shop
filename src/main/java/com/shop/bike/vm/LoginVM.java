package com.shop.bike.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginVM extends UsernameVM{
	
	@NotNull
	@Size(min = 4, max = 100)
	private String password;
	
	private boolean rememberMe;
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isRememberMe() {
		return rememberMe;
	}
	
	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	// prettier-ignore
	@Override
	public String toString() {
		return "LoginVM{" +
				"username='" + getUsername() + '\'' +
				", rememberMe=" + rememberMe +
				'}';
	}
	
}
