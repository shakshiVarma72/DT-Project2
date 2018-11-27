package com.niit.BackendProject2.Dao;

import com.niit.BackendProject2.dto.ProfilePicture;

public interface ProfilePictureUpload {
	public void save(ProfilePicture profilePicture);
    public ProfilePicture getProfilePicture(String loginName);
}
