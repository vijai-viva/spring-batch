/**

============================================================================

Project Name : Spring Batch Processing Demo
File Name : CustomerRepository.java
Author : Vijai Srirangan
Created Date : 2025-05-05
Description : Repository interface for Customer entity. Provides CRUD operations
            and pagination support by extending Spring Data JPA's JpaRepository.
============================================================================
*/
package com.viva.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viva.batch.entity.Customer;
/**
 * Repository interface for managing {@link Customer} entities.
 * Inherits basic CRUD, pagination, and sorting capabilities from {@link JpaRepository}.
 *
 * <p>Spring Data JPA automatically provides the implementation at runtime.
 *
 * @author Vijai Srirangan
 * @since 1.0
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
