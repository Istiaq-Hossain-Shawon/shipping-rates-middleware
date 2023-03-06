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
	private String length;
	
	@Column(name = "width")
	private String width;
	
	@Column(name = "height")
	private String height;
	
	@Column(name = "weight")
	private String weight;
	

	@Column(name = "goods_selected_type")
	private String goodsSelectedType;
	
	@CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
	
	@OneToOne(mappedBy = "shippingRateRequest")
    private ShippingRateResponse shippingRateResponse;

	
	
	public void setWeight(String weight) {
		this.weight = weight;
	}

	public void setGoodsSelectedType(String goodsSelectedType) {
		this.goodsSelectedType = goodsSelectedType;
	}

	public String getWeight() {
		return weight;
	}

	public String getGoodsSelectedType() {
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

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
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
