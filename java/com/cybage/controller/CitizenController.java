package com.cybage.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.repository.*;
import com.cybage.dto.ComplaintDTO;
import com.cybage.model.Complaint;

import com.cybage.model.Role;
import com.cybage.model.Status;
import com.cybage.model.User;
import com.cybage.service.ICitizenService;

@RestController
@RequestMapping("/citizen")
public class CitizenController {

	@Autowired
	ICitizenService citizenService ;
	
	@PostMapping("/login")
	public User authenticateUser(@RequestBody User user) {
		String email=user.getEmail();
	String 	password=user.getPassword();
		User user1=citizenService.findByEmailAndPassword(email, password);
		
		return user1;
	}
	
	@GetMapping("/complaints/{id}")
	public ResponseEntity<?> getComplaints(@PathVariable int id,@RequestParam String status){
		List<ComplaintDTO> customers= citizenService.findByUserId(id, status);
		return new ResponseEntity<String>(customers.toString(), HttpStatus.OK);
	}
	
	@PostMapping("/addComplaint")
	public ResponseEntity<?> makeComplain(@RequestBody ComplaintDTO complaintDTO){
		try{
			return new ResponseEntity<>(citizenService.makeComplain(complaintDTO),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user){
		try{
			return new ResponseEntity<>(citizenService.addUser(user),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	
	@GetMapping("getComplainById/{complainId}")
	public ResponseEntity<String> getComplaintById(@PathVariable int complainId){
		Optional<Complaint> customers= citizenService.getComplaintById(complainId);
		return new ResponseEntity<String>(customers.toString(), HttpStatus.OK);
	}
	
	@PostMapping("/sendReminder/{id}")
	public void sendReminder(@PathVariable int id){
		citizenService.sendReminder(id);
		
	}
	
	@GetMapping("/like")
	public String like(@RequestParam int userId, @RequestParam int complaintId){
		System.out.println("in like ");
		
		return citizenService.like(userId, complaintId);
	
		
	}
	
	
	
	

	
	
	
	
	
	
	
}
