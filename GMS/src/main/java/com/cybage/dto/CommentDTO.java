package com.cybage.dto;

import java.time.LocalDate;



public class CommentDTO {

	private int id;

	private String comment;

	private LocalDate commentDate;
	
	private int userId;
	
	private String username;
	
	private int complaintId;
	
	public CommentDTO() {
		super();
	}

	public CommentDTO(int id, String comment, LocalDate commentDate, int userId, String username, int complaintId) {
		super();
		this.id = id;
		this.comment = comment;
		this.commentDate = commentDate;
		this.userId = userId;
		this.username = username;
		this.complaintId = complaintId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(LocalDate commentDate) {
		this.commentDate = commentDate;
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

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", comment=" + comment + ", commentDate=" + commentDate + ", userId=" + userId
				+ ", username=" + username + ", complaintId=" + complaintId + "]";
	}
	
	
}
