package com.middleware.api.model;

import java.util.Date;
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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(name = "parcel_weight")
	private String parcelWeight;
	
	@Column(name = "document_weight")
	private String documentWeight;	
	
	@Column(name = "selected_type")
	private String selectedType;
	
	@CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;
	
//	@OneToOne(mappedBy = "shippingRateRequest",fetch = FetchType.EAGER, optional = false)
//	@JsonIgnoreProperties("shippingRateRequest")
//    private ShippingRateResponse shippingRateResponse;

//	@OneToOne(mappedBy = "shippingRateRequest")
//    private ShippingRateResponse shippingRateResponse;
	
//	@OneToOne(mappedBy = "shippingRateRequest", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = false)
//  private ShippingRateResponse shippingRateResponse;
	
//	@OneToOne(mappedBy = "shippingRateRequest", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = false)
//  private ShippingRateResponse shippingRateResponse;
	
	@OneToOne(mappedBy = "shippingRateRequest")
    private ShippingRateResponse shippingRateResponse;
	
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

	public String getParcelWeight() {
		return parcelWeight;
	}

	public void setParcelWeight(String parcelWeight) {
		this.parcelWeight = parcelWeight;
	}

	public String getDocumentWeight() {
		return documentWeight;
	}

	public void setDocumentWeight(String documentWeight) {
		this.documentWeight = documentWeight;
	}

	public String getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(String selectedType) {
		this.selectedType = selectedType;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

//	@Override
//	public String toString() {
//		return "ShippingRateRequest [id=" + id + ", originCountry=" + originCountry + ", originState=" + originState
//				+ ", originPostcode=" + originPostcode + ", destinationCountry=" + destinationCountry
//				+ ", destinationState=" + destinationState + ", destinationPostcode=" + destinationPostcode
//				+ ", length=" + length + ", width=" + width + ", height=" + height + ", parcelWeight=" + parcelWeight
//				+ ", documentWeight=" + documentWeight + ", selectedType=" + selectedType + ", createdAt=" + createdAt
//				+ ", shippingRateResponse=" + shippingRateResponse + "]";
//	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(createdAt, destinationCountry, destinationPostcode, destinationState, documentWeight,
//				height, id, length, originCountry, originPostcode, originState, parcelWeight, selectedType,
//				shippingRateResponse, width);
//	}

//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		ShippingRateRequest other = (ShippingRateRequest) obj;
//		return Objects.equals(createdAt, other.createdAt)
//				&& Objects.equals(destinationCountry, other.destinationCountry)
//				&& Objects.equals(destinationPostcode, other.destinationPostcode)
//				&& Objects.equals(destinationState, other.destinationState)
//				&& Objects.equals(documentWeight, other.documentWeight) && Objects.equals(height, other.height)
//				&& id == other.id && Objects.equals(length, other.length)
//				&& Objects.equals(originCountry, other.originCountry)
//				&& Objects.equals(originPostcode, other.originPostcode)
//				&& Objects.equals(originState, other.originState) && Objects.equals(parcelWeight, other.parcelWeight)
//				&& Objects.equals(selectedType, other.selectedType)
//				&& Objects.equals(shippingRateResponse, other.shippingRateResponse)
//				&& Objects.equals(width, other.width);
//	}

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
