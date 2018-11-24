package com.niit.dao;

import com.niit.models.ProfilePicture;

public interface ProfilePictureDao {
	ProfilePicture  saveOrUpdateProfilePic(ProfilePicture profilePicture);
	ProfilePicture  getImage(String email);

}
