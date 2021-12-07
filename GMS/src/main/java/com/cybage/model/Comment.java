package com.cybage.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String comment;
	@Column
	private LocalDate commentDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Complaint complaint;
	
	public Comment() {
		super();
	}
	public Comment(int id, String comment, LocalDate commentDate) {
		super();
		this.id = id;
		this.comment = comment;
		this.commentDate = commentDate;
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
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", commentDate=" +
	commentDate +"]";
	}
	
	
}
