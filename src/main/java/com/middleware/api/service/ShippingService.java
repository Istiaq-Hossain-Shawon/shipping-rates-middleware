package com.middleware.api.service;

import java.util.Optional;

import com.middleware.api.model.CityLinkExpressRequest;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;

public interface ShippingService {
	MiddlewareResponse getShippingRate(ShippingRateRequestDto shippingRateRequest) ;
}
