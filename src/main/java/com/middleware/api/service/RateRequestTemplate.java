package com.middleware.api.service;

import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;

public abstract class RateRequestTemplate {
	
	public ShippingRateDto makeRateRequest(ShippingRateRequestDto shippingRequest) {
		

		if (isCSRFTokenNeed()) {

			getCSRFToken();

		}

		if (isAuthorizationTokenNeed()) {

			getAuthorizationToken();

		}

		String shippingRateResponse=PostExternalURL(shippingRequest);
		
		return ExtractRateFromResponse(shippingRateResponse);

	}
	
	public abstract void getCSRFToken();
	public abstract void getAuthorizationToken();
	
	public abstract boolean isCSRFTokenNeed();
	public abstract boolean isAuthorizationTokenNeed();
	
	public abstract String PostExternalURL(ShippingRateRequestDto shippingRequest);
	
	public abstract ShippingRateDto ExtractRateFromResponse(String shippingRateResponse);

	

}