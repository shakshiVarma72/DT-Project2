package com.niit.Midddleware1.Controllers;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.niit.BackendProject2.Dao.ForumDao;
import com.niit.BackendProject2.Dao.UserDao;
import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.BlogComment;
import com.niit.BackendProject2.dto.Forum;
import com.niit.BackendProject2.dto.ForumComment;
import com.niit.BackendProject2.dto.User;

@RestController
public class ForumController {
	@Autowired
	ForumDao forumDao;
	@Autowired
	UserDao userDao;
	
	@Autowired
	HttpSession session;
	
	@GetMapping(value="/approveForum/{forumId}")
	public ResponseEntity<String> approveBlog(@PathVariable int forumId){
		Forum forum=forumDao.getForum(forumId);
		if(forumDao.approveForum(forum)){
			return new ResponseEntity<String>("Forum Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in updating forum ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectForum/{forumId}")
	public ResponseEntity<String> rejectBlog(@PathVariable int forumId){
		Forum forum=forumDao.getForum(forumId);
		if(forumDao.rejectForum(forum)){
			return new ResponseEntity<String>("Blog Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping(value="/updateForum")
	public ResponseEntity<String> updateBlog(@RequestBody Forum forum){
		System.out.println(forum);
		if(forumDao.updateForum(forum)){
			return new ResponseEntity<String>("Blog Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/deleteForum/{forumId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int forumId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+forumId);
		Forum forum=forumDao.getForum(forumId);
		
		if(forumDao.deleteForum(forum)){
			return new ResponseEntity<String>("Blog Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/addForum")
	public ResponseEntity<String> addBlog(@RequestBody Forum forum,HttpSession session){
		forum.setCreateDate(new java.util.Date());
		
		forum.setStatus("Pending");
		
		User user=(User)session.getAttribute("userObj");
		System.out.println("User  = "+user.getLoginName());
		forum.setLoginName(user.getLoginName());
		
		if(forumDao.addForum(forum)){
			return new ResponseEntity<String>("forum Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/addForumComment")
	public ResponseEntity<String> addForumComment(@RequestBody ForumComment forumComment,HttpSession session){
		
		forumComment.setCommentDate(new Date());
		forumComment.setForumId(forumComment.getForumId());
		

		System.out.println(userDao.getUser(forumComment.getLoginName()));
		
		if(userDao.getUser(forumComment.getLoginName())!=null)
		{
			if(forumDao.addForumComment(forumComment)){
				return new ResponseEntity<String>("Blog Comment Added Succesfully",HttpStatus.OK);
			}
			else{
				System.out.println("I m in else");
				return new ResponseEntity<String>("Error in Adding blog Comments",HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		else {
			System.out.println("User doesnt exist");
			System.out.println("I m in else 2");
			return new ResponseEntity<String>("User doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/listForums")
	public ResponseEntity<List<Forum>> getListForums(){

		List<Forum> listForums=null;
		User user=(User)session.getAttribute("userObj");
		System.out.println("user = "+user);
		
		if(user!=null){
		
			listForums=forumDao.listForums(user.getLoginName() );
			for(Forum f: listForums) {
				System.out.println(f.getForumId()+" "+f.getForumName()+" "+f.getForumContent());
			}
			if(listForums.size()>0) {
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.NOT_FOUND);
		}}

		else {
			listForums=forumDao.listAllApprovedForums();
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
			
		}
		}
	@GetMapping(value="/listPendingForums")
	public ResponseEntity<List<Forum>> getAllPendingForums(){

		List<Forum> listForums=forumDao.listPendingForums();
		
		if(listForums.size()>0){
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Forum>>(listForums,HttpStatus.NOT_FOUND);
		}
	}
	
	}
	
