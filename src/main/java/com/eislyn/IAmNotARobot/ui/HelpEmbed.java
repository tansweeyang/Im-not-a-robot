package com.eislyn.IAmNotARobot.ui;

/**
 * Creates a help embed using EmbedTemplate by overriding abstract methods.
 * @author Eislyn
 * @since 15/10/2022
 */
public class HelpEmbed extends EmbedTemplate{
	
	@Override
	public void setTitle() {
		embedBuilder.setTitle("Help Menu");
	}

	@Override
	public void setDescription() {
		embedBuilder.setDescription("-----------------------------------------------------------------------------------------------\r\n"
				+ "Description\r\n"
				+ "I'm not a robot is a multipurpose discord bot equipped with utility tools. No setup is required to use the bot.\r\n"
				+ "-----------------------------------------------------------------------------------------------");
	}

	/**
	 * Constructs and add fields for helpEmbed.
	 */
	@Override
	public void addField() {
		embedBuilder.addField("Prefix and help", 
				  "1. ``e!``: Type this in front of a command.\r\n"
				+ "2. ``e!help``:  Gets a help menu.\r\n"
				+ "3. ``e!helpts``: Gets the full translation supported languages list.\r\n"
				+ "4. ``e!helpct``: Gets the currency table list.\r\n"
				+ "5. ``e!helptime``: Gets the time zone name and code list.\r\n"
				+ "-----------------------------------------------------------------------------------------------", false);
		embedBuilder.addField("Core Utility Commands", 
				  "1. ``e!ts targetLanguage message``: Auto detects a language and translates the message to the target language.\r\n"
				+ "2. ``e!d word``: Gets the definition of a English word.\r\n"
				+ "3. ``e!e baseCurrency targetCurrency amountToExchange``: Translate a currency from one to another.\r\n"
				+ "4. ``e!time timeZoneName / e!time timeZoneCode``: Gets the current date and time from a time zone.\r\n"
				+ "5. ``e!timer minutes``: Set a ping timer in number of minutes.\r\n"				+ "-----------------------------------------------------------------------------------------------", false);
		embedBuilder.addField("Other Commands", 
				  "1. ``e!info @user``: Gets the server info of a user.\r\n"
				+ "2. ``e!about``: Gets info about this bot and its developer.\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "", false);
	}

	@Override
	public void setThumbanail() {
		// TODO Auto-generated method stub
		
	}
}
