package com.eislyn.IAmNotARobot.dataAccessAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 
 * @author Eislyn
 * 15/10/2022
 */
public class TranslatorAPI {
	/**
	 * Connects to TranslatorAPI and translates a given text to the target language from a source language
	 * @param langFrom Source language(always "" for auto)
	 * @param langTo Target language
	 * @param text Text to translate
	 * @return response Translated text
	 * @throws IOException
	 */
	public String getResponse(String langFrom, String langTo, String text) throws IOException {
		if(langTo == null || langTo == "" || text == null || text == "")
			throw new IllegalArgumentException();
		
		String urlStr = "https://script.google.com/macros/s/AKfycbxA9Hn4G2jFfHJbzjD91Yo64rZAJ19i8FHSQLtTIqOBTUWEabXItuS_YSkLsE9qddka/exec" + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo +"&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
	}

}
