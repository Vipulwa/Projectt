package com.cybage.service;

import org.springframework.http.ResponseEntity;

import com.cybage.model.Feedback;

public interface IUserService {

	ResponseEntity<?> findByEmailAndPassword(String email,String password);
	ResponseEntity<?> addFeedback(Feedback feedback);
	ResponseEntity<?> getAllFeedback();
	
}
