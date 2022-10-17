package com.eislyn.IAmNotARobot.domain;

public class CurrencyExchangeEmbed extends EmbedTemplate{

	private String baseCurrency;
	private String targetCurrency;
	private double amountToExchange;
	private Currency currency;
	
	public CurrencyExchangeEmbed(String baseCurrency, String targetCurrency, double amountToExchange, Currency currency) {
		this.baseCurrency = baseCurrency;
		this.targetCurrency = targetCurrency;
		this.amountToExchange = amountToExchange;
		this.currency = currency;
	}
	
	@Override
	public void setTitle() {
		embedBuilder.setTitle("Currency Exchange");
	}

	@Override
	public void setDescription() {
		embedBuilder.setDescription("Converted from " + baseCurrency + " to " + targetCurrency);
	}

	@Override
	public void addField() {
		embedBuilder.addField("Amount to convert", amountToExchange + baseCurrency, false);
		embedBuilder.addField("Converted amount", currency.getConvertedAmount() + currency.getCode(), false);
		embedBuilder.addField("Last updated", currency.getLastUpdated(), false);
	}
}
