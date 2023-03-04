package com.middleware.api.request;


import java.util.Objects;



public class ShippingRateRequestDto {

		
	private String originCountry;
	
	
	private String originState;
	
	
	private String originPostcode;
	
	
	private String destinationCountry;
	
	
	private String destinationState;
	
	
	private String destinationPostcode;
	
	
	private String length;
	
	
	private String width;
	
	
	private String height;
	
	
	private String parcelWeight;
	
	
	private String documentWeight;	
	 
	private String selectedType;
	
	

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

	@Override
	public int hashCode() {
		return Objects.hash(destinationCountry, destinationPostcode, destinationState, documentWeight, height, length,
				originCountry, originPostcode, originState, parcelWeight, selectedType, width);
	}
	//need default constructor for JSON Parsing
    public ShippingRateRequestDto()
    {

    }

	public ShippingRateRequestDto(String originCountry, String originState, String originPostcode,
			String destinationCountry, String destinationState, String destinationPostcode, String length, String width,
			String height, String parcelWeight, String documentWeight, String selectedType) {
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
		this.parcelWeight = parcelWeight;
		this.documentWeight = documentWeight;
		this.selectedType = selectedType;
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
				&& Objects.equals(documentWeight, other.documentWeight) && Objects.equals(height, other.height)
				&& Objects.equals(length, other.length) && Objects.equals(originCountry, other.originCountry)
				&& Objects.equals(originPostcode, other.originPostcode)
				&& Objects.equals(originState, other.originState) && Objects.equals(parcelWeight, other.parcelWeight)
				&& Objects.equals(selectedType, other.selectedType) && Objects.equals(width, other.width);
	}

	@Override
	public String toString() {
		return "CityLinkExpressRequestDto [originCountry=" + originCountry + ", originState=" + originState
				+ ", originPostcode=" + originPostcode + ", destinationCountry=" + destinationCountry
				+ ", destinationState=" + destinationState + ", destinationPostcode=" + destinationPostcode
				+ ", length=" + length + ", width=" + width + ", height=" + height + ", parcelWeight=" + parcelWeight
				+ ", documentWeight=" + documentWeight + ", selectedType=" + selectedType + "]";
	}
	
 
	
	
}
