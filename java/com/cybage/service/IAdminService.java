package com.cybage.service;

import java.util.List;

import com.cybage.model.Department;
import com.cybage.model.User;

public interface IAdminService {
	
	public User addUser(User user);
	
	public Department addDepartment(Department department);
	public User getUser(String name);
	public String removeDepartment(String name);
	public User login(String email,String password);
	public User update(User user);
	public List<User> getAllDepartments();
	public List<User> getAllCitizens();
	public List<User> getBlockList();
	public String unBlockAccount(int id);
	
}
