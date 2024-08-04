package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonInclude(Include.NON_EMPTY)
public class QuizCategory {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("quiz_cat_id")
	private int quizCategoryId;
	
	@Column(nullable=false)
	@JsonProperty("quiz_cat_name")
	private String quizCategoryName;
	
	
	@JsonManagedReference
	@OneToMany(mappedBy="quizCategory")
	private Set<Quiz> quizes=new HashSet<>();
	
	
	public QuizCategory() { }
	
	public QuizCategory(int quizCategoryId, String quizCategoryName) {
		super();
		this.quizCategoryId = quizCategoryId;
		this.quizCategoryName = quizCategoryName;
	}

	public int getQuizCategoryId() {
		return quizCategoryId;
	}

	public void setQuizCategoryId(int quizCategoryId) {
		this.quizCategoryId = quizCategoryId;
	}

	public String getQuizCategoryName() {
		return quizCategoryName;
	}

	public void setQuizCategoryName(String quizCategoryName) {
		this.quizCategoryName = quizCategoryName;
	}

	public Set<Quiz> getQuizes() {
		return quizes;
	}

	public void setQuizes(Set<Quiz> quizes) {
		this.quizes = quizes;
	}
	
	
	
}
