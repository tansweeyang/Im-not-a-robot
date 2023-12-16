package com.eislyn.IAmNotARobot.domain;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.eislyn.IAmNotARobot.dataAccess.HttpConnector;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Setups and stores the information necessary for Translator API, set deploymentKeyname, langTo and Text first before using the translate() method.
 * @author Eislyn
 * @since 15/10/2022
 */
public class Translator {
	private final static String deploymentKeyName = "GOOGLE_SCRIPT_TRANSLATOR_DEPLOYMENT_ID";
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
	 * Gets the deploymentKey from the keyName
	 * @return deploymentKey
	 */
	private String getDeploymentKey() {
		if(deploymentKeyName == null || deploymentKeyName == "") {
			throw new IllegalArgumentException();
		}
		Dotenv dotenv = Dotenv.load();
		return dotenv.get(deploymentKeyName);
	}
	
	/**
	 * Creates an URL string and get the response from HttpConnector
	 * @return response Response from API
	 */
	public String translate() {
		String urlStr = "";
		try {
			//API: Google script Translator class
			urlStr = "https://665.uncovernet.workers.dev/translate?text=" + URLEncoder.encode(text, "UTF-8") + "&source_lang=" + langFrom + "&target_lang=" + langTo;
		} catch (UnsupportedEncodingException e) {
			return "Unsupported encoding";
		}

		String jsonResponse = HttpConnector.getResponse(urlStr);

		// Parse the JSON response
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(jsonResponse);

			JsonNode responseNode = rootNode.path("response");
			JsonNode translatedTextNode = responseNode.path("translated_text");

			if (!translatedTextNode.isMissingNode()) {
				// Return the translated text as a String
				return translatedTextNode.asText();
			} else {
				return "Error occurred during translation";
			}
		} catch (IOException e) {
			e.printStackTrace(); // Handle the exception or log it
			return "Error occurred during translation";
		}
	}
}
