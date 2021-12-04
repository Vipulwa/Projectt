package com.cybage.service;

import java.util.List;


import java.util.Properties;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import com.cybage.customexception.AdminException;
import com.cybage.model.AccountStatus;
import com.cybage.model.Department;
import com.cybage.model.Role;
import com.cybage.model.User;
import com.cybage.repository.AdminRepository;
import com.cybage.repository.CitizenRepository;
import com.cybage.repository.DepartmentRepository;

@Service
@Transactional
public class AdminService implements IAdminService{
	
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	CitizenRepository userRepository;
	@Autowired
	private JavaMailSender javaMailSender;
	

	@Override
	public String addUser(User user) {	
		
		User existedUser=adminRepository.findByEmail(user.getEmail());
		if(existedUser!=null) {
			return "Department with given email is already registered..";
		}
		else{
			
			user.setRole(Role.DEPARTMENT);
			user.setStatus(AccountStatus.UNLOCKED);
		
			user.setUsername(user.getUsername().toUpperCase());
			User users=adminRepository.save(user);
			int id=userRepository.findByUsername(user.getUsername()).getId();
			Department department= new Department(user.getUsername());
			department.setUserId(id);
			departmentRepository.save(department);
//			Properties prop= new Properties();
			StringBuilder text= new StringBuilder();
			SimpleMailMessage msg= new SimpleMailMessage();
			
			msg.setTo(user.getEmail());
			msg.setFrom("Trng1@evolvingsols.com");
			msg.setSubject("Login Credentials of "+user.getUsername()+" Department");
			
			text.append("Hello User, \n");
			text.append("We received your signup request,below are your credentials for login to GMS . \n"
					+"Department Name : "+user.getUsername()+"Department\n"
					+"Email ID : "+user.getEmail()+"\n"
					+"Password : "+user.getPassword()+"\n\n");
			text.append("Regards,\n"+"Admin \nGrievence Management System \nPune Municipal corporation");
			text.append("THANK YOU");
			msg.setText(text.toString());
			javaMailSender.send(msg);
			return "Successfuly Added";
		}
			
					
	}
	@Override
	public ResponseEntity<?> addDepartment(Department department) {
		// TODO Auto-generated method stub
		
		return ResponseEntity.ok(departmentRepository.save(department));
	}
	@Override
	public ResponseEntity<?> getUser(String name) {
		// TODO Auto-generated method stub
		User user=userRepository.findByUsername(name);
		if(user==null) {
			return  ResponseEntity.ok("User Not found");
		}else {
			return  ResponseEntity.ok(user);
		}
		
	}
	@Override
	public String removeDepartment(String name) {
		// TODO Auto-generated method stub
		User u=userRepository.findByUsername(name);
		if(u==null) {
			return "Department not found with given Name";
		}
		else {
		Department department=departmentRepository.findByDepartmentName(name);
		userRepository.delete(u);
		departmentRepository.delete(department);
		return "Remove Successfully";
		}
	}
	@Override
	public ResponseEntity<?> login(String email, String password) {
		
		User user=userRepository.findByEmailAndPassword(email, password);
		if(user==null) {
			return ResponseEntity.ok("Please enter right credentials");
		}
		else {
			return ResponseEntity.ok(user);
		}
		
	}
	@Override
	public ResponseEntity<?> update(User user) {
	
		System.out.println(user);
		Department department=departmentRepository.findByUserId(user.getId());
		if(department==null) {
			return ResponseEntity.ok("Department Not found");
		}
		else {
			System.out.println(department);
			department.setDepartmentName(user.getUsername());
			User u=userRepository.findById(user.getId()).get();
			u=user;
			System.out.println(u);
			userRepository.save(u);
			departmentRepository.save(department);
			return ResponseEntity.ok(u);
		}
		
	}
	public ResponseEntity<?> getAllDepartments() {
		
		List<User> departmentList=userRepository.getDepartmentList();
		if(departmentList==null) {
			return ResponseEntity.ok("Records not found");
		}
		else {
			return ResponseEntity.ok(departmentList);
		}
		
	}
	
	public  ResponseEntity<?> getAllCitizens(){
		
		List<User> citizenList=userRepository.getCitizenList();;
		if(citizenList==null) {
			return ResponseEntity.ok("Records not found");
		}
		else {
			return ResponseEntity.ok(citizenList);
		}
		
		
	}
	@Override
	public ResponseEntity<?> getBlockList() {
		
		List<User> blockList=userRepository.findAllByStatus(AccountStatus.LOCKED);
		if(blockList==null) {
			return ResponseEntity.ok("Records not found");
		}
		else {
			return ResponseEntity.ok(blockList);
		}
		
	}
	@Override
	public ResponseEntity<?> unBlockAccount(int id) {
		User user=userRepository.findById(id).get();
		if(user==null) {
			return ResponseEntity.ok("User not found");
		}
		else {
			user.setStatus(AccountStatus.UNLOCKED);
			user.setCounter(0);
			userRepository.save(user);
			return ResponseEntity.ok("UNLOCKED SUCCESSFULLY.");
		}
		
	}
	

}
