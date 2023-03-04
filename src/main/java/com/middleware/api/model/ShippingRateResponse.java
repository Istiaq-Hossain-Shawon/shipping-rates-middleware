package com.middleware.api.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
//	
//	@OneToOne(fetch = FetchType.EAGER, optional = false)
//    @JoinColumn(name = "request_id", referencedColumnName = "id")
//    private ShippingRateRequest shippingRateRequest;
	
	@OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "request_id", referencedColumnName = "id")
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

	@Override
	public int hashCode() {
		return Objects.hash(id, shippingRateRequest);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShippingRateResponse other = (ShippingRateResponse) obj;
		return id == other.id && Objects.equals(shippingRateRequest, other.shippingRateRequest);
	}

	@Override
	public String toString() {
		return "ShippingRateResponse [id=" + id + ", shippingRateRequest=" + shippingRateRequest + "]";
	}

	public ShippingRateResponse(int id, ShippingRateRequest shippingRateRequest) {
		super();
		this.id = id;
		this.shippingRateRequest = shippingRateRequest;
	}
	public ShippingRateResponse() {
		 
	}
	
	
}
