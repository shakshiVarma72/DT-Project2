package com.niit.BackendProject2.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BackendProject2.dto.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
@Autowired
SessionFactory sessionFactory;

 
	public boolean registerUser(User user) {
		try {
			sessionFactory.getCurrentSession().save(user);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	
		return false;
	}
/*run*/
	public boolean checkLogin(User user) {
		try {
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where loginName=:x and Password=:y ");
		query.setParameter("x",user.getLoginName());
		query.setParameter("y",user.getPassword());
		List<User> list=query.list();
		if(list.size()>0){
        User userObj=list.get(0);
        return true;
	}}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
		return false;
	}

	public boolean updateOnlineStatus(String status, String loginName) {
	
		try{
			Session session=sessionFactory.getCurrentSession();
			User user=(User)session.get(User.class,loginName);
			
			user.setOnlineStatus(status);
			sessionFactory.getCurrentSession().merge(user);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


		
	public User getUser(String loginName) {
		try {
			Session session =sessionFactory.getCurrentSession();
			User user=(User)session.get(User.class,loginName);
			return user;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null; 
	}

	public List<User> getUser() {
		Session session =sessionFactory.getCurrentSession();
		Query query=session.createQuery("from com.niit.BackendProject2.dto.User");
		List<User> list=query.list();
		return list;
	}
	
	public boolean deleteUser(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		}
		catch(Exception e) {
		e.printStackTrace();
		return false;
		}
	}

	public boolean updateUser(User user) {
		try {
			sessionFactory.getCurrentSession().update(user);
			return true;
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	}
 