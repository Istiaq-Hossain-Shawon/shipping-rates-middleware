package com.middleware.api.service;

import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;

public abstract class RateRequestTemplate {
	
	public ShippingRateDto makeRateRequest(ShippingRateRequestDto shippingRequest) {
		

		if (isCRFTokenNeed()) {

			getCRFToken();

		}

		if (isAuthorizationTokenNeed()) {

			getAuthorizationToken();

		}

		String shippingRateResponse=PostExternalURL(shippingRequest);
		
		return ExtractRateFromResponse(shippingRateResponse);

	}
	
	public abstract void getCRFToken();
	public abstract void getAuthorizationToken();
	
	boolean isCRFTokenNeed() { return true; }
	boolean isAuthorizationTokenNeed() { return true; }
	
	public abstract String PostExternalURL(ShippingRateRequestDto shippingRequest);
	
	public abstract ShippingRateDto ExtractRateFromResponse(String shippingRateResponse);

	

}
