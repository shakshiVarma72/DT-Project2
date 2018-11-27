package com.niit.BackendProject2.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BackendProject2.dto.ApplyJob;

import com.niit.BackendProject2.dto.Job;
@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
@Autowired
SessionFactory sessionFactory;
	public boolean addJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return false;
	}
		
			

	public boolean deleteJob(Job job) {
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	
	}
	

	public boolean updateJob(Job job) {
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
		
	}

	public Job getJob(int jobId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Job job=(Job)session.get(Job.class,jobId);
			return job;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return null; 
	}
		
	}

	public List<Job> listAllJobs() {
		Session session =sessionFactory.getCurrentSession();
		Criteria sakshi1=session.createCriteria(Job.class);
		List<Job> jobList=sakshi1.list();
			return jobList;
			
		}
	

	public boolean applyJob(ApplyJob applyJob) {
		try {
			sessionFactory.getCurrentSession().save(applyJob);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return false;
	}
		
	

	public List<ApplyJob> getAllAppliedJobDetails() {
		Session session =sessionFactory.getCurrentSession();
		Criteria sakshi1=session.createCriteria(ApplyJob.class);
		List<ApplyJob> job1List=sakshi1.list();
			return job1List;
	}



	public List<Job> listSortedJobs() {
		Session session =sessionFactory.getCurrentSession();
		Query q=session.createQuery("from com.niit.BackendProject2.dto.Job order by lastDateApply");
		List<Job> job2List=q.list();
			return job2List;
			
		}
		
}
