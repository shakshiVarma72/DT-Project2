package com.niit.BackendProject2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.BackendProject2.Dao.JobDao;
import com.niit.BackendProject2.dto.ApplyJob;
import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.Job;

public class JobTest {
	static JobDao jobDao;
	
	@BeforeClass
	public static void init()	{
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		jobDao=(JobDao)context.getBean("jobDao");
		System.out.println("Job Dao : "+jobDao);
}
	
	@Test
	@Ignore
	public void addJobTest(){
		Job job=new Job();
		job.setJobDesc("Project Manager ");
		job.setCompany("Wipro");
		job.setJobDesignation(" Manager");
		job.setLocation("Banglore");
		job.setSalary(45000);
		
		String date="15-10-2018";
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		Date lastDate = null;
		try {
			lastDate = sdf.parse(date);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		job.setLastDateApply(lastDate);
		
		assertTrue("jobs added successfully",jobDao.addJob(job));
		
	}
	@Test
	@Ignore
public void deleteJobTest() {
	Job job=jobDao.getJob(50);
	assertEquals("Succesfully deleted the job", true,
			jobDao.deleteJob(job));
}
	@Test
	@Ignore
	 public void updateJobTest() {
		 Job job =jobDao.getJob(100);
		 job.setJobDesc("Good Knowledge of Spring,Hibernate,Oracle");
		 job.setJobDesignation("Developer");
		 job.setSalary(35000);
		 assertEquals("Job Updated Succesfully",true,jobDao.updateJob(job));
	 }
	@Test
	@Ignore
	public void getJobTest(){
		
		
		assertNotNull("Succesfully fetched a single Job from the table",
				jobDao.getJob(100));
		
	}
	
	@Test
	@Ignore
	public void listJob() {
	List<Job> job=jobDao.listAllJobs();	
	for(Job job1:job) {
		System.out.println(job1);
	}
	
	assertNotNull("No Pending Blogs Found", job);
		
	}
	@Test
	@Ignore
	public void applyJobTest() {
		ApplyJob app=new ApplyJob(); {
		app.setJobId(100);
		app.setLoginName("Mukul Singh");
		Date date=new Date();
		app.setApplyDate(date);
		assertTrue("job applied successfully",jobDao.applyJob(app));
		}
		}
	@Test
	@Ignore
	public void List() {
	List<ApplyJob> job=jobDao.getAllAppliedJobDetails();	   
	for(ApplyJob job1:job) {
		System.out.println(job1);
	}
	
	}
	@Test
	public void listSortedJobs() {
		List<Job> job1=jobDao.listSortedJobs();
		for(Job job2:job1) {
			System.out.println(job2);
			
		}
	}
	
	
}

	
	
