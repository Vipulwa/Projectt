package com.cybage.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String departmentName;
	@Column
	private int userId;
	@OneToMany(mappedBy = "departmentId" ,orphanRemoval = true)
	private List<Complaint> complaints;
	
	public Department() {}
	
	public Department(int id, String departmentName, int userId) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.userId = userId;
	}
	public Department(String username, int id2) {
		// TODO Auto-generated constructor stub
		this.departmentName=username;
		this.userId=id2;
	}
	public Department(String username) {
		// TODO Auto-generated constructor stub
		this.departmentName=username;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + ", userId=" + userId + "]";
	}
	
	
	
	
}
