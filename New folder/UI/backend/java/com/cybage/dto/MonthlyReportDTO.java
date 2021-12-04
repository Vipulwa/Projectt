package com.cybage.dto;

public class MonthlyReportDTO {
	private int month;
	private long active;
	private long closed;
	private long reminder;
	public MonthlyReportDTO() {
		
	}
	
//	public MonthlyReportDTO(int month, long active) {
//		super();
//		this.month = month;
//		this.active = active;
//	}

	public MonthlyReportDTO(int month, long active, long closed, long reminder) {
		super();
		this.month = month;
		this.active = active;
		this.closed = closed;
		this.reminder = reminder;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public long getActive() {
		return active;
	}
	public void setActive(long active) {
		this.active = active;
	}
	public long getClosed() {
		return closed;
	}
	public void setClosed(long closed) {
		this.closed = closed;
	}
	public long getReminder() {
		return reminder;
	}
	public void setReminder(long reminder) {
		this.reminder = reminder;
	}

	@Override
	public String toString() {
		return "MonthlyReportDTO [month=" + month + ", active=" + active + "]";
	}
	
	
}
