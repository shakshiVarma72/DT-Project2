package com.niit.BackendProject2.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table
public class ProfilePicture {

	 @Id
	    private String loginName;
	     
	    @Lob//large object model -which is used to store image into database.
	    private byte[] image;
	 
	    public String getLoginName() {
	        return loginName;
	    }
	 
	    public void setLoginName(String loginName) {
	        this.loginName = loginName;
	    }
	 
	    public byte[] getImage() {
	        return image;
	    }
	 
	    public void setImage(byte[] image) {
	        this.image = image;
	    }
	     	
	
}
