package com.springProject.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springProject.Entity.Answer;
import com.springProject.Entity.Question;
import com.springProject.service.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	QuestionService Qservice;

	@RequestMapping("question")
	public ModelAndView question(HttpServletRequest request) {
		System.out.println("in Question");
		HttpSession session = request.getSession();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("question");// to redirect on question.jsp

		String sub = (String) session.getAttribute("subjectName"); // getting subject name from session object

		List<Question> question = Qservice.getQuestions(sub); // getting the questions in list by subject name.
		Question que = question.get(0); // getting 1st first question from list.

		session.setAttribute("question", que); // setting first question in session.
		session.setAttribute("questions", question); // setting the all question list in session
		session.setAttribute("qno", 0);
		session.setAttribute("score", 0);

		HashMap<Integer, Answer> hashmap = new HashMap<Integer, Answer>();// hashmap for saving the questions with the
																			// answer submitted by user.
		session.setAttribute("submittedDetails", hashmap);

		return mav;
	}

	@RequestMapping("next")
	public ModelAndView next(HttpServletRequest request, Answer answer) {
		System.out.println("next called");
		// save the submitted answer in hashmap
		HttpSession httpsession = request.getSession();
		ModelAndView modelAndView = new ModelAndView();

		//storing the submitted answer in hashmap as Answer Object. 
		if (request.getParameter("submittedAnswer") != null)// check if radio button is clicked
		{
			HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails"); // Create hashmap with submitted question
			hashmap.put(answer.getQno(), answer); // [1 = [qno, qtext, submittedAns, OriginalAns]
			System.out.println("Radio Clicked if"+hashmap);
		}

		// increment the question number
		Question question = null;
		List<Question> listofquestions = (List<Question>) httpsession.getAttribute("questions"); // get all the
																									// questions

		// here we compare qno with size of list of question
		// qno starts from 0 which contains 1st question
		// therefore the last question will be listSize-1
		// so if qno = size - 1, don't increment qno
		int qno =(int) httpsession.getAttribute("qno");
		if (qno < listofquestions.size() - 1) {
			System.out.println("qno size is less than size of list - 1 :" + httpsession.getAttribute("qno") + " " + listofquestions.size());
			// incrementing the qno
			qno++;
			System.out.println("qno : "+qno);
			httpsession.setAttribute("qno", qno);
			// get the question from list related to qno
			question = listofquestions.get(qno);
		} else {
			// if qno = listSize-1
			System.out.println("in else \n " + qno + "  " + listofquestions.size());
			// set last que on page again
			question = listofquestions.get(listofquestions.size() - 1);

			modelAndView.addObject("message", "This is the last Question");
		}
		System.out.println(question);
		
		//check if answer of next question already given
		
		HashMap<Integer, Answer> hashmap = (HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		System.out.println("Hashmap after submiting last details :"+hashmap);
		answer = hashmap.get(question.getQno());
		String nextAnswer = "";
		System.out.println("answer of current qno : " + answer);
		if(answer!=null) {
			nextAnswer = answer.getSubmittedAnswer();
		}
		System.out.println("lastly submitted answer : "+nextAnswer);
		modelAndView.addObject("question", question);// add que to display on page
		modelAndView.setViewName("question");
		modelAndView.addObject("nextAnswer", nextAnswer);
		

		return modelAndView;
	}
	
	@RequestMapping("previous") 
	public ModelAndView previous(HttpServletRequest request,Answer answer1) {
	 
		Question question=null;
	
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpsession=request.getSession();
	   
		List<Question> listofquestions=(List<Question>) httpsession.getAttribute("questions"); //get the list
		
		if((Integer)httpsession.getAttribute("qno")>0) {//check if question is first or not
			httpsession.setAttribute("qno",(Integer)httpsession.getAttribute("qno") - 1);//decrease the qno to go on previous question
			question=listofquestions.get((Integer)httpsession.getAttribute("qno")); //get object of question by index
		}
		else { 
			question=listofquestions.get(0); //set same question on page if it is first question
			modelAndView.addObject("message","This is the First Question.");  
		}
		
		
		// get previously entered answer of current question and display it on browser
		  
		int qno=question.getQno();
		  
		HashMap<Integer,Answer> hashmap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		
		//updated code
         if(answer1.getSubmittedAnswer()!=null)
         {
        	 hashmap.put(answer1.getQno(),answer1);
         }
		Answer answer=hashmap.get(question.getQno());
		String previousAnswer="";
		
		if(answer!=null) { 
			previousAnswer=answer.getSubmittedAnswer(); 
		}
		
		System.out.println(previousAnswer);
		
		modelAndView.addObject("question",question);
		modelAndView.addObject("previousAnswer",previousAnswer);
		modelAndView.setViewName("question");
		return modelAndView;
		  
	}
		  
	
	@RequestMapping("endexam") 
	public ModelAndView endexam(HttpServletRequest request) {
	  
		ModelAndView modelAndView = new ModelAndView();
		  
		HttpSession httpsession=request.getSession();
		  
		HashMap<Integer,Answer> hashmap=(HashMap<Integer, Answer>) httpsession.getAttribute("submittedDetails");
		  
		if(hashmap==null) {
		  
			modelAndView.setViewName("login");
		    return modelAndView; 
		}
		 
		Collection<Answer> collection=hashmap.values();
		  
		for (Answer answer : collection) {
			if(answer.getOriginalAnswer().equals(answer.getSubmittedAnswer())) {
				httpsession.setAttribute("score",(Integer)httpsession.getAttribute("score") +1); 
			}
		}
		  
		modelAndView.addObject("allanswers",collection);
		  
		modelAndView.addObject("score",httpsession.getAttribute("score"));
		 
		modelAndView.setViewName("score");
			  
		httpsession.invalidate();// remove all attributs from session
		  
		return modelAndView;
		  
	}
		
}
