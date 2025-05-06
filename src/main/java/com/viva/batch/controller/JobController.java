/**

============================================================================

Project Name : Spring Batch Processing Demo
File Name : JobController.java
Author : Vijai Srirangan
Created Date : 2025-05-05
Description : REST controller to manually trigger the Spring Batch job using
            the `/jobs/importCust` endpoint. This class uses Spring's JobLauncher o initiate the 
            job with a unique timestamp to avoid duplication issues.
============================================================================
*/
package com.viva.batch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * REST Controller that exposes an endpoint to manually trigger the Spring Batch job.
 *
 * <p>Endpoint: <ul>
 *     <li><code>POST /jobs/importCust</code> – Triggers the batch job</li>
 * </ul> *
 * <p>The job is uniquely triggered using the current timestamp to prevent conflicts
 * with previously run instances. All job execution exceptions are logged using stack trace.
 * @author Vijai Srirangan
 * @since 1.0
 */
@RestController
@RequestMapping("/jobs")
public class JobController {

    private static final Logger logger = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobLauncher launcher;
	
	@Autowired
	private Job job;
	
	
	/**
     * Triggers the job using a unique JobParameter (timestamp).
     * Handles and logs various job execution exceptions.
     */
	@PostMapping("/importCust")
	public ResponseEntity<String> importCSVToDBJob() {
		JobParameters params = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
		
		 try {
	            launcher.run(job, params);
	            logger.info("Batch job triggered successfully.");
	            return ResponseEntity.ok("Batch job triggered successfully.");
	        } catch (JobExecutionAlreadyRunningException e) {
	            logger.error("Job is already running.", e);
	            return ResponseEntity.status(409).body("Job is already running.");
	        } catch (JobRestartException e) {
	            logger.error("Job restart failed.", e);
	            return ResponseEntity.status(500).body("Job restart failed.");
	        } catch (JobInstanceAlreadyCompleteException e) {
	            logger.error("Job instance already completed.", e);
	            return ResponseEntity.status(409).body("Job instance already completed.");
	        } catch (JobParametersInvalidException e) {
	            logger.error("Invalid job parameters.", e);
	            return ResponseEntity.badRequest().body("Invalid job parameters.");
	        }
		
		
	}
	
}
