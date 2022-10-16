package com.eislyn.IAmNotARobot.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores the dictionary response informations as a class. 
 * @author Eislyn
 * @since 17/10/2022
 */
public class PartOfSpeech {
	private String partOfSpeech; 
	private List<String> definitionList = new ArrayList<String>();

	public List<String> getDefinitionList() {
		return definitionList;
	}

	public void setDefinitionList(List<String> definitionList) {
		this.definitionList = definitionList;
	}

	public String getPartOfSpeech() {
		return partOfSpeech;
	}

	public void setPartOfSpeech(String partOfSpeech) {
		this.partOfSpeech = partOfSpeech;
	}
	
	public void addDefinitionList(String definition) {
		definitionList.add(definition);
	}
	

}
