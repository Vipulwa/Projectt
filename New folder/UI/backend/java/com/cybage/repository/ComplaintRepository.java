package com.cybage.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.cybage.model.Complaint;
import com.cybage.model.Status;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Integer>{
	List<Complaint> findByDepartmentIdId(int dId);
	
	List<Complaint> findByDepartmentIdIdAndStatus(int departmentId, Status status);
	
	@Transactional
	@Modifying
	@Query("update Complaint c set c.status='CLOSED' where c.id=?1")
	int closeComplaint(int complaintId);

	@Transactional
	@Modifying
	@Query("update Complaint c set c.departmentId.id=?1 where c.id=?2")
	int transferComplaint(int departmentId ,int complaintId);

	@Query("select c from Complaint c where c.departmentId.id=:id and c.createDate between :dt1 and :dt2")
	List<Complaint> findBetweenDate(@Param("id")int departmentId,@Param("dt1")LocalDate date1, @Param("dt2")LocalDate date2);
	
	@Query("select c from Complaint c where c.departmentId.id=?1 and c.status=?2 and c.createDate between ?3 and ?4")
	List<Complaint> findBetweenDateWithStatus(int departmentId,Status sts,LocalDate date1,LocalDate date2);
	
	
	//suraj
	@Query("SELECT u FROM Complaint u WHERE u.userId = userId")
	List<Complaint> getComplaintStatus(int userId);
	
	
	
	
	@Modifying
	@Transactional
	@Query("update Complaint u set u.status = 'REMINDER' WHERE u.id = :id")
	//@Query("UPDATE Complaint SET status = 'REMINDER' WHERE id = :id")
	void sendReminder(@Param("id") int id);
	
	@Query("select u from Complaint u where u.userId = userId and u.status = 'ACTIVE'")
	List<Complaint> getActiveComplaints(int userId);

	List<Complaint> findByUserIdId(int userId);

	List<Complaint> findByUserIdIdAndStatus(int userId, Status sts);
	
	

	

		
}
