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
			new Object[] {"https://665.uncovernet.workers.dev/translate?text=" + URLEncoder.encode("bonjour", "UTF-8") + "&source_lang=" + "" +"&target_lang=" + "en", "{\"inputs\":{\"text\":\"bonjour\",\"source_lang\":\"en\",\"target_lang\":\"en\"},\"response\":{\"translated_text\":\"Welcome to\"}}"},
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
