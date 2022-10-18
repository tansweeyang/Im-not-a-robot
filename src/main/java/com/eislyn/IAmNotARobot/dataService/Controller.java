package com.eislyn.IAmNotARobot.dataService;

import java.util.List;

import com.eislyn.IAmNotARobot.domain.Currency;
import com.eislyn.IAmNotARobot.domain.CurrencyExchange;
import com.eislyn.IAmNotARobot.domain.CurrencyExchangeEmbed;
import com.eislyn.IAmNotARobot.domain.Dictionary;
import com.eislyn.IAmNotARobot.domain.DictionaryEmbed;
import com.eislyn.IAmNotARobot.domain.EmbedTemplate;
import com.eislyn.IAmNotARobot.domain.PartOfSpeech;
import com.eislyn.IAmNotARobot.domain.Translator;

import net.dv8tion.jda.api.EmbedBuilder;

/**
 * Directs input and output to the corresponding upper and lower layers.
 * @author Eislyn
 * @since 15/10/2022
 */
public class Controller {
	private EmbedTemplate embedTemplate;
	private Translator translator;
	private Dictionary dictionary;
	private CurrencyExchange currencyExchange;
	
	/**
	 * Pass in the parameters needed(owns business layer) from setup class, don't create it here.
	 * @param embedTemplate
	 * @param translator
	 */
	public Controller(EmbedTemplate embedTemplate, Translator translator, Dictionary dictionary, CurrencyExchange currencyExchange) {
		this.embedTemplate = embedTemplate;
		this.translator = translator;
		this.dictionary = dictionary;
		this.currencyExchange = currencyExchange;
	}
	
	/**
	 * Directs the EmbedTemplate class to create a HelpEmbed.
	 * @param guildName Discord guild name
	 * @return embedBuilder helpEmbed
	 */
	public EmbedBuilder help(String guildName) {
		if(guildName == null || guildName == "")
			throw new IllegalArgumentException();
		
		EmbedBuilder embedBuilder = embedTemplate.buildEmbed(guildName);
		return embedBuilder;
	}
	
	/**
	 * Directs the Translator class to do translation.
	 * @param langTo target language
	 * @param text text to translate
	 * @return response translated text
	 */
	public String translate(String langTo, String text) {
		if(langTo == null || langTo == "" || text == null || text == "")
			throw new IllegalArgumentException();
		
		translator.setLangTo(langTo);
		translator.setText(text);
		String response = translator.translate();
		return response;
	}
	
	/**
	 * Directs the Dictionary class to get definitions.
	 * @param guildName Discord guild name
	 * @param word Word to get definition
	 * @return dictionaryEmbedBuilder Dictionary embed
	 */
	public EmbedBuilder dictionary(String guildName, String word) {
		if(guildName == null || guildName == "" || word == null || word == "") {
			throw new IllegalArgumentException();
		}
		
		dictionary.setWord(word);
		List<PartOfSpeech> partOfSpeechList = dictionary.getResponseAsPartOfSpeechList();
		EmbedTemplate dictionaryEmbed = new DictionaryEmbed(partOfSpeechList, word);
		EmbedBuilder dictionaryEmbedBuilder = dictionaryEmbed.buildEmbed(guildName);
		
		return dictionaryEmbedBuilder;
	}
	
	/**
	 * 
	 * @param authorName Discord guild name
	 * @param baseCurrency Base currency to exchange
	 * @param targetCurrency Target currency to exchange to
	 * @param amountToExchange Amount to exchange 
	 * @return currencyExchangeEmbedBuilder Output embed builder
	 */
	public EmbedBuilder exchangeCurrency(String authorName, String baseCurrency, String targetCurrency, double amountToExchange) {
		if(authorName == null || authorName == "" || baseCurrency == null || baseCurrency == "" || targetCurrency == null || targetCurrency == "" || amountToExchange < 0) {
			throw new IllegalArgumentException();
		}
		
		currencyExchange.setBaseCurrency(baseCurrency);
		currencyExchange.setTargetCurrency(targetCurrency);
		currencyExchange.setAmountToExchange(amountToExchange);
		Currency currency = currencyExchange.exchangeCurrency();
		
		EmbedTemplate currencyExchangeEmbed = new CurrencyExchangeEmbed(baseCurrency, targetCurrency, amountToExchange, currency);
		EmbedBuilder currencyExchangeEmbedBuilder = currencyExchangeEmbed.buildEmbed(authorName);
		
		return currencyExchangeEmbedBuilder;
	}
}
