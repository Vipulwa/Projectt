package com.cybage.service;

import java.time.LocalDate;
import java.util.List;

import com.cybage.dto.ComplaintDTO;

public interface IDepartmentService {

	public List<ComplaintDTO> getComplaintList(int id,String status);
	
	public String closeComplaint(int complaintId,int departmentId);
	
	public String transferCompalint(int complaintId, int departmentId,String deptName);
	
	public List<ComplaintDTO> findBetweenDates(int departmentId,String status,String date1,String date2);

	public Object getDepartmentList(int departmentId);
//	public List<ComplaintDTO> findByDepartmentId(int departmentId,String status) ;
}
