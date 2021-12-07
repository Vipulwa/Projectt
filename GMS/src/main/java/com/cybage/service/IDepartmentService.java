package com.cybage.service;
import java.util.List;

import com.cybage.dto.ComplaintDTO;
import com.cybage.dto.MonthlyReportDTO;
import com.cybage.dto.ReportDTO;

public interface IDepartmentService {

	public List<ComplaintDTO> getComplaintList(int id,String status);
	
	public String closeComplaint(int complaintId,int departmentId);
	
	public String transferCompalint(int complaintId, int departmentId,String deptName);
	
	public List<ComplaintDTO> reportByDates(int userId,String status,String date1,String date2);

	public Object getDepartmentList(int userId);
//	public List<ComplaintDTO> findByDepartmentId(int userId,String status) ;
	public ReportDTO report(int userId);
	public List<MonthlyReportDTO> getMonthlyReport(int userId);

//	public Object getDepartmentList(int departmentId);

}
