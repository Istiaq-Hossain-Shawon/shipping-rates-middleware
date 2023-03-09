package com.middleware.api.service;

import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.ShippingRateDto;

public interface  ShippingRate {
	ShippingRateDto getRate(ShippingRateRequestDto shippingRequest);
}
