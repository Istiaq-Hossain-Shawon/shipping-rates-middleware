package com.middleware.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.ShippingRateRequestService;
import com.middleware.api.service.ShippingRateResponseService;
import com.middleware.api.service.CacheHandleShippingService;

@Service
public class CacheHandleShippingServiceImpl implements CacheHandleShippingService{


	@Autowired 
	private  ShippingRateRequestService shippingRateRequestService;
	
	@Autowired 
	private ShippingRateResponseService shippingRateResponseService;
	
	private final Logger logger = LoggerFactory.getLogger(CacheHandleShippingServiceImpl.class);

 
	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequestDto) {
		return shippingRateRequestService.saveRequest(shippingRateRequestDto);
	}

	public ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest, MiddlewareResponse responseDTO) {
		return shippingRateResponseService.saveResponse(shippingRateRequest, responseDTO);
	}

	public MiddlewareResponse requestCacheHandle(ShippingRateRequestDto shippingRequest) {
		try {

			ShippingRateRequest matchingRequestObject =shippingRateRequestService.filterShippingRateRequest(shippingRequest);
			if (matchingRequestObject != null) {
				Gson g = new Gson();
				MiddlewareResponse existingResponse = g.fromJson(
						matchingRequestObject.getShippingRateResponse().getDetailResponse(), MiddlewareResponse.class);
				return existingResponse;
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new MiddlewareResponse();
	}
	

}
