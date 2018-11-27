package com.niit.BackendProject2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.BackendProject2.Dao.BlogDao;
import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.BlogComment;

public class BlogTest {
	static BlogDao blogDao;
	@BeforeClass
	public static void init()	{
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		blogDao=(BlogDao)context.getBean("blogDao");
		System.out.println("Blog Dao : "+blogDao);
}
	@Test
	//@Ignore
	public void addBlogTest() {
		Blog blog1= new Blog();
		blog1.setBlogName("C++");
		blog1.setBlogContent("Object Oriented Programming Language");
		
		Date date=new Date();
		blog1.setCreateDate(date);
		
		blog1.setLoginName("DivyaG");
		blog1.setLikes(0);
		blog1.setStatus("Approved");
		
		assertTrue("Problem in Inserting User",blogDao.addBlog(blog1));
		
		
		
	}
	
	@Test
	@Ignore
	public void approveBlog() {
		Blog blog=blogDao.getBlog(200);
		System.out.println("Blog - "+blog);
		assertTrue("Blog Approved Succesfully",blogDao.approveBlog(blog));
		
	}
	@Test
	@Ignore
	public void rejectedBlogTest() {
		Blog blog=blogDao.getBlog(100);
		System.out.println("Blog-"+blog);
		assertTrue("Blog rejected Succesfully",blogDao.rejectBlog(blog));
	}
	@Test
	@Ignore
	public void updateTest() {
		Blog blog=blogDao.getBlog(200);
		blog.setBlogName("JDBC_1");
		blog.setBlogContent("Java database Connectivity_1");
		assertEquals("Succesfully updated the loginname of the User", true,
				blogDao.updateBlog(blog));
	}
	@Test
@Ignore
	public void deleteTest() {
		Blog blog=blogDao.getBlog(3153);
		assertEquals("Succesfully deleted the blog", true,
				blogDao.deleteBlog(blog));
	}
	@Test
	@Ignore
	public void addComment() {
		BlogComment blog4 =new BlogComment();
		blog4.setCommentText("nice blog");
		Date date=new Date();
		blog4.setCommentDate(date);
		blog4.setLoginname("janvi");
		blog4.setBlogId(100); 
		 assertTrue("Comment Add Succesfully",blogDao.addBlogComment(blog4));
	}
	
	@Test
	@Ignore
	public void deleteComment() {
	BlogComment blog5=blogDao.getBlogComment(50);
	blog5.setLoginname("janvi");
	blog5.setCommentText("nice blog");
	blog5.setBlogId(50);
	assertEquals("Succesfully Comment Deleted",true,blogDao.deleteBlogComment(blog5));
	
		
	}
	
	@Test
	@Ignore
	public void Likes() {
		Blog blog=blogDao.getBlog(150);
		System.out.println("Blog Obj = "+blog);
	//blog6.setLikes());
		assertEquals("Succesfully increment likes",true,blogDao.incrementLikes(blog));
		
		}
	
	@Test
	
	public void List() {
	List<Blog> blogs=blogDao.listAllApprovedBlogs();	
	for(Blog blog:blogs) {
		System.out.println(blog);
	}
	
	assertNotNull("No Approved Blogs Found", blogs);
		
	}
	@Test
	@Ignore
	public void ListPending() {
	List<Blog> blogs=blogDao.listPendingBlogs();	
	for(Blog blog:blogs) {
		System.out.println(blog);
	}
	
	assertNotNull("No Pending Blogs Found", blogs);
		
	}
}
	

