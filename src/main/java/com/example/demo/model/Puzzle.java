package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@JsonInclude(Include.NON_EMPTY)
public class Puzzle {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("puzzle_id")
	private int puzzleId;
	
	@Column(nullable=false)
	@JsonProperty("puzzle_name")
	private String puzzleName;
	
	@Lob
	@Column(nullable=false ,columnDefinition = "text")
	@Type(type = "org.hibernate.type.TextType") // ADDED FOR MAKING POSTGRES COMPATIBLE, OTHERWISE NOT REQUIRED IF USING MYSQL
	@JsonProperty("puzzle_question")
	private String puzzleQuestion;
	
	@Lob
	@Column(nullable=false ,columnDefinition = "text")
	@Type(type = "org.hibernate.type.TextType") // ADDED FOR MAKING POSTGRES COMPATIBLE, OTHERWISE NOT REQUIRED IF USING MYSQL
	@JsonProperty("puzzle_answer")
	private String puzzleAnswer;
	
	@Lob
	@Column(nullable=false ,columnDefinition = "text")
	@Type(type = "org.hibernate.type.TextType") // ADDED FOR MAKING POSTGRES COMPATIBLE, OTHERWISE NOT REQUIRED IF USING MYSQL
	@JsonProperty("puzzle_explanation")
	private String puzzleExplanation;
	
	public Puzzle() {}

	public int getPuzzleId() {
		return puzzleId;
	}

	public void setPuzzleId(int puzzleId) {
		this.puzzleId = puzzleId;
	}

	public String getPuzzleName() {
		return puzzleName;
	}

	public void setPuzzleName(String puzzleName) {
		this.puzzleName = puzzleName;
	}

	public String getPuzzleQuestion() {
		return puzzleQuestion;
	}

	public void setPuzzleQuestion(String puzzleQuestion) {
		this.puzzleQuestion = puzzleQuestion;
	}

	public String getPuzzleAnswer() {
		return puzzleAnswer;
	}

	public void setPuzzleAnswer(String puzzleAnswer) {
		this.puzzleAnswer = puzzleAnswer;
	}

	public String getPuzzleExplanation() {
		return puzzleExplanation;
	}

	public void setPuzzleExplanation(String puzzleExplanation) {
		this.puzzleExplanation = puzzleExplanation;
	}

	@Override
	public String toString() {
		return "\nPuzzle [puzzleId=" + puzzleId + ", puzzleName=" + puzzleName + ", puzzleQuestion=" + puzzleQuestion
				+ ", puzzleAnswer=" + puzzleAnswer + ", puzzleExplanation=" + puzzleExplanation + "]";
	}
	
}
