package com.eislyn.IAmNotARobot.dataAccessAPI;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TranslatorAPITest {
	TranslatorAPI translatorAPI = new TranslatorAPI();
	
	@Test
	@Parameters(method = "paramTestValidGetResponse")
	public void testValidGetResponse(String langFrom, String langTo, String text, String expectedResult) throws IOException {
		String actualResponse = translatorAPI.getResponse(langFrom, langTo, text);
		assertEquals(expectedResult, actualResponse);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestValidGetResponse() {
		return new Object[] {
			new Object[] {"", "fr", "hello", "bonjour"},
			new Object[] {"", "en", "bonjour", "hello"},
			new Object[] {"", "zh", "hello", "你好"},
			new Object[] {"", "en", "你好", "Hello"}			
		};
	}
	
	@Test (expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidGetResponse")
	public void testInvalidGetResponse(String langFrom, String langTo, String text) throws IOException {
		translatorAPI.getResponse(langFrom, langTo, text);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidGetResponse() {
		return new Object[] {
			new Object[] {"", "", ""},
			new Object[] {"", null, null},
			new Object[] {null, "", null},
			new Object[] {null, null, ""},
			new Object[] {"", "", null},
			new Object[] {"", null, ""},
			new Object[] {null, "", ""},
			new Object[] {null, null, null}
		};
	}
}
