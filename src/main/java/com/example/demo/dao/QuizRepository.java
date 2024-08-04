package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Quiz;
import com.example.demo.model.QuizCategory;
import com.example.demo.model.QuizResult;
import com.example.demo.model.QuizState;
import com.example.demo.model.User;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

@Repository
public class QuizRepository {

	@Autowired
	EntityManager em;
	
	public List<QuizCategory> getAllQuizCategories(){
		TypedQuery<QuizCategory> tq=em.createQuery("Select new QuizCategory(qc.quizCategoryId , qc.quizCategoryName) from QuizCategory qc", QuizCategory.class);
		List<QuizCategory> res=tq.getResultList();
		return res;
	}
	
	@Transactional
	public String saveQuizInDB(Quiz q) {
		em.persist(q);
		em.flush();
		return "OK";
	}

	public List<Quiz> getQuizByCategory(int quiz_cat_id) {
		TypedQuery<Quiz> tq=em.createQuery("Select new Quiz(q.quizId , q.quizName) from Quiz q where q.quizCategory.quizCategoryId=:qcat", Quiz.class);
		tq.setParameter("qcat", quiz_cat_id);
		List<Quiz> res=tq.getResultList();
		for(Quiz a:res)
		System.out.println(a);
		return res;
	}
	
	public Quiz getQuizById(int id) {
		Quiz q=em.find(Quiz.class, id);
		return q;
	}

	public List<Quiz> getQuizByTitle(String title) {
		TypedQuery<Quiz> tq=em.createQuery("Select new Quiz(q.quizId , q.quizName) from Quiz q where q.quizName LIKE :param", Quiz.class);
		tq.setParameter("param", "%"+title+"%");
		List<Quiz> res=tq.getResultList();
		for(Quiz a:res)
		System.out.println(a);
		return res;
	}

	@Transactional
	public int saveQuizState(QuizState qs) {
		em.persist(qs);
		//System.out.println("after persist -> "+qs);
		return qs.getStateId();
	}

	@Transactional
	public int updateQuizState(QuizState qs) {
		em.merge(qs);
		//System.out.println("after persist -> "+qs);
		return qs.getStateId();
	}

	public QuizState getQuizStateByUserIdAndQuizId(int uid, int qid) {
		TypedQuery<QuizState> tq=em.createQuery("Select new QuizState(qs.stateId,qs.answers,qs.timeLeft,qs.automatic) from QuizState qs where qs.user.userId = :uid and qs.quiz.quizId = :qid",QuizState.class);
		tq.setParameter("qid", qid);
		tq.setParameter("uid", uid);
		QuizState qs=tq.getSingleResult();
		qs.setUserId(uid);
		qs.setQuizId(qid);
		System.out.println(qs);
		return qs;
	}

	@Transactional
	public void deleteQuizState(int id) {
		// TODO : Find a way to replace em.find() method since it is causing join queries which is absolutely not needed
		QuizState qs=em.find(QuizState.class, id);
		em.remove(qs);
	}

	@Transactional
	public void saveQuizResult(QuizResult qres) {
		em.persist(qres);
	}
	
	public List<Object[]> getQuizAnalysis() {
		Query tq=em.createQuery("select q.quizName,count(qr.quiz.quizId)AS Analysis from QuizResult qr JOIN Quiz q ON qr.quiz.quizId=q.quizId GROUP BY qr.quiz.quizId,q.quizName");
		 List<Object[]> ls=tq.getResultList();
		 return ls;
	}
	
}
