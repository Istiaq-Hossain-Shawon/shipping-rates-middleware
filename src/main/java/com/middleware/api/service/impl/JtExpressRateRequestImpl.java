package com.middleware.api.service.impl;

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

import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.config.util.JtExpressToken;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.ShippingRateDto;
import com.middleware.api.service.RateRequestTemplate;

public class JtExpressRateRequestImpl extends RateRequestTemplate {
	private final Logger logger = LoggerFactory.getLogger(JtExpressRateRequestImpl.class);
	
	JtExpressToken jtExpressToken= new JtExpressToken();
	
	
	
	int selectedGoodTypes =GoodTypes.PARCEL.getId();
	
	@Override
	public boolean isCSRFTokenNeed() {return true;}

	@Override
	public boolean isAuthorizationTokenNeed() {return false;}

	public void getCSRFToken() {
		
		RestTemplate restTemplate= new RestTemplate();
		
	    ResponseEntity<String> jtRestResponse = restTemplate
	            .getForEntity(Courier.JTEXPRESS.getUrl(), String.class);          
        
        Document doc = Jsoup.parse(jtRestResponse.toString());
        
        String tokenStr = doc.body()
                .getElementsByAttributeValue("name", "_token")
                .first()
                .attributes()
                .get("value");
        
        HttpHeaders responseHeaders = jtRestResponse.getHeaders();
		String setCookie1="";
		String setCookie2="";
		List<String> responseList =responseHeaders.get(HttpHeaders.SET_COOKIE);
		if(responseList!=null && responseList.size()>=1){
			setCookie1=responseList.get(0);
			setCookie2=responseList.get(1);
		}
        String headerCookie=setCookie1+";"+setCookie2;
        jtExpressToken.setToken(tokenStr);
        jtExpressToken.setCookie(headerCookie);
		
	}

	@Override
	public void getAuthorizationToken() { /* Do not need Authorization token for JT Express.Only need CSRFToken */ }
	
	@Override
	public String postExternalURL(ShippingRateRequestDto shippingRequest) {
		
		RestTemplate restTemplate= new RestTemplate();		
        
		StringBuilder requestBody = new StringBuilder();
		requestBody.append("_token="+jtExpressToken.getToken());
		requestBody.append("&shipping_rates_type="+shippingRequest.getShippingRatesType());
		requestBody.append("&sender_postcode="+shippingRequest.getOriginPostcode());
		requestBody.append("&receiver_postcode="+shippingRequest.getDestinationPostcode());
		requestBody.append("&destination_country="+shippingRequest.getDestinationCountry());
		requestBody.append("&shipping_type="+shippingRequest.getShippingType());
		requestBody.append("&weight="+shippingRequest.getWeight());
		requestBody.append("&length="+shippingRequest.getLength());
		requestBody.append("&width="+shippingRequest.getWidth());
		requestBody.append("&height="+shippingRequest.getHeight());
		requestBody.append("&item_value="+shippingRequest.getItemValue());
		requestBody.append("&selected_type="+shippingRequest.getGoodsSelectedType());
		selectedGoodTypes=shippingRequest.getGoodsSelectedType();
		
		logger.info(jtExpressToken.getToken());
		
		logger.info(jtExpressToken.getCookie());		
			

	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    headers.set("X-Requested-With", "XMLHttpRequest");
	    headers.add("Cookie", jtExpressToken.getCookie() );
	    
	    HttpEntity<String> request = new HttpEntity<String>(
	    		requestBody.toString(), headers);
	    
	    String jtRestResponse = restTemplate.postForObject(Courier.JTEXPRESS.getUrl(), request, String.class);
	    logger.info(Courier.JTEXPRESS.getName());
	    logger.info(jtRestResponse);
        return jtRestResponse;
		
	}

	@Override
	public ShippingRateDto extractRateFromResponse(String jtRestResponse) {
		Document doc = Jsoup.parse(jtRestResponse);
        
        double rates=0;
        
        Element table = doc.select("table").get(0); 
        Elements rows = table.select("tr");
        if(selectedGoodTypes==GoodTypes.PARCEL.getId()) {
        	Element row = rows.get(3);
		    Elements cols = row.select("td");
		    rates=Double.parseDouble(cols.get(0).text());
		    logger.info(Courier.JTEXPRESS.getName());
	        logger.info(Double.toString(rates));
        }
        else if(selectedGoodTypes==GoodTypes.DOCUMENT.getId()) {
        	Element row = rows.get(3);
		    Elements cols = row.select("td");
		    rates=Double.parseDouble(cols.get(1).text());
		    logger.info(Courier.JTEXPRESS.getName(),jtRestResponse);
	        logger.info(Double.toString(rates));
        }
       
        return new ShippingRateDto(Courier.JTEXPRESS.getName(),rates);	
		
	}
	public JtExpressToken getJtExpressToken() {
		return jtExpressToken;
	}
	
}
