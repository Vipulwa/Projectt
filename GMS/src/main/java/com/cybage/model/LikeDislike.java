package com.cybage.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class LikeDislike {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private int likes=0;
	
	@Column
	private int dislike=0;
	
	@ManyToOne(fetch = FetchType.LAZY)
	Complaint complaint;
	
	@ManyToOne(fetch = FetchType.LAZY)
	User user;

	public LikeDislike() {
		super();
	}

	public LikeDislike(int likes, int dislike) {
		super();
		this.likes = likes;
		this.dislike = dislike;
	}

	public LikeDislike(int likes, int dislike, Complaint complaint, User user) {
		super();
		this.likes = likes;
		this.dislike = dislike;
		this.complaint = complaint;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getDislike() {
		return dislike;
	}

	public void setDislike(int dislike) {
		this.dislike = dislike;
	}

	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "LikeDislike [id=" + id + ", likes=" + likes + ", dislike=" + dislike + "]";
	}

	
	
	
}
