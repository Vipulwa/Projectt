package com.cybage.service;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cybage.repository.CitizenRepository;
import com.cybage.repository.CommentRepository;
import com.cybage.repository.ComplaintRepository;
import com.cybage.repository.DepartmentRepository;
import com.cybage.dto.ComplaintDTO;
import com.cybage.model.AccountStatus;
import com.cybage.model.Comment;
import com.cybage.model.Complaint;
import com.cybage.model.Role;
import com.cybage.model.Status;
import com.cybage.model.User;


@Service

public class CitizenServiceImpl implements ICitizenService{
	
	@Autowired
	CitizenRepository citizenRepository;
	
	@Autowired
	ComplaintRepository complaintRepository;
	
	@Autowired
	CitizenRepository userRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	CommentRepository commentRepository;
	
//	@Override
//	public ResponseEntity<?> findByEmailAndPassword(String email,String password) {
//		
//		User user=citizenRepository.findByEmailAndPassword(email,password);
//		
//		if(user==null) {
//			User citizen=citizenRepository.findByEmail(email);
//			if(citizen==null) {
//				return ResponseEntity.ok("Entered Email is not registered in system");
//			}
//			if(citizen.getCounter()<=1) {
//				int counter=citizen.getCounter();
//				counter++;
//				citizen.setCounter(counter);
//				citizenRepository.save(citizen);
//				return ResponseEntity.ok("Please enter valid credentials");
//			}		
//			
//			else{
//				System.out.println("INSiDE");
//				citizen.setStatus(AccountStatus.LOCKED);
//				citizenRepository.save(citizen);
//				return ResponseEntity.ok("Your account has been block due to multiple bad credentials");
//				
//			}
//		}
//		else if(user.getStatus().equals(AccountStatus.UNLOCKED ) ) {
//			user.setCounter(0);
//			user=citizenRepository.save(user);
//			return ResponseEntity.ok(user);
//		}
//		return  ResponseEntity.ok(" Sry..We can not proceed further..your account is blocked");
//		
//		
//		
//		
//	}
//
	

	@Override
	public ResponseEntity<?> makeComplain(ComplaintDTO complaintDTO) {
		Complaint complaint=new Complaint();
		
		complaint.setComplain(complaintDTO.getComplain()); 
		complaint.setSubject(complaintDTO.getSubject());
		
		complaint.setUserId(userRepository.getById(complaintDTO.getUserId()));
		complaint.setDepartmentId(departmentRepository.findBydepartmentName(complaintDTO.getDepartmentName()));
		complaint.setStatus(Status.ACTIVE);
		complaint.setCreateDate(LocalDate.now());
		
		 complaintRepository.save(complaint);
		 return ResponseEntity.ok("added successfully");
		 
	}

	@Override
	public ResponseEntity<?> addUser(User user) {
	user.setRole(Role.CITIZEN);
	user.setStatus(AccountStatus.UNLOCKED);
	 citizenRepository.save(user);
	 return ResponseEntity.ok("Successfully added");
	}

	

	@Override
	public ResponseEntity<?> getComplaintById(int complainId) {
		
		
		Complaint complaint=complaintRepository.findById(complainId).get();
		if(complaint==null) {
			return ResponseEntity.ok("Records not found with given Id");
		}
		else {
			return ResponseEntity.ok(complaint);
		}
		 
	}

	@Override
	public void sendReminder( int id) {
		
		 complaintRepository.sendReminder(id);
	}

	

	
	
	
	
	//VISHAL
	
	
	public List<ComplaintDTO> findByUserId(int userId,String status) {
		List<Complaint> complaintList=new ArrayList<>();
		if(status.equals("ALL"))
			complaintList=complaintRepository.findByUserIdId(userId);
		else {
			Status sts=Status.ACTIVE;
			if(status.equals("CLOSED")){
				sts=Status.CLOSED;
				System.out.println(sts);
			}
			else if(status.equals("REMINDER")){
				sts=Status.REMINDER;
			}
			complaintList=complaintRepository.findByUserIdIdAndStatus(userId,sts);
		}
		
		List<ComplaintDTO> complaintDtoList=new ArrayList<>();		
		complaintList.forEach((complaint)->
			complaintDtoList.add(new ComplaintDTO(
						complaint.getId(),complaint.getSubject(),
						complaint.getComplain(),complaint.getCreateDate(),
						complaint.getCloseDate(),complaint.getStatus().toString(),
						complaint.getUserId().getId(),complaint.getUserId().getUsername(),complaint.getDepartmentId().getDepartmentName(),complaint.getComment()
					
						)));
		return complaintDtoList;
	}

	@Override
	public String like(int userId, int complaintId) {
		Complaint complaint= complaintRepository.findById(complaintId).get();
		
		if(complaint.getComment().size()==0) {
			Comment coment=new Comment();
			coment.setComplaint(complaint);
			coment.setUser(userRepository.findById(userId).get());
			coment.setComment(complaint.getComplain());
			if(coment.getDisLikes()==0) {
				coment.setLikes(1);
			}
			else {
				coment.setDisLikes(0);
				coment.setLikes(1);
			}
			
			commentRepository.save(coment);
			return "Liked..";
		}
		else {
		
			List<Comment> commentList=complaint.getComment();
			Comment comment= new Comment();
			
			Comment newComment= commentRepository.findByComplaintId(complaintId);
			
			User user=userRepository.findById(userId).get();
			if(newComment.getUser().getId()==userId){
				
				return "you have already liked the post!!";
			}
			else {
				newComment.setUser(user);
				int count=newComment.getLikes();
				count++;
				newComment.setLikes(count);
				newComment.setComplaint(complaint);
				newComment.setComment(complaint.getComplain());
			   commentRepository.save(newComment);
			   return "liked succesfully";
			}
			
		}
		
		
		
		
	}	


	



	
}
