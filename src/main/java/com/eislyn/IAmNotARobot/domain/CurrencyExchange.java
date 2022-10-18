package com.eislyn.IAmNotARobot.domain;

import org.json.JSONObject;

import com.eislyn.IAmNotARobot.dataAccessAPI.HttpConnector;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Stores data from user required to exchange currency using the api, set baseCurrency, targetCurrency and amountToConvert first before using exchangeCurrency() 
 * @author Eislyn
 * @since 18/10/2022
 */
public class CurrencyExchange {
	private static final String apiKeyName = "CURRENCY_TRANSLATOR_API_KEY";
	
	private String baseCurrency;
	private String targetCurrency;
	private double amountToExchange;

	public void setBaseCurrency(String baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public void setAmountToExchange(double amountToExchange) {
		this.amountToExchange = amountToExchange;
	}

	/**
	 * Loads the apiKeyValue from the apiKeyName
	 * @return
	 */
	private String getApiKey() {
		Dotenv dotenv = Dotenv.load();
		return dotenv.get(apiKeyName);
	}

	/**
	 * Exchange currency based on an amount, set baseCurrency, targetCurrency and amountToConvert first before using this.
	 * @return currency Currency object
	 */
	public Currency exchangeCurrency() {
		if(baseCurrency == null || baseCurrency == "" || targetCurrency == null || targetCurrency == "" || amountToExchange < 0) {
			throw new IllegalArgumentException();
		}
		
		//Gets the apiKeyValue and use it to get the response 
		String apiKey = getApiKey();
		String urlStr = "https://api.currencyapi.com/v3/latest?apikey=" + apiKey + "&currencies=" + targetCurrency
				+ "&base_currency=" + baseCurrency;
		String responseStr = HttpConnector.getResponse(urlStr);

		//Maps the json data into a currency object
		Currency currency = new Currency();
		
		//Last updated
		JSONObject conversionObject = new JSONObject(responseStr);
		JSONObject metaObject = conversionObject.getJSONObject("meta");
		currency.setLastUpdated(metaObject.getString("last_updated_at"));

		JSONObject dataObject = conversionObject.getJSONObject("data");
		JSONObject currencyObject = dataObject.getJSONObject(targetCurrency);
		
		//Set values in currency class
		//*Code = Target currency returned by the api*
		currency.setCode(currencyObject.getString("code"));
		currency.setConversionRate(currencyObject.getDouble("value"));
		currency.setExchangedAmount(currency.getConversionRate() * amountToExchange);

		//return the mapped currency object
		return currency;
	}

}
