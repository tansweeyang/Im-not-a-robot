package com.eislyn.IAmNotARobot.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.eislyn.IAmNotARobot.dataAccessAPI.HttpConnector;

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
		String deploymentKey = getDeploymentKey();
		String urlStr = "";
		try {
			//API: Google script Translator class
			urlStr = "https://script.google.com/macros/s/" + deploymentKey + "/exec" + "?q=" + URLEncoder.encode(text, "UTF-8") + "&target=" + langTo +"&source=" + langFrom;
		} catch (UnsupportedEncodingException e) {
			return "Unsupported encoding";
		}
		return HttpConnector.getResponse(urlStr);
	}

}
