package com.niit.Midddleware1.Controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.BackendProject2.Dao.UserDao;
import com.niit.BackendProject2.dto.User;

@RestController
public class UserController {
	@Autowired
	UserDao userDao;
	
	@GetMapping(value="/demo")
	public ResponseEntity<String> demoPurpose(){
		System.out.println("Hello I m Demo");
		return new ResponseEntity<String>("Demo Data",HttpStatus.OK);
	}
	
	
	@PostMapping(value="/register")
	public ResponseEntity<String> registerUser(@RequestBody User user){
		System.out.println("I m here");
		user.setOnlineStatus("Offline");
		user.setRole("Role_User");
		
		if(userDao.registerUser(user)){
			return new ResponseEntity<String>("User Registered Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Registering User . Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping(value="getUser/{loginName}")
	public ResponseEntity<User> getUserByLoginName(@PathVariable String loginName){
		
		System.out.println("In get user function"+loginName);
		User user=userDao.getUser(loginName);
		if(user!=null){
			return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<User>(user,HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="checkStatus")
	public ResponseEntity<String> checkStatus(){
		return  new ResponseEntity<String>("Checking Status",HttpStatus.OK);
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<User> checkLogin(@RequestBody User user,HttpSession session){
		if(userDao.checkLogin(user)){
			
			System.out.println("I m valid user");
			User user1=(User)userDao.getUser(user.getLoginName());
			
			userDao.updateOnlineStatus("online", user.getLoginName());
			session.setAttribute("userObj",user1);
			System.out.println("Attribute Added in Session");
			return new ResponseEntity<User>(user1,HttpStatus.OK);
		 	
		}
		else {
			System.out.println("Invalid user");
			return new ResponseEntity<User>(user,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value="/updateOnlineStatus/{status}/{loginName}")
	public ResponseEntity<String> updateOnlineStatus(@PathVariable String status,@PathVariable String loginName){
		System.out.println("Status : "+status);
		System.out.println("Loginname : "+loginName);
		if(userDao.updateOnlineStatus(status, loginName)){
			return new ResponseEntity<String>("Status Updated Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Not able to update status succesfully",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestBody User user){
		User userObj=userDao.getUser(user.getLoginName());
		if(userDao.deleteUser(userObj)){
			return new ResponseEntity<String>("User deleted succesfully...",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem in deleting User...",HttpStatus.NOT_FOUND);
		}
	}
	@PostMapping("/updateUser")
	public ResponseEntity<String> updateUser(@RequestBody User user){
		
		if(userDao.updateUser(user)){
			return new ResponseEntity<String>("User updated succesfully...",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Problem in updating User...",HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping(value="/getListOfUsers")
	public ResponseEntity<List<User>> getUsersList(){
		List<User> list=userDao.getUser();
		if(list.size()==0){
			return new ResponseEntity<List<User>>(list,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<User>>(list,HttpStatus.NOT_FOUND);
		}
	}
}
