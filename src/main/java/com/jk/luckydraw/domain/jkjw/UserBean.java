package com.jk.luckydraw.domain.jkjw;

import java.io.Serializable;

public class UserBean implements Serializable{
	
	private Integer id;
	
	private String username;
	
	private String pwd;
	
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserBean [id=" + id + ", username=" + username + ", pwd=" + pwd + ", phone=" + phone + "]";
	}
	
	
	

}
