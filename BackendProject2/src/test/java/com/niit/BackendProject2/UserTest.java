package com.niit.BackendProject2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.BackendProject2.Dao.UserDao;
import com.niit.BackendProject2.dto.User;

public class UserTest{
	static UserDao userDao;
@BeforeClass
public static void initialize()	{
	System.out.println("Initializing Test Case");
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
	
	
	userDao=(UserDao)context.getBean("userDao");
	System.out.println("User Dao : "+userDao);
}

/*run*/
@Test
@Ignore
public void registerUserTest(){
	
	User user=new User();
	user.setLoginName("sakshi07");
	user.setFirstName("sakshi");
	user.setLastName("Rajput");
	user.setEmail("sakshi@gmail.com");
	user.setMobileNumber("8750194312");
	user.setOnlineStatus("N");
	user.setPassword("sakshi123");
	user.setRole("Role_Admin");
	
	assertTrue("Problem in Inserting User",userDao.registerUser(user));
	
	/*User user1=new User();
	user1.setLoginName("Amar22");
	user1.setFirstName("Amarjeet");
	user1.setLastName("Singh");
	user1.setEmail("Amar@gmail.com");
	user1.setMobileNumber("9845632124");
	user1.setOnlineStatus("N");
	user1.setPassword("amar123");
	user1.setRole("Role_User");
	
	assertTrue("Problem in Inserting User",userDao.registerUser(user1));*/
}
/*run*/
@Test
@Ignore 
public void testUpdateOnlineStatus(){
	User user=userDao.getUser("sakshi07");
	assertTrue("Problem in Updating",userDao.updateOnlineStatus("Y", user.getLoginName()));

}	
/*run*/
@Test
@Ignore
public void checkLoginTest(){
	User obj=new User();
	obj.setLoginName("sakshi07");
	obj.setPassword("sakshi123");
	assertTrue("Check User Fail",userDao.checkLogin(obj));
}
@Test
@Ignore
public void listUsersTest(){
	List<User> users=userDao.getUser();
	for(User u:users){
		System.out.println(u.getFirstName()+" "+u.getLastName());
	}
	/*
	* assertTrue will fail if the second parameter evaluates to false 
	* (in other words, it ensures that the value is true). 
	*/
	assertTrue("Users doesnt exist",users.size()==0);
}


@Test
@Ignore
public void getUserTest(){
	
	/*This test case will fail if getUser method is returning null*/
	assertNotNull("Succesfully fetched a single User from the table",
			userDao.getUser("sakshi07"));
	
}

@Test
public void testUpdateUser(){
	User user=userDao.getUser("sakshi07");
	
	user.setEmail("sakshi1@gmail.com");
	
	
	assertEquals("Succesfully updated the loginname of the User", true,
			userDao.updateUser(user));
}


@Test
@Ignore
public void testDeleteUser(){
	User User=userDao.getUser("sakshi07");
	assertEquals("User Deleted Succesfully", true,userDao.deleteUser(User));
}





}
