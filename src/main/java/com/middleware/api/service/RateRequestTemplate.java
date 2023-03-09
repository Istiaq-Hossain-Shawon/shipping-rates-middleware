package com.middleware.api.service;

import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.ShippingRateDto;

public abstract class RateRequestTemplate {
	
	public ShippingRateDto makeRateRequest(ShippingRateRequestDto shippingRequest) {
		

		if (isCSRFTokenNeed()) {

			getCSRFToken();

		}

		if (isAuthorizationTokenNeed()) {

			getAuthorizationToken();

		}

		String shippingRateResponse= postExternalURL(shippingRequest);
		
		return extractRateFromResponse(shippingRateResponse);

	}
	
	public abstract void getCSRFToken();
	public abstract void getAuthorizationToken();
	
	public abstract boolean isCSRFTokenNeed();
	public abstract boolean isAuthorizationTokenNeed();
	
	public abstract String postExternalURL(ShippingRateRequestDto shippingRequest);
	
	public abstract ShippingRateDto extractRateFromResponse(String shippingRateResponse);

	

}
