package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.User;

public interface AdminRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
	
	

}
