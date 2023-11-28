package com.springProject.DAO;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springProject.Entity.User;

@Repository
public class LoginDao {

	@Autowired
	SessionFactory fact;
	public String checkPass(String username) {
		
		System.out.println("In DAO");
		
		Session session = fact.openSession();
		User userFDB = session.get(User.class, username);
		
		System.out.println(userFDB);
		
		if(userFDB == null) {
			return null;
		}
		else
			return userFDB.getPassword();	
	}
	public String getSrc(String username) {
		
		Session session = fact.openSession();
		User user = session.get(User.class, username);
		return user.getImagepath();
	}
}
