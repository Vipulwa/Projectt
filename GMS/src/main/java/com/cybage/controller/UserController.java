package com.cybage.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.ResponseDTO;
import com.cybage.model.Feedback;
import com.cybage.model.User;
import com.cybage.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@PostMapping("/login")
	public ResponseDTO<?> authenticateUser(@RequestBody User user) {
		String email=user.getEmail();
	String 	password=user.getPassword();
	
	try{
		return new ResponseDTO<>(HttpStatus.CREATED,"Login Successfully",userService.findByEmailAndPassword(email, password));
	}catch (RuntimeException e) {
		return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR,"",e.getMessage());
	}
		
	}
	@PostMapping("/feedback/add")
	public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback){
		try{
			System.out.println("insede add feedback");
			feedback.setDate(LocalDate.now());
			return new ResponseEntity<>(userService.addFeedback(feedback),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/feedback/get")
	public ResponseEntity<?> getAllFeedback(){
		try{
			return new ResponseEntity<>(userService.getAllFeedback(),HttpStatus.CREATED);
		}catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	

}
