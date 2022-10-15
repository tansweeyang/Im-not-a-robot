package com.eislyn.IAmNotARobot.dataService;

import com.eislyn.IAmNotARobot.domain.EmbedTemplate;
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
	
	/**
	 * Pass in the parameters needed(owns business layer) from setup class, don't create it here.
	 * @param embedTemplate
	 * @param translator
	 */
	public Controller(EmbedTemplate embedTemplate, Translator translator) {
		this.embedTemplate = embedTemplate;
		this.translator = translator;
	}
	
	/**
	 * Directs the EmbedTemplate class to create a HelpEmbed.
	 * @param title Discord guild name
	 * @return embedBuilder helpEmbed
	 */
	public EmbedBuilder help(String title) {
		if(title == null || title == "")
			throw new IllegalArgumentException();
		
		EmbedBuilder embedBuilder = embedTemplate.buildEmbed(title);
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
}
