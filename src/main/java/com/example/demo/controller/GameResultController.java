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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.GameResultRepository;
import com.example.demo.model.GameResult;

@RestController
@CrossOrigin
public class GameResultController {

private final String GAMES_RESULT_URL="/api/scores";
	
@Autowired
GameResultRepository grr;

	@PostMapping(GAMES_RESULT_URL)
	public ResponseEntity<String> addGamesResult(@RequestBody GameResult gr) {
		System.out.println(gr);
		grr.addGameResult(gr);
		
		JSONObject jres = new JSONObject();
		jres.put("message", "OK");
	
		return new ResponseEntity<>(jres.toString(),HttpStatus.OK);
		
	}
	
	@GetMapping(GAMES_RESULT_URL+"/getTopScores")
	public List<HashMap<String, Object>> getGamesTopScores() {
		//System.out.println("called getGamesTopScores");
		List<Object[]> ls=grr.getGamesTopScores();
		List<HashMap<String, Object>> res=new ArrayList<>();
		
		for(Object[] g:ls) {
			HashMap<String, Object> json = new HashMap<String, Object>();
			json.put("first_name", g[1]);
			json.put("score", Integer.parseInt(g[2].toString()));
			json.put("email", g[0]);
			res.add(json);
		}
		
		return res;
	}
	
	@GetMapping(GAMES_RESULT_URL+"/getGamesPie")
	public List<HashMap<String, Object>> getUserCountOfGames() {
		//System.out.println("called getGamesTopScores");
		List<Object[]> ls=grr.getUserCountOfGames();
		List<HashMap<String, Object>> res=new ArrayList<>();
		
		for(Object[] g:ls) {
			HashMap<String, Object> json = new HashMap<String, Object>();
			json.put("game_name", g[0]);
			json.put("Count", g[1]);
			res.add(json);
		}
		
		return res;
	}
	
}
