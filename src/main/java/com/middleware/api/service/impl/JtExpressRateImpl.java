package com.middleware.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.middleware.api.config.util.Courier;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.service.IShippingRate;
import com.middleware.api.service.RateRequestTemplate;

public class JtExpressRateImpl  implements IShippingRate{

	private final Logger logger = LoggerFactory.getLogger(JtExpressRateImpl.class);
	@Override
	public ShippingRateDto getRate(ShippingRateRequestDto shippingRequest) {
		try {
			RateRequestTemplate jtExpressRateRequestImpl = new JtExpressRateRequestImpl();
			return jtExpressRateRequestImpl.makeRateRequest(shippingRequest);	
		}catch (Exception e) {
			logger.info(e.getMessage());
			return new ShippingRateDto(Courier.JTEXPRESS.getName(),0.0);
		}
				
	} 

}
