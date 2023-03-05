package com.middleware.api.service;

import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;

public interface  IShippingRate {
	ShippingRateDto getRate(ShippingRateRequestDto shippingRequest);
}
