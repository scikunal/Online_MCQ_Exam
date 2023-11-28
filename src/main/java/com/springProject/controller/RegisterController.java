package com.springProject.controller;

import java.io.File;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.springProject.Entity.User;

@Controller
public class RegisterController {

	@Autowired
	SessionFactory factory;
	
	@RequestMapping("register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("saveDb")
	public ModelAndView saveDb(User user, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		if(user == null) {
			mav.setViewName("register");
			mav.addObject("message", "Please fill all the Fields");
		}
		else {
			Session session = factory.openSession();
			Transaction tx = session.beginTransaction();
			
			user.setImagepath("/images/" + user.getImage().getOriginalFilename());
			session.save(user);
			tx.commit();
			
			//image uploading code
			MultipartFile image = user.getImage();
			String foldername = request.getServletContext().getRealPath("/images");
			image.transferTo(new File(foldername, image.getOriginalFilename()));
			
			
			mav.setViewName("login");
			mav.addObject("message", "Registration successfull!!");
		}	
		return mav;
		
	}
}
