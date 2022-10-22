package com.eislyn.IAmNotARobot.ui;

public class AboutEmbed extends EmbedTemplate{

	@Override
	public void setTitle() {
		embedBuilder.setTitle("__About__");
	}

	@Override
	public void setThumbanail() {
		embedBuilder.setThumbnail("https://img-9gag-fun.9cache.com/photo/apGgbv9_460s.jpg");
	}

	@Override
	public void setDescription() {
		embedBuilder.setDescription("----------------------------------------------------------------------------\r\n" + "Hi! My name is Eislyn and I am a Software Engineer from Malaysia. If you like this bot, you can check out more projects I made here - https://github.com/Eislyn2911" + "\r\n----------------------------------------------------------------------------");
	}

	@Override
	public void addField() {
		embedBuilder.addField("Programming Language Used","Java" + "\r\n----------------------------------------------------------------------------",false);
		embedBuilder.addField("Technologies Used","Maven, Git, JUnit" + "\r\n----------------------------------------------------------------------------",false);
		embedBuilder.addField("Tools Used", "Esclipse IDE" + "\r\n----------------------------------------------------------------------------", false);
	}

}