package com.middleware.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.middleware.api.config.util.Courier;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.service.ShippingRate;
import com.middleware.api.service.RateRequestTemplate;

public class CityExpressRateImpl  implements ShippingRate {

	
	private final Logger logger = LoggerFactory.getLogger(CityExpressRateImpl.class);
	
	
	@Override
	public ShippingRateDto getRate(ShippingRateRequestDto shippingRequest) {		
		
		try {
			 RateRequestTemplate cityLinkRateRequest = new CityLinkRateRequestImpl();
			 return cityLinkRateRequest.makeRateRequest(shippingRequest);	
		} catch (Exception e) {
			logger.info(e.getMessage());
			return new ShippingRateDto(Courier.CITYLINK.getName(),0.0);
		}			
		 
	}
	 	
		

}
