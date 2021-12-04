package com.cybage.dto;

import java.util.List;
import com.cybage.model.Complaint;
import com.cybage.model.Role;

public class User {
	
	private int id;
	private String username;
	private String email;
	private String password;
	private Role role;
	private List<Complaint> complaints;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
