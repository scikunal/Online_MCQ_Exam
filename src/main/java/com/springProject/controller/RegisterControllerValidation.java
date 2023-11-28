package com.springProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springProject.service.RegistrationService;

@RestController
public class RegisterControllerValidation {

	@Autowired
	RegistrationService regService;
	
	@RequestMapping("checkUser")
	public String checkUsername(String username) {
		return regService.checkUsername(username);
	}
}
