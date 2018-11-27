package com.niit.BackendProject2.Dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.BackendProject2.dto.ProfilePicture;
@Repository("profilePictureDAO")
@Transactional
public class ProfilePictureUploadImpl implements ProfilePictureUpload {
	@Autowired
    private SessionFactory sessionFactory;
	public void save(ProfilePicture profilePicture) {
		 Session session=sessionFactory.getCurrentSession();
	        Object obj=session.get(ProfilePicture.class,profilePicture.getLoginName());
	        
	        System.out.println(obj);
	        
	        if(obj==null){
	        session.save(profilePicture);
	        }
	        else {
	            session.merge(profilePicture);
	        }

	}

	public ProfilePicture getProfilePicture(String loginName) {
		 Session session=sessionFactory.getCurrentSession();
	        ProfilePicture profilePicture=(ProfilePicture)session.get(ProfilePicture.class, loginName);
	        return profilePicture;
		
	}

}
