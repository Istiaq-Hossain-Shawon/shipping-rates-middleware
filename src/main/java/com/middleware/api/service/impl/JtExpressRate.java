package com.middleware.api.service.impl;

import com.middleware.api.config.util.Courier;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.service.CourierRate;

public class JtExpressRate  implements CourierRate{

	@Override
	public ShippingRateDto getRate(ShippingRateRequestDto shippingRequest) {
		return new ShippingRateDto(Courier.JTEXPRESS.getName(), 0.0);		
	}

}
