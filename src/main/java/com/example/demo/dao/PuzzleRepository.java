package com.example.demo.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Puzzle;
import com.example.demo.model.QuizCategory;

import java.util.List;

@Repository
public class PuzzleRepository {

	@Autowired
	EntityManager em;
	
	@Transactional
	public void savePuzzle(Puzzle puzzle) {
		em.persist(puzzle);
	}

	public List<Puzzle> getAllPuzzles() {
		TypedQuery<Puzzle> tq=em.createQuery("Select p from Puzzle p", Puzzle.class);
		List<Puzzle> res=tq.getResultList();
		return res;
	}

}
