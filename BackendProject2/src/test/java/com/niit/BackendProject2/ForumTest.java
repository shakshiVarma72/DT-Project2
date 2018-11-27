package com.niit.BackendProject2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.niit.BackendProject2.Dao.ForumDao;
import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.Forum;
import com.niit.BackendProject2.dto.ForumComment;


public class ForumTest {
	static ForumDao forumDao;
	
	@BeforeClass
	public static void init()	{
		System.out.println("Initializing Test Case");
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		
		forumDao=(ForumDao)context.getBean("forumDao");
		System.out.println("Forum Dao : "+forumDao);
}
	@Test
	@Ignore
public void addForum()	{
	Forum forum3=new Forum();
	forum3.setForumName("EnterPrise Social Networks");
	forum3.setForumContent("Social Media");
	forum3.setLoginName("Divya Garg");
	Date date =new Date();
	forum3.setCreateDate(date);
	forum3.setStatus("pending");
	assertTrue("Forum addes Successfully",forumDao.addForum(forum3));
	}
	/*Forum forum2=new Forum();
	forum2.setForumName(" Social Networks");
	forum2.setForumContent("Social Hacking");
	forum2.setLoginName("Neha");
	Date date =new Date();
	forum2.setCreateDate(date);
	forum2.setStatus("pending");
	
	assertTrue("Forum addes Successfully",forumDao.addForum(forum2));
}*/
	@Test
	@Ignore
	public void deleteTest() {
	Forum forum1=forumDao.getForum(100)	;
	assertEquals("Forum Deleted Succesfully",true,forumDao.deleteForum(forum1));
	}
	
	@Test
	@Ignore
	public void updateTest() {
		Forum forum2=forumDao.getForum(250);
		forum2.setLoginName("Aditi");
		forum2.setForumName("Students Ethics");
		forum2.setForumContent("Exams");
		Date date=new Date();
		forum2.setCreateDate(date);
		forum2.setStatus("pending");
		assertEquals("updated Successfully",true,forumDao.updateForum(forum2));
	}
	@Test
	@Ignore
	public void approveForum() {
		Forum forum=forumDao.getForum(300);
		System.out.println("Forum - "+forum);
		assertTrue("Forum Approved Succesfully",forumDao.approveForum( forum));
		
	}
	@Test
	@Ignore
	public void rejectForum(){
		Forum forum=forumDao.getForum(250);
		
		assertTrue("Forum Rejected Succesfully",forumDao.rejectForum(forum));
			
		
	}
	@Test
	@Ignore
	public void  addComment() {
		ForumComment forum =new ForumComment();
		forum.setLoginName("Yuvaank");;
		Date date=new Date();
		forum.setCommentDate(date);
		forum.setForumId(100);
		forum.setCommentText(" good");
	assertTrue("Comment Added Succesfully",forumDao.addForumComment(forum));	
	}
	
	
	/*not Working*/
	@Test
	@Ignore
	public void getComment() {
		ForumComment forum1=forumDao.getForumComment(100);
		
		assertNull("User Not Found",forum1);
	}
	
	@Test
@Ignore
	public void List() {
	List<Forum> forums=forumDao.listAllApprovedForums();	
	for(Forum forum:forums) {
		System.out.println(forum);
	}
	
	assertNotNull("No Approved forums Found", forums);
		
	}
	@Test
	
	public void ListPending() {
		List<Forum> forums=forumDao.listPendingForums();	
		for(Forum forum:forums) {
			System.out.println(forum);
		}
		
		assertNotNull("No Pending  forums Found", forums);
			
		
 

	}
}

	
	
	
	
	
	
	

