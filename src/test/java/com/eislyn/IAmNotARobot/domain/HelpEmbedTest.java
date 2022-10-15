package com.eislyn.IAmNotARobot.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import net.dv8tion.jda.api.EmbedBuilder;

@RunWith(JUnitParamsRunner.class)
public class HelpEmbedTest {
	@Test
	public void testValidBuildEmbed() {
		EmbedTemplate helpEmbed = new HelpEmbed();
		EmbedBuilder actualEmbedBuilder = helpEmbed.buildEmbed("Server Name");
		String actualEmbedBuilderString = actualEmbedBuilder.build().toData().toString();
		
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
	@Parameters(method = "paramTestInvalidBuildEmbed")
	public void testInvalidBuildEmbed(String serverName) {
		EmbedTemplate helpEmbed = new HelpEmbed();
		helpEmbed.buildEmbed(serverName);
	}
	
	@SuppressWarnings("unused")
	private Object[] paramTestInvalidBuildEmbed() {
		return new Object[] {
			new Object[] {null},
			new Object[] {""}
		};
	}

}
