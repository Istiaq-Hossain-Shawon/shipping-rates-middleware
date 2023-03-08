package com.middleware.api.service;

import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;

public interface ShippingRateRequestService {
	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequest) ;
	public ShippingRateRequest filterShippingRateRequest(ShippingRateRequestDto shippingRateRequest) ;
	
	public ShippingRateRequest getById(int id) ;
}
