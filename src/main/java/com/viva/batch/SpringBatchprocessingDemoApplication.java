/**
 * ============================================================================
 * Project Name  : Spring Batch Processing Demo
 * File Name     : SpringBatchprocessingDemoApplication.java
 * Author        : Vijai Srirangan
 * Created Date  : 2025-05-05
 * Description   : Main entry point for the Spring Boot application which initializes
 *                 the Spring context and starts the Spring Batch process.
 * ============================================================================
 */

package com.viva.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Spring Batch Processing Demo.
 * This class bootstraps the Spring Boot application.
 *
 * <p>Features:
 * <ul>
 *     <li>Auto-configures the Spring Boot environment</li>
 *     <li>Loads application properties and context</li>
 *     <li>Kicks off Spring Batch infrastructure</li>
 * </ul>
 *
 * @author Vijai Srirangan
 * @since 1.0
 */
@SpringBootApplication
public class SpringBatchprocessingDemoApplication {

	  /**
     * Main method which serves as the entry point for the Spring Boot application.
     *
     * @param args command-line arguments passed during application startup.
     */
	public static void main(String[] args) {
		SpringApplication.run(SpringBatchprocessingDemoApplication.class, args);
	}}
