package com.cybage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Rating {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int rating;
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Complaint complaint;
	@OneToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	public Rating() {

	}
	
	public Rating(int id, int rating) {
		super();
		this.id = id;
		this.rating = rating;
	}

	public Rating(int id, int rating, Complaint complaint, User user) {
		super();
		this.id = id;
		this.rating = rating;
		this.complaint = complaint;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
		return "Rating [id=" + id + ", rating=" + rating + "]";
	}
	
	
}
