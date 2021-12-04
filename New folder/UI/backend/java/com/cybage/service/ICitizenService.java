package com.cybage.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.cybage.dto.ComplaintDTO;
import com.cybage.model.Complaint;
import com.cybage.model.User;

public interface ICitizenService {

	
//	ResponseEntity<?> findByEmailAndPassword(String email,String password);
	
	
	
	
	ResponseEntity<?> makeComplain(ComplaintDTO complaintDTO);
	
	ResponseEntity<?> addUser(User user);
	
	
	
	ResponseEntity<?> getComplaintById(int complainId);
	
	void sendReminder(int id);
	
	
	
	//VISHAL
	

	public List<ComplaintDTO> findByUserId(int userId,String status);
	
//	void likeComplain(int likeCounter,int id);
//	int getLikeCounter(int id);
//	
	
	String like(int userId,int complaintId );
	
	

}
