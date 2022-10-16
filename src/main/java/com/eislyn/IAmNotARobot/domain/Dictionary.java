package com.eislyn.IAmNotARobot.domain;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.eislyn.IAmNotARobot.dataAccessAPI.HttpConnector;

/**
 * 
 * @author Eislyn
 * @since 16/10/2022
 */
public class Dictionary {
	
	private String response;
	private String word;
	private List<PartOfSpeech> partOfSpeechClasses;
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public String getWord() {
		return word;
	}
	
	public List<PartOfSpeech> getResponseAsListOfClasses() {
		if(word == null || word == "") {
			throw new IllegalArgumentException();
		}
		
		String urlStr = "https://api.dictionaryapi.dev/api/v2/entries/en/" + word;
		response = HttpConnector.getResponse(urlStr);
		
		JSONArray wordTypesArray = new JSONArray(response);
		
		partOfSpeechClasses = new ArrayList<PartOfSpeech>();
		
		for(int i=0; i<wordTypesArray.length(); i++) {
			JSONObject wordTypeObject = wordTypesArray.getJSONObject(i);
			JSONArray meaningsArray = wordTypeObject.getJSONArray("meanings");
			
			for(int j=0; j<meaningsArray.length(); j++) {
				JSONObject meaningJsonObject = meaningsArray.getJSONObject(j);
				String partOfSpeechString = meaningJsonObject.getString("partOfSpeech");
				PartOfSpeech partOfSpeechObject = new PartOfSpeech();
				
				//set part of speech of one class
				partOfSpeechObject.setPartOfSpeech(partOfSpeechString);
				
				//add in the definition list
				JSONArray definitionsJsonArray = meaningJsonObject.getJSONArray("definitions");
				for(int k=0; k<definitionsJsonArray.length(); k++) {
					JSONObject definitionJsonObject = definitionsJsonArray.getJSONObject(k);
					String definition = definitionJsonObject.getString("definition");
					partOfSpeechObject.addDefinitionList(definition);
				}
				partOfSpeechClasses.add(partOfSpeechObject);
			}
		}
		return partOfSpeechClasses;
	}
}
