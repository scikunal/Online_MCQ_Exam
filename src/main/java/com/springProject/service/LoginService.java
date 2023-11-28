package com.springProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProject.DAO.LoginDao;
import com.springProject.Entity.User;

@Service
public class LoginService {

	@Autowired
	LoginDao dao;
	public Boolean validPassword(String username, String password) {
		
		
		System.out.println("In Service");
		String pass = dao.checkPass(username);
		
		if(pass==null) {
			return null; 
		}
		else if(pass.equals(password))
			return true;
		else 
			return false;
	}
	
	public String getSrc(String username) {
		
		return dao.getSrc(username);
		 
	}
	
}
