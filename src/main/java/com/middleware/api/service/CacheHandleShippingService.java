package com.middleware.api.service;

import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;

public interface CacheHandleShippingService {
	MiddlewareResponse requestCacheHandle(ShippingRateRequestDto shippingRequest);
	ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRequest);
	ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest, MiddlewareResponse responseDTO);
}
