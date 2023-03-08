package com.middleware.api.service;

import javax.transaction.Transactional;

import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;

public interface CacheHandleShippingService {
	@Transactional
	public MiddlewareResponse requestCacheHandle(ShippingRateRequestDto shippingRequest);
	@Transactional
	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRequest);
	@Transactional
	public ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest, MiddlewareResponse responseDTO);
	
	@Transactional
	public ShippingRateRequest getRequestById(int id);
}
