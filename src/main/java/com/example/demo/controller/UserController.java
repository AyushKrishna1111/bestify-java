package com.example.demo.controller;

import javax.persistence.PersistenceException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;


@RestController
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	private final String URL = "/api/auth/";
	
	@PostMapping(URL+"signin")
	public ResponseEntity signIn(@RequestBody User user) {
		System.out.println("signin -> "+user); 
		User us=null;
		try {
		us=userRepository.signInUser(user);
		}
		catch(org.springframework.dao.EmptyResultDataAccessException e) {
			//System.out.println(e);
			JSONObject j = new JSONObject().put("message", "Invalid username or password");
			return new ResponseEntity(j.toString(),HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity(us,HttpStatus.OK);
	}
	
	@PostMapping(URL+"signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		System.out.println("signup -> "+user); 
		String res=null;
		
		res=validateSignUpData(user);
		
		if(res.equals("Success")) {
		try {
		res=userRepository.signUpUser(user);
		}
		catch(org.springframework.dao.DataIntegrityViolationException e2) {
			System.out.println("e2 exception --> "+e2);		
			res="Email already available";
		}
		}
		
		JSONObject j = new JSONObject().put("output", res);
		System.out.println("signup result ->"+j);
		
		if(res.equals("Success"))
			return new ResponseEntity<String>(j.toString(), HttpStatus.OK);
		else if(res.equals("Email already available"))
			return new ResponseEntity<String>(j.toString(), HttpStatus.CONFLICT);
		else
			return new ResponseEntity<String>(j.toString(), HttpStatus.BAD_REQUEST);
	}

	private String validateSignUpData(User user) {
		if(user.getFirstName()==null || user.getUserName().length()==0)
			return "Signup failed ; Username missing";
		if(user.getEmail()==null || user.getEmail().length()==0)
			return "Signup failed ; Email missing";
		if(user.getPassword()==null || user.getPassword().length()==0)
			return "Signup failed ; Password missing";
		if(user.getUserName()==null || user.getUserName().length()==0)
			return "Signup failed ; Password missing";
		return "Success";
	}

}
