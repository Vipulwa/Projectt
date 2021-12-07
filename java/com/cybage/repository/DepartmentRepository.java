package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public Department findByDepartmentName(String name);
	public Department findByUserId(int id);
	public Department findBydepartmentName(String departmentName);

}
