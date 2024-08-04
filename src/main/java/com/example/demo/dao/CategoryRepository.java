package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Category;
import com.example.demo.model.User;

@Repository
public class CategoryRepository {
	
	@Autowired
	EntityManager em;
	
	public List<Category> getAllCategories(){
		TypedQuery<Category> tq=em.createQuery("Select c from Category c", Category.class);
		List<Category>res=tq.getResultList();
		return res;
	}
	
	
}
