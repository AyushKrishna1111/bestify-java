package com.example.demo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.GameResult;
import com.example.demo.model.QuizState;

@Repository
public class GameResultRepository {

	@Autowired
	EntityManager em;
	
	@Transactional
	public void addGameResult(GameResult gr) {
		TypedQuery<GameResult> tq=em.createQuery("Select new GameResult(gr.resultId,gr.gameId,gr.score) from GameResult gr where gr.user.userId = :uid and gr.gameId = :gid",GameResult.class);
		tq.setParameter("gid", gr.getGameId());
		tq.setParameter("uid", gr.getUserId());
		
		GameResult ogr=null;
		
		try {
			ogr=tq.getSingleResult();
		}
		catch(javax.persistence.NoResultException e) {
		//System.out.println("No previous data for user_id and game_id ");
		}
		
		if(ogr==null) {
			//System.out.println("CREATING NEW ENRTY");
			em.persist(gr);
		}
		else {
			//System.out.println("old record -> "+ogr);
			if(ogr.getScore() <= gr.getScore()) {
				//System.out.println("UPDATING NEW ENRTY");
				ogr.setScore(gr.getScore());
				ogr.setDateAttempted(gr.getDateAttempted());
				ogr.setUserId(gr.getUserId());
				em.merge(ogr);
			}
		}
	}

	@Transactional
	public List<Object[]> getGamesTopScores() {
		 Query tq=em.createQuery("select u.email,u.firstName,gr.score from User u JOIN GameResult gr ON u.userId=gr.user.userId ORDER BY gr.score DESC");
		 tq=tq.setMaxResults(5);
		 List<Object[]> ls=tq.getResultList();
		 //for(Object[] a:ls)
			// for(int i=0;i<a.length;i++)
		 //System.out.println(a[i]);
		 return ls;
	}

	public List<Object[]> getUserCountOfGames() {
		 Query tq=em.createQuery("select g.gameName,count(gr.gameId) AS Count from GameResult gr JOIN Game g ON gr.gameId=g.gameId GROUP BY gr.gameId,g.gameName");
		 List<Object[]> ls=tq.getResultList();
		 //for(Object[] a:ls)
			// for(int i=0;i<a.length;i++)
			 //System.out.println(a[i]);
		 return ls;
	}

	
}
