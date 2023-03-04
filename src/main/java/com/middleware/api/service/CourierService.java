package com.middleware.api.service;

import java.util.Optional;

import com.middleware.api.model.CityLinkExpressRequest;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.ResponseDTO;

public interface CourierService {
	ResponseDTO GetShippingRate(ShippingRateRequestDto shippingRateRequest) ;
}
