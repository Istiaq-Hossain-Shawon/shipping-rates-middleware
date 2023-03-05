package com.middleware.api.service.impl;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Security.TrustStrategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.controller.ShippingRateController;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.factory.CourierFactory;
import com.middleware.api.model.CityLinkExpressRequest;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.response.ResponseDTO;
import com.middleware.api.service.CourierRate;
import com.middleware.api.service.CourierService;
 
@Service
public class CourierServiceImpl implements  CourierService
{
	  
	private ShippingRateRequestRepository shippingRateRequestRepository;	
	private ShippingRateResponseRepository shippingRateResponseRepository;	
	private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	 
	
	@Autowired
	public CourierServiceImpl(ShippingRateRequestRepository shippingRateRequestRepository,ShippingRateResponseRepository shippingRateResponseRepository) {
		this.shippingRateRequestRepository = shippingRateRequestRepository;
		this.shippingRateResponseRepository = shippingRateResponseRepository;
	}
	 
	private ResponseDTO requestCacheHandle(ShippingRateRequestDto shippingRequest) {
		try {
			List<ShippingRateRequest> existingShippingrequest=shippingRateRequestRepository.getShippingRateRequest(
					shippingRequest.getOriginCountry(),
					shippingRequest.getOriginState(),
					shippingRequest.getOriginPostcode(),
					shippingRequest.getDestinationCountry(),
					shippingRequest.getDestinationState(),
					shippingRequest.getDestinationPostcode(),
					shippingRequest.getLength(),
					shippingRequest.getWidth(),
					shippingRequest.getHeight(),
					shippingRequest.getParcelWeight(),
					shippingRequest.getDocumentWeight(),
					shippingRequest.getSelectedType()
					);

			System.out.println("Existing Shipping Request");
			for(var gadgets : existingShippingrequest){
			      System.out.println(gadgets.getOriginPostcode());
			}
			if(!existingShippingrequest.isEmpty()) {
				
				Gson g = new Gson();
				ResponseDTO existingResponse = g.fromJson(existingShippingrequest.get(0).getShippingRateResponse().getDetailResponse(), ResponseDTO.class);
				return existingResponse;
			}
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return new ResponseDTO();		
	}
	
	@Override
	public ResponseDTO GetShippingRate(ShippingRateRequestDto shippingRequest) {		
		
//		CourierFactory courierFactory = new CourierFactory();  
//		
//	    CourierRate jtsCourierRate = courierFactory.getCourier(Courier.JTEXPRESS.getName());  
//	    jtsCourierRate.getRate(shippingRequest);
//	    
//	    return ShippingRateUtil.createResponseSuccess();
		
		var cacheResponse=requestCacheHandle(shippingRequest);

		if(cacheResponse.getData()!=null && cacheResponse.getData().size()>0) {
			return cacheResponse;
		}		
		
		ShippingRateRequest shippingRateRequest=saveRequest(shippingRequest);
		
		List<ShippingRateDto> data=new ArrayList<>();
		
		CourierFactory courierFactory = new CourierFactory();
	    
        CourierRate cityLinkCourierRate = courierFactory.getCourier(Courier.CITYLINK.getName());      
        
        data.add(cityLinkCourierRate.getRate(shippingRequest));        
        
        	    
        CourierRate jtsCourierRate = courierFactory.getCourier(Courier.JTEXPRESS.getName());      
        
        data.add(jtsCourierRate.getRate(shippingRequest));		
		
		
		ResponseDTO responseDTO = ShippingRateUtil.createResponseSuccess();
				
		responseDTO.setData(data);
		
		Gson gson = new Gson();
		String responeString=gson.toJson(responseDTO, ResponseDTO.class);
		
		saveResponse(shippingRateRequest,responeString);
		
		return responseDTO;
	}
 
	private ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequestDto) {
		var shippingRateRequest = new ShippingRateRequest();
		BeanUtils.copyProperties(shippingRateRequestDto, shippingRateRequest);		
		shippingRateRequest=shippingRateRequestRepository.saveAndFlush(shippingRateRequest);
		return shippingRateRequest;
	}	
	private ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest,String response) {
		var shippingRateResponse = new ShippingRateResponse();
		shippingRateResponse.setShippingRateRequest(shippingRateRequest);
		shippingRateResponse.setDetailResponse(response);	
		shippingRateResponse=shippingRateResponseRepository.saveAndFlush(shippingRateResponse);
		return shippingRateResponse;
	}	
}
