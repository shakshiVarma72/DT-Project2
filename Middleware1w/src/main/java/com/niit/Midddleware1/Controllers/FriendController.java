package com.niit.Midddleware1.Controllers;
import java.util.List;

import javax.servlet.http.HttpSession;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.BackendProject2.Dao.FriendDao;
import com.niit.BackendProject2.Dao.UserDao;

import com.niit.BackendProject2.dto.Friend;
import com.niit.BackendProject2.dto.User;
 

 
@Controller
public class FriendController {
     
    @Autowired
    private FriendDao friendDao;
 
    @Autowired
    private UserDao userDao;
     
    @RequestMapping(value="/suggestedusers",method=RequestMethod.GET)
    public ResponseEntity<?> suggestedUsers(HttpSession session){
         
        User userObj=(User)session.getAttribute("userObj");
        String loginName=userObj.getLoginName();
        if(loginName==null)
        {
           
            return new ResponseEntity<String>("Unauthorized User",HttpStatus.UNAUTHORIZED);  
        }
        List<User> suggestedUsers=friendDao.suggestedUsers(loginName);
        return new ResponseEntity <List<User>>(suggestedUsers,HttpStatus.OK);
         
    }
     
    @RequestMapping(value="/addfriend",method=RequestMethod.POST)
    public ResponseEntity<?> addFriend(@RequestBody User toId,HttpSession session){
         
    	User userObj=(User)session.getAttribute("userObj");
        String loginName=userObj.getLoginName();
        if(loginName==null)
        {
           
            return new ResponseEntity<String>("Unauthorized User",HttpStatus.UNAUTHORIZED);  
        }
        User fromId=userDao.getUser(loginName);
        Friend friend=new Friend();
        friend.setFromId(fromId);
        friend.setToId(toId);
        friend.setStatus('P');
        friendDao.addFriend(friend);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
     
    @RequestMapping(value="/pendingrequests",method=RequestMethod.GET)
    public ResponseEntity<?> pendingRequests(HttpSession session){
         
    	User userObj=(User)session.getAttribute("userObj");
        String loginName=userObj.getLoginName();
        if(loginName==null)
        {
            
            return new ResponseEntity<String>("Unauthorized User",HttpStatus.UNAUTHORIZED);  
        }
        List<Friend> pendingRequests=friendDao.pendingRequests(loginName);
        return new ResponseEntity<List<Friend>>(pendingRequests,HttpStatus.OK);
    }       
     
    @RequestMapping(value="/acceptrequest",method=RequestMethod.PUT)
    public ResponseEntity<?> acceptRequest(@RequestBody Friend request,HttpSession session){
         
    	User userObj=(User)session.getAttribute("userObj");
        String loginName=userObj.getLoginName();
        if(loginName==null)
        {
            return new ResponseEntity<String>("User not Recognized",HttpStatus.UNAUTHORIZED);  
        }
        friendDao.acceptRequest(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    @RequestMapping(value="/deleterequest",method=RequestMethod.PUT)
    public ResponseEntity<?> deleteRequest(@RequestBody Friend request,HttpSession session){
         
    	User userObj=(User)session.getAttribute("userObj");
        String loginName=userObj.getLoginName();
        if(loginName==null)
        {
            return new ResponseEntity<String>("User not Recognized",HttpStatus.UNAUTHORIZED);  
              
        }
        friendDao.deleteRequest(request);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
     
    @RequestMapping(value="/friends",method=RequestMethod.GET)
    public ResponseEntity<?> getAllFriends(HttpSession session){
    	User userObj=(User)session.getAttribute("userObj");
        String loginName=userObj.getLoginName();
        if(loginName==null)
        {
            return new ResponseEntity<String>("User not Recognized",HttpStatus.UNAUTHORIZED);  
            
          }
        List<Friend> friends=friendDao.listOfFriends(loginName);
        return new ResponseEntity<List<Friend>>(friends,HttpStatus.OK);
    }
}
