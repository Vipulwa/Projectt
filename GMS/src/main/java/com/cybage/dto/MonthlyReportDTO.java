package com.cybage.dto;

public class MonthlyReportDTO {
	private int month;
	private long complaints;
	public MonthlyReportDTO() {
		
	}
	public MonthlyReportDTO(int month, long complaints) {
		super();
		this.month = month;
		this.complaints = complaints;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public long getComplaints() {
		return complaints;
	}
	public void setComplaints(long complaints) {
		this.complaints = complaints;
	}
	@Override
	public String toString() {
		return "MonthlyReportDTO [month=" + month + ", complaints=" + complaints + "]";
	}
	
	
	
}
