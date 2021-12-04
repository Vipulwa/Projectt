package com.cybage.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String comment;
	@Column
	private LocalDate commentDate;
	@Column
	private int rating;
	@Column
	private int likes;
	@Column
	private int disLikes;
	@OneToOne
	private User user;
	@ManyToOne
	private Complaint complaint;
	public Comment(int id, String comment, LocalDate commentDate, int rating, int likes, int disLikes, User user,
			Complaint complaint) {
		super();
		this.id = id;
		this.comment = comment;
		this.commentDate = commentDate;
		this.rating = rating;
		this.likes = likes;
		this.disLikes = disLikes;
		this.user = user;
		this.complaint = complaint;
	}
	public Comment() {
		
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
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getDisLikes() {
		return disLikes;
	}
	public void setDisLikes(int disLikes) {
		this.disLikes = disLikes;
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
		return "Comment [id=" + id + ", comment=" + comment + ", commentDate=" + commentDate + ", rating=" + rating
				+ ", likes=" + likes + ", disLikes=" + disLikes + ", user=" + user + ", complaint=" + complaint + "]";
	}
	
	
	
	
}
