package com.eislyn.IAmNotARobot.domain;

/**
 * Stores data returned from the currency exchange api.
 * @author Eislyn
 * @since 18/10/2022
 */
public class Currency {
	private String lastUpdated;
	private String code; //target Currency returned from the api
	private double conversionRate;
	private double exchangedAmount;
	
	public String getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public double getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(double conversionRate) {
		this.conversionRate = conversionRate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public double getExchangedAmount() {
		return exchangedAmount;
	}
	public void setExchangedAmount(double exchangedAmount) {
		this.exchangedAmount = exchangedAmount;
	}
}
