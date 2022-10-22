package com.eislyn.IAmNotARobot.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.dv8tion.jda.api.EmbedBuilder;

public class AboutEmbedTest {
	@Test
	public void testBuildEmbed() {
		EmbedTemplate aboutEmbed = new AboutEmbed();
		EmbedBuilder actualAboutEmbedBuilder = aboutEmbed.buildEmbed("Server Name");
		
		EmbedBuilder expectedAboutEmbedBuilder = new EmbedBuilder();
		expectedAboutEmbedBuilder.setAuthor("Server Name");
		expectedAboutEmbedBuilder.setTitle("__About__");
		expectedAboutEmbedBuilder.setThumbnail("https://img-9gag-fun.9cache.com/photo/apGgbv9_460s.jpg");
		expectedAboutEmbedBuilder.setDescription("----------------------------------------------------------------------------\r\n" + "Hi! My name is Eislyn and I am a Software Engineer from Malaysia. If you like this bot, you can check out more projects I made here - https://github.com/Eislyn2911" + "\r\n----------------------------------------------------------------------------");
		expectedAboutEmbedBuilder.addField("Programming Language Used","Java" + "\r\n----------------------------------------------------------------------------",false);
		expectedAboutEmbedBuilder.addField("Technologies Used","Maven, Git, JUnit" + "\r\n----------------------------------------------------------------------------",false);
		expectedAboutEmbedBuilder.addField("Tools Used", "Esclipse IDE" + "\r\n----------------------------------------------------------------------------", false);
		expectedAboutEmbedBuilder.setFooter("Created by Eislyn", "https://images-ext-1.discordapp.net/external/Eaa_hmN5uh9n2NH8FMUTK1WgHa-5dTGo2Ain7s6VSI8/https/static.boredpanda.com/blog/wp-content/uploads/2020/07/expressive-cat-nana-1-21-5f16d009589a4__700.jpg");
	
		assertEquals(expectedAboutEmbedBuilder.build().toData().toPrettyString(), actualAboutEmbedBuilder.build().toData().toPrettyString());
	}

}
