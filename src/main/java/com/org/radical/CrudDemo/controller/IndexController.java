/**
 * 
 */
package com.org.radical.CrudDemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;
import com.google.gson.Gson;
import com.org.radical.CrudDemo.entity.Employee;
import com.org.radical.CrudDemo.service.EmployeeServiceImpl;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RestController
@Slf4j
public class IndexController {

	@Autowired
	EmployeeServiceImpl employeeServiceImpl;

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/home")
	public void home() {
		log.info("THIS IS INFO");
		log.debug("THIS IS DEBUG");
		log.error("THIS IS ERROR");
		log.warn("THIS IS WARN");
		log.trace("THISa IS trace");
		for (int i = 0; i < 1000000; i++) {
			log.debug("i VALUE IS " + i);
		}

	}

	@PostMapping(value = "/saveemployee")
	public boolean saveEmployee(@RequestBody Employee employee) {
		employeeServiceImpl.saveEmployee(employee);
		return true;
	}

	@PostMapping(value = "/saveempinbulk")
	public List<Employee> saveEmployee(@RequestBody List<Employee> employeeList) {
		return employeeServiceImpl.saveInBulk(employeeList);
	}

	@GetMapping("/saveemp")
	public void saveEmp() {
		Employee employee = new Employee();
//		employee.setEmpId(4l);
		employee.setName("ABC");
		employee.setCity("Pune");
		employee.setGender("M");
		employee.setCountry("IND");
		employee.setDept("IT");
		employeeServiceImpl.saveEmployee(employee);
	}

	@GetMapping("/fetchemp")
	public List<Employee> fetchEmp() {
		return employeeServiceImpl.fetchAllEmployee();
	}

	@GetMapping("/findbyempid")
	public Optional<Employee> getMethodName(@RequestParam String id) {
		return employeeServiceImpl.findById(Long.valueOf(id));
	}

	@GetMapping("/findbyempname")
	public List<Employee> getByEmpName(@RequestParam String name) {
		return employeeServiceImpl.findByEmpName(name);
	}

	@GetMapping("/findbyempnameandcity")
	public List<Employee> getByEmpNameAndCity(@RequestParam String name, @RequestParam String city) {
		return employeeServiceImpl.findByEmpNameAndCity(name, city);
	}

	@GetMapping("/findbyempnameorcity")
	public List<Employee> getByEmpNameOrCity(@RequestParam String name, @RequestParam String city) {

		return employeeServiceImpl.findByEmpNameOrCity(name, city);
	}

	@DeleteMapping("/deleteemp")
	public void deleteEmployee(@RequestParam String id) {
		employeeServiceImpl.deleteEmployeeById(id);
	}

	@DeleteMapping("/deleteallemp")
	public void deleteemployee() {
		employeeServiceImpl.deleteAllEmployee();
	}

	// demo for customized setting of status code and message
	@GetMapping("/findbyempnameandcitycustomized")
	public ResponseEntity<Object> getByEmpNameAndCityCustomized(@RequestParam String name, @RequestParam String city) {
		List<Employee> empList = employeeServiceImpl.findByEmpNameAndCity(name, city);
		if (!empList.isEmpty()) {
			return new ResponseEntity<Object>(employeeServiceImpl.findByEmpNameAndCity(name, city), HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(employeeServiceImpl.findByEmpNameAndCity(name, city),
					HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/get")
	public ResponseEntity<Object> get(@RequestParam String a, @RequestParam String b) {
		int numbera = Integer.parseInt(a);
		int numberb = Integer.parseInt(b);
		int c;

		try {
			c = numbera / numberb;
			return new ResponseEntity<Object>(c, HttpStatus.OK);
		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value="/posts", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> get(@RequestParam String id) {
		ResponseEntity<String> responseEntity = employeeServiceImpl.callAPI(id);
		Gson gson = new Gson();
		Root r = gson.fromJson(responseEntity.getBody(), Root.class);
		if(r.getId() == 3)
		{
			return new ResponseEntity<String>("INVALID REQUEST", HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<String>(gson.toJson(r), HttpStatus.OK);
		}
	}
}
