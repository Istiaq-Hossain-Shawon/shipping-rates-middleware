package com.middleware.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.factory.ShippingFactory;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.response.ShippingRateDto;
import com.middleware.api.service.ShippingRate;
import com.middleware.api.service.CacheHandleShippingService;
import com.middleware.api.service.LogisticShippingService;

@Service
public class LogisticShippingServiceImpl implements LogisticShippingService {
	@Autowired
	private CacheHandleShippingService cacheHandleShippingService;


	@Override
	public MiddlewareResponse getShippingRate(ShippingRateRequestDto shippingRequest) {

		var cacheResponse=cacheHandleShippingService.requestCacheHandle(shippingRequest);

		if (cacheResponse.getData() != null && !cacheResponse.getData().isEmpty()) {
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
