package com.niit.Midddleware1.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.niit.BackendProject2.Dao.JobDao;

import com.niit.BackendProject2.Dao.UserDao;
import com.niit.BackendProject2.dto.ApplyJob;
import com.niit.BackendProject2.dto.Job;
import com.niit.BackendProject2.dto.User;

@Controller
public class JobController {
	@Autowired
	JobDao jobDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	HttpSession session;
	
	@PostMapping(value="/addJob")
	public ResponseEntity<String> addJob(@RequestBody Job job,HttpSession session){
		System.out.println("I m here");
		
		
		if(jobDao.addJob(job)){
			return new ResponseEntity<String>("job Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/deleteJob/{jobId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int jobId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+jobId);
		Job job=jobDao.getJob(jobId);
		
		if(jobDao.deleteJob(job)){
			return new ResponseEntity<String>("Job Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Job",HttpStatus.OK);
		}
	}
	@PostMapping(value="/updateJob")
	public ResponseEntity<String> updateJob(@RequestBody Job job){
		System.out.println(job.getJobId()+" "+job.getJobDesc()+" "+job.getCompany()+" "+job.getJobDesignation()+" "+job.getLocation()+" "+job.getSalary());
		if(jobDao.updateJob(job)){
			return new ResponseEntity<String>(" Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating job",HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping(value="/getJob/{jobId}")
	public ResponseEntity<Job> getJob(@PathVariable int jobId){
		
		Job job =jobDao.getJob(jobId);
		if(job==null){
			System.out.println("Job  Not Found");
			return new ResponseEntity<Job>(job,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Job Found "+job.getJobDesignation()+job.getCompany()+job.getJobDesc()+job.getLocation()+job.getSalary());
			return new ResponseEntity<Job>(job,HttpStatus.OK);
		}
	}
	@GetMapping(value="/listAllJobs")
	public ResponseEntity<List<Job>> getAlljobs(){

		List<Job> listJob=jobDao.listAllJobs();
		
		for(Job j:listJob) {
			System.out.println(j);
		}
		
		if(listJob.size()>0){
			return new ResponseEntity<List<Job>>(listJob,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Job>>(listJob,HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/addApplyJob")
	public ResponseEntity<String> addApplyJob(@PathVariable int jobId){
		System.out.println("I m here");
		ApplyJob app=new ApplyJob();
	app.setApplyDate(new java.util.Date());
	app.setJobId(jobId);
		
		User user=(User)session.getAttribute("userObj");
		System.out.println("User Details = "+user.getLoginName());
		app.setLoginName(user.getLoginName());
		
		if(jobDao.applyJob(app)){
			return new ResponseEntity<String>(" job applied Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}	 
	@GetMapping(value="/listAllApplyJob")
	public ResponseEntity<List<ApplyJob>> getAllAppliedJobDetails(){

		List<ApplyJob> listJob=jobDao.getAllAppliedJobDetails();
		
		if(listJob.size()>0){
			return new ResponseEntity<List<ApplyJob>>(listJob,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<ApplyJob>>(listJob,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/listSortedJobs")
	public ResponseEntity<List<Job>> getlistSortedJobs(){

		List<Job> listJob1=jobDao.listSortedJobs();
		
		for(Job j:listJob1) {
			System.out.println(j);
		}
		
		if(listJob1.size()>0){
			return new ResponseEntity<List<Job>>(listJob1,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Job>>(listJob1,HttpStatus.NOT_FOUND);
		}
	}
}
