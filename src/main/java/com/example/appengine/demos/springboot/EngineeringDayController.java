/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.appengine.demos.springboot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Transaction;

@RestController
public class EngineeringDayController {
	
	@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED, reason="Le numéro d'équipe doit être > 0.")  
	@ExceptionHandler(NumberFormatException.class)
	public void numberFormatException() {
	}
	
	@ResponseStatus(value=HttpStatus.PRECONDITION_FAILED, reason="Critère erroné.")  
	@ExceptionHandler(BadCritetionException.class)
	public void conflict() {
			// Nothing to do
	}
	
	@GetMapping("/assessmentTable")
	public List<Criterion> getTeamAssessmentTable() {
		
		List<Criterion> criteria = new ArrayList<Criterion>(); 
		

		Criterion criterion = new Criterion("Caractère innovant - originalité");

		CriterionValues values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);

		criteria.add(criterion);
		
		
		criterion = new Criterion("Utilité - réponse à besoin réel");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);
		

		
		criterion = new Criterion("Technologie : concept et aboutissement");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);

		
		
		criterion = new Criterion("Ergonomie");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);

		
		criterion = new Criterion("Analyse du marche et du besoin");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);

		
		criterion = new Criterion("Modèle économique");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);

		
		
		criterion = new Criterion("Démo, supports, discours");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);

		
		criterion = new Criterion("Dynamisme, force de conviction");
		
		values = new CriterionValues(1, "excellent");
		criterion.addCriterionValues(values);
		
		values = new CriterionValues(2, "très bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(3, "bien");
		criterion.addCriterionValues(values);

		values = new CriterionValues(4, "moyen");
		criterion.addCriterionValues(values);

		values = new CriterionValues(5, "pas clair");
		criterion.addCriterionValues(values);
		
		criteria.add(criterion);

		return criteria;
	}

	@PutMapping("/teamAssessment/{teamNumber}")
	public void updateTeamAssessment(@RequestBody List<CriterionValues> criteria, @PathVariable("teamNumber") int teamNumber) throws BadCritetionException {
		
		if(teamNumber <= 0) {
			throw new NumberFormatException("Le numéro d'équipe doit être > 0.");
		}
		
		List<Criterion> assessmentTable = this.getTeamAssessmentTable();
		
		if(criteria==null || criteria.size()!=assessmentTable.size()) {
			throw new BadCritetionException();
		}
		
		for(CriterionValues value: criteria) {
			if(value.getName()==null || value.getName().equals("")) {
				throw new BadCritetionException();
			}
		}
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity teamAssessment = new Entity("teamAssessment");
		
		teamAssessment.setProperty("teamNumber", teamNumber);
		
		int i=0;
		for(CriterionValues values: criteria) {
			
			String criterion = assessmentTable.get(i).getCriterion();
			int value = values.getValue();
			String name = values.getName();
			
			EmbeddedEntity embeddedCriterion = new EmbeddedEntity();
			embeddedCriterion.setProperty("value", value);
			embeddedCriterion.setProperty("name", name);
			
			teamAssessment.setProperty(criterion, embeddedCriterion);
			
			i++;
		}
		
		Transaction txn = datastore.beginTransaction();

		datastore.put(teamAssessment);
	
		txn.commit();
		
	}
	
	
	@GetMapping("/teamAssessment")
	public List<TeamAssessment> getAllTeamsAssessment() {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("teamAssessment").addSort("teamNumber", SortDirection.ASCENDING);

		List<Entity> results = datastore.prepare(q).asList(FetchOptions.Builder.withDefaults());
		
		List<TeamAssessment> teams = new ArrayList<TeamAssessment>();
		List<Criterion> assessmentTable = this.getTeamAssessmentTable();
		
		for(Entity entity: results) {
			
			long teamNumber = (long) entity.getProperty("teamNumber");			
			TeamAssessment teamAssessment = new TeamAssessment((int)teamNumber);
		
			List<Assessment> assessments = new ArrayList<Assessment>();
			
			for(Criterion criter: assessmentTable) {
				
				String criterion = criter.getCriterion();				
				EmbeddedEntity embeddedCriterion = (EmbeddedEntity) entity.getProperty(criterion);				
 				long value = (long) embeddedCriterion.getProperty("value");
				String name = (String) embeddedCriterion.getProperty("name");				
				Assessment assessment = new Assessment(criterion, name, (int)value);
				
				assessments.add(assessment);				
			}
			
			teamAssessment.setCriteria(assessments);
			
			teams.add(teamAssessment);			
		}
		 
		return teams;		
	}
	
	@GetMapping("/rankings")
	public List<TeamScore> getRankings() {
		
		List<TeamAssessment> assessments = this.getAllTeamsAssessment();
		
		Hashtable<Integer,List<Float>> scoreTable = new Hashtable<Integer,List<Float>>();
		
		for(TeamAssessment teamAssessment: assessments) {
			int teamNumber = teamAssessment.getTeamNumber();
			List<Float> teamScores = scoreTable.get(teamNumber);
			if(teamScores == null) {
				teamScores = new ArrayList<Float>();
			}
			float mark = teamAssessment.getMark();
			teamScores.add(mark);
			scoreTable.put(teamNumber, teamScores);
		}
		
		List<TeamScore> scores = new ArrayList<TeamScore>();
		
		Enumeration<Integer> numbers = scoreTable.keys();
		
		while(numbers.hasMoreElements()) {
			int teamNumber = numbers.nextElement();
			TeamScore ts = new TeamScore(teamNumber);
			List<Float> teamScores = scoreTable.get(teamNumber);
			for(Float mark: teamScores) {
				ts.setMark(ts.getMark()+mark);
			}
			ts.setMark(ts.getMark() / teamScores.size());
			scores.add(ts);
		}
		
		Collections.sort(scores, new java.util.Comparator<TeamScore>(){

			@Override
			public int compare(TeamScore score2, TeamScore score1) {
				return ( (int)(score1.getMark() * (float)1000.0) - (int)(score2.getMark() * (float)1000.0) )  ;
			}
			
		});
		
		return scores;
		
	}
}
