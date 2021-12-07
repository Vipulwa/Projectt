package com.cybage.dto;

public class ReportDTO {
	private long active;
	private long closed;
	private long reminder;
	public ReportDTO() {
		super();
	}
	public ReportDTO(long active, long closed, long reminder) {
		super();
		this.active = active;
		this.closed = closed;
		this.reminder = reminder;
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
		return "ReportDTO [active=" + active + ", closed=" + closed + ", reminder=" + reminder + "]";
	}
	
}
