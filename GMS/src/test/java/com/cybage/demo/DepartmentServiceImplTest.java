package com.cybage.demo;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cybage.model.Department;
import com.cybage.repository.ComplaintRepository;
import com.cybage.repository.DepartmentRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = GmsApplication.class)
public class DepartmentServiceImplTest {

	@Autowired
	ComplaintRepository complaintRepo;
	@Autowired
	DepartmentRepository deptRepo;

//	@Test
//	public void closeComplaintByDept() {
//		Department dept = deptRepo.findByUserId(1);
//		complaintRepo.findById(1);
//		
//		assertThat(dept.getId()).isGreaterThan(0);
//	}

}
