package com.cybage.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cybage.dto.CommentDTO;
import com.cybage.dto.ComplaintDTO;
import com.cybage.dto.RatingDTO;
import com.cybage.dto.UserLoginDTO;
import com.cybage.model.AccountStatus;
import com.cybage.model.Comment;
import com.cybage.model.Complaint;
import com.cybage.model.LikeDislike;
import com.cybage.model.OTP;
import com.cybage.model.Rating;
import com.cybage.model.Role;
import com.cybage.model.Status;
import com.cybage.model.User;
import com.cybage.repository.CitizenRepository;
import com.cybage.repository.CommentRepository;
import com.cybage.repository.ComplaintRepository;
import com.cybage.repository.DepartmentRepository;
import com.cybage.repository.LikeDislikeRepository;
import com.cybage.repository.OTPRepository;
import com.cybage.repository.RatingRepository;


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
	
	@Autowired
	LikeDislikeRepository likeDislikeRepository;
	
	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	OTPRepository otpRepository;
	
	
	static boolean flag=false;
	static String msg="";
	
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
						complaint.getUserId().getId(),complaint.getUserId().getUsername(),complaint.getDepartmentId().getDepartmentName(),complaint.getComments()
					
						)));
		return complaintDtoList;
	}

	@Override
	public User changePassword(String email, String password,String newPassword) {
		
	User user=	citizenRepository.findByEmailAndPassword(email, password);
	
		user.setPassword(newPassword);
		citizenRepository.save(user);
		return user;
	}

	@Override
	public List<String> getAllDepartments() {
		// TODO Auto-generated method stub
		return  citizenRepository.getDepartments();
		
		
	}

	@Override
	public String like(int userId, int complaintId,String reaction) {
		User citizen = citizenRepository.getById(userId);
		Complaint complaint= complaintRepository.getById(complaintId);
		if(citizen!=null && complaint!=null){
			List<LikeDislike> likeDislikeList=complaint.getLikeDislikeList();

				CitizenServiceImpl.flag=false;

			likeDislikeList.forEach((likeDislike->{
				if(likeDislike.getComplaint().equals(complaint) 
						&& likeDislike.getUser().equals(citizen)){
					switch (reaction){
						case "LIKE":
							if(likeDislike.getLikes()!=1){
								likeDislike.setDislike(0);
								likeDislike.setLikes(1);
								CitizenServiceImpl.msg="liked successfully";
								CitizenServiceImpl.flag=true;
							}
							else{
								CitizenServiceImpl.msg="already liked";
								CitizenServiceImpl.flag=true;
							}
							break;
						case "DISLIKE":
							if(likeDislike.getDislike()!=1){
								likeDislike.setDislike(1);
								likeDislike.setLikes(0);
								CitizenServiceImpl.msg="disliked successfully";
								CitizenServiceImpl.flag=true;
							}
							else{
								CitizenServiceImpl.msg="already disliked";
								CitizenServiceImpl.flag=true;
							}
							break;
					}	
				}
			}));
			if(!CitizenServiceImpl.flag){
				switch(reaction){
				case "LIKE":
					likeDislikeList.add(new LikeDislike(1,0,complaint,citizen));
					CitizenServiceImpl.msg="liked successfully";
					break;
				case "DISLIKE":
					likeDislikeList.add(new LikeDislike(0,1,complaint,citizen));
					CitizenServiceImpl.msg="disliked successfully";
					break;
				}			
			}
			complaint.setLikeDislikeList(likeDislikeList);
			complaintRepository.save(complaint);
			return CitizenServiceImpl.msg;
		}
		return "not found";
	}	

	@Override
	public String addComment(int userId, int complaintId,String comment) {
		User citizen = citizenRepository.getById(userId);
		Complaint complaint= complaintRepository.getById(complaintId);
		if(citizen!=null && complaint!=null){
			List<Comment> commentList=complaint.getComments();
			Comment cmt=new Comment();
			cmt.setComment(comment);
			cmt.setUser(citizen);
			cmt.setComplaint(complaint);
			cmt.setCommentDate(LocalDate.now());
			if(commentList.add(cmt)){
				complaint.setComments(commentList);
				complaintRepository.save(complaint);
				return "Commented";
			}
		}
		return "not found";
	}



	@Override
	public String addRating(int userId, int complaintId, int rating) {
		User citizen = citizenRepository.getById(userId);
		Complaint complaint= complaintRepository.getById(complaintId);
		if(citizen!=null && complaint!=null){
			CitizenServiceImpl.flag=false;
			List<Rating> ratingList=complaint.getRatings();
			if(!ratingList.isEmpty()){
				ratingList.forEach((extRating)->{
					if(extRating.getComplaint().equals(complaint)&&
							extRating.getUser().equals(citizen)){
						extRating.setRating(rating);
						CitizenServiceImpl.msg="ratings changed";
						CitizenServiceImpl.flag=true;
					}
				});
			}
			if(!CitizenServiceImpl.flag){
				Rating rate=new Rating();;
				rate.setRating(rating);
				rate.setUser(citizen);
				rate.setComplaint(complaint);
				if(ratingList.add(rate)){
					CitizenServiceImpl.msg= "rated successfully";
				}
			}
			complaint.setRatings(ratingList);
			complaintRepository.save(complaint);
			return CitizenServiceImpl.msg;
		}
		return "not found";
	}
	
	@Override
	public long likeCount(int complaintId){
		Complaint complaint = complaintRepository.getById(complaintId);
		List<LikeDislike> likeDislikeList = likeDislikeRepository.findBycomplaintId(complaintId);
		
		if(complaint != null &&  !likeDislikeList.isEmpty() ){
			return  likeDislikeRepository.findLikeCountByComplaintNo(complaintId);	
		}
		return 0;

	}
	
	@Override
	public long dislikeCount(int complaintId){
		Complaint complaint = complaintRepository.getById(complaintId);
		List<LikeDislike> likeDislikeList = likeDislikeRepository.findBycomplaintId(complaintId);
		
		if(complaint != null &&  !likeDislikeList.isEmpty() ){
			return  likeDislikeRepository.findDislikeCountByComplaintNo(complaintId);	
		}
		return 0;
	}
		
	@Override
	public List<CommentDTO> getComments(int complaintId){
		Complaint complaint = complaintRepository.getById(complaintId);
		List<Comment> commentList= commentRepository.findByComplaintId(complaintId);
		List<CommentDTO> commentDTOList=new ArrayList<>();
		if(complaint!=null && !commentList.isEmpty() ){
			commentList.forEach((comment)->{
				commentDTOList.add
				(new CommentDTO(comment.getId(),comment.getComment(),
						comment.getCommentDate(),comment.getUser().getId(),
						comment.getUser().getUsername(),comment.getComplaint().getId()));
			});
			return commentDTOList;
		}
		return null;
	}
	
	@Override
	public List<RatingDTO> getRatings(int complaintId){
		Complaint complaint = complaintRepository.getById(complaintId);
		List<Rating> ratingList=ratingRepository.findByComplaintId(complaintId);
		List<RatingDTO> ratingDTOList=new ArrayList<>();
		if(complaint!=null && !ratingList.isEmpty()) {
			ratingList.forEach((rating)->{
				ratingDTOList.add(new RatingDTO(rating.getId(),rating.getRating(),rating.getComplaint().getId(),rating.getUser().getId(),rating.getUser().getUsername()));
			});
			return ratingDTOList;
		}
		return null;
	}



	@Override
	public List<ComplaintDTO> getAllComplaints() {
		// TODO Auto-generated method stub
		List<Complaint> complaintList=new ArrayList<>();
		complaintList=complaintRepository.getAllComplaints();
		List<ComplaintDTO> complaintDtoList=new ArrayList<>();		
		complaintList.forEach((complaint)->
			complaintDtoList.add(new ComplaintDTO(
						complaint.getId(),complaint.getSubject(),
						complaint.getComplain(),complaint.getCreateDate(),
						complaint.getCloseDate(),complaint.getStatus().toString(),
						complaint.getUserId().getId(),complaint.getUserId().getUsername(),complaint.getDepartmentId().getDepartmentName(),complaint.getComments()
					
						)));
		return complaintDtoList;
		
	}
	
	@Override
	public UserLoginDTO checkByEmail(String email) {
		User user = citizenRepository.findByEmail(email);
		if(user!=null){
			UserLoginDTO userDto= new UserLoginDTO();
			userDto.setEmail(user.getEmail());
			userDto.setId(user.getId());
			userDto.setRole(user.getRole().toString());
			userDto.setUsername(user.getUsername());
			userDto.setCounter(user.getCounter());
			userDto.setStatus(user.getStatus().toString());
			return userDto;
		}
		return null;
	}



	@Override
	public UserLoginDTO otpForLoginWithOtp(UserLoginDTO user) {	
		OTP otp=otpRepository.findByUserId(user.getId());
		if(otp!=null){
			otp.setOtp(user.getOtp());
			otpRepository.save(otp);
			return user;
		}
		OTP newOtp=new OTP();
		newOtp.setOtp(user.getOtp());
		newOtp.setUserId(user.getId());
		otpRepository.save(newOtp);
		return user;
	}

	@Override
	public boolean liked(int complaintId,int userId) {
	LikeDislike likeDislike=likeDislikeRepository.findByUserIdAndComplaintId(userId,complaintId);
	if(likeDislike!=null){
		return likeDislike.getLikes()==1;
	}
		return false;
	}
	
	@Override
	public boolean disliked(int complaintId,int userId) {
	LikeDislike likeDislike=likeDislikeRepository.findByUserIdAndComplaintId(userId,complaintId);
	if(likeDislike!=null){
		return likeDislike.getDislike()==1;
	}
		return false;
	}

	



	
}
