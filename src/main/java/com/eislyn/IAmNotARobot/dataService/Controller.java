package com.eislyn.IAmNotARobot.dataService;

import java.util.List;

import org.json.JSONException;

import com.eislyn.IAmNotARobot.domain.Currency;
import com.eislyn.IAmNotARobot.domain.CurrencyExchange;
import com.eislyn.IAmNotARobot.domain.Dictionary;
import com.eislyn.IAmNotARobot.domain.PartOfSpeech;
import com.eislyn.IAmNotARobot.domain.Time;
import com.eislyn.IAmNotARobot.domain.Translator;

/**
 * Directs input and output to the corresponding upper and lower layers.
 * @author Eislyn
 * @since 15/10/2022
 */
public class Controller {
	/**
	 * Pass in the parameters needed(owns business layer) from setup class, don't create it here.
	 * @param embedTemplate
	 * @param translator
	 */
	public Controller() {
		
	}
	
	/**
	 * Directs Translator class to do translation.
	 * @param langTo target language
	 * @param text text to translate
	 * @return response translated text
	 */
	public String translate(String langTo, String text) {
		Translator translator = new Translator();
		if(langTo == null || langTo == "" || text == null || text == "")
			throw new IllegalArgumentException();
		
		translator.setLangTo(langTo);
		translator.setText(text);
		String response = translator.translate();
		return response;
	}
	
	/**
	 * Directs Dictionary class to get definitions.
	 * @param guildName Discord guild name
	 * @param word Word to get definition
	 * @return dictionaryEmbedBuilder Dictionary embed
	 */
	public List<PartOfSpeech> dictionary(String word) {
		if(word == null || word == "") {
			throw new IllegalArgumentException();
		}
		
		Dictionary dictionary = new Dictionary(word);
		List<PartOfSpeech> partOfSpeechList = dictionary.getResponseAsPartOfSpeechList();
		return partOfSpeechList;
	}
	
	/**
	 * Directs CurrencyExchange class to exchange currency.
	 * @param authorName Discord guild name
	 * @param baseCurrency Base currency to exchange
	 * @param targetCurrency Target currency to exchange to
	 * @param amountToExchange Amount to exchange 
	 * @return currencyExchangeEmbedBuilder Output embed builder
	 */
	public Currency exchangeCurrency(String baseCurrency, String targetCurrency, double amountToExchange) throws JSONException{
		if(baseCurrency == null || baseCurrency == "" || targetCurrency == null || targetCurrency == "" || amountToExchange < 0) {
			throw new IllegalArgumentException();
		}
		
		CurrencyExchange currencyExchange = new CurrencyExchange(baseCurrency, targetCurrency, amountToExchange);
		Currency currency = currencyExchange.exchangeCurrency();
		
		return currency;
	}
	
	public String currentTime(String timeZone) {
		Time time = new Time();
		time.setTimeZone(timeZone);
		time.formatTimeAndDate();
	    return time.getCurrentDateAndTime();
	}
}
