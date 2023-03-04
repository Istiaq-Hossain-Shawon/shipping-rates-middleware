package com.middleware.api.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "jt_express_request_tbl")
public class JtExpressRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "shipping_rates_type")
	private String shippingRatesType;
	
	@Column(name = "sender_postcode")
	private String senderPostcode;
	
	@Column(name = "receiver_postcode")
	private String receiverPostcode;
	
	@Column(name = "destination_country")
	private String destinationCountry;
	
	@Column(name = "shipping_type")
	private String shippingType;
	
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "length")
	private String length;
	
	@Column(name = "width")
	private String width;
	
	@Column(name = "height")
	private String height;	
	
	
	@Column(name = "item_value")
	private String itemValue;		
	 
	
	@CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Date createdAt;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getShippingRatesType() {
		return shippingRatesType;
	}


	public void setShippingRatesType(String shippingRatesType) {
		this.shippingRatesType = shippingRatesType;
	}


	public String getSenderPostcode() {
		return senderPostcode;
	}


	public void setSenderPostcode(String senderPostcode) {
		this.senderPostcode = senderPostcode;
	}


	public String getReceiverPostcode() {
		return receiverPostcode;
	}


	public void setReceiverPostcode(String receiverPostcode) {
		this.receiverPostcode = receiverPostcode;
	}


	public String getDestinationCountry() {
		return destinationCountry;
	}


	public void setDestinationCountry(String destinationCountry) {
		this.destinationCountry = destinationCountry;
	}


	public String getShippingType() {
		return shippingType;
	}


	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
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


	public String getItemValue() {
		return itemValue;
	}


	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}


	public Date getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	@Override
	public int hashCode() {
		return Objects.hash(createdAt, destinationCountry, height, id, itemValue, length, receiverPostcode,
				senderPostcode, shippingRatesType, shippingType, weight, width);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JtExpressRequest other = (JtExpressRequest) obj;
		return Objects.equals(createdAt, other.createdAt)
				&& Objects.equals(destinationCountry, other.destinationCountry) && Objects.equals(height, other.height)
				&& id == other.id && Objects.equals(itemValue, other.itemValue) && Objects.equals(length, other.length)
				&& Objects.equals(receiverPostcode, other.receiverPostcode)
				&& Objects.equals(senderPostcode, other.senderPostcode)
				&& Objects.equals(shippingRatesType, other.shippingRatesType)
				&& Objects.equals(shippingType, other.shippingType) && Objects.equals(weight, other.weight)
				&& Objects.equals(width, other.width);
	}


	@Override
	public String toString() {
		return "JtExpressRequest [id=" + id + ", shippingRatesType=" + shippingRatesType + ", senderPostcode="
				+ senderPostcode + ", receiverPostcode=" + receiverPostcode + ", destinationCountry="
				+ destinationCountry + ", shippingType=" + shippingType + ", weight=" + weight + ", length=" + length
				+ ", width=" + width + ", height=" + height + ", itemValue=" + itemValue + ", createdAt=" + createdAt
				+ "]";
	}

	 
	
}
