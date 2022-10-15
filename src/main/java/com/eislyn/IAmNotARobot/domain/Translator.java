package com.eislyn.IAmNotARobot.domain;

import java.io.IOException;

import com.eislyn.IAmNotARobot.dataAccessAPI.TranslatorAPI;

/**
 * Setups and stores the information necessary for Translator API. 
 * @author Eislyn
 * @since 15/10/2022
 */
public class Translator {
	private TranslatorAPI translatorAPI;
	
	private String langFrom = "";
	private String langTo;
	private String text;
	
	/**
	 * Pass in the parameters needed(owns data access layer) from setup class, don't create it here.
	 * @param translatorAPI
	 */
	public Translator(TranslatorAPI translatorAPI) {
		this.translatorAPI = translatorAPI;
	}
	
	public void setLangTo(String langTo) {
		this.langTo = langTo;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Sends in the data to translatorAPI and get the response
	 * @return response Translated text
	 */
	public String translate() {
		String response = null;
		try {
			response = translatorAPI.getResponse(langFrom, langTo, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
