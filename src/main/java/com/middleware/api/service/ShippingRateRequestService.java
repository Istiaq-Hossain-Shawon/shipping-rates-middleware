package com.middleware.api.service;

import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;

public interface ShippingRateRequestService {
	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequest) ;
	public ShippingRateRequest filterShippingRateRequest(ShippingRateRequestDto shippingRateRequest) ;
}
