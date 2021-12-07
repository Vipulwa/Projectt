package com.cybage.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.dto.ComplaintDTO;
import com.cybage.dto.MonthlyReportDTO;
import com.cybage.dto.ReportDTO;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Status;
import com.cybage.repository.ComplaintRepository;
import com.cybage.repository.DepartmentRepository;
@Transactional
@Service
public class DepartmentServiceImpl implements IDepartmentService {
	@Autowired
	ComplaintRepository complaintRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<ComplaintDTO> getComplaintList(int userId,String status){
		
		Department department =departmentRepository.findByUserId(userId);
		List<ComplaintDTO> complaintDtoList=new ArrayList<>();
		List<Complaint> complaintList=department.getComplaints();
		complaintList.forEach((complaint)->
		{
			ComplaintDTO complaintDTO=new ComplaintDTO(
					complaint.getId(),complaint.getSubject(),
					complaint.getComplain(),complaint.getCreateDate(),
					complaint.getCloseDate(),complaint.getStatus().toString(),
					complaint.getUserId().getId(),complaint.getUserId().getUsername(),
					complaint.getDepartmentId().getDepartmentName(),complaint.getComments()
					);
			switch (status) {
			 case "ALL":
				 complaintDtoList.add(complaintDTO);
	             break;
	         case "CLOSED":
	        	 if(complaintDTO.getStatus().equals("CLOSED"))
	        		 complaintDtoList.add(complaintDTO);
	             break;
	         case "ACTIVE":
	        	 if(complaintDTO.getStatus().equals("ACTIVE"))
	        		 complaintDtoList.add(complaintDTO);
	        	 break;
	         case "REMINDER":
	        	 if(complaintDTO.getStatus().equals("REMINDER"))
	        		 complaintDtoList.add(complaintDTO);
	        	 break;
	     }
		});
		return complaintDtoList;
	}
	
	
	@Override
	public String closeComplaint(int complaintId,int userId) {
		Department department =departmentRepository.findByUserId(userId);
		if(department!=null){
		List<Complaint> complaintList=department.getComplaints();
		complaintList.forEach((complaint)-> 
		{
			if(complaint.getId()==complaintId && complaint.getStatus()!=Status.CLOSED){
				complaint.setStatus(Status.CLOSED);
				complaint.setCloseDate(LocalDate.now());
			}
		}
		);
		return "Complaint Resolved";
		}
		return "Complaint Not found";
	}

	@Override
	public String transferCompalint(int complaintId, int userId,String departmentName) {
		Department department =departmentRepository.findByUserId(userId);
		if(department!=null){
		List<Complaint> complaintList=department.getComplaints();
		complaintList.forEach((complaint)-> 
		{
			if(complaint.getId()==complaintId ){
				complaint.setDepartmentId(departmentRepository.findByDepartmentName(departmentName));
			}
		}
		);
		return "Complaint Transfered";
		}
		return "Complaint Not found";

	}

	@Override
	public List<ComplaintDTO> reportByDates(int userId, String status, String date1, String date2) {
		
		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		LocalDate from = LocalDate.parse(date1, inputFormatter);
		LocalDate to = LocalDate.parse(date2, inputFormatter);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
//	    LocalDate from = LocalDate.parse(date1, formatter);
//	    LocalDate to = LocalDate.parse(date2, formatter);
		Department department =departmentRepository.findByUserId(userId);
		if(department!=null){
			List<Complaint> complaintList=department.getComplaints();
			List<ComplaintDTO> complaintDtoList=new ArrayList<>();
			complaintList.forEach((complaint)->
			{
				if(from.compareTo(complaint.getCreateDate())<=-1 && to.compareTo(complaint.getCreateDate())>=-1){
					ComplaintDTO complaintDTO=new ComplaintDTO(
							complaint.getId(),complaint.getSubject(),
							complaint.getComplain(),complaint.getCreateDate(),
							complaint.getCloseDate(),complaint.getStatus().toString(),
							complaint.getUserId().getId(),
							complaint.getUserId().getUsername(),
							complaint.getDepartmentId().getDepartmentName(),
							complaint.getComments()
							);
				switch (status) {
				 case "ALL":
					 complaintDtoList.add(complaintDTO);
		             break;
		         case "CLOSED":
		        	 if(complaintDTO.getStatus().equals("CLOSED"))
		        		 complaintDtoList.add(complaintDTO);
		             break;
		         case "ACTIVE":
		        	 if(complaintDTO.getStatus().equals("ACTIVE"))
		        		 complaintDtoList.add(complaintDTO);
		        	 break;
		         case "REMINDER":
		        	 if(complaintDTO.getStatus().equals("REMINDER"))
		        		 complaintDtoList.add(complaintDTO);
		        	 break;
		     }}
			});
			return complaintDtoList;
		}
		return null;	
	}

	@Override
	public List<String> getDepartmentList(int userId) {
		Department department =departmentRepository.findByUserId(userId);
		if(department!=null){
			List<String> departmentNameList=new ArrayList<>();
			departmentRepository.findAll().forEach((dept)->{
				if(department.getDepartmentName().compareTo(dept.getDepartmentName())!=0)
					departmentNameList.add(dept.getDepartmentName());
				}
			);
			return departmentNameList;
		}
		return null;
	}




@Override
	public ReportDTO report(int userId) {
		Department department=departmentRepository.findByUserId(userId);
		if(department!=null){
			long closed=complaintRepository.getClosedReport(department.getId());
			long active=complaintRepository.getActiveReport(department.getId());
			long reminder=complaintRepository.getReminderReport(department.getId());
			return new ReportDTO(active,closed,reminder);
		}
		return null;
		
		
	}

	@Override
	public List<MonthlyReportDTO> getMonthlyReport(int userId){
		Department department=departmentRepository.findByUserId(userId);
		if(department!=null){
			return complaintRepository.getMonthlyReport(department.getId());
		}
		return null;
	}

	

	
}
