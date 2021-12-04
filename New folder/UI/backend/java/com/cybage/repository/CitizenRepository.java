package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cybage.model.AccountStatus;
import com.cybage.model.User;
@Repository
public interface CitizenRepository extends JpaRepository<User, Integer>{
	
//	User findByEmailAndPassword(String email,String password);

	User findByEmail(String email);
	
	public User findByUsername(String name);
	public User findByEmailAndPassword(String email,String password);
	
	@Query(value = "select * from user where role='DEPARTMENT'",nativeQuery = true)
	public List<User> getDepartmentList();
	
	@Query(value = "select * from user where role='CITIZEN'",nativeQuery = true)
	public List<User> getCitizenList();
	
	
	public List<User> findAllByStatus(AccountStatus locked);


}
