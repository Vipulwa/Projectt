package com.cybage.dto;

import java.time.LocalDate;
import java.util.List;

import com.cybage.model.Comment;

public class ComplaintDTO {
	
	private int id;
	private String subject;
	private String complain;
	private LocalDate createDate;
	private LocalDate closeDate;
	private String status;
	private int userId;
	private String username;
	private String departmentName;
	private List<Comment> comment;
	public ComplaintDTO() {
		super();
	}
	public ComplaintDTO(int id, String subject, String complain, 
			LocalDate createDate, LocalDate closeDate,
			String status, int userId, String username, 
			String departmentName, List<Comment> comment) {
		super();
		this.id = id;
		this.subject = subject;
		this.complain = complain;
		this.createDate = createDate;
		this.closeDate = closeDate;
		this.status = status;
		this.userId = userId;
		this.username = username;
		this.departmentName = departmentName;
		this.comment = comment;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getComplain() {
		return complain;
	}
	public void setComplain(String complain) {
		this.complain = complain;
	}
	public LocalDate getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}
	public LocalDate getCloseDate() {
		return closeDate;
	}
	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	
	@Override
	public String toString() {
		return "ComplaintDTO [id=" + id + ", subject=" + subject + ", complain=" + complain + ", createDate="
				+ createDate + ", closeDate=" + closeDate + ", status=" + status + ", userId=" + userId + ", username="
				+ username + ", departmentName=" + departmentName + ", comment=" + comment +  "]";
	}
	
}
