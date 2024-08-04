package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="bestify_user")
@JsonInclude(Include.NON_EMPTY)
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@JsonProperty("user_id")
	private int userId;
	
	@JsonProperty("first_name")
	@Column(nullable=false)
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("username")
	@Column(nullable=false)
	private String userName;
	
	@Column(nullable=false,unique=true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	@JsonProperty("role")
	@Column(nullable=false)
	private boolean isAdmin;
	
	@Temporal(TemporalType.DATE)
	private Date dateCreated;
	
	
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private List<QuizResult> quizResults= new ArrayList<>();
	
	@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	private List<QuizState> quizStates= new ArrayList<>();
	
	// used when username and password are received for signing in
	public User(String username,String pass) {
		System.out.println("User -> construcotr with 2 values");
		this.userName=username;
		password=pass;
	}
	
	// used for sending response back after successful sign in 
	public User(String firstName, String lastName, String userName, String email,int userId,boolean isAdmin,Date date) {
		super();
		System.out.println("User -> construcotr with 5 values");
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.userId=userId;
		this.isAdmin=isAdmin;
		this.dateCreated=date;
	}

	protected User() {
		System.out.println("User -> default construcotr");
	}
	
	
	public int getUserId() {
		return userId;
	}

	public List<QuizResult> getQuizResults() {
		return quizResults;
	}

	
	public void setEmail(String email) {
		System.out.println("setEmail");
		
//		if(email==null)
//			throw new NullPointerException("Email is null");
		
		this.email = email;
	}

	public void setFirstName(String firstName) {
		System.out.println("setfirstName");

//		if(firstName==null)
//			throw new NullPointerException("First name is null");
		
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		System.out.println("setLastName");
		
		this.lastName = lastName;
	}

	public void setUserName(String userName) {
		System.out.println("setUserName");
		
//		if(userName==null)
//			throw new NullPointerException("User name is null");
		
		this.userName = userName;
	}

	public void setPassword(String password) {
		System.out.println("setPassword");		
		
//		if(password==null)
//			throw new NullPointerException("Password is null");
		
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public List<QuizState> getQuizStates() {
		return quizStates;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getLastName() {
		return lastName;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setQuizResults(List<QuizResult> quizResults) {
		this.quizResults = quizResults;
	}

	public void setQuizStates(List<QuizState> quizStates) {
		this.quizStates = quizStates;
	}

	@Override
	public String toString() {
		return "\nUser [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + ", password=" + password + ", isAdmin=" + isAdmin
				+ ", registeredDate=" + dateCreated + "]";
	}
	
	
	
}
