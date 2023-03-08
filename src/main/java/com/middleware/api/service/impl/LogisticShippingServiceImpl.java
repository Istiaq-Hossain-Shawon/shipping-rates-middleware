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
import com.middleware.api.service.CacheHandleShippingService;
import com.middleware.api.service.LogisticShippingService;

@Service
public class LogisticShippingServiceImpl implements LogisticShippingService {

	 	
	private final Logger logger = LoggerFactory.getLogger(LogisticShippingServiceImpl.class);
	
	@Autowired 
	private CacheHandleShippingService cacheHandleShippingService;

	public LogisticShippingServiceImpl() {
		
	}	

	@Override
	public MiddlewareResponse getShippingRate(ShippingRateRequestDto shippingRequest) {

		var cacheResponse=cacheHandleShippingService.requestCacheHandle(shippingRequest);

		if (cacheResponse.getData() != null && cacheResponse.getData().size() > 0) {
			logger.info("Cache Data");
			logger.info(cacheResponse.getData().toString());
			return cacheResponse;
		}

		ShippingRateRequest shippingRateRequest = cacheHandleShippingService.saveRequest(shippingRequest);

		List<ShippingRateDto> data = new ArrayList<>();

		ShippingFactory courierFactory = new ShippingFactory();

		ShippingRate cityLinkShippingRate = courierFactory.getShippingRate(Courier.CITYLINK.getName());
		data.add(cityLinkShippingRate.getRate(shippingRequest));

		ShippingRate jtsShippingRate = courierFactory.getShippingRate(Courier.JTEXPRESS.getName());
		data.add(jtsShippingRate.getRate(shippingRequest));

		MiddlewareResponse responseDTO = ShippingRateUtil.createResponseSuccess();
		responseDTO.setData(data);

		cacheHandleShippingService.saveResponse(shippingRateRequest, responseDTO);

		return responseDTO;
	}

}
