package com.middleware.api.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.controller.ShippingRateController;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.response.ResponseDTO;
import com.middleware.api.service.CourierRate;

public class CityExpressRate  implements CourierRate {

	private ShippingRateRequestRepository shippingRateRequestRepository;	
	private ShippingRateResponseRepository shippingRateResponseRepository;	
	private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	@Autowired
	RestTemplate restTemplate;
	@Override
	public ShippingRateDto getRate(ShippingRateRequestDto shippingRequest) {

		
		ShippingRateDto shippingRateDto=GetCityLinkRates(shippingRequest);
		
		return shippingRateDto;
//		data.add(shippingRateDto);
		
		
		//
//		ResponseDTO responseDTO = ShippingRateUtil.createResponseSuccess();
//				
//		responseDTO.setData(data);
//		
//		Gson gson = new Gson();
//		String responeString=gson.toJson(responseDTO, ResponseDTO.class);
//		SaveResponse(shippingRateRequest,responeString);
		
	}
	private ShippingRateDto GetCityLinkRates(ShippingRateRequestDto shippingRequest) {
		
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
		requestBody.append("&selected_type="+shippingRequest.getSelectedType());
		requestBody.append("&parcel_weight="+shippingRequest.getParcelWeight());
		requestBody.append("&document_weight="+shippingRequest.getDocumentWeight());
		logger.info(requestBody.toString());

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

	    HttpEntity<String> request = new HttpEntity<String>(
	    		requestBody.toString(), headers);
	    String url = "https://www.citylinkexpress.com/wp-json/wp/v2/getShippingRate";
	    String cityLinkRestResponse = restTemplate
	            .postForObject(url, request, String.class);
        System.out.println(cityLinkRestResponse);
        
        Gson gson = new Gson();
        CityLinkExpressResponse response = gson.fromJson(cityLinkRestResponse, CityLinkExpressResponse.class);

        int status = response.getReq().getStatus();
        Double rate=0.0;
        if(status==200) {
        	rate = response.getReq().getData().getRate();
        }
        return new ShippingRateDto(Courier.CITYLINK.getName(),rate);
	   }

}
