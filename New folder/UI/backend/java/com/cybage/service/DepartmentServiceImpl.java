package com.cybage.service;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybage.repository.ComplaintRepository;
import com.cybage.repository.DepartmentRepository;
import com.cybage.dto.ComplaintDTO;
import com.cybage.model.Complaint;
import com.cybage.model.Department;
import com.cybage.model.Status;
@Transactional
@Service
public class DepartmentServiceImpl implements IDepartmentService {
	@Autowired
	ComplaintRepository complaintRepository;
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<ComplaintDTO> getComplaintList(int id,String status){
		Department department =departmentRepository.getById(id);
		List<ComplaintDTO> complaintDtoList=new ArrayList<>();
		List<Complaint> complaintList=department.getComplaints();
		complaintList.forEach((complaint)->
		{
			ComplaintDTO complaintDTO=new ComplaintDTO(
					complaint.getId(),complaint.getSubject(),
					complaint.getComplain(),complaint.getCreateDate(),
					complaint.getCloseDate(),complaint.getStatus().toString(),
					complaint.getUserId().getId(),complaint.getUserId().getUsername(),
					complaint.getDepartmentId().getDepartmentName(),complaint.getComment()
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
	public String closeComplaint(int complaintId,int departmentId) {
		Department department =departmentRepository.getById(departmentId);
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
	public String transferCompalint(int complaintId, int departmentId,String departmentName) {
		Department department =departmentRepository.getById(departmentId);
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
	public List<ComplaintDTO> findBetweenDates(int departmentId, String status, String from, String to) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
	    LocalDate date1 = LocalDate.parse(from, formatter);
	    LocalDate date2 = LocalDate.parse(to, formatter);
		Department department =departmentRepository.getById(departmentId);
		if(department!=null){
			List<Complaint> complaintList=department.getComplaints();
			List<ComplaintDTO> complaintDtoList=new ArrayList<>();
			complaintList.forEach((complaint)->
			{
				if(date1.compareTo(complaint.getCreateDate())>=0 && date2.compareTo(complaint.getCreateDate())<=0){
					ComplaintDTO complaintDTO=new ComplaintDTO(
							complaint.getId(),complaint.getSubject(),
							complaint.getComplain(),complaint.getCreateDate(),
							complaint.getCloseDate(),complaint.getStatus().toString(),
							complaint.getUserId().getId(),
							complaint.getUserId().getUsername(),
							complaint.getDepartmentId().getDepartmentName(),
							complaint.getComment()
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
	public List<String> getDepartmentList(int departmentId) {
		Department department =departmentRepository.getById(departmentId);
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
	



//	public List<?> report(int departmentId,String interval) {
//		Department department =departmentRepository.getById(departmentId);
//		if(department!=null){
//			List<Complaint> complaintList=department.getComplaints();
//			List<ComplaintDTO> complaintDtoList=new ArrayList<>();
//		if(interval.equalsIgnoreCase("MONTHLY"));
//			
////			return complaintRepository.monthlyReport(departmentId);	
////		if(interval.equalsIgnoreCase("QUARTERLY"))
////			return complaintRepository.quarterlyReport(departmentId);
//		}
//		return null;
//	}

	

	
}
