package com.cybage.dto;

public class UserLoginDTO {
	private int id;
	private String username;
	private String email;
	private String role ;
	private String status;
	private int	counter;
	private String otp;
	public UserLoginDTO() {
		super();
	}
	public UserLoginDTO(int id, String username, String email, String role, String status, int counter, String otp) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
		this.status = status;
		this.counter = counter;
		this.otp = otp;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	@Override
	public String toString() {
		return "UserLoginDTO [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role + ", status="
				+ status + ", counter=" + counter + ", otp=" + otp + "]";
	}
	
		
}
