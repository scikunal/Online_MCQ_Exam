package com.springProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springProject.DAO.QuestionDAO;
import com.springProject.Entity.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDAO questionDao;
	public List<Question> getQuestions(String subject){
		return questionDao.getQuestions(subject);
	}
}
