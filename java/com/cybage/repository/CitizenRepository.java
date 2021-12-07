package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.model.User;
@Repository
public interface CitizenRepository extends JpaRepository<User, Integer>{
	
	User findByEmailAndPassword(String email,String password);

	User findByEmail(String email);

}
