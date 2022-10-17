package com.eislyn.IAmNotARobot.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class TranslatorTest {
	
	@Test
	@Parameters(method = "paramTestTranslate")
	public void testTranslate(String langTo, String text, String expectedResult) {
		Translator translator = new Translator();
		translator.setLangTo(langTo);
		translator.setText(text);
		
		String actualResult = translator.translate();
		assertEquals(expectedResult, actualResult);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestTranslate() {
		return new Object[] {
			new Object[] {"fr", "hello", "bonjour"},
			new Object[] {"en", "bonjour", "hello"},
			new Object[] {"zh", "hello", "你好"},
			new Object[] {"en", "你好", "Hello"}			
		};		
	}

}
