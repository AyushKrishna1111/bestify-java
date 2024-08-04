package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonInclude(Include.NON_EMPTY)
public class QuizResult {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int resultId;
	
	@ManyToOne
	private Quiz quiz;
	
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	@JsonProperty("date_played")
	private Date dateAttempted;
	
	private int score;
	
	@JsonProperty("out_off")
	private int outOff;
	
	public QuizResult() { }

	@JsonProperty("user_id")
	public void setUserId(int uid) {
		if(user==null)
			user=new User();
		user.setUserId(uid);
	}
	
	@JsonProperty("user_id")
	public int getUserId() {
		return user.getUserId();
	}
	
	@JsonProperty("quiz_id")
	public void setQuizId(int qid) {
		if(quiz==null)
			quiz=new Quiz();
		quiz.setQuizId(qid);
	}
	
	@JsonProperty("quiz_id")
	public int getQuizId() {
		return quiz.getQuizId();
	}
	
	public int getResultId() {
		return resultId;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDateAttempted() {
		return dateAttempted;
	}

	public void setDateAttempted(Date dateAttempted) {
		this.dateAttempted = dateAttempted;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getOutOff() {
		return outOff;
	}

	public void setOutOff(int outOff) {
		this.outOff = outOff;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	@Override
	public String toString() {
		return "\nQuizResult [resultId=" + resultId + ", quiz=" + quiz + ", user=" + user + ", dateAttempted="
				+ dateAttempted + ", score=" + score + ", outOff=" + outOff + "]";
	}
	
}
