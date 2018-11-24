package com.niit.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.models.Friend;
import com.niit.models.User;
@Repository
@Transactional
public class FriendDaoImpl implements FriendDao{

	@Autowired
	private SessionFactory sessionFactory;
		public List<User> getAllSuggestedUsers(String email) {
			String queryString="select * from user_s190038 where email in "
					+ "( select email from user_s190038 where email!=? "
					+ " minus "
					+ "(select toId_email from friend_s190038 where fromId_email=? "
					+ " union "
					+ "select fromId_email from friend_s190038 where toId_email=?))";
			
			Session session=sessionFactory.getCurrentSession();
			SQLQuery sqlQuery=session.createSQLQuery(queryString);
			sqlQuery.addEntity(User.class);
			sqlQuery.setString(0, email);
			sqlQuery.setString(1, email);
			sqlQuery.setString(2, email);
			List<User> suggestedUsers=sqlQuery.list();
			return suggestedUsers;
		}
		public void addFriendRequest(Friend friend) {
			Session session=sessionFactory.getCurrentSession();
			session.save(friend);//insert into friend_s190038 values (?,fromId_email,toId_email,'P')
			
		}
		public List<Friend> getPendingRequests(String email) {
			Session session=sessionFactory.getCurrentSession();
			//property - friendId,fromId:User,toId:User,status:char
			//friend.toId.email=?
			String queryString="from Friend where toId.email=? and status=?";
			Query query=session.createQuery(queryString);
			query.setString(0, email);
			query.setCharacter(1, 'P');
			return query.list();
		}
		public void acceptRequest(Friend friendRequest) {
			Session session=sessionFactory.getCurrentSession();
			friendRequest.setStatus('A');
			session.update(friendRequest);//update friend_s190038 set status='A' where friendId=friendRequest.friendId
			//UPDATE
		}
		public void deleteRequest(Friend friendRequest) {
			Session session=sessionFactory.getCurrentSession();
			session.delete(friendRequest);//delete from friend_s190038 where friendId=friendRequest.friendId
		}
		public List<User> listOfFriends(String email) {
			Session session=sessionFactory.getCurrentSession();
			//f is alias
			Query query1=session.createQuery("select f.toId from Friend f where f.fromId.email=? and f.status=?");
			query1.setString(0, email);
			query1.setCharacter(1, 'A');
			List<User> list1=query1.list();//list of users who have accepted the request from logged in user
			
			Query query2=session.createQuery("select f.fromId from Friend f where f.toId.email=? and f.status=?");
			query2.setString(0, email);
			query2.setCharacter(1, 'A');
			List<User> list2=query2.list();//logged in user accepted the requests from other users
			
			list1.addAll(list2);//list1=list1 U  list2
			
			return list1;
		}

	}
