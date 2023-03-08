package com.middleware.api.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.factory.ShippingFactory;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.ShippingRate;
import com.middleware.api.service.ShippingRateRequestService;
import com.middleware.api.service.ShippingRateResponseService;
import com.middleware.api.service.LogisticShippingService;

@Service
public class ShippingRateResponseServiceImpl implements ShippingRateResponseService{

	 
	private ShippingRateResponseRepository shippingRateResponseRepository;
	private final Logger logger = LoggerFactory.getLogger(ShippingRateResponseServiceImpl.class);

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
