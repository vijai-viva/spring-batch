/**============================================================================

Project Name : Spring Batch Processing Demo
File Name : BatchConfig.java
Author : Vijai Srirangan
Created Date : 2025-05-05
Description : This class contains the configuration for Spring Batch jobs.
            It defines the reader, processor, writer, step, job, and task executor  
            required to import CSV data into the database using a JPA repository.  
============================================================================
*/
package com.viva.batch.config;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.viva.batch.entity.Customer;
import com.viva.batch.repository.CustomerRepository;

import jakarta.annotation.PostConstruct;
/**
 * Batch configuration class for defining job steps, readers, writers, and processors.
 * Reads customer data from a CSV file and writes it to a database using a JPA repository.
 * 
 * @author Vijai Srirangan
 * @since 1.0
 */
@Configuration
public class BatchConfig {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Value("${batch.csv-path}")
	private String csvPath;
	
	 @PostConstruct
	    public void validateCSVFile() {
	        File csvFile = new File(csvPath);
	        if (!csvFile.exists() || !csvFile.isFile()) {
	            throw new IllegalArgumentException("CSV file is missing or path is invalid.");
	        }
	    }

	  /**
     * Defines a FlatFileItemReader to read customer data from a CSV file.
     * 
     * @return configured FlatFileItemReader
     */
	@Bean
	public FlatFileItemReader<Customer> fileReader() {
		FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setResource(new FileSystemResource("./src/main/resources/random_users.csv"));
		reader.setLineMapper(lineMapper());
		return reader;
	}
	 /**
     * Configures line mapping from CSV line to Customer object.
     * 
     * @return LineMapper<Customer>
     */
	private LineMapper<Customer> lineMapper() {
		DefaultLineMapper<Customer> lineMapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames("id", "custname", "emailid", "age");
		tokenizer.setStrict(false);
		
		
		BeanWrapperFieldSetMapper<Customer> mapper = new BeanWrapperFieldSetMapper<>();
		mapper.setTargetType(Customer.class);
		mapper.setDistanceLimit(0);
		
		lineMapper.setLineTokenizer(tokenizer);
		lineMapper.setFieldSetMapper(mapper);
		
		return lineMapper;
	}

	 /**
     * Returns a custom Customer processor bean.
     * 
     * @return CustomerProcesser instance
     */
	@Bean
	public CustomerProcesser getCustomerProcesser() {
		return new CustomerProcesser();
	}

	  /**
     * Configures the repository writer to persist Customer data using CustomerRepository.
     * 
     * @return RepositoryItemWriter<Customer>
     */
	@Bean
	public RepositoryItemWriter<Customer> repoWriter() {
		RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}

	 /**
     * Configures a step that reads, processes, and writes chunks of Customer data.
     * 
     * @param jobRepository JobRepository instance
     * @param transactionManager Transaction manager
     * @return configured Step
     */
	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("CSV-SAVE", jobRepository)
				.<Customer, Customer>chunk(100, transactionManager)
				.reader(fileReader())
				.processor(getCustomerProcesser())
				.writer(repoWriter())
				.taskExecutor(taskExecutor())
				.build();
	}

	/**
     * Configures the job that executes the customer import step.
     * 
     * @param jobRepository JobRepository
     * @param transactionManager PlatformTransactionManager
     * @return configured Job
     */
	@Bean
	public Job runJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new JobBuilder("ImportCustomers", jobRepository).start(step1(jobRepository, transactionManager)).build();
	}
	
	/**
     * Provides a simple async task executor for parallel execution of chunks.
     * 
     * @return SimpleAsyncTaskExecutor instance
     */
	@Bean
	public TaskExecutor taskExecutor() { 
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(5);
		return asyncTaskExecutor;
	}
}
