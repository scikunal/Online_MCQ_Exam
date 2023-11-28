package com.springProject.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springProject.Entity.Question;



@Repository
public class QuestionDAO {

	@Autowired
	SessionFactory fact;
	
	public List<Question> getQuestions(String subject) {
		Session session = fact.openSession();
		
		Criteria criteria = session.createCriteria(Question.class);
		criteria.add(Restrictions.eq("subject", subject));
		System.out.println(criteria.list());
		return criteria.list();
	}
}
