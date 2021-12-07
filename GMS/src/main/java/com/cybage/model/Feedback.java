package com.cybage.model;

import java.time.LocalDate;

public class Feedback {

	private String username; 
	private LocalDate date;
	private String feedback;
	private String suggestion;
	
	public Feedback() {
		super();
	}

	public Feedback(String username, String feedback, String suggestion) {
		super();
		this.username = username;
		this.feedback = feedback;
		this.suggestion = suggestion;
	}

	public Feedback(String username, LocalDate date, String feedback, String suggestion) {
		super();
		this.username = username;
		this.date = date;
		this.feedback = feedback;
		this.suggestion = suggestion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	@Override
	public String toString() {
		return "Feedback [username=" + username + ", date=" + date + ", feedback=" + feedback + ", suggestion="
				+ suggestion + "]";
	}
	
	
	
}
