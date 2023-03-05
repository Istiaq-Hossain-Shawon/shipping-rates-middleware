package com.middleware.api.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.config.util.JtExpressToken;
import com.middleware.api.controller.ShippingRateController;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.service.CourierRate;

public class JtExpressRate  implements CourierRate{

	private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	@Override
	public ShippingRateDto getRate(ShippingRateRequestDto shippingRequest) {
		
		try {
			RestTemplate restTemplate= new RestTemplate();
			
			JtExpressToken token =getJtExpressToken(restTemplate);
	        
			StringBuilder requestBody = new StringBuilder();
			requestBody.append("_token="+token.getToken());
			requestBody.append("&shipping_rates_type="+shippingRequest.getShippingRatesType());
			requestBody.append("&sender_postcode="+shippingRequest.getOriginPostcode());
			requestBody.append("&receiver_postcode="+shippingRequest.getDestinationPostcode());
			requestBody.append("&destination_country="+shippingRequest.getDestinationCountry());
			requestBody.append("&shipping_type="+shippingRequest.getShippingType());
			requestBody.append("&weight="+shippingRequest.getParcelWeight());
			requestBody.append("&length="+shippingRequest.getLength());
			requestBody.append("&width="+shippingRequest.getWidth());
			requestBody.append("&height="+shippingRequest.getHeight());
			requestBody.append("&item_value="+shippingRequest.getItemValue());
			
			logger.info(token.getToken());
			
			logger.info(token.getCookie());
			
			System.out.println(requestBody.toString());

		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		    headers.set("X-Requested-With", "XMLHttpRequest");
		    headers.set("X-CSRF-TOKEN", token.getToken());
		    headers.set("csrf_token", token.getToken());
		    headers.set("XSRF-TOKEN", token.getCookie());
		    headers.add("Cookie", token.getCookie() );
		    
		    HttpEntity<String> request = new HttpEntity<String>(
		    		requestBody.toString(), headers);

		    
		   // RestTemplate restTemplate= new RestTemplate();
		    
		    String jtRestResponse = restTemplate
		            .postForObject(Courier.JTEXPRESS.getUrl(), request, String.class);
//	        System.out.println(jtRestResponse);
	        logger.info(jtRestResponse);
	        
	        Document doc = Jsoup.parse(jtRestResponse.toString());
	        
	        double rates=0;
	        
	        Element table = doc.select("table").get(0); 
	        Elements rows = table.select("tr");
	        if(shippingRequest.getSelectedType().equals(GoodTypes.PARCEL.getId())) {
	        	Element row = rows.get(3);
			    Elements cols = row.select("td");
			    rates=Double.parseDouble(cols.get(0).text());
			    System.out.print(rates);
	        }
	        else if(shippingRequest.getSelectedType().equals(GoodTypes.DOCUMENT.getId())) {
	        	Element row = rows.get(3);
			    Elements cols = row.select("td");
			    rates=Double.parseDouble(cols.get(1).text());
			    System.out.print(rates);
	        } 
	        return new ShippingRateDto(Courier.JTEXPRESS.getName(),rates);	
		
			
		} catch (Exception e) {
			logger.info(e.getMessage());	
	        
		}
		
		return new ShippingRateDto(Courier.JTEXPRESS.getName(), 0.0);		
	}
	
	
	public JtExpressToken getJtExpressToken(RestTemplate restTemplate) {
		
		try {
			
		    HttpHeaders headers = new HttpHeaders();
		   
		    HttpEntity<String> request = new HttpEntity<String>("", headers);
		    
		    //RestTemplate restTemplate= new RestTemplate();
		    
		    ResponseEntity<String> jtRestResponse = restTemplate
		            .getForEntity(Courier.JTEXPRESS.getUrl(), String.class);          
	        
	        Document doc = Jsoup.parse(jtRestResponse.toString());
	        
	        String tokenStr = doc.body()
	                .getElementsByAttributeValue("name", "_token")
	                .first()
	                .attributes()
	                .get("value");
	        System.out.println("token");
	        System.out.println(tokenStr);
	        
//	        System.out.println("Response: " + jtRestResponse.toString() + "\n");
	        HttpHeaders responseHeaders = jtRestResponse.getHeaders();
	        
	        List<String> responseList =responseHeaders.get(HttpHeaders.SET_COOKIE);
	        
	        String setCookie1=responseList.get(0);
	        String setCookie2=responseList.get(1);
	        
	        System.out.println(setCookie1);
	        System.out.println(setCookie2);
	        
	        String headerCookie=setCookie1+";"+setCookie2;

	        return new JtExpressToken(tokenStr,headerCookie);
			
		} catch (Exception e) {
			logger.info(e.getMessage());			
		}
		
		return new JtExpressToken("","");		
	} 

}
