
/**============================================================================

Project Name : Spring Batch Processing Demo
File Name : CustomerProcessor.java
Author : Vijai Srirangan
Created Date : 2025-05-05
Description : This class is an implementation of Spring Batch's ItemProcessor.
            It processes `Customer` items and is used to perform business logic  
            or transformations before writing them to the destination.  
============================================================================
*/
package com.viva.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.viva.batch.entity.Customer;

/**
 * Processor class for processing Customer entities in the batch job.
 * 
 * This processor simply passes the input item through without any changes.
 * Modify this class if you wish to apply filtering, transformation, or validation logic.
 * 
 * @author Vijai Srirangan
 * @since 1.0
 */

public class CustomerProcesser implements ItemProcessor<Customer, Customer> {

	 /**
     * Processes a single Customer item.
     *
     * @param customer the input Customer object
     * @return the processed Customer object (can return null to filter out)
     * @throws Exception if any processing error occurs
     */
	@Override
	public Customer process(Customer customer) throws Exception {
		 // Currently no processing logic; returning as-is.
		return customer;
	}

}
