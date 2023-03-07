package com.middleware.api.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "shipping_rate_request_tbl")
public class ShippingRateRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "origin_country")
	private String originCountry;
	
	@Column(name = "origin_state")
	private String originState;
	
	@Column(name = "origin_postcode")
	private String originPostcode;
	
	@Column(name = "destination_country")
	private String destinationCountry;
	
	@Column(name = "destination_state")
	private String destinationState;
	
	@Column(name = "destination_postcode")
	private String destinationPostcode;
	
	@Column(name = "length")
	private float length;
	
	@Column(name = "width")
	private float width;
	
	@Column(name = "height")
	private float height;
	
	@Column(name = "weight")
	private float weight;
	

	@Column(name = "goods_selected_type")
	private int goodsSelectedType;
	
	@CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
	
	@Column(name = "shipping_rates_type")
	private String shippingRatesType;
	
	@Column(name = "item_value")
	private float itemValue;
	
	@Column(name = "shipping_type")
	private String shippingType;
	
	
	
	public String getShippingRatesType() {
		return shippingRatesType;
	}

	public void setShippingRatesType(String shippingRatesType) {
		this.shippingRatesType = shippingRatesType;
	}

	public float getItemValue() {
		return itemValue;
	}

	public void setItemValue(float itemValue) {
		this.itemValue = itemValue;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}
	@OneToOne(mappedBy = "shippingRateRequest")
    private ShippingRateResponse shippingRateResponse;

	
	
	public void setWeight(float weight) {
		this.weight = weight;
	}

	public void setGoodsSelectedType(int goodsSelectedType) {
		this.goodsSelectedType = goodsSelectedType;
	}

	public float getWeight() {
		return weight;
	}

	public int getGoodsSelectedType() {
		return goodsSelectedType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	 

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
 

	public ShippingRateResponse getShippingRateResponse() {
		return shippingRateResponse;
	}

	public void setShippingRateResponse(ShippingRateResponse shippingRateResponse) {
		this.shippingRateResponse = shippingRateResponse;
	}
	public ShippingRateRequest() {
		super();
	}	

	
	
	
}
