package com.eislyn.IAmNotARobot.dataService;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.eislyn.IAmNotARobot.domain.CurrencyExchange;
import com.eislyn.IAmNotARobot.domain.Dictionary;
import com.eislyn.IAmNotARobot.domain.EmbedTemplate;
import com.eislyn.IAmNotARobot.domain.HelpEmbed;
import com.eislyn.IAmNotARobot.domain.Translator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.dv8tion.jda.api.EmbedBuilder;

@RunWith(JUnitParamsRunner.class)
public class ControllerTest {
	
	EmbedTemplate helpEmbed = new HelpEmbed();
	Translator translator = new Translator();
	Dictionary dictionary = new Dictionary();
	CurrencyExchange currencyExchange = new CurrencyExchange();
	Controller controller = new Controller(helpEmbed, translator, dictionary, currencyExchange);
	
	@Test
	public void testValidHelp() {
		EmbedBuilder actualHelpEmbed = controller.help("Server Name");
		String actualEmbedBuilderString = actualHelpEmbed.build().toData().toString();
		
		EmbedBuilder expectedEmbedBuilder = new EmbedBuilder();
		expectedEmbedBuilder.setAuthor("Server Name");
		expectedEmbedBuilder.setTitle("Help Menu");
		expectedEmbedBuilder.setDescription("-----------------------------------------------------------------------------------------------\r\n"
				+ "Description\r\n"
				+ "I'm not a robot is a multipurpose discord bot equipped with utility tools. No setup is required to use the bot.\r\n"
				+ "-----------------------------------------------------------------------------------------------");
		expectedEmbedBuilder.addField("Prefix and help", 
				  "1. ``e!``: Type this in front of a command.\r\n"
				+ "2. ``e!help``:  Gets a help menu.\r\n"
				+ "3. ``e!helpts``: Gets the full translation supported languages list.\r\n"
				+ "4. ``e!helpct``: Gets the currency table list.\r\n"
				+ "5. ``e!helptime``: Gets the time zone name and code list.\r\n"
				+ "-----------------------------------------------------------------------------------------------", false);
		expectedEmbedBuilder.addField("Core Utility Commands", 
				  "1. ``e!ts targetLanguage message``: Auto detects a language and translates the message to the target language.\r\n"
				+ "2. ``e!d word``: Gets the definition of a English word.\r\n"
				+ "3. ``e!e baseCurrency targetCurrency amountToExchange``: Translate a currency from one to another.\r\n"
				+ "4. ``e!time timeZoneName / e!time timeZoneCode``: Gets the current time from a time zone.\r\n"
				+ "5. ``e!timer minutes``: Set a ping timer in number of minutes.\r\n"				+ "-----------------------------------------------------------------------------------------------", false);
		expectedEmbedBuilder.addField("Other Commands", 
				  "1. ``e!info @user``: Gets the server info of a user.\r\n"
				+ "2. ``e!about``: Gets info about this bot and its developer.\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "", false);
		expectedEmbedBuilder.setFooter("Created by Eislyn", "https://images-ext-1.discordapp.net/external/Eaa_hmN5uh9n2NH8FMUTK1WgHa-5dTGo2Ain7s6VSI8/https/static.boredpanda.com/blog/wp-content/uploads/2020/07/expressive-cat-nana-1-21-5f16d009589a4__700.jpg");
		String expectedEmbedBuilderString = expectedEmbedBuilder.build().toData().toString();
		
		assertEquals(expectedEmbedBuilderString, actualEmbedBuilderString);
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidHelp")
	public void testInvalidHelp(String title) {
		controller.help(title);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidHelp() {
		return new Object[] {
			new Object[] {null},
			new Object[] {""}
		};
	}
	
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
		String guildName = "Guild name";
		String word = "work";
		EmbedBuilder actualDictionaryEmbed = controller.dictionary(guildName, word);
		
		EmbedBuilder expectedDictionaryEmbed = new EmbedBuilder();
		
		expectedDictionaryEmbed.setFooter("Created by Eislyn", "https://images-ext-1.discordapp.net/external/Eaa_hmN5uh9n2NH8FMUTK1WgHa-5dTGo2Ain7s6VSI8/https/static.boredpanda.com/blog/wp-content/uploads/2020/07/expressive-cat-nana-1-21-5f16d009589a4__700.jpg");
		expectedDictionaryEmbed.setAuthor("Guild name");
		expectedDictionaryEmbed.setTitle("Work\r\n" + "--------------------------------------------------------------------------------------");
		expectedDictionaryEmbed.addField("1. Part Of Speech: noun", "The equipment needed to inject a drug (syringes, needles, swabs etc.)", false);
		expectedDictionaryEmbed.addField("2. Part Of Speech: verb", "To hurt; to ache.", false);
		expectedDictionaryEmbed.addField("--------------------------------------------------------------------------------------------------", "", false);
		
		assertEquals(expectedDictionaryEmbed.build().toData().toString(), actualDictionaryEmbed.build().toData().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidDictionary")
	public void testInvalidDictionary(String guildName, String word) {
		controller.dictionary(guildName, word);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidDictionary() {
		return new Object[] {
			new Object[] {null, null},
			new Object[] {"", ""},
			new Object[] {"", null},
			new Object[] {null, ""}
		};
	}
	
	@Test
	public void testValidExchangeCurrency() {
		String guildName = "Guild name";
		String baseCurrency = "USD";
		String targetCurrency = "USD";
		double amountToExchange = 100;
		
		EmbedBuilder actualCurrencyExchangeEmbedBuilder = controller.exchangeCurrency(guildName, baseCurrency, targetCurrency, amountToExchange);
		int embedSize = actualCurrencyExchangeEmbedBuilder.getFields().size();
		String lastUpated = actualCurrencyExchangeEmbedBuilder.getFields().get(embedSize-1).getValue();
		
		EmbedBuilder expectedCurrencyExchangeEmbedBuilder = new EmbedBuilder();
		expectedCurrencyExchangeEmbedBuilder.setAuthor(guildName);
		expectedCurrencyExchangeEmbedBuilder.setFooter("Created by Eislyn", "https://images-ext-1.discordapp.net/external/Eaa_hmN5uh9n2NH8FMUTK1WgHa-5dTGo2Ain7s6VSI8/https/static.boredpanda.com/blog/wp-content/uploads/2020/07/expressive-cat-nana-1-21-5f16d009589a4__700.jpg");
		expectedCurrencyExchangeEmbedBuilder.setTitle("Currency Exchange");
		expectedCurrencyExchangeEmbedBuilder.setDescription("Converted from " + "USD" + " to " + "USD");
		expectedCurrencyExchangeEmbedBuilder.addField("Amount to convert", amountToExchange + baseCurrency, false);
		expectedCurrencyExchangeEmbedBuilder.addField("Converted amount", 100.0 + "USD", false);
		expectedCurrencyExchangeEmbedBuilder.addField("Last updated", lastUpated, false);

		assertEquals(expectedCurrencyExchangeEmbedBuilder.build().toData().toString(), actualCurrencyExchangeEmbedBuilder.build().toData().toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "paramTestInvalidExchangeCurrency")
	public void testInvalidExchangeCurrency(String guildName, String baseCurrency, String targetCurrency, double amountToExchange) {
		controller.exchangeCurrency(guildName, baseCurrency, targetCurrency, amountToExchange);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidExchangeCurrency() {
		return new Object[] {
			new Object[] {null, null, null, -1},
			new Object[] {"", "", "", -2},
			new Object[] {"", null, null, -3},
			new Object[] {null, "", null, -4},
			new Object[] {null, null, "", -5},
			new Object[] {"", "", null, -6},
			new Object[] {null, "", "", -7},
			new Object[] {"", null, "", -8},
		};		
	}
}
