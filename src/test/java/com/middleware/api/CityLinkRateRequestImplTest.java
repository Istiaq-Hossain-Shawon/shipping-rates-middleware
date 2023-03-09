package com.middleware.api;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.CityLinkExpressResponse;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.response.ShippingRateDto;
import com.middleware.api.service.ShippingRateRequestService;
import com.middleware.api.service.ShippingRateResponseService;
import com.middleware.api.service.impl.CityLinkRateRequestImpl;

@SpringBootTest
@EnableTransactionManagement 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = true)
@ActiveProfiles("test")
 class CityLinkRateRequestImplTest {

	
	private CityLinkRateRequestImpl cityLinkRateRequestImpl= new CityLinkRateRequestImpl();

	@Test
	void postExternalURL_When_ValidRequestIsUsed_Then_ShouldReturnValidCityLinkResponse() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("999994");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("99999");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);

		var cityLinkRestResponse=cityLinkRateRequestImpl.postExternalURL(shippingRateRequestDto);
		
		
		Gson gson = new Gson();
        CityLinkExpressResponse response = gson.fromJson(cityLinkRestResponse, CityLinkExpressResponse.class);

        int responseStatusFromCityLink = 200;         
		boolean result=response.getReq().getStatus()==responseStatusFromCityLink;
		Assertions.assertThat(result).isTrue();
	}
	@Test
	void extractRateFromResponse_When_ValidRequestIsUsed_Then_ShouldExtractRateFromValidCityLinkResponse() {

		ShippingRateRequestDto shippingRateRequestDto = new ShippingRateRequestDto();

		shippingRateRequestDto.setDestinationCountry("AW");
		shippingRateRequestDto.setDestinationPostcode("999994");
		shippingRateRequestDto.setDestinationState("Aruba");
		shippingRateRequestDto.setOriginCountry("MY");
		shippingRateRequestDto.setOriginPostcode("99999");
		shippingRateRequestDto.setOriginState("Selangor");
		shippingRateRequestDto.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequestDto.setWeight(3);
		shippingRateRequestDto.setHeight(12);
		shippingRateRequestDto.setLength(32);
		shippingRateRequestDto.setWidth(20);

		shippingRateRequestDto.setShippingRatesType("domestic");
		shippingRateRequestDto.setShippingType("EZ");
		shippingRateRequestDto.setItemValue(0);

		var cityLinkRestResponse=cityLinkRateRequestImpl.postExternalURL(shippingRateRequestDto);		
		
		Gson gson = new Gson();
        CityLinkExpressResponse response = gson.fromJson(cityLinkRestResponse, CityLinkExpressResponse.class);

		boolean result=response.getReq().getData().getRate()>=0;
		Assertions.assertThat(result).isTrue();
	}
	
	 
 
}
