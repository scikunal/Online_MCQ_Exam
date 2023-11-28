package com.springProject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springProject.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	LoginService service;
	
	
	@RequestMapping("validate")
	public ModelAndView validate(String username, String password, String subject, HttpServletRequest request) {
		System.out.println("in validate");
		String src = null;
		Boolean ans = service.validPassword(username, password);
		
		ModelAndView mav = new ModelAndView();
		String viewName = "";
		String msg = "";
		
		HttpSession session = request.getSession();
		
		if(ans == null) {
			viewName = "login";
			msg = "Invalid Username";
		}else if(ans == false) {
			viewName = "login";
			msg = "Invalid Password";
		}else if(ans) {
			src = service.getSrc(username);// getting image source from database
			
			viewName = "loginWelcome";
			msg = "Login Successfull!!";
		
			session.setAttribute("user", username);
			session.setAttribute("imagepath", src);
			session.setAttribute("subjectName", subject);
			
		}
		
		mav.setViewName(viewName);
		mav.addObject("message", msg);
		
		return mav;
	}
}
