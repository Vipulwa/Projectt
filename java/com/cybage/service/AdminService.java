package com.cybage.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cybage.customexception.AdminException;
import com.cybage.model.AccountStatus;
import com.cybage.model.Department;
import com.cybage.model.User;
import com.cybage.repository.AdminRepository;
import com.cybage.repository.DepartmentRepository;
import com.cybage.repository.UserRepository;

@Service
@Transactional
public class AdminService implements IAdminService{
	
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public User addUser(User user) {	
			return adminRepository.save(user);
					
	}
	@Override
	public Department addDepartment(Department department) {
		// TODO Auto-generated method stub
		
		return departmentRepository.save(department);
	}
	@Override
	public User getUser(String name) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(name);
	}
	@Override
	public String removeDepartment(String name) {
		// TODO Auto-generated method stub
		User u=userRepository.findByUsername(name);
		Department department=departmentRepository.findByDepartmentName(name);
		userRepository.delete(u);
		departmentRepository.delete(department);
		return null;
	}
	@Override
	public User login(String email, String password) {
		
		return userRepository.findByEmailAndPassword(email, password);
	}
	@Override
	public User update(User user) {
	
		System.out.println(user);
		Department department=departmentRepository.findByUserId(user.getId());
		System.out.println(department);
		department.setDepartmentName(user.getUsername());
		User u=userRepository.findById(user.getId()).get();
		u=user;
		System.out.println(u);
		userRepository.save(u);
		departmentRepository.save(department);
		return u;
	}
	public List<User> getAllDepartments() {
		
		return userRepository.getDepartmentList();
	}
	
	public List<User> getAllCitizens(){
		return userRepository.getCitizenList();
	}
	@Override
	public List<User> getBlockList() {
		
		return userRepository.findAllByStatus(AccountStatus.LOCKED);
	}
	@Override
	public String unBlockAccount(int id) {
		User user=userRepository.findById(id).get();
		user.setStatus(AccountStatus.UNLOCKED);
		user.setCounter(0);
		userRepository.save(user);
		return "UNLOCKED SUCCESSFULLY.";
	}
	

}
