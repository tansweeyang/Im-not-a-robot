package com.eislyn.IAmNotARobot.domain;

import org.json.JSONObject;

import com.eislyn.IAmNotARobot.dataAccessAPI.HttpConnector;

import io.github.cdimascio.dotenv.Dotenv;

public class CurrencyExchange {
	private static final String apiKeyName = "CURRENCY_TRANSLATOR_API_KEY";
	
	private String baseCurrency;
	private String targetCurrency;
	private double amountToConvert;

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public void setAmountToConvert(double amountToConvert) {
		this.amountToConvert = amountToConvert;
	}

	private String getApiKey() {
		Dotenv dotenv = Dotenv.load();
		return dotenv.get(apiKeyName);
	}

	/**
	 * Exchange currency based on an amount, set baseCurrency, targetCurrency and amountToConvert first before using this.
	 * @return
	 */
	public Currency exchangeCurrency() {
		if(baseCurrency == null || baseCurrency == "" || targetCurrency == null || targetCurrency == "" || amountToConvert < 0) {
			throw new IllegalArgumentException();
		}
		
		String apiKey = getApiKey();
		String urlStr = "https://api.currencyapi.com/v3/latest?apikey=" + apiKey + "&currencies=" + targetCurrency
				+ "&base_currency=" + baseCurrency;
		String responseStr = HttpConnector.getResponse(urlStr);

		Currency currency = new Currency();
		
		// Last updated
		JSONObject conversionObject = new JSONObject(responseStr);
		JSONObject metaObject = conversionObject.getJSONObject("meta");
		currency.setLastUpdated(metaObject.getString("last_updated_at"));

		// code
		JSONObject dataObject = conversionObject.getJSONObject("data");
		JSONObject currencyObject = dataObject.getJSONObject(targetCurrency);
		
		//set values in currency class
		currency.setCode(currencyObject.getString("code"));
		currency.setConversionRate(currencyObject.getDouble("value"));
		currency.setConvertedAmount(currency.getConversionRate() * amountToConvert);

		return currency;
	}

}
