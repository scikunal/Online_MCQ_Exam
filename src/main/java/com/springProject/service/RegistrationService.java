package com.springProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProject.DAO.RegistrationDao;

@Service
public class RegistrationService {

	@Autowired
	RegistrationDao rDao;
	
	public String checkUsername(String username) {
		return rDao.checkUsername(username);
	}
}
