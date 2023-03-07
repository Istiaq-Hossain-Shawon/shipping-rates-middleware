package com.middleware.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.service.RateRequestTemplate;

public class CityLinkRateRequestImpl extends RateRequestTemplate {
	private final Logger logger = LoggerFactory.getLogger(CityLinkRateRequestImpl.class);
	
	
	@Override
	public
	boolean isCSRFTokenNeed() {return false;}

	
	@Override
	public
	boolean isAuthorizationTokenNeed() {return false;}	

	
	@Override
	public void getAuthorizationToken() { /* TODO document why this method is empty */ }

	@Override
	public void getCSRFToken() { /* TODO document why this method is empty */ }
	
	public String PostExternalURL(ShippingRateRequestDto shippingRequest) {
		
		StringBuilder requestBody = new StringBuilder();		 
		requestBody.append("origin_country="+shippingRequest.getOriginCountry());
		requestBody.append("&origin_state="+shippingRequest.getOriginState());
		requestBody.append("&origin_postcode="+shippingRequest.getOriginPostcode());
		requestBody.append("&destination_country="+shippingRequest.getDestinationCountry());
		requestBody.append("&destination_state="+shippingRequest.getDestinationState());
		requestBody.append("&destination_postcode="+shippingRequest.getDestinationPostcode());
		requestBody.append("&length="+shippingRequest.getLength());
		requestBody.append("&width="+shippingRequest.getWidth());
		requestBody.append("&height="+shippingRequest.getHeight());
		requestBody.append("&selected_type="+shippingRequest.getGoodsSelectedType());
		
		if(shippingRequest.getGoodsSelectedType()==GoodTypes.PARCEL.getId()){
			requestBody.append("&parcel_weight="+shippingRequest.getWeight());
		}
		if(shippingRequest.getGoodsSelectedType()==GoodTypes.DOCUMENT.getId()){
			requestBody.append("&document_weight="+shippingRequest.getWeight());
		}
		
		logger.debug(requestBody.toString());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    HttpEntity<String> request = new HttpEntity<String>(
	    		requestBody.toString(), headers);

	    
	    RestTemplate restTemplate= new RestTemplate();
	    
	    String cityLinkRestResponse = restTemplate
	            .postForObject(Courier.CITYLINK.getUrl(), request, String.class);

	    logger.info(Courier.CITYLINK.getName());
        logger.info(cityLinkRestResponse);
        
        return cityLinkRestResponse;
		
	}

	@Override
	public ShippingRateDto ExtractRateFromResponse(String cityLinkRestResponse) {
		Gson gson = new Gson();
        CityLinkExpressResponse response = gson.fromJson(cityLinkRestResponse, CityLinkExpressResponse.class);

        int status = response.getReq().getStatus();
        Double rate=0.0;
        if(status==200) {
        	rate = response.getReq().getData().getRate();
        	logger.info(Courier.CITYLINK.getName());
            logger.info(rate.toString());
        }
        
        return new ShippingRateDto(Courier.CITYLINK.getName(),rate);
		
	}

	
}
