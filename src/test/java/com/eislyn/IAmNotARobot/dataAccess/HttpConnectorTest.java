package com.eislyn.IAmNotARobot.dataAccess;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class HttpConnectorTest {
	@Test
	@Parameters(method = "paramTestValidGetResponse")
	public void testValidGetResponse(String url, String expectedResult) {
		String actualResult = HttpConnector.getResponse(url);
		assertEquals(expectedResult, actualResult);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestValidGetResponse() throws UnsupportedEncodingException {
		return new Object[] {
			new Object[] {"https://script.google.com/macros/s/AKfycbxA9Hn4G2jFfHJbzjD91Yo64rZAJ19i8FHSQLtTIqOBTUWEabXItuS_YSkLsE9qddka/exec" + "?q=" + URLEncoder.encode("hello", "UTF-8") + "&target=" + "fr" +"&source=" + "", "bonjour"},
			new Object[] {"https://api.dictionaryapi.dev/api/v2/entries/en/" + "recursion", "[{\"word\":\"recursion\",\"phonetics\":[],\"meanings\":[{\"partOfSpeech\":\"noun\",\"definitions\":[{\"definition\":\"The act of recurring.\",\"synonyms\":[],\"antonyms\":[]},{\"definition\":\"The act of defining an object (usually a function) in terms of that object itself.\",\"synonyms\":[],\"antonyms\":[],\"example\":\"n! = n × (n − 1)! (for n > 0) or 1 (for n = 0) defines the factorial function using recursion.\"},{\"definition\":\"The invocation of a procedure from within itself.\",\"synonyms\":[],\"antonyms\":[],\"example\":\"This function uses recursion to compute factorials.\"}],\"synonyms\":[],\"antonyms\":[]}],\"license\":{\"name\":\"CC BY-SA 3.0\",\"url\":\"https://creativecommons.org/licenses/by-sa/3.0\"},\"sourceUrls\":[\"https://en.wiktionary.org/wiki/recursion\"]}]"}		
		};
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidGetResponse")
	public void testInvalidGetResponse(String url) {
		HttpConnector.getResponse(url);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidGetResponse() throws UnsupportedEncodingException {
		return new Object[] {
			new Object[] {null},
			new Object[] {""},
		};
	}

}
