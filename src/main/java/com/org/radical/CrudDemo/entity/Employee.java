/**
 * 
 */
package com.org.radical.CrudDemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 
 */

@Data
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "emp_Id", length = 10, nullable = false)
	private Long empId;
	@Column(name = "name", length = 100, nullable = false)
	private String name;
	@Column(name = "address", length = 150, nullable = true)
	private String address;
	@Column(name = "pincode", length = 10, nullable = false)
	private long pincode;
	@Column(name = "city", length = 150, nullable = false)
	private String city;
	@Column(name = "country", length = 150, nullable = false)
	private String country;
	@Column(name = "gender", length = 19, nullable = false)
	private String gender;

	@Column(name = "dept", length = 19, nullable = false)
	private String dept;

}
