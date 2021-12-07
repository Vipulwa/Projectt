package com.cybage.service;

import java.util.List;
import java.util.Optional;

import com.cybage.dto.ComplaintDTO;
import com.cybage.model.Complaint;
import com.cybage.model.User;

public interface ICitizenService {

	
	User findByEmailAndPassword(String email,String password);
	
	
	
	
	String makeComplain(ComplaintDTO complaintDTO);
	
	User addUser(User user);
	
	
	
	Optional<Complaint> getComplaintById(int complainId);
	
	void sendReminder(int id);
	
	
	
	//VISHAL
	

	public List<ComplaintDTO> findByUserId(int userId,String status);
	
//	void likeComplain(int likeCounter,int id);
//	int getLikeCounter(int id);
//	
	
	String like(int userId,int complaintId );
	
	

}
