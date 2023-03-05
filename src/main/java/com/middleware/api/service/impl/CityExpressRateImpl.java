package com.middleware.api.service.impl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.SSLUtils;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.controller.ShippingRateController;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.IShippingRate;
import com.middleware.api.service.RateRequestTemplate;

public class CityExpressRateImpl  implements IShippingRate {

	
	private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	
	
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
