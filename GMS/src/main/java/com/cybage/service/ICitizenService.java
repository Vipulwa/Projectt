package com.cybage.service;

import java.util.List;


import org.springframework.http.ResponseEntity;

import com.cybage.dto.CommentDTO;
import com.cybage.dto.ComplaintDTO;
import com.cybage.dto.RatingDTO;
import com.cybage.dto.UserLoginDTO;
import com.cybage.model.User;

public interface ICitizenService {
		
	
	ResponseEntity<?> makeComplain(ComplaintDTO complaintDTO);
	
	ResponseEntity<?> addUser(User user);
	
	ResponseEntity<?> getComplaintById(int complainId);
	
	void sendReminder(int id);
	
	public List<ComplaintDTO> findByUserId(int userId,String status);
	
	User changePassword(String email,String Password,String newPassword);
	
	List<String> getAllDepartments();

	public String like(int userId,int complaintId,String reaction );
	
	public String addComment(int userId, int complaintId,String comment);
	
	public String addRating(int userId, int complaintId, int rating);
	
	public long likeCount(int complaintId);
	
	public long dislikeCount(int complaintId);
	
	public List<CommentDTO> getComments(int complaintId);
	
	public List<RatingDTO> getRatings(int complaintId);
	
	public List<ComplaintDTO> getAllComplaints();
	
	public UserLoginDTO checkByEmail(String email);
	
	public UserLoginDTO otpForLoginWithOtp(UserLoginDTO user);

	public boolean liked(int complaintId,int userId);

	public boolean disliked(int complaintId,int userId);
}
