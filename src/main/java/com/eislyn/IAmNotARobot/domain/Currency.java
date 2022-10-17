package com.eislyn.IAmNotARobot.domain;

public class Currency {
	private String lastUpdated;
	private String code;
	private double conversionRate;
	private double convertedAmount;
	
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
	public double getConvertedAmount() {
		return convertedAmount;
	}
	public void setConvertedAmount(double convertedAmount) {
		this.convertedAmount = convertedAmount;
	}
}
