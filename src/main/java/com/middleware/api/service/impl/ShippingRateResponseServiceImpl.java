package com.middleware.api.service.impl;

import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.ShippingRateResponseService;

@Service
public class ShippingRateResponseServiceImpl implements ShippingRateResponseService{

	 
	private ShippingRateResponseRepository shippingRateResponseRepository;

	public ShippingRateResponseServiceImpl(ShippingRateResponseRepository shippingRateResponseRepository) {
		 
		this.shippingRateResponseRepository = shippingRateResponseRepository;
	}

	@Override
	public ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest, MiddlewareResponse responseDTO) {
		Gson gson = new Gson();
		String responeString = gson.toJson(responseDTO, MiddlewareResponse.class);

		var shippingRateResponse = new ShippingRateResponse();
		shippingRateResponse.setShippingRateRequest(shippingRateRequest);
		shippingRateResponse.setDetailResponse(responeString);
		shippingRateResponse = shippingRateResponseRepository.saveAndFlush(shippingRateResponse);
		return shippingRateResponse;
	}	
  
}
