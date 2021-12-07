package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybage.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	public Department findByDepartmentName(String name);
	public Department findByUserId(int id);
	public Department findBydepartmentName(String departmentName);
	@Query("select d.departmentName from Department d")
	public List<String> getDepartmentNames();

}
