package com.cybage.dto;

public class RatingDTO {
	

	private int id;

	private int rating;

	private int complaintId;

	private int userId;
	
	private String username;
	
	public RatingDTO() {

	}

	public RatingDTO(int id, int rating, int complaintId, int userId, String username) {
		super();
		this.id = id;
		this.rating = rating;
		this.complaintId = complaintId;
		this.userId = userId;
		this.username = username;
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

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
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

	@Override
	public String toString() {
		return "RatingDTO [id=" + id + ", rating=" + rating + ", complaintId=" + complaintId + ", userId=" + userId
				+ ", username=" + username + "]";
	}
	
	
}
