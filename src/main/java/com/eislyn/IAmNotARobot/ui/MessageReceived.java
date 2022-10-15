package com.eislyn.IAmNotARobot.ui;

import java.util.EventListener;

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
			if (args.length > 1)
				sendMessage(event, "Invalid help command. Type ``e!help`` to get the help menu.");
			else {
				help(event, event.getGuild().getName());
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
	}

	/**
	 * Communicates with controller to print out help menu.
	 * @param event MessageReceivedEvent
	 * @param guildName Discord guild name
	 */
	private void help(MessageReceivedEvent event, String guildName) {
		EmbedBuilder embedBuilder = controller.help(guildName);
		sendEmbedMessage(event, embedBuilder);
	}
	
	/**
	 * Combine array of input to text and communicates with controller to translate the text.
	 * @param event MessageReceivedEvent
	 * @param args array of input after the prefix and command
	 * @param langTo Target language
	 * @param text Text to translate
	 */
	private void translate(MessageReceivedEvent event, String[] args, String langTo) {
		String text = null;
		for(int i=2; i<args.length; i++) {
			text += args[i] + " ";
		}
		String response = controller.translate(langTo, text);
		sendMessage(event, "Translation result: " + response);
	}

	private void sendMessage(MessageReceivedEvent event, String message) {
		event.getChannel().sendTyping().queue();
		event.getChannel().sendMessage(message).queue();
	}

	private void sendEmbedMessage(MessageReceivedEvent event, EmbedBuilder embed) {
		event.getChannel().sendTyping().queue();
		event.getChannel().sendMessageEmbeds(embed.build()).queue();
	}

}
