package com.cybage.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybage.model.AccountStatus;
import com.cybage.model.Complaint;
import com.cybage.model.Role;
import com.cybage.model.Status;
import com.cybage.model.User;
import com.cybage.repository.CitizenRepository;
import com.cybage.repository.ComplaintRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GmsApplication.class)
public class CitizenServiceTest {
	@Autowired
	ComplaintRepository complaintRepo;
	@Autowired
	private CitizenRepository citizenRepo;

	@Test
	public void makeComplaint() {
		Complaint complaint = new Complaint(1, "electricity", "connection shutdown", LocalDate.of(2021, 12, 01),
				LocalDate.of(2021, 12, 04), Status.ACTIVE);
		complaintRepo.save(complaint);
		assertThat(complaint.getId()).isGreaterThan(0);
	}

	@Test
	public void addCitizen() {
		User citizen = new User(2, "citizen", "citizen@gmail.com", "citizen@123", Role.CITIZEN, AccountStatus.UNLOCKED);
		citizenRepo.save(citizen);
		assertThat(citizen.getId()).isGreaterThan(0);
	}

	@Test
	public void getComplainById() {
		Complaint complaint = complaintRepo.findById(1).get();
		assertEquals(1, complaint.getId());
	}

	/*
	 * @Test public void getComplainByUserId() { User citizen = new User(2,
	 * "citizen", "citizen@gmail.com", "citizen@123", Role.CITIZEN,
	 * AccountStatus.UNLOCKED); List<Complaint> complaintList = new
	 * ArrayList<>(); complaintList = complaintRepo.findByUserIdId(userId);
	 * assertEquals(1, complaintList.getId()); }
	 */

}
