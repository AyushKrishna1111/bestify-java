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

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class GameResult {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int resultId;
	
	@Column(nullable=false)
	@JsonProperty("game_id")
	private int gameId;
	
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	@JsonProperty("date_played")
	private Date dateAttempted;
	
	@Column(nullable=false)
	private int score;
	
	public GameResult() {}
	
	public GameResult(int rid,int gid,int sc) {
		resultId=rid;
		gameId=gid;
		score=sc;
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	@JsonProperty("user_id")
	public int getUserId() {
		System.out.println("called getUserId");
		return user.getUserId();
	}

	@JsonProperty("user_id")
	public void setUserId(int uid) {
		System.out.println("called setUserId");
		if(user==null)
		this.user = new User();
		user.setUserId(uid);
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
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "\nGameResult [resultId=" + resultId + ", gameId=" + gameId + ", user=" + user + ", dateAttempted="
				+ dateAttempted + ", score=" + score + "]";
	}
	
}
