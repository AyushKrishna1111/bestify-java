package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.dao.QuizRepository;
import com.example.demo.model.Category;

@RestController
@CrossOrigin
public class CategoryController {
	
	private final String GET_ALL_CATEGORIES_URL="/api/categories";
	
	@Autowired
	CategoryRepository cr;
	
	@GetMapping(GET_ALL_CATEGORIES_URL)
	public List<Category> getAllCategories() {
		List<Category> res=cr.getAllCategories();
		return res;
	}

}
