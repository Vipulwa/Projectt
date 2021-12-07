package com.cybage.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.ComplaintDTO;
import com.cybage.dto.ResponseDTO;
import com.cybage.dto.UserLoginDTO;
import com.cybage.model.User;
import com.cybage.service.ICitizenService;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/citizen")
public class CitizenController {

	@Autowired
	ICitizenService citizenService ;
	
	@Autowired
	JavaMailSender emailSender;
	
	@Value("${spring.mail.username}")
	private String host;
	
	
	
	@GetMapping("/complaints/{id}")
	public ResponseDTO<?>  getComplaints(@PathVariable int id,@RequestParam String status){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.findByUserId(id, status));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.findByUserId(id, status));
		}	
	}
	
	
	
	@PostMapping("/addComplaint")
	public ResponseEntity<?> makeComplain(@RequestBody ComplaintDTO complaintDTO){
		return ResponseEntity.ok(citizenService.makeComplain(complaintDTO));
	}
	
	
	
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user){
	return ResponseEntity.ok(citizenService.addUser(user));
		
	}

	
	@GetMapping("getComplainById/{complainId}")
	public ResponseEntity<?> getComplaintById(@PathVariable int complainId){
		return ResponseEntity.ok(citizenService.getComplaintById(complainId));
	}
	
	
	@PostMapping("/sendReminder/{id}")
	public void sendReminder(@PathVariable int id){
	citizenService.sendReminder(id);
	}
		
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@RequestBody User user,@RequestParam String newPassword){
		String email=user.getEmail();
		String password=user.getPassword();
		return ResponseEntity.ok(citizenService.changePassword(email, password, newPassword));
		
	}
	
	@GetMapping("/getAllDepartments")
	public List<String> getAllDepartments(){
	return citizenService.getAllDepartments();
	}

	
	@PostMapping("/like")
	public ResponseDTO<?> like(@RequestParam int userId, @RequestParam int complaintId,@RequestParam String reaction){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.like(userId, complaintId,reaction));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.like(userId, complaintId,reaction));
		}	
	}
	
	@PostMapping("/comment")
	public ResponseDTO<?> comment(@RequestParam int userId, @RequestParam int complaintId,@RequestParam String comment){
		try{
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
						citizenService.addComment(userId, complaintId, comment));
		}catch (RuntimeException e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.addComment(userId, complaintId, comment));
		}
	}
	@PostMapping("/rating")
	public ResponseDTO<?> rating(@RequestParam int userId, @RequestParam int complaintId,@RequestParam int rating){
		try{
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.addRating(userId, complaintId, rating));
		}catch (RuntimeException e) {
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.addRating(userId, complaintId, rating));
		}
	}
	@GetMapping("/likes")
	public ResponseDTO<?> getLikeCount(@RequestParam int complaintId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.likeCount(complaintId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.likeCount(complaintId));
		}	
	}
	@GetMapping("/dislikes")
	public ResponseDTO<?> getDislikeCount(@RequestParam int complaintId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.dislikeCount(complaintId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.dislikeCount(complaintId));
		}	
	}
	@GetMapping("/comments")
	public ResponseDTO<?> getComments(@RequestParam int complaintId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.getComments(complaintId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.getComments(complaintId));
		}	
	}
	@GetMapping("/ratings")
	public ResponseDTO<?> getRatings(@RequestParam int complaintId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.getRatings(complaintId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.getRatings(complaintId));
		}	
	}
	
	
	@GetMapping("/getAllComplaints")
	public ResponseDTO<?> getAllComplaints(){
			try {
				return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
						citizenService.getAllComplaints());
			}catch (RuntimeException e) {
				System.out.println("Error in finding complaint");
				return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
						citizenService.getAllComplaints());
			}
	}
	
	@GetMapping("/loginWithOtp")
	public ResponseDTO<?> loginWithOtp(@RequestParam String email) {
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.checkByEmail(email));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.checkByEmail(email));
		}	
	}
	
	@PutMapping("/otpForLoginWithOtp")
	public ResponseDTO<?> otpForLoginWithOtp(@RequestBody UserLoginDTO user) {
		try {
			Random random = new Random();
			int number = 100000 + random.nextInt(900000);
			String otp=""+number;
			SimpleMailMessage message = new SimpleMailMessage();
		  	message.setFrom(host);
			message.setTo(user.getEmail());
			message.setSubject("OTP to activate your account for Grievance Management Portal ");
			message.setText("Hello " + user.getUsername()  + "," + "\n\nOTP to Login to your account is \n\n" + otp 
					+ "\n\n.");
			emailSender.send(message);
	
			user.setOtp(otp);
			System.out.println(user);
			
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.otpForLoginWithOtp(user));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.otpForLoginWithOtp(user));
		}	
		
	}
	@GetMapping("/liked")
	public ResponseDTO<?> getLiked(@RequestParam int complaintId,@RequestParam int userId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.liked(complaintId,userId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.liked(complaintId,userId));
		}	
	}
	
	@GetMapping("/disliked")
	public ResponseDTO<?> getDisLiked(@RequestParam int complaintId,@RequestParam int userId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					citizenService.disliked(complaintId,userId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					citizenService.disliked(complaintId,userId));
		}	
	}
	
	
	
}
