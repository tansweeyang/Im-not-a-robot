package com.eislyn.IAmNotARobot.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CurrencyExchangeTest {
	CurrencyExchange currencyExchange = new CurrencyExchange();
	
	@Test
	public void testValidExchangeCurrencyNotNullCode() {
		currencyExchange.setBaseCurrency("USD");
		currencyExchange.setTargetCurrency("MYR");
		currencyExchange.setAmountToExchange(100);
		
		Currency actualCurrency = currencyExchange.exchangeCurrency();
		
		assertNotNull(actualCurrency.getCode());
	}
	
	@Test
	public void testValidExchangeCurrencyNotNullConversionRate() {
		currencyExchange.setBaseCurrency("USD");
		currencyExchange.setTargetCurrency("MYR");
		currencyExchange.setAmountToExchange(100);
		
		Currency actualCurrency = currencyExchange.exchangeCurrency();
		
		assertNotNull(actualCurrency.getConversionRate());
	}
	
	@Test
	public void testValidExchangeCurrencyNotNullLastUpdated() {
		currencyExchange.setBaseCurrency("USD");
		currencyExchange.setTargetCurrency("MYR");
		currencyExchange.setAmountToExchange(100);
		
		Currency actualCurrency = currencyExchange.exchangeCurrency();
		
		assertNotNull(actualCurrency.getLastUpdated());
	}
	
	@Test
	public void testValidExchangeCurrencyNotNullConvertedAmount() {
		currencyExchange.setBaseCurrency("USD");
		currencyExchange.setTargetCurrency("MYR");
		currencyExchange.setAmountToExchange(100);
		
		Currency actualCurrency = currencyExchange.exchangeCurrency();
		
		assertNotNull(actualCurrency.getExchangedAmount());
	}
	
	@Test
	public void testValidExchangeCurrencyGreaterZeroConvertedAmount() {
		currencyExchange.setBaseCurrency("USD");
		currencyExchange.setTargetCurrency("MYR");
		currencyExchange.setAmountToExchange(100);
		
		Currency actualCurrency = currencyExchange.exchangeCurrency();
		
		boolean isGreaterThanZero = false;
		if(actualCurrency.getExchangedAmount()>0) {
			isGreaterThanZero = true;
		}
		assertSame(true, isGreaterThanZero);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidExchangeCurrency")
	public void testInvalidExchangeCurrency(String baseCurrency, String targetCurrency, double amountToExchange) {
		currencyExchange.setBaseCurrency(baseCurrency);
		currencyExchange.setTargetCurrency(targetCurrency);
		currencyExchange.setAmountToExchange(amountToExchange);
		
		currencyExchange.exchangeCurrency();
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidExchangeCurrency() {
		return new Object[] {
			new Object[] {null, null, -1.0},
			new Object[] {"", "", -2.0},
			new Object[] {null, "", -3.0},
			new Object[] {"", null, -4.0},
			new Object[] {null, null, 1.0},
			new Object[] {"", "", 2.0},
			new Object[] {null, "", 3.0},
			new Object[] {"", null, 4.0},
		};
	}
}
