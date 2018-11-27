package com.niit.BackendProject2.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BackendProject2.dto.Forum;
import com.niit.BackendProject2.dto.ForumComment;
@Repository("forumDao")
@Transactional
public class ForumDaoImpl implements ForumDao {
@Autowired
SessionFactory sessionFactory;
	public boolean addForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().save(forum);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return false;
	}

	

	public boolean deleteForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().delete(forum);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	}
	

	public boolean updateForum(Forum forum) {
		try {
			sessionFactory.getCurrentSession().update(forum);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	}

	public Forum getForum(int forumId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Forum forum=(Forum)session.get(Forum.class,forumId);
			return forum;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return null; 
	}
		
	}

	public boolean approveForum(Forum forum) {
		try {
			Session session=sessionFactory.getCurrentSession();
			forum.setStatus("Approved");
			session.update(forum);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return false; 
	}
	}

	public boolean rejectForum(Forum forum) {
		try {
			Session session=sessionFactory.getCurrentSession();
			forum.setStatus("Rejected");
			session.update(forum);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return false; 
	}
	}

	public List<Forum> listForums(String userName) {
		try{
			Session session=sessionFactory.getCurrentSession();
			Query query=session.createQuery
			("from com.niit.BackendProject2.dto.Forum where loginName=:a ");
			query.setParameter("a",userName);
			
			@SuppressWarnings("unchecked")
			List<Forum> listForum=query.list();
			return listForum;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}	
	

	public boolean addForumComment(ForumComment ForumComment) {
		try {
			sessionFactory.getCurrentSession().save(ForumComment);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return false;
	}


	public boolean deleteForumComment(ForumComment ForumComment) {
		try {
			sessionFactory.getCurrentSession().delete(ForumComment);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	}
	

	public ForumComment getForumComment(int commentId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			ForumComment forum=(ForumComment)session.get(Forum.class,commentId);
			return forum;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return null; 
	}
		
	}

	public List<ForumComment> listForumComments(int ForumId) {
		Session session =sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("from Blog where status='Active'");
		List<ForumComment> forumComment=query.list();
		return forumComment;
		
	}



	public List<Forum> listAllApprovedForums() {
		Session session =sessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(Forum.class);
		cr.add(Restrictions.eq("status", "Approved"));
		List<Forum> ForumList=cr.list();
			return ForumList;
			
		}



	public List<Forum> listPendingForums() {
		Session session =sessionFactory.getCurrentSession();
		Criteria cr=session.createCriteria(Forum.class);
		cr.add(Restrictions.eq("status", "Pending"));
		List<Forum> ForumList1=cr.list();
			return ForumList1;
	}
	}
		

