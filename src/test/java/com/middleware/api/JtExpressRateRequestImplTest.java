package com.middleware.api;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.config.util.JtExpressToken;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.response.JTExpressErrorResponse;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.ShippingRateRequestService;
import com.middleware.api.service.ShippingRateResponseService;
import com.middleware.api.service.impl.CityLinkRateRequestImpl;
import com.middleware.api.service.impl.JtExpressRateRequestImpl;

import net.minidev.json.JSONObject;

@SpringBootTest
@EnableTransactionManagement 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = true)
@ActiveProfiles("test")
 class JtExpressRateRequestImplTest {
	
	private JtExpressRateRequestImpl jtExpressRateRequestImpl= new JtExpressRateRequestImpl();	

	
	@Test
	@Order(1)  
	void getCSRFToken_When_RequestTheJTExpressURL_ShouldGetCSRFTokenFromPage() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("50500");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("50088");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);
		
		jtExpressRateRequestImpl.getCSRFToken();


		boolean resutl=!jtExpressRateRequestImpl.getJtExpressToken().getToken().isEmpty();
		Assertions.assertThat(resutl).isTrue();

		boolean resutl2=!jtExpressRateRequestImpl.getJtExpressToken().getCookie().isEmpty();
		Assertions.assertThat(resutl2).isTrue();


		
	}
	
	@Test
	@Order(2)  
	void postExternalURLWithoutCSRFToken_When_PostJtExpressURLWithoutCSRFToken_Then_ShouldReturnCSRFTokenMismatch() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("50500");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("50088");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);			
		
		try {
			jtExpressRateRequestImpl.postExternalURL(shippingRateRequestDto);
			
		} catch (HttpClientErrorException e) {
			System.out.print(e.getMessage());
			String message="419 unknown status: [{\n"
					+ "    \"message\": \"CSRF token mismatch.\"\n"
					+ "}]";
			boolean result=e.getMessage().equals(message);
			Assertions.assertThat(result).isTrue();

		}			
		
	}
	
	@Test
	@Order(3)  
	void postExternalURL_When_ValidRequestIsUsed_Then_ShouldReturnValidJTExpressLinkResponse() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("50500");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("50088");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);

		jtExpressRateRequestImpl.getCSRFToken();	
		
		var jtRestResponse=jtExpressRateRequestImpl.postExternalURL(shippingRateRequestDto);
		
		if(!isValidJson(jtRestResponse)) {
			Document doc = Jsoup.parse(jtRestResponse.toString());        
	        
	        Element table = doc.select("table").get(0);     
	        Elements rows = table.select("tr");
			boolean result=!rows.isEmpty();
			Assertions.assertThat(result).isTrue();
		}		
		
	}
	@Test
	@Order(4)  
	void postExternalURL_When_InvalidPostCodeIsUsed_Then_ShouldReturnStatusFail() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("999994");//Invalid PostCode
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("99999");//Invalid PostCode
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);		
		
		jtExpressRateRequestImpl.getCSRFToken();	

		var jtRestResponse=jtExpressRateRequestImpl.postExternalURL(shippingRateRequestDto);
		
		if(isValidJson(jtRestResponse)) {
			Gson gson = new Gson();
			JTExpressErrorResponse response = gson.fromJson(jtRestResponse, JTExpressErrorResponse.class);
			boolean result=response.getStatus().equals("fail");
			Assertions.assertThat(result).isTrue();
		}	
		
	}

	@Test
	@Order(5)  
	void extractRateFromResponse_When_ValidRequestIsUsed_Then_ShouldExtractRateFromValidJtExpressResponse() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("50088");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("50050");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);

		jtExpressRateRequestImpl.getCSRFToken();	
		
		var jtRestResponse=jtExpressRateRequestImpl.postExternalURL(shippingRateRequestDto);		
		
		Document doc = Jsoup.parse(jtRestResponse.toString());
        
        double rates=0;
        
        Element table = doc.select("table").get(0); 
        Elements rows = table.select("tr");
        if(shippingRateRequestDto.getGoodsSelectedType()==GoodTypes.PARCEL.getId()) {
        	Element row = rows.get(3);
		    Elements cols = row.select("td");
		    rates=Double.parseDouble(cols.get(0).text());		     
        }
        else if(shippingRateRequestDto.getGoodsSelectedType()==GoodTypes.DOCUMENT.getId()) {
        	Element row = rows.get(3);
		    Elements cols = row.select("td");
		    rates=Double.parseDouble(cols.get(1).text());
		    
        }
		boolean result=rates>=0;
		Assertions.assertThat(result).isTrue();
	}
	
	@Test
	@Order(6)  
	void shouldNotExtractRateFromResponse_When_WeightHeightLengthWidthNotProvideInRequest_Then_ResponseFailShouldReturn() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("50088");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("50050");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);

		jtExpressRateRequestImpl.getCSRFToken();	
		
		var jtRestResponse=jtExpressRateRequestImpl.postExternalURL(shippingRateRequestDto);		
		
		if(isValidJson(jtRestResponse)) {
			Gson gson = new Gson();
			JTExpressErrorResponse response = gson.fromJson(jtRestResponse, JTExpressErrorResponse.class);
			boolean result=response.getStatus().equals("fail");
			Assertions.assertThat(result).isTrue();
		}		
	}
	
	
	private boolean isValidJson(String json) {
	    try {
	        new org.json.JSONObject(json);
	    } catch (JSONException e) {
	        return false;
	    }
	    return true;
	}
	
	 
 
}
