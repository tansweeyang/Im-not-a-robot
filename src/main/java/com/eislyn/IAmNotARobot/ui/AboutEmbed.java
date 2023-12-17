package com.eislyn.IAmNotARobot.ui;

/**
 * Creates a about embed using EmbedTemplate by overriding abstract methods.
 * @author Eislyn
 * @since 22/10/2022
 */
public class AboutEmbed extends EmbedTemplate{

	@Override
	public void setTitle() {
		embedBuilder.setTitle("__About__");
	}

	@Override
	public void setThumbnail() {
		embedBuilder.setThumbnail("https://img-9gag-fun.9cache.com/photo/apGgbv9_460s.jpg");
	}

	@Override
	public void setDescription() {
		embedBuilder.setDescription("""
                --------------------------------------------------------------------\r
                Hi! My name is Eislyn and I am a Software Engineer from Malaysia. If you like this bot, you can check out more projects I made here - https://github.com/Eislyn2911\r
                --------------------------------------------------------------------""");
	}

	@Override
	public void addField() {
		embedBuilder.addField("Programming Language Used","Java" + "\r\n--------------------------------------------------------------------",false);
		embedBuilder.addField("Technologies Used","Maven, Git, JUnit" + "\r\n--------------------------------------------------------------------",false);
		embedBuilder.addField("Tools Used", "Eclipse IDE" + "\r\n--------------------------------------------------------------------", false);
	}

}
