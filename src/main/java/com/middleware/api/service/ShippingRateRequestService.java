package com.middleware.api.service;

import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;

public interface ShippingRateRequestService {
	ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequest) ;
	ShippingRateRequest filterShippingRateRequest(ShippingRateRequestDto shippingRateRequest) ;
}
