package com.niit.BackendProject2.Dao;

import java.util.List;

import com.niit.BackendProject2.dto.User;

public interface UserDao {

	 public boolean registerUser(User user);
		public boolean checkLogin(User user );
		public boolean updateOnlineStatus(String status,String loginName);
		public User getUser(String loginName);
		public List<User> getUser();
		public boolean deleteUser(User user);
		public boolean updateUser( User user);
}
