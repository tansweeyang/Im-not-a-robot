package com.eislyn.IAmNotARobot.dataService;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.eislyn.IAmNotARobot.dataAccessAPI.TranslatorAPI;
import com.eislyn.IAmNotARobot.domain.EmbedTemplate;
import com.eislyn.IAmNotARobot.domain.HelpEmbed;
import com.eislyn.IAmNotARobot.domain.Translator;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.dv8tion.jda.api.EmbedBuilder;

@RunWith(JUnitParamsRunner.class)
public class ControllerTest {
	
	EmbedTemplate helpEmbed = new HelpEmbed();
	TranslatorAPI translatorAPI = new TranslatorAPI();
	Translator translator = new Translator(translatorAPI);
	Controller controller = new Controller(helpEmbed, translator);
	
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
		expectedEmbedBuilder.addField("Prefix and help", "``e!``: Type this in front of a command.\r\n"
				+ "``e!help``: Type this to get help (This)\r\n"
				+ "-----------------------------------------------------------------------------------------------", false);
		expectedEmbedBuilder.addField("Core Utility Commands", "1. ``e!ts targetLanguage message``: Auto detects a language and translates the message to the target language.\r\n"
				+ "2. e!d word: Gets the definition of a English word.\r\n"
				+ "3. ``e!e baseCurrency targetCurrency``: Translate a currency from one to another.\r\n"
				+ "4. ``e!helpct``: Gets the currency table list.\r\n"
				+ "5. ``e!time timeZoneName / e!time timeZoneCode``: Gets the current time from a time zone.\r\n"
				+ "6. ``e!tzl``: Gets the time zone name and code list.\r\n"
				+ "7. ``e!timer minutes``: Set a ping timer in number of minutes.\r\n"
				+ "8. ``e!i number``: Get a invite link for number of usage."
				+ "-----------------------------------------------------------------------------------------------", false);
		expectedEmbedBuilder.addField("Other Commands", "1. ``e!info @user``: Gets the server info of a user.\r\n"
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
}
