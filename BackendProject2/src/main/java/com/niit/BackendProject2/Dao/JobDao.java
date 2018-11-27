package com.niit.BackendProject2.Dao;

import java.util.List;

import com.niit.BackendProject2.dto.ApplyJob;
import com.niit.BackendProject2.dto.Job;

public interface JobDao {
	

		public boolean addJob(Job job);
		public boolean deleteJob(Job job);
		public boolean updateJob(Job job);
		public Job getJob(int jobId);
		public List<Job> listAllJobs();
		public boolean applyJob(ApplyJob applyJob);
		
		public List<ApplyJob> getAllAppliedJobDetails();
		public List<Job> listSortedJobs();
	}



