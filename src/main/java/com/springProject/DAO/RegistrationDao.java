package com.springProject.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springProject.Entity.User;

@Repository
public class RegistrationDao {
	
	@Autowired
	SessionFactory fact;
	
	public String checkUsername(String username) {
		Session session = fact.openSession();
		User user = session.get(User.class, username);
		if(user!=null) {
			return user.getUsername()+" is Taken!";
		}else
			return username+" is Available!";
	}
	
	public static void main(String[] args) {
		
	}
}
