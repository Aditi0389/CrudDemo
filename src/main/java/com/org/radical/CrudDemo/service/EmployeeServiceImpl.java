/**
 * 
 */
package com.org.radical.CrudDemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.org.radical.CrudDemo.controller.Root;
import com.org.radical.CrudDemo.entity.Employee;
import com.org.radical.CrudDemo.repository.EmployeeRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public long saveEmployee(Employee employee) {
		employeeRepository.save(employee);
		return employeeRepository.count();

	}

	@Override
	public List<Employee> fetchAllEmployee() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> saveInBulk(List<Employee> list) {
		employeeRepository.saveAll(list);
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> findById(long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public List<Employee> findByEmpName(String name) {
		return employeeRepository.findByName(name);
	}

	@Override
	public List<Employee> findByEmpNameAndCity(String name, String city) {
		return employeeRepository.findByNameAndCity(name, city);
	}

	@Override
	public List<Employee> findByEmpNameOrCity(String name, String city) {
		return employeeRepository.findByNameOrCity(name, city);
	}
	
	@Override
	public void deleteEmployeeById(String id) {
		employeeRepository.deleteById(Long.valueOf(id));
		
	}

	@Override
	public void deleteAllEmployee() {
		employeeRepository.deleteAll();
	}

	@Override
	public ResponseEntity<String> callAPI(String id) {

		Gson gson = new Gson();
		ResponseEntity<String> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/"+id,
				String.class);
		Root root = gson.fromJson(responseEntity.getBody(), Root.class);
		log.debug("Response received from https://jsonplaceholder.typicode.com/posts/1"+ root.toString());
		
		Root root2 = new Root();
		root2.setId(root.getId());
		root2.setTitle(root.getTitle());
		root2.setUserId(root.getUserId());
		return new ResponseEntity<String>(gson.toJson(root2), HttpStatus.OK);
	}
}
