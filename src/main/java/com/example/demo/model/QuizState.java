package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonInclude(Include.NON_EMPTY)
public class QuizState {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int stateId;
	
	@ManyToOne
	private Quiz quiz;
	
	@ManyToOne
	private User user;
	
	@Lob
	@Column(nullable=false, columnDefinition = "text")
	@Type(type = "org.hibernate.type.TextType") // ADDED FOR MAKING POSTGRES COMPATIBLE, OTHERWISE NOT REQUIRED IF USING MYSQL
	private String answers;
	
	@Column(nullable=false)
	@JsonProperty("timer")
	private int timeLeft;
	
	@Column(nullable=false)
	private int automatic;

	public QuizState() { }
	
	public QuizState(int sid,String ans,int time,int auto) {
		stateId=sid;
		answers=ans;
		timeLeft=time;
		automatic=auto;
	}
	
	@JsonProperty("quiz_id")
	public void setQuizId(int qid) {
		this.quiz=new Quiz();
		this.quiz.setQuizId(qid);
	}
	
	@JsonProperty("quiz_id")
	public int getQuizId(int qid) {
		return this.quiz.getQuizId();
	}

	@JsonProperty("user_id")
	public void setUserId(int uid) {
		if(user==null) {
			this.user=new User();
		}
		this.user.setUserId(uid);
	}
	
	@JsonProperty("user_id")
	public int getUserId() {
		return this.user.getUserId();
	}
	
	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public Quiz getQuiz() {
		return quiz;
	}
	
	public User getUser() {
		return user;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public int getTimeLeft() {
		return timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public int getAutomatic() {
		return automatic;
	}

	public void setAutomatic(int automatic) {
		this.automatic = automatic;
	}

	@Override
	public String toString() {
		return "QuizState [stateId=" + stateId + ", answers=" + answers
				+ ", timeLeft=" + timeLeft + ", automatic=" + automatic+", quiz=" + quiz + ", user=" + user +"]";
	}	
	
}
