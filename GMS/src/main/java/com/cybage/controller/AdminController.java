package com.cybage.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.ResponseDTO;
import com.cybage.model.User;
import com.cybage.service.IAdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
	
	@Autowired
	IAdminService adminSerive;

	//ADD DEPARTMENT
	@PostMapping("/add")
	public ResponseDTO<?> addDepartment(@RequestBody User user) {
		
		try{
			
			System.out.println(user);
			return new ResponseDTO<>(HttpStatus.CREATED,"Department Added Successfully",adminSerive.addUser(user));
		}catch (RuntimeException e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR,"",e.getMessage());
		}
		
	}
	
	
	@GetMapping("/get/{name}")
	public ResponseEntity<?> getUser(@PathVariable String name) {
		
		
		try{
			return new ResponseEntity<>(adminSerive.getUser(name),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
				
	}

	
	@DeleteMapping("/delete/{name}")
	public ResponseDTO<?> removeDepartment(@PathVariable String name) {
		
		try{
			return new ResponseDTO<>(HttpStatus.CREATED,"Department Removed Successfully",adminSerive.removeDepartment(name));
		}catch (RuntimeException e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR,"",e.getMessage());
		}
		
	}
	

	
	
	@GetMapping("/departmentList")
	public ResponseEntity<?> departmentList(){
		
		try{
			return new ResponseEntity<>(adminSerive.getAllDepartments(),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
		
	@GetMapping("/citizenList")
	public ResponseEntity<?	> citizensList(){
		try{
			return new ResponseEntity<>(adminSerive.getAllCitizens(),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@GetMapping("/getBlockList")
	public ResponseEntity<?> getBlockedAccounts(){
		
		try{
			return new ResponseEntity<>(adminSerive.getBlockList(),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
	
	@GetMapping("/unBlock/{id}")
	public ResponseEntity<?> removeFromBlockList(@PathVariable int id) {
		
		try{
			return new ResponseEntity<>(adminSerive.unBlockAccount(id),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/complaintsDates")
	public ResponseDTO<?> getComplaintsBetweenDates(@RequestParam String departmentName,@RequestParam String status,
			@RequestParam String from,@RequestParam String to) {
		
		try {
			
			return new ResponseDTO<>(HttpStatus.OK, "Complaints Found",
					adminSerive.reportByDates(departmentName, status, from, to));
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					adminSerive.reportByDates(departmentName, status, from, to));
		}
	}
	
	@GetMapping("/department-names")
	public ResponseDTO<?> getDepartmentNames() {
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaints Found",
					adminSerive.getDepartmentNames());
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					adminSerive.getDepartmentNames());
		}
	}
	
	@GetMapping("/departmentreports")
	public ResponseDTO<?> getDepartmentWiseReport() {
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaints Found",
					adminSerive.getDepartmentReport());
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					adminSerive.getDepartmentReport());
		}
	}


}

