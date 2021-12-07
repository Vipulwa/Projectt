package com.cybage.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String subject;
	@Column
	private String complain;
	@Column
	private LocalDate createDate;
	@Column
	private LocalDate closeDate;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User userId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Department departmentId;
	@OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Comment> comments;
	@OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<LikeDislike> likeDislikeList;
	@OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Rating> ratings;
	
	public Complaint(int id, String subject, String complain, LocalDate createDate, LocalDate closeDate,
			Status status) {
		super();
		this.id = id;
		this.subject = subject;
		this.complain = complain;
		this.createDate = createDate;
		this.closeDate = closeDate;
		this.status = status;
	}
	public Complaint() {
		// TODO Auto-generated constructor stub
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
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getUserId() {
		return userId;
	}
	public void setUserId(User userId) {
		this.userId = userId;
	}
	public Department getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public List<LikeDislike> getLikeDislikeList() {
		return likeDislikeList;
	}
	public void setLikeDislikeList(List<LikeDislike> likeDislikeList) {
		this.likeDislikeList = likeDislikeList;
	}
	public List<Rating> getRatings() {
		return ratings;
	}
	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	@Override
	public String toString() {
		return "Complaint [id=" + id + ", subject=" + subject + ", complain=" + complain + ", createDate=" + createDate
				+ ", closeDate=" + closeDate + ", status=" + status +  "]";
	}
	
	
	
	
	
	

	
}
