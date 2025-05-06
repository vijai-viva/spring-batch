package com.viva.batch;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.viva.batch.config.BatchConfig;
import com.viva.batch.config.CustomerProcesser;
import com.viva.batch.entity.Customer;
import com.viva.batch.repository.CustomerRepository;

class BatchConfigTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private org.springframework.batch.core.repository.JobRepository jobRepository;

    @InjectMocks
    private BatchConfig batchConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    

    @Test
    void testCustomerProcessorReturnsSameCustomer() throws Exception {
        CustomerProcesser processor = batchConfig.getCustomerProcesser();
        Customer customer = new Customer(1, "John", "john@test.com", 30);
        Customer result = processor.process(customer);
        assertEquals(customer, result);
    }



    @Test
    void testStepIsNotNull() {
        Step step = batchConfig.step1(jobRepository, transactionManager);
        assertNotNull(step);
    }

    @Test
    void testJobIsNotNull() {
        Job job = batchConfig.runJob(jobRepository, transactionManager);
        assertNotNull(job);
    }

    @Test
    void testTaskExecutorConfig() {
        TaskExecutor executor = batchConfig.taskExecutor();
        assertNotNull(executor);
    }
}
