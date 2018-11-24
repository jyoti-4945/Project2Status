package com.niit.dao;

import java.util.List;

import com.niit.models.Friend;
import com.niit.models.User;

public interface FriendDao 
{
	List<User> getAllSuggestedUsers(String email);

	void addFriendRequest(Friend friend);
	List<Friend> getPendingRequests(String email);

	void acceptRequest(Friend friendRequest);

	void deleteRequest(Friend friendRequest);

	List<User>  listOfFriends(String email);

}
