package com.example.demo.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PuzzleRepository;
import com.example.demo.model.Puzzle;

@RestController
@CrossOrigin
public class PuzzleController {

	private final String PUZZLE_URL="/api/puzzles";

	//api for fetching user from database
	private final String UserUrl="/api/users";

	//api for fetching Puzzle Results from database
	private final String  PuzzleResultUrl="/api/puzzleresult";

	private final String topScores="/api/puzzleresult/getTopScores";

	@Autowired
	PuzzleRepository pr;
	
	@PostMapping(PUZZLE_URL)
	public ResponseEntity<String> savePuzzle(@RequestBody Puzzle puzzle){
		System.out.println(puzzle);
		pr.savePuzzle(puzzle);
		
		JSONObject jres = new JSONObject();
		jres.put("message", "OK");
	
		return new ResponseEntity<>(jres.toString(),HttpStatus.OK);
	}
	
	@GetMapping(PUZZLE_URL)
	public List<Puzzle> getAllPuzzles(){
		List<Puzzle> list=pr.getAllPuzzles();
		return list;
	}
	
}
