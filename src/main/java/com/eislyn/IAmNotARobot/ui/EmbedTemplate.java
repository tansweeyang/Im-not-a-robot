package com.eislyn.IAmNotARobot.ui;

import net.dv8tion.jda.api.EmbedBuilder;

/**
 * A template class for creating embeds, only need to call buildEmbed() when using created embed, applied template pattern.
 * @author Eislyn
 * @since 15/10/2022
 */
public abstract class EmbedTemplate {
	protected EmbedBuilder embedBuilder = new EmbedBuilder();
	
	/**
	 * Creates and builds the embed step by step, insert author name from ui classes that has Discord Events.
	 * @param authorName Discord guild name
	 * @return embedBuilder EmbedBuilder
	 */
	public final EmbedBuilder buildEmbed(String authorName) {
		if(authorName == null || authorName == "")
			throw new IllegalArgumentException();
		
		setAuthor(authorName);
		setTitle();
		setThumbnail();
		setDescription();
		addField();
		setFooter();
		buildBuilder();
		return embedBuilder;
	}

	private void setAuthor(String authorName) {
		embedBuilder.setAuthor(authorName);
	}
	
	public abstract void setTitle();
	public abstract void setThumbnail();
	public abstract void setDescription();
	public abstract void addField();

	private void setFooter() {
		embedBuilder.setFooter("Created by Eislyn", "https://images-ext-1.discordapp.net/external/Eaa_hmN5uh9n2NH8FMUTK1WgHa-5dTGo2Ain7s6VSI8/https/static.boredpanda.com/blog/wp-content/uploads/2020/07/expressive-cat-nana-1-21-5f16d009589a4__700.jpg");
	}
	
	private void buildBuilder() {
		embedBuilder.build();
	}
}
