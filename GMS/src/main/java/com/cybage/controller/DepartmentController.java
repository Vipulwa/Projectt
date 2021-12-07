package com.cybage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cybage.dto.ComplaintDTO;
import com.cybage.dto.ResponseDTO;
import com.cybage.service.DepartmentServiceImpl;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/department")
public class DepartmentController {


	@Autowired
	DepartmentServiceImpl departmentService;
	
	@GetMapping("/complaints")
	public ResponseDTO<?> getComplaints(@RequestParam int userId,@RequestParam String status) {
		try {
			List<ComplaintDTO> complaintDTO=departmentService.getComplaintList(userId,status);
			if(complaintDTO!=null)
				return new ResponseDTO<>(HttpStatus.OK, "Complaints found",complaintDTO);
			else
				return new ResponseDTO<>(HttpStatus.OK, "There are no Comments","There are no Comments");
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.getComplaintList(userId,status));
		}
	}
	
	@PutMapping("/resolve")
	public ResponseDTO<?> closeComplaint(@RequestParam int complaintId, @RequestParam int userId) {
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Resolved",
					departmentService.closeComplaint(complaintId,userId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.closeComplaint(complaintId,userId));
		}
	}
	
	@PutMapping("/transfer")
	public ResponseDTO<?> transferComplaint(@RequestParam int complaintId, 
			@RequestParam int userId,
			@RequestParam String departmentName) {
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Transfered",
					departmentService.transferCompalint(complaintId,userId,departmentName));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.transferCompalint(complaintId,userId,departmentName));
		}
	}
	
	@GetMapping("/complaintsDates")
	public ResponseDTO<?> getComplaintsBetweenDates(@RequestParam int userId,@RequestParam String status,
			@RequestParam String from,@RequestParam String to) {
		
		try {
			
			return new ResponseDTO<>(HttpStatus.OK, "Complaints Found",
					departmentService.reportByDates(userId, status, from, to));
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.reportByDates(userId, status, from, to));
		}
	}
	
	@GetMapping("/departmentNames")
	public ResponseDTO<?>  getDepartmentList(@RequestParam int userId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaints Found",
					departmentService.getDepartmentList(userId));
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.getDepartmentList(userId));
		}
	}
	
	@GetMapping("/report")
	public ResponseDTO<?> date(@RequestParam int userId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaints Found",
					departmentService.report(userId));
		} catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.report(userId));
		}
	}
	
	@GetMapping("/monthlyReport")
	public ResponseDTO<?> report(@RequestParam int userId){
		try {
			return new ResponseDTO<>(HttpStatus.OK, "Complaint Added",
					departmentService.getMonthlyReport(userId));
		}catch (RuntimeException e) {
			System.out.println("Error in finding complaint");
			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
					departmentService.getMonthlyReport(userId));
		}
	}
	
	
//	@GetMapping("/complaints/{id}")
//	public ResponseDTO<?> getComplaints(@PathVariable int id,@RequestParam String status) {
//		try {
//			return new ResponseDTO<>(HttpStatus.OK, "Complaints found",
//					
//					departmentService.findByDepartmentId(id,status)
//					);
//			
//		} catch (RuntimeException e) {
//			System.out.println("Error in finding complaint");
//			return new ResponseDTO<>(HttpStatus.INTERNAL_SERVER_ERROR, "Complaint Not Found",
//					departmentService.findByDepartmentId(id,status));
//		}
//	}
}

