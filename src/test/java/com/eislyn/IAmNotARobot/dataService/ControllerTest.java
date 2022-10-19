package com.eislyn.IAmNotARobot.dataService;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.eislyn.IAmNotARobot.domain.Currency;
import com.eislyn.IAmNotARobot.domain.PartOfSpeech;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class ControllerTest {
	Controller controller = new Controller();
	
	@Test
	@Parameters(method = "paramTestTranslate")
	public void testValidTranslate(String langTo, String text, String expectedResult) {
		String actualResult = controller.translate(langTo, text);
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
	
	@Test
	public void testValidDictionary() {
		String word = "multithreading";
		
		List<PartOfSpeech> actualPartOfSpeechList = controller.dictionary(word);
		
		List<PartOfSpeech> expectedPartOfSpeechList = new ArrayList<PartOfSpeech>();
		
		
		PartOfSpeech partOfSpeechNoun;
		List<String> definitionListNoun = new ArrayList<String>();
		definitionListNoun.add("The use of multithreaded code.");
		partOfSpeechNoun = new PartOfSpeech("noun", definitionListNoun);
		expectedPartOfSpeechList.add(partOfSpeechNoun);
		
		assertEquals(expectedPartOfSpeechList.toString(), actualPartOfSpeechList.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidDictionary")
	public void testInvalidDictionary(String word) {
		controller.dictionary(word);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidDictionary() {
		return new Object[] {
			new Object[] {null},
			new Object[] {""}
		};
	}
	
	@Test
	public void testValidExchangeCurrency() {
		String baseCurrency = "USD";
		String targetCurrency = "USD";
		double conversionRate = 1.0;
		double amountToExchange = 100.0;
		double exchangedAmount = 100.0;
		Currency expectedCurrency = new Currency("", targetCurrency, conversionRate, exchangedAmount);
		
		Currency actualCurrency = controller.exchangeCurrency(baseCurrency, targetCurrency, amountToExchange);
		assertEquals(expectedCurrency.toString(), actualCurrency.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidExchangeCurrency")
	public void testInvalidExchangeCurrency(String baseCurrency, String targetCurrency, double amountToExchange) {
		controller.exchangeCurrency(baseCurrency, targetCurrency, amountToExchange);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidExchangeCurrency() {
		return new Object[] {
			new Object[] {null, null, -1},
			new Object[] {"", null, -2},
			new Object[] {null, "", -3},
			new Object[] {"", "", -4},
		};		
	}
}
