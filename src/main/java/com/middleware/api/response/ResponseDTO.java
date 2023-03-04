package com.middleware.api.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.middleware.api.dto.ShippingRateDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO
{	
	private List<ShippingRateDto> data;

	public List<ShippingRateDto> getData() {
		return data;
	}

	public void setData(List<ShippingRateDto> data) {
		this.data = data;
	}
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
