/**

============================================================================

Project Name : Spring Batch Processing Demo
File Name : Customer.java
Author : Vijai Srirangan
Created Date : 2025-05-05
Description : Entity class that maps to the "tbl_customer" table in the database.
            Represents customer data including ID, name, email, and age.
============================================================================
*/
package com.viva.batch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * Entity class for the customer table (`tbl_customer`).
 * This class uses Jakarta Persistence API annotations for ORM mapping.
 * Lombok is used to reduce boilerplate code for getters, setters, and constructors.
 *
 * <p>Fields:
 * <ul>
 *     <li><b>id</b> – Primary key of the customer</li>
 *     <li><b>custname</b> – Customer's name</li>
 *     <li><b>emailid</b> – Email address of the customer</li>
 *     <li><b>age</b> – Age of the customer</li>
 * </ul>
 *
 * <p>Note: Ensure that Lombok is properly configured in your build tool.
 *
 * @author Vijai Srirangan
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tbl_customer")
public class Customer {

	@Id
	@Column(name = "id")
	private int id;
	@Column(name = "custname")
	private String custname;
	@Column(name = "emailid")
	private String emailid;
	@Column(name = "age")
	private int age;
}
