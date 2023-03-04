package com.middleware.api.dto;


public class ShippingRateDto {

	private String courier;
	
	private Double rate;

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Override
	public String toString() {
		return "ShippingRateDto [courier=" + courier + ", rate=" + rate + "]";
	}

	public ShippingRateDto(String courier, Double rate) {
		super();
		this.courier = courier;
		this.rate = rate;
	}	
	
}
