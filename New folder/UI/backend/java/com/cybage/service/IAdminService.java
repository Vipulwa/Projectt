package com.cybage.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cybage.model.Department;
import com.cybage.model.User;

public interface IAdminService {
	
	public String addUser(User user);
	
	public ResponseEntity<?> addDepartment(Department department);
	public ResponseEntity<?> getUser(String name);
	public String removeDepartment(String name);
	public ResponseEntity<?> login(String email,String password);
	public ResponseEntity<?> update(User user);
	public ResponseEntity<?> getAllDepartments();
	public ResponseEntity<?> getAllCitizens();
	public ResponseEntity<?> getBlockList();
	public ResponseEntity<?> unBlockAccount(int id);
	
}
