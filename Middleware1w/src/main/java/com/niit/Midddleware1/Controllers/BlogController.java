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

import com.niit.BackendProject2.Dao.BlogDao;
import com.niit.BackendProject2.Dao.UserDao;
import com.niit.BackendProject2.dto.Blog;
import com.niit.BackendProject2.dto.BlogComment;
import com.niit.BackendProject2.dto.User;

@RestController
public class BlogController {
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	HttpSession session;
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog,HttpSession session){
		System.out.println("I m here");
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("Pending");
		
		System.out.println(session+" "+session.getAttribute("userObj"));
		User user=(User)session.getAttribute("userObj");
		System.out.println("User Details = "+user.getLoginName());
		blog.setLoginName(user.getLoginName());
		
		if(blogDao.addBlog(blog)){
			return new ResponseEntity<String>("Blog Added Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Failure",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/incrementBlogLikes/{blogId}")
	public ResponseEntity<String> incrementBlogLikes(@PathVariable int blogId){
		
		System.out.println("Increment Blog in Rest Controller : "+blogId);
		Blog blog=blogDao.getBlog(blogId);
		
		if(blogDao.incrementLikes(blog)){
			return new ResponseEntity<String>("Blog's likes incremented",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Not able to increment blog likes",HttpStatus.OK);
		}
	}
	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable int blogId){
		
		Blog blog=blogDao.getBlog(blogId);
		if(blog==null){
			System.out.println("Blog Not Found");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Blog Found "+blog.getBlogName());
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}

	
	@GetMapping(value="/listBlogs")
	public ResponseEntity<List<Blog>> getListBlogs(){

		List<Blog> listBlogs=null;
		User user=(User)session.getAttribute("userObj");
		
		System.out.println("Login Name in Blog Controller: "+user.getLoginName());
		
		if(user!=null){
		System.out.println("Role:"+user.getRole());
		if(user.getRole().equals("Role_User")) {
			System.out.println("Role is User:"+user.getRole());
		
			listBlogs=blogDao.listBlogs(user.getLoginName());
		}else {
			System.out.println("Role is Admin:"+user.getRole());
			listBlogs=blogDao.listBlogs(null);
			
		}
			if(listBlogs.size()>0) {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
			}
		else {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
		}
		else {
			listBlogs=blogDao.listAllApprovedBlogs();
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
			
		}
	}
	
	@GetMapping(value="/listPendingBlogs")
	public ResponseEntity<List<Blog>> getAllPendingBlogs(){

		List<Blog> listBlogs=blogDao.listPendingBlogs();
		
		if(listBlogs.size()>0){
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping(value="/updateBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog){
		System.out.println(blog.getBlogId()+" "+blog.getBlogContent()+" "+blog.getLoginName()+" "+blog.getBlogName());
		if(blogDao.updateBlog(blog)){
			return new ResponseEntity<String>("Blog Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable int blogId){
		Blog blog=blogDao.getBlog(blogId);
		if(blogDao.approveBlog(blog)){
			return new ResponseEntity<String>("Blog Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value="/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable int blogId){
		Blog blog=blogDao.getBlog(blogId);
		if(blogDao.rejectBlog(blog)){
			return new ResponseEntity<String>("Blog Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value="/listAllBlogComments/{blogId}")
	public ResponseEntity<List<BlogComment>> getAllBlogComments(@PathVariable int blogId){

		List<BlogComment> listBlogComments=blogDao.listBlogComments(blogId);
		
		if(listBlogComments.size()>0){
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<BlogComment>>(listBlogComments,HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@GetMapping(value="/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+blogId);
		Blog blog=blogDao.getBlog(blogId);
		
		if(blogDao.deleteBlog(blog)){
			return new ResponseEntity<String>("Blog Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	@PostMapping(value="/addBlogComment")
	public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
		
		blogComment.setCommentDate(new Date());
		blogComment.setBlogId(blogComment.getBlogId());
		
		System.out.println(userDao.getUser(blogComment.getLoginname()));
		User user=(User)session.getAttribute("userObj");
		System.out.println("User Details = "+user.getLoginName());
		blogComment.setLoginname(user.getLoginName());
		
		if(userDao.getUser(blogComment.getLoginname())!=null)
		{
			if(blogDao.addBlogComment(blogComment)){
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
	
	@GetMapping(value="/listAllApprovedBlogs")
	public ResponseEntity<List<Blog>> getAllListBlogs(){

		List<Blog> listBlogs=blogDao.listAllApprovedBlogs();
		
		if(listBlogs.size()>0){
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
		}
	}
	
}



