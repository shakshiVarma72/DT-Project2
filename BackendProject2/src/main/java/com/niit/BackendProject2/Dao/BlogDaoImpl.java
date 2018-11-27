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

import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.BlogComment;

@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
@Autowired
SessionFactory sessionFactory;

	public boolean addBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return false;
	}

	public boolean deleteBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	
	}

	public boolean updateBlog(Blog blog) {
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
		
	}

	public Blog getBlog(int blogId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			Blog blog=(Blog)session.get(Blog.class,blogId);
			return blog;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return null; 
	}
		
	}

	public boolean approveBlog(Blog blog) {
		try {
			Session session=sessionFactory.getCurrentSession();
			blog.setStatus("Approved");
			session.update(blog);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return false; 
	}
	}

	public boolean rejectBlog(Blog blog) {
		try {
			Session session=sessionFactory.getCurrentSession();
			blog.setStatus("Rejected");
			session.update(blog);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return false; 
	}
	}

	
	public List<Blog> listBlogs(String userName) {
		try{
			System.out.println("user Name : "+userName);
			Session session=sessionFactory.getCurrentSession();
			System.out.println("session:"+session);
			
			Query query=null;
			if(userName==null) {
			query =session.createQuery("from Blog");
			}
			else {
				query=session.createQuery("from com.niit.BackendProject2.dto.Blog where loginName=:a");
				query.setParameter("a",userName);
			}
			
			@SuppressWarnings("unchecked")
			List<Blog> listBlogs=query.list();
			return listBlogs;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}

	

	public boolean addBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	return false;
	}
		
		

	public boolean deleteBlogComment(BlogComment blogComment) {
		try {
			sessionFactory.getCurrentSession().delete(blogComment);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	
	}
		
  public BlogComment getBlogComment(int commentId) {
		try {
			Session session=sessionFactory.getCurrentSession();
			BlogComment blog11=(BlogComment)session.get(BlogComment.class,commentId);
			return blog11 ;
		}
		catch(Exception e) {
			e.printStackTrace();
		
		return null; 
	}
		
	}
		

	public List<BlogComment> listBlogComments(int blogId) {
		Session session =sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		Query query=session.createQuery("from Blog where status='Active'");
		List<BlogComment> blogComment=query.list();
		return blogComment;
		
	}

	public boolean incrementLikes(Blog blog) {
		try {
		Session session=sessionFactory.getCurrentSession();
		blog.setLikes(blog.getLikes()+1);
		session.update(blog);
		return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<Blog> listAllApprovedBlogs() {
		  Session session =sessionFactory.getCurrentSession();
	        Criteria cr=session.createCriteria(Blog.class);
	        cr.add(Restrictions.eq("status", "Approved"));
	        List<Blog> BlogList=cr.list();
	            return BlogList;
	}

	public List<Blog> listPendingBlogs() {
		 Session session =sessionFactory.getCurrentSession();
	        Criteria cr=session.createCriteria(Blog.class);
	        cr.add(Restrictions.eq("status", "Pending"));
	        List<Blog> Blog1List=cr.list();
	            return Blog1List;	
	            }
	
	

}
