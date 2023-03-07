package com.middleware.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "shipping_rate_response_tbl")
public class ShippingRateResponse {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.MERGE )
    @JoinColumn(name = "request_id",nullable = false)
    private ShippingRateRequest shippingRateRequest;
	
	@Column(name = "detail_response")
	private String detailResponse;
	

	public String getDetailResponse() {
		return detailResponse;
	}

	public void setDetailResponse(String detailResponse) {
		this.detailResponse = detailResponse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ShippingRateRequest getShippingRateRequest() {
		return shippingRateRequest;
	}

	public void setShippingRateRequest(ShippingRateRequest shippingRateRequest) {
		this.shippingRateRequest = shippingRateRequest;
	}
 
	public ShippingRateResponse() {
		super();
	}	

	
	
}
