package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_EMPTY)
public class Quiz {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("quiz_id")
	private int quizId;
	
	@Column(nullable=false)
	@JsonProperty("quiz_name")
	private String quizName;
	
	@JsonBackReference
	@ManyToOne
	private QuizCategory quizCategory;
	
//	For MYSQL, BELOW STATEMENT WORKS	
//	@Column(nullable=false , columnDefinition = "mediumtext")
	@Lob
	@Column(nullable=false , columnDefinition = "text") // FOR POSTGRES
	@Type(type = "org.hibernate.type.TextType") // ADDED FOR MAKING POSTGRES COMPATIBLE, OTHERWISE NOT REQUIRED IF USING MYSQL
	private String questions;
	
	@Column(nullable=false)
	@JsonProperty("quiz_time")
	private int quizTime;
	
	@Lob
	@Column(nullable=false, columnDefinition = "text")
	@Type(type = "org.hibernate.type.TextType") // ADDED FOR MAKING POSTGRES COMPATIBLE, OTHERWISE NOT REQUIRED IF USING MYSQL 
	private String description;

	@OneToMany(mappedBy="quiz")
	private List<QuizResult> quizResults= new ArrayList<>();
	
	@OneToMany(mappedBy="quiz")
	private List<QuizState> quizStates= new ArrayList<>();
	
	public Quiz() {
		System.out.println("Quiz ; defaulr const");
	}
	
	public Quiz(int qid,String qname,String desc) {
		System.out.println("Quiz ; 3 value constr");
		this.quizId=qid;
		this.description=desc;
		this.quizName=qname;
	}
	
	public Quiz(int qid,String qname) {
		//System.out.println("Quiz ; 3 value constr");
		this.quizId=qid;
		this.quizName=qname;
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		System.out.println("Quiz ; set quiz time");
		this.quizId = quizId;
	}

	public String getQuizName() {
		return quizName;
	}

	public void setQuizName(String quizName) {
		System.out.println("Quiz ; set quiz name");
		this.quizName = quizName;
	}

	public QuizCategory getQuizCategory() {
		return quizCategory;
	}

	public void setQuizCategory(QuizCategory quizCategory) {
		this.quizCategory = quizCategory;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		System.out.println("Quiz ; set questions");
		this.questions = questions;
	}

	public int getQuizTime() {
		return quizTime;
	}

	public void setQuizTime(int quizTime) {
		System.out.println("Quiz ; set quiz time");
		this.quizTime = quizTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		System.out.println("Quiz ; set desc");
		this.description = description;
	}

	public List<QuizResult> getQuizResults() {
		return quizResults;
	}

	public void setQuizResults(List<QuizResult> quizResults) {
		this.quizResults = quizResults;
	}

	public List<QuizState> getQuizStates() {
		return quizStates;
	}

	public void setQuizStates(List<QuizState> quizStates) {
		this.quizStates = quizStates;
	}

	@Override
	public String toString() {
		return "\nQuiz [quizId=" + quizId + ", quizName=" + quizName + ", quizCategory=" + quizCategory + ", questions="
				+ questions + ", quizTime=" + quizTime + ", description=" + description + "]";
	}
	
	
	
}
