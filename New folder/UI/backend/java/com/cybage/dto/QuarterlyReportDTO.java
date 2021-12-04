package com.cybage.dto;

public class QuarterlyReportDTO {
	private int quarter;
	private int active;
	private int closed;
	private int reminder;
	public QuarterlyReportDTO() {
		
	}
	public QuarterlyReportDTO(int quarter, int active, int closed, int reminder) {
		super();
		this.quarter = quarter;
		this.active = active;
		this.closed = closed;
		this.reminder = reminder;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public int getActive() {
		return active;
	}
	public void setActive(int active) {
		this.active = active;
	}
	public int getClosed() {
		return closed;
	}
	public void setClosed(int closed) {
		this.closed = closed;
	}
	public int getReminder() {
		return reminder;
	}
	public void setReminder(int reminder) {
		this.reminder = reminder;
	}
	
	
}
