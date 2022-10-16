package com.eislyn.IAmNotARobot.domain;

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
		embedBuilder.addField("Prefix and help", "``e!``: Type this in front of a command.\r\n"
				+ "``e!help``: Type this to get help.\r\n"
				+ "-----------------------------------------------------------------------------------------------", false);
		embedBuilder.addField("Core Utility Commands", "1. ``e!ts targetLanguage message``: Auto detects a language and translates the message to the target language.\r\n"
				+ "2. ``e!d word``: Gets the definition of a English word.\r\n"
				+ "3. ``e!e baseCurrency targetCurrency``: Translate a currency from one to another.\r\n"
				+ "4. ``e!helpct``: Gets the currency table list.\r\n"
				+ "5. ``e!time timeZoneName / e!time timeZoneCode``: Gets the current time from a time zone.\r\n"
				+ "6. ``e!tzl``: Gets the time zone name and code list.\r\n"
				+ "7. ``e!timer minutes``: Set a ping timer in number of minutes.\r\n"
				+ "8. ``e!i number``: Get a invite link for number of usage.\r\n"
				+ "-----------------------------------------------------------------------------------------------", false);
		embedBuilder.addField("Other Commands", "1. ``e!info @user``: Gets the server info of a user.\r\n"
				+ "2. ``e!about``: Gets info about this bot and its developer.\r\n"
				+ "-----------------------------------------------------------------------------------------------\r\n"
				+ "", false);
	}
}
