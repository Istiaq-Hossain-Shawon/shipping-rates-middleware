package com.middleware.api.service;

import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;

public interface LogisticShippingService {
	MiddlewareResponse getShippingRate(ShippingRateRequestDto shippingRateRequest) ;
}
