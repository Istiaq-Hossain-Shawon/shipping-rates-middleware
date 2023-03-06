package com.middleware.api.service;

import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;

public interface  ShippingRate {
	ShippingRateDto getRate(ShippingRateRequestDto shippingRequest);
}
