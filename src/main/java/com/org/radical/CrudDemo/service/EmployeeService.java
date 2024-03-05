package com.org.radical.CrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.org.radical.CrudDemo.entity.Employee;

@Service
public interface EmployeeService {

	public long saveEmployee(Employee employee);

	public List<Employee> saveInBulk(List<Employee> list);

	public List<Employee> fetchAllEmployee();

	public Optional<Employee> findById(long id);

	public List<Employee> findByEmpName(String name);

	public List<Employee> findByEmpNameAndCity(String name, String city);

	public List<Employee> findByEmpNameOrCity(String name, String city);
	
	public void deleteEmployeeById(String id);
	
	public void deleteAllEmployee();
	
	public ResponseEntity<String> callAPI(@RequestParam String id);

}
