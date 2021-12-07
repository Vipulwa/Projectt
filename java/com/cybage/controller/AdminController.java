package com.cybage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.model.AccountStatus;
import com.cybage.model.Department;
import com.cybage.model.Role;
import com.cybage.model.User;
import com.cybage.service.IAdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	IAdminService adminSerive;

	//ADD DEPARTMENT
	@PostMapping("/add")
	public ResponseEntity<User> addDepartment(@RequestBody User user) {
		user.setRole(Role.DEPARTMENT);
		user.setStatus(AccountStatus.UNLOCKED);
		user.setUsername(user.getUsername().toUpperCase());
		adminSerive.addUser(user);
		int id=adminSerive.getUser(user.getUsername()).getId();
		Department department= new Department(user.getUsername().toUpperCase());
		department.setUserId(id);
		adminSerive.addDepartment(department);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping("/get/{name}")
	public User getUser(@PathVariable String name) {
		return adminSerive.getUser(name);
	}

	
	@DeleteMapping("/delete/{name}")
	public String removeDepartment(@PathVariable String name) {
		return adminSerive.removeDepartment(name);
	}
	
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		return adminSerive.login(user.getEmail(),user.getPassword());
	} 
	
	
	@PutMapping("/update")
	public User update(@RequestBody User user) {
		return adminSerive.update(user);
	}
	
	
	@GetMapping("/departmentList")
	public List<User> departmentList(){
		return adminSerive.getAllDepartments();
	}
	
		
	@GetMapping("/citizenList")
	public List<User> citizensList(){
		return adminSerive.getAllCitizens();
	}
	
	
	@GetMapping("/getBlockList")
	public List<User> getBlockedAccounts(){
		return adminSerive.getBlockList();
	}
	
	
	@PostMapping("/unBlock/{id}")
	public String removeFromBlockList(@PathVariable int id) {
		return adminSerive.unBlockAccount(id);
	}


}

