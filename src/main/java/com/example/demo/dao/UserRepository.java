package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserRepository {

	@Autowired
	EntityManager em;
	
	@Transactional
	public String signUpUser(User u) {
		String res=null;
		em.persist(u);
		em.flush();
		if(u.getUserId()!=0)
			res="Success";
			
		//System.out.println("func end");	
		return res;
	}
	
	public User signInUser(User u) {
		TypedQuery<User> tq=em.createQuery("Select new User(u.firstName,u.lastName,u.userName,u.email,u.userId,u.isAdmin,u.dateCreated) from User u where email = :enl and password = :pwd", User.class);
		tq.setParameter("pwd", u.getPassword());
		tq.setParameter("enl", u.getEmail());
		User res=tq.getSingleResult();
		return res;
	}
	
}
