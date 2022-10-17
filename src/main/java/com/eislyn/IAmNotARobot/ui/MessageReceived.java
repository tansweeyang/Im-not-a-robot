package com.eislyn.IAmNotARobot.ui;

import java.util.EventListener;

import org.json.JSONException;

import com.eislyn.IAmNotARobot.dataService.Controller;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * Recives input from user to sends the input to controller or receive output from controller and displays it to the user.
 * @author Eislyn
 * @since 15/10/2022
 */
public class MessageReceived extends ListenerAdapter implements EventListener {
	Controller controller;
	String prefix;

	public MessageReceived(Controller controller, String prefix) {
		this.controller = controller;
		this.prefix = prefix;
	}

	/**
	 * Overrides onMessageReceived in ListenerAdapter to receive input from discord and checks with supported commands, do not change method name.
	 */
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		// split("\\s+") will split the string into string of array with separator as
		// space or multiple spaces. \s+ is a regular expression for one or more spaces.
		String[] args = event.getMessage().getContentRaw().split("\\s+");

		if (event.getAuthor().isBot() == true) {
			return;
		}
		else if (args[0].equalsIgnoreCase(prefix + "help")) {
			if (args.length < 2) {
				help(event, event.getGuild().getName());
				return;
			}
			else {
				sendMessage(event, "Invalid command. Type ``e!help`` to get the help menu.");
				return;
			}
		}
		else if(args[0].equalsIgnoreCase(prefix + "ts")) {
			String langTo = args[1];
			
			if(args.length < 3) {
				sendMessage(event, "Invalid translate command. Type ``e!ts targetLanguage message to translate.``");
				return;
			}
			else {
				translate(event, args, langTo);
				return;
			}
		}
		else if(args[0].equalsIgnoreCase(prefix + "helpts")) {
			if(args.length < 2) {
				sendMessage(event, "See https://cloud.google.com/translate/docs/languages for the full list of supported languages.");
				return;
			}
			else {
				sendMessage(event, "Invalid command. Type ``e!help`` to get the help menu.");
				return;
			}
		}
		else if(args[0].equalsIgnoreCase(prefix + "d")) {
			if(args.length < 2 || args.length > 2) {
				sendMessage(event, "Enter a word to get its definition. ``e!d word``.");
				return;
			}
			else {
				String word = args[1];
				
				EmbedBuilder dictionaryEmbedBuilder;
				try {
					dictionaryEmbedBuilder = dictionary(event.getGuild().getName(), word);
				}catch (JSONException e) {
					sendMessage(event,  "Ops, cannot find this word in the dictionary!");
					return;
				}
				sendEmbedMessage(event, dictionaryEmbedBuilder);
				return;
			}
		}
		else if(args[0].contains("e!") || args[0].contains("E!")) {
			sendMessage(event, "Invalid command. Type ``e!help`` to get the help menu.");
			return;
		}
	}

	/**
	 * Communicates with controller to print out help menu.
	 * @param event MessageReceivedEvent
	 * @param guildName Discord guild name
	 */
	private void help(MessageReceivedEvent event, String guildName) {
		EmbedBuilder embedBuilder = controller.help(guildName);
		sendEmbedMessage(event, embedBuilder);
		return;
	}
	
	/**
	 * Combine array of input to text and communicates with controller to translate the text.
	 * @param event MessageReceivedEvent
	 * @param args array of input after the prefix and command
	 * @param langTo Target language
	 * @param text Text to translate
	 */
	private void translate(MessageReceivedEvent event, String[] args, String langTo) {
		String text = "";
		for(int i=2; i<args.length; i++) {
			text += args[i] + " ";
		}
		String response = controller.translate(langTo, text);
		
		if(response.contains("<!DOCTYPE html>")) {
			sendMessage(event, "Invalid target language. See https://cloud.google.com/translate/docs/languages for the full list of supported languages.");
			return;
		}
		else {
			sendMessage(event, "Translation result: " + response);
			return;
		}
	}
	
	/**
	 * Get a dictionary embed from the given guildName and word
	 * @param guildName Discord guild name
	 * @param word word to get definitions
	 * @return dictionaryEmbed a dictionary Embed
	 */
	private EmbedBuilder dictionary(String guildName, String word) {
		return controller.dictionary(guildName, word);
	}

	/**
	 * sends a message in discord event channel with the message given.
	 * @param event MessageReceivedEvent
	 * @param message message to send
	 */
	private void sendMessage(MessageReceivedEvent event, String message) {
		event.getChannel().sendTyping().queue();
		event.getChannel().sendMessage(message).queue();
		return;
	}

	/**
	 * Sends an embed in discord event channel with the embedBuilder given.
	 * @param event MessageReceivedEvent
	 * @param embed embedBuilder to send
	 */
	private void sendEmbedMessage(MessageReceivedEvent event, EmbedBuilder embed) {
		event.getChannel().sendTyping().queue();
		event.getChannel().sendMessageEmbeds(embed.build()).queue();
		return;
	}

}
