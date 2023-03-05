package com.middleware.api.service.impl;

import java.util.ArrayList;
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
import com.middleware.api.service.IShippingRate;
import com.middleware.api.service.ILogisticShippingService;
 
@Service
public class LogisticShippingServiceImpl implements  ILogisticShippingService
{
	  
	private ShippingRateRequestRepository shippingRateRequestRepository;	
	private ShippingRateResponseRepository shippingRateResponseRepository;	
	private final Logger logger = LoggerFactory.getLogger(LogisticShippingServiceImpl.class);
	 
	
	
	public LogisticShippingServiceImpl(ShippingRateRequestRepository shippingRateRequestRepository,ShippingRateResponseRepository shippingRateResponseRepository) {
		this.shippingRateRequestRepository = shippingRateRequestRepository;
		this.shippingRateResponseRepository = shippingRateResponseRepository;
	}
	 
	private MiddlewareResponse requestCacheHandle(ShippingRateRequestDto shippingRequest) {
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
					shippingRequest.getGoodsSelectedType()
					);

			System.out.println("Existing Shipping Request");
			for(var gadgets : existingShippingrequest){
			      System.out.println(gadgets.getOriginPostcode());
			}
			if(!existingShippingrequest.isEmpty()) {
				
				Gson g = new Gson();
				MiddlewareResponse existingResponse = g.fromJson(existingShippingrequest.get(0).getShippingRateResponse().getDetailResponse(), MiddlewareResponse.class);
				return existingResponse;
			}
			
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return new MiddlewareResponse();		
	}
	
	@Override
	public MiddlewareResponse getShippingRate(ShippingRateRequestDto shippingRequest) {		
		
 		
//		var cacheResponse=requestCacheHandle(shippingRequest);
//
//		if(cacheResponse.getData()!=null && cacheResponse.getData().size()>0) {
//			return cacheResponse;
//		}		
		
		ShippingRateRequest shippingRateRequest=saveRequest(shippingRequest);
		
		List<ShippingRateDto> data=new ArrayList<>();
		
		ShippingFactory courierFactory = new ShippingFactory();
	    
        IShippingRate cityLinkShippingRate = courierFactory.getShippingRate(Courier.CITYLINK.getName());        
        data.add(cityLinkShippingRate.getRate(shippingRequest)); 
        	    
        IShippingRate jtsShippingRate = courierFactory.getShippingRate(Courier.JTEXPRESS.getName());        
        data.add(jtsShippingRate.getRate(shippingRequest));			
		
		MiddlewareResponse responseDTO = ShippingRateUtil.createResponseSuccess();				
		responseDTO.setData(data);		
		
		saveResponse(shippingRateRequest,responseDTO);
		
		return responseDTO;
	}
 
	private ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequestDto) {
		var shippingRateRequest = new ShippingRateRequest();
		BeanUtils.copyProperties(shippingRateRequestDto, shippingRateRequest);		
		shippingRateRequest=shippingRateRequestRepository.saveAndFlush(shippingRateRequest);
		return shippingRateRequest;
	}	
	private ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest,MiddlewareResponse responseDTO) {
		
		Gson gson = new Gson();
		String responeString=gson.toJson(responseDTO, MiddlewareResponse.class);
		
		var shippingRateResponse = new ShippingRateResponse();
		shippingRateResponse.setShippingRateRequest(shippingRateRequest);
		shippingRateResponse.setDetailResponse(responeString);	
		shippingRateResponse=shippingRateResponseRepository.saveAndFlush(shippingRateResponse);
		return shippingRateResponse;
	}	
}
