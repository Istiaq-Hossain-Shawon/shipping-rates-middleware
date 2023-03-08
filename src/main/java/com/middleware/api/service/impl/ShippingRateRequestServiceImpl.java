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
import com.middleware.api.service.LogisticShippingService;

@Service
public class ShippingRateRequestServiceImpl implements ShippingRateRequestService{

	private ShippingRateRequestRepository shippingRateRequestRepository;
	private ShippingRateResponseRepository shippingRateResponseRepository;
	private final Logger logger = LoggerFactory.getLogger(ShippingRateRequestServiceImpl.class);

	public ShippingRateRequestServiceImpl(ShippingRateRequestRepository shippingRateRequestRepository,
			ShippingRateResponseRepository shippingRateResponseRepository) {
		this.shippingRateRequestRepository = shippingRateRequestRepository;
		this.shippingRateResponseRepository = shippingRateResponseRepository;
	}	
 
	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequestDto) {
		var shippingRateRequest = new ShippingRateRequest();
		BeanUtils.copyProperties(shippingRateRequestDto, shippingRateRequest);
		shippingRateRequest = shippingRateRequestRepository.saveAndFlush(shippingRateRequest);
		return shippingRateRequest;
	}
	public ShippingRateRequest getById(int id) {
		return shippingRateRequestRepository.findById(id).get();		
	}
	
	
	
	public ShippingRateRequest filterShippingRateRequest(ShippingRateRequestDto shippingRequest) {
		List<ShippingRateRequest> existingShippingrequestList = shippingRateRequestRepository
				.getByOriginPostcodeAndDestinationPostcode(shippingRequest.getOriginPostcode(),
						shippingRequest.getDestinationPostcode());
		
		ShippingRateRequest matchingRequestObject = existingShippingrequestList.stream()
				.filter(p -> p.getOriginCountry().equals(shippingRequest.getOriginCountry()))
				.filter(p -> p.getOriginState().equals(shippingRequest.getOriginState()))
				.filter(p -> p.getOriginPostcode().equals(shippingRequest.getOriginPostcode()))
				.filter(p -> p.getDestinationCountry().equals(shippingRequest.getDestinationCountry()))
				.filter(p -> p.getDestinationState().equals(shippingRequest.getDestinationState()))
				.filter(p -> p.getDestinationPostcode().equals(shippingRequest.getDestinationPostcode()))
				.filter(p -> p.getLength() == shippingRequest.getLength())
				.filter(p -> p.getWidth() == shippingRequest.getWidth())
				.filter(p -> p.getHeight() == shippingRequest.getHeight())
				.filter(p -> p.getWeight() == shippingRequest.getWeight())
				.filter(p -> p.getGoodsSelectedType() == shippingRequest.getGoodsSelectedType())
				.filter(p -> p.getShippingRatesType().equals(shippingRequest.getShippingRatesType()))
				.filter(p -> p.getItemValue() == shippingRequest.getItemValue())
				.filter(p -> p.getShippingType().equals(shippingRequest.getShippingType()))
				.sorted(Comparator.comparing(ShippingRateRequest::getCreatedAt).reversed()).findFirst().orElse(null);		
	
		return matchingRequestObject;
	}
}
