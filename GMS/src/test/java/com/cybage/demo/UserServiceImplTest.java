package com.cybage.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybage.model.AccountStatus;
import com.cybage.model.Department;
import com.cybage.model.Role;
import com.cybage.model.User;
import com.cybage.repository.CitizenRepository;
import com.cybage.repository.DepartmentRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GmsApplication.class)
public class UserServiceImplTest {
	@Autowired
	private CitizenRepository citizenRepo;
	@Autowired
	private DepartmentRepository deptRepo;

	@Test
	public void addUser() {
		User user1 = new User(1, "user", "user@gmail.com", "user@123", Role.CITIZEN, AccountStatus.UNLOCKED);
		citizenRepo.save(user1);
		assertThat(user1.getId()).isGreaterThan(0);
	}

	@Test
	public void login() {
		User user = citizenRepo.findByEmailAndPassword("user@gmail.com", "user@123");
		assertEquals("user@gmail.com", user.getEmail());
	}

	@Test
	public void addDepartment() {
		Department dept = new Department(1, "WATER", 1);
		deptRepo.save(dept);
		assertThat(dept.getId()).isGreaterThan(0);
	}

	@Test
	public void getUser() {
		User user = citizenRepo.findByUsername("user");
		assertEquals("user", user.getUsername());
	}

	@Test
	public void removeDepartment() {
		User user = citizenRepo.findByUsername("user");
		citizenRepo.delete(user);
		assertNull(citizenRepo.findByUsername("user"));
	}

	@Test
	public void updateDepartment() {
		Department dept = deptRepo.findByUserId(1);
		dept.setDepartmentName("HEALTH");
		deptRepo.save(dept);
		assertThat(dept.getId()).isGreaterThan(0);
	}

}
