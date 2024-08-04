package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.QuizRepository;
import com.example.demo.model.Quiz;
import com.example.demo.model.QuizCategory;
import com.example.demo.model.QuizResult;
import com.example.demo.model.QuizState;

@RestController
@CrossOrigin
public class QuizController {
	
	private final String QUIZ_STATE_URL = "/api/state";
	private final String QUIZ_RESULT_URL= "/api/quizeresult/quizres";
	private final String QUIZ_URL= "/api/quizes";
	private final String QUIZ_CATEGORY_URL = "/api/quizcategories";
	private final String SEARCH_QUIZ_BY_CATEGOTY_URL = "/api/quizes/getSpecificQuizes/";
	private final String GET_QUIZ_BY_ID_URL="/api/quizes/findOne/";
	private final String SEARCH_QUIZ_BY_TITLE_URL="/api/quizes/findAll/";
	
	@Autowired
	QuizRepository qr;
	
	@PostMapping(QUIZ_RESULT_URL)
	public ResponseEntity<String> saveQuizResult(@RequestBody QuizResult qres) {
		//System.out.println(qr);
		qr.saveQuizResult(qres);
		
		JSONObject jres = new JSONObject();
		jres.put("message", "OK");
	
		return new ResponseEntity<>(jres.toString(),HttpStatus.OK);
	}
	
	@PostMapping(QUIZ_STATE_URL)
	public ResponseEntity<String> saveQuizState(@RequestBody String str) {
		System.out.println(str);
		
		JSONObject j = new JSONObject(str);
		
		QuizState qs=new QuizState();
		qs.setAnswers(j.getJSONArray("answers").toString());
		qs.setAutomatic(j.getInt("automatic"));
		qs.setTimeLeft(j.getInt("timer"));
		qs.setQuizId(j.getInt("quiz_id"));
		qs.setUserId(j.getInt("user_id"));
		System.out.println(qs);
		
		int res=qr.saveQuizState(qs);
		JSONObject jres = new JSONObject();
		jres.put("state_id", res);
	
		return new ResponseEntity<>(jres.toString(),HttpStatus.OK);
	}
	
	@PostMapping(QUIZ_STATE_URL+"/{stateId}")
	public ResponseEntity<String> updateQuizState(@RequestBody String str,@PathVariable int stateId) {
System.out.println("update state ;"+str);
		
		JSONObject j = new JSONObject(str);
		
		QuizState qs=new QuizState();
		qs.setAnswers(j.getJSONArray("answers").toString());
		qs.setAutomatic(j.getInt("automatic"));
		qs.setTimeLeft(j.getInt("timer"));
		qs.setQuizId(j.getInt("quiz_id"));
		qs.setUserId(j.getInt("user_id"));
		qs.setStateId(j.getInt("state_id"));
		System.out.println(qs);
		
		System.out.println("for updating -> "+qs);
		
		int res=qr.updateQuizState(qs);
		JSONObject jres = new JSONObject();
		jres.put("state_id", res);
		
		return new ResponseEntity<>(jres.toString(),HttpStatus.OK);
	}
	
	@DeleteMapping(QUIZ_STATE_URL+"/{id}")
	public ResponseEntity<String> deleteQuizState(@PathVariable int id) {
		qr.deleteQuizState(id);
		JSONObject jres = new JSONObject();
		jres.put("message", "OK");
		
		return new ResponseEntity<>(jres.toString(),HttpStatus.OK);
	}
	
	@GetMapping(QUIZ_STATE_URL+"/userid={uid}&quizid={qid}")
	public ResponseEntity<String> getQuizStateByUserIdAndQuizId(@PathVariable int uid, @PathVariable int qid) {
		System.out.println("getting quiz state ; userid = "+uid+" ; quizid = "+qid);
		QuizState qs=null;
		try {
		qs=qr.getQuizStateByUserIdAndQuizId(uid,qid);
		}
		catch(org.springframework.dao.EmptyResultDataAccessException e) {
			System.out.println("NO STATE FOUND FOR CURRENT USERID AND QUIZ_ID-> ");		
		}
		
		if(qs!=null) {
		JSONObject jobj=new JSONObject();	
		jobj.put("state_id",qs.getStateId());
		jobj.put("timer",qs.getTimeLeft());
		jobj.put("automatic", qs.getAutomatic());
		jobj.put("user_id", qs.getUser().getUserId());
		jobj.put("quiz_id", qs.getQuiz().getQuizId());
		
		// WHEN USING MYSQL DATABASE, UNCOMMENT THESE 2 LINES, THEN YOU WILL BE ABLE TO SEND JSON ARRAY IN THE RESPONCE
		//JSONArray ja=new JSONArray(qs.getAnswers());
		//jobj.put("answers",ja);
		
		jobj.put("answers",qs.getAnswers());
		
		return new ResponseEntity<>(jobj.toString(),HttpStatus.OK);
		}
		else {
			JSONObject ja=new JSONObject();
			ja.put("message","no quiz state found");
	
			return new ResponseEntity<>(ja.toString(),HttpStatus.OK);
		}
	}
	
	@GetMapping(SEARCH_QUIZ_BY_TITLE_URL+"{title}")
	public List<Quiz> getQuizByTitle(@PathVariable("title") String title){
		System.out.println("QuizController ; getQuizByTitle ; title = "+title);
		List<Quiz> list=qr.getQuizByTitle(title);
		return list;
	}	

	@GetMapping(GET_QUIZ_BY_ID_URL+"{id}")
	public ResponseEntity<String> getQuizById(@PathVariable("id") int id){
		System.out.println("QuizController ; getQuizById ; id = "+id);
		Quiz q=qr.getQuizById(id);
		
		JSONObject jobj=new JSONObject();	
		jobj.put("quiz_time", q.getQuizTime());
		jobj.put("quiz_name", q.getQuizName());
		jobj.put("quiz_id", q.getQuizId());
		jobj.put("description", q.getDescription());
		System.out.println(q.getQuestions());
		
		// WHEN USING MYSQL DATABASE, UNCOMMENT THESE 2 LINES, THEN YOU WILL BE ABLE TO SEND JSON ARRAY IN THE RESPONCE
		//JSONArray ja=new JSONArray(q.getQuestions());
		//jobj.put("questions",ja);
		
		jobj.put("questions",q.getQuestions());
		//System.out.println(jobj.toString());
		
		return new ResponseEntity<>(jobj.toString(),HttpStatus.OK);
	}
	
	
	@GetMapping(QUIZ_CATEGORY_URL)
	public List<QuizCategory> getAllQuizCategories(){
		System.out.println("QuizController ; getAllQuizCategories ");
		List<QuizCategory> list=qr.getAllQuizCategories();
		return list;
	}
	
	@GetMapping(SEARCH_QUIZ_BY_CATEGOTY_URL+"{id}")
	public List<Quiz> getAllQuizByCategory(@PathVariable("id") int id){
		System.out.println("QuizController ; getAllQuizByCategory ; id = "+id);
		List<Quiz> list=qr.getQuizByCategory(id);
		return list;
	}
	
	@PostMapping(QUIZ_URL)
	public ResponseEntity<String> saveQuizInDB(@RequestBody String quiz){
		System.out.println("QuizController ; saveQuizInDB ; quiz -> "+quiz);
		JSONObject j = new JSONObject(quiz);
		
		Quiz q=new Quiz();
		
		q.setQuestions(j.getJSONArray("questions").toString());
		
		q.setDescription(j.getString("description"));
		
		q.setQuizTime(j.getInt("quiz_time"));
		
		q.setQuizName(j.getString("quiz_name"));
		
		QuizCategory temp=new QuizCategory();
		temp.setQuizCategoryId(j.getInt("quiz_cat_id"));
		q.setQuizCategory(temp);
		
		System.out.println(q);
		
		String res=null;
		try {
		res=qr.saveQuizInDB(q);
		}
		catch(Exception e) {
		e.printStackTrace();	
		}
		
		JSONObject resp = new JSONObject().put("message", res);
		
		if(res.equals("OK"))
			return new ResponseEntity<String>(resp.toString(),HttpStatus.OK);
		else
			return new ResponseEntity<String>("Error occured",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/api/quizeresult/getQuizAnalysis")
	public List<HashMap<String, Object>> getQuizAnalysis() {
			
		List<Object[]> ls=qr.getQuizAnalysis();
		List<HashMap<String, Object>> res=new ArrayList<>();
		
		for(Object[] g:ls) {
			HashMap<String, Object> json = new HashMap<String, Object>();
			json.put("quiz_name", g[0]);
			json.put("Analysis", g[1]);
			res.add(json);
		}
		
		return res;
	}
	
}
