package com.middleware.api.request;


import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShippingRateRequestDto {

	
		
	private String originCountry;
	
	
	private String originState;
	
	@NotNull()
	private String originPostcode;
	
	
	private String destinationCountry;
	
	
	private String destinationState;
	
	
	private String destinationPostcode;
	 
	private float length;
	
 
	private float width;
	 
	private float height;
	
	 
	private float weight;	
		
	@Min(1)
	@Max(2) 
	private int goodsSelectedType;
		
	private String shippingRatesType;
	
	private String shippingType;
	
	private float itemValue;
	
	 

	public float getItemValue() {
		return itemValue;
	}

	public void setItemValue(float itemValue) {
		this.itemValue = itemValue;
	}

	public String getShippingRatesType() {
		return shippingRatesType;
	}

	public void setShippingRatesType(String shippingRatesType) {
		this.shippingRatesType = shippingRatesType;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getOriginCountry() {
		return originCountry;
	}

	public void setOriginCountry(String originCountry) {
		this.originCountry = originCountry;
	}

	public String getOriginState() {
		return originState;
	}

	public void setOriginState(String originState) {
		this.originState = originState;
	}

	public String getOriginPostcode() {
		return originPostcode;
	}

	public void setOriginPostcode(String originPostcode) {
		this.originPostcode = originPostcode;
	}

	public String getDestinationCountry() {
		return destinationCountry;
	}

	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}

	public String getDestinationState() {
		return destinationState;
	}

	public void setDestinationState(String destinationState) {
		this.destinationState = destinationState;
	}

	public String getDestinationPostcode() {
		return destinationPostcode;
	}

	public void setDestinationPostcode(String destinationPostcode) {
		this.destinationPostcode = destinationPostcode;
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destinationCountry, destinationPostcode, destinationState, height, length,
				originCountry, originPostcode, originState, weight, goodsSelectedType, width,itemValue);
	}
	//need default constructor for JSON Parsing
    public ShippingRateRequestDto()
    {

    }


	public ShippingRateRequestDto(String originCountry, String originState, String originPostcode,
			String destinationCountry, String destinationState, String destinationPostcode, float length, float width,
			float height, float weight, int selectedType,float itemValue) {
		super();
		this.originCountry = originCountry;
		this.originState = originState;
		this.originPostcode = originPostcode;
		this.destinationCountry = destinationCountry;
		this.destinationState = destinationState;
		this.destinationPostcode = destinationPostcode;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.goodsSelectedType = selectedType;
		this.itemValue = itemValue;
		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingRateRequestDto other = (ShippingRateRequestDto) obj;
		return Objects.equals(destinationCountry, other.destinationCountry)
				&& Objects.equals(destinationPostcode, other.destinationPostcode)
				&& Objects.equals(destinationState, other.destinationState)
				&& Objects.equals(height, other.height)
				&& Objects.equals(length, other.length) && Objects.equals(originCountry, other.originCountry)
				&& Objects.equals(originPostcode, other.originPostcode)
				&& Objects.equals(originState, other.originState) && Objects.equals(weight, other.weight)
				&& Objects.equals(goodsSelectedType, other.goodsSelectedType) && Objects.equals(width, other.width);
	}

	@Override
	public String toString() {
		return "ShippingRateRequestDto [originCountry=" + originCountry + ", originState=" + originState
				+ ", originPostcode=" + originPostcode + ", destinationCountry=" + destinationCountry
				+ ", destinationState=" + destinationState + ", destinationPostcode=" + destinationPostcode
				+ ", length=" + length + ", width=" + width + ", height=" + height + ", parcelWeight=" + weight
				+ ", goodsSelectedType=" + goodsSelectedType + "]";
	}

	public int getGoodsSelectedType() {
		return goodsSelectedType;
	}

	public void setGoodsSelectedType(int goodsSelectedType) {
		this.goodsSelectedType = goodsSelectedType;
	}
	
 
	
	
}
