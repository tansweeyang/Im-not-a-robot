package com.eislyn.IAmNotARobot.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.eislyn.IAmNotARobot.dataAccessAPI.HttpConnector;

/**
 * Setups and stores the information necessary for Translator API. 
 * @author Eislyn
 * @since 15/10/2022
 */
public class Translator {
	private String langFrom = "";
	private String langTo;
	private String text;
	
	public void setLangTo(String langTo) {
		this.langTo = langTo;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * Creates an url string and get the response from HttpConnector
	 * @return response Response from api
	 */
	public String translate() {
		String urlStr = "";
		try {
			urlStr = "https://script.google.com/macros/s/AKfycbxA9Hn4G2jFfHJbzjD91Yo64rZAJ19i8FHSQLtTIqOBTUWEabXItuS_YSkLsE9qddka/exec" + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo +"&source=" + langFrom;
		} catch (UnsupportedEncodingException e) {
			return "Unsupported encoding";
		}
		return HttpConnector.getResponse(urlStr);
	}

}
