package com.niit.BackendProject2.Dao;

import java.util.List;

import javax.transaction.Transactional;
 
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BackendProject2.dto.Friend;
import com.niit.BackendProject2.dto.User;
 

 
@Repository
@Transactional
public class FriendDaoImpl implements FriendDao{
 
    @Autowired
    private SessionFactory sessionFactory;

	public List<User> suggestedUsers(String loginName) {
		  Session session=sessionFactory.getCurrentSession();
	        String queryString="select * from user_tab where loginName in "
	                + "(select loginName from user_tab where loginName!=? "
	                + "minus "
	                + "(select toId_loginName from friend_tab where fromid_loginName=? "
	                + "union "
	                + "select fromid_loginName from friend_tab where toId_loginName=? )) ";
	        SQLQuery query=session.createSQLQuery(queryString);
	        query.setString(0, loginName);
	        query.setString(1, loginName);
	        query.setString(2, loginName);
	        query.addEntity(User.class);
	        List<User> suggestedUsers=query.list();
	        return suggestedUsers;
	}

	public void addFriend(Friend friend) {
		 Session session=sessionFactory.getCurrentSession();
	        session.save(friend);
	         
	}

	public List<Friend> pendingRequests(String toIdloginName) {
		Session session=sessionFactory.getCurrentSession();
        Query query=session.createQuery("from Friend where status=? and toId.loginName=?");
        query.setCharacter(0, 'P');
        query.setString(1, toIdloginName);
        List<Friend> pendingRequests=query.list();
        return pendingRequests;
	}

	public void acceptRequest(Friend request) {
		 Session session=sessionFactory.getCurrentSession();
	        request.setStatus('A');
	        session.update(request);
		
	}

	public void deleteRequest(Friend request) {
		 Session session=sessionFactory.getCurrentSession();
	        session.delete(request);
	         
	}

	public List<Friend> listOfFriends(String loginName) {
		Session session=sessionFactory.getCurrentSession();
	     
        Query query1=session.createQuery
    ("select toId.loginName from Friend  where fromId.loginName=? and status=?");

        
        
        query1.setString(0, loginName);
        query1.setCharacter(1, 'A');
        List<Friend> friendList1=query1.list();
         
        Query query2=session.createQuery("select fromId.loginName"
                + " from Friend  where toId.loginName=? and status=?");
        query2.setString(0, loginName);
        query2.setCharacter(1, 'A');
        List<Friend> friendList2=query2.list();
         
        friendList1.addAll(friendList2);
        return friendList1;
    }
	}
     
