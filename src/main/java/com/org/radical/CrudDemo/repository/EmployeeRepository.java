/**
 * 
 */
package com.org.radical.CrudDemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.radical.CrudDemo.entity.Employee;

/**
 * 
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public List<Employee> findByName(String name);

	public List<Employee> findByNameAndCity(String name, String city);

	public List<Employee> findByNameOrCity(String name, String city);

}

