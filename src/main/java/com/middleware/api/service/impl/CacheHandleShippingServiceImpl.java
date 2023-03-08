package com.middleware.api.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.middleware.api.service.CacheHandleShippingService;
import com.middleware.api.service.LogisticShippingService;

@Service
public class CacheHandleShippingServiceImpl implements CacheHandleShippingService{


	@Autowired 
	public  ShippingRateRequestService shippingRateRequestService;
	
	@Autowired 
	public ShippingRateResponseService shippingRateResponseService;
	
	private final Logger logger = LoggerFactory.getLogger(CacheHandleShippingService.class);

	public CacheHandleShippingServiceImpl() {
		
	}	
 
	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequestDto) {
		return shippingRateRequestService.saveRequest(shippingRateRequestDto);
	}

	public ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest, MiddlewareResponse responseDTO) {
		return shippingRateResponseService.saveResponse(shippingRateRequest, responseDTO);
	}
	public ShippingRateRequest getRequestById(int id) {
		return shippingRateRequestService.getById(id);
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
