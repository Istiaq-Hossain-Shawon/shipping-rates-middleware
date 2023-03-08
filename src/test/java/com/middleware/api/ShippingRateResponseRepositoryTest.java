package com.middleware.api;

import static org.mockito.Mockito.when;


import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.request.ShippingRateRequestDto;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
public class ShippingRateResponseRepositoryTest {
	
	@Autowired
    private ShippingRateResponseRepository shippingRateResponseRepository;
	
	@Autowired
    private ShippingRateRequestRepository shippingRateRequestRepository;
	
    @Test
    @Order(1)
    @Rollback(value = true)
    @Transactional
    void saveShippingRateResponse_When_ValidRequestIsUsed_Then_ShouldSaveShippingRateResponse(){

    	ShippingRateRequest shippingRateRequest = new ShippingRateRequest();

		shippingRateRequest.setDestinationCountry("AW");
		shippingRateRequest.setDestinationPostcode("999990");
		shippingRateRequest.setDestinationState("Aruba");
		shippingRateRequest.setOriginCountry("MY");
		shippingRateRequest.setOriginPostcode("99999");
		shippingRateRequest.setOriginState("Selangor");
		shippingRateRequest.setGoodsSelectedType(GoodTypes.PARCEL.getId());
		shippingRateRequest.setWeight(3);
		shippingRateRequest.setHeight(12);
		shippingRateRequest.setLength(32);
		shippingRateRequest.setWidth(20);

		shippingRateRequest.setShippingRatesType("domestic");
		shippingRateRequest.setShippingType("EZ");
		shippingRateRequest.setItemValue(0);;
		
		shippingRateRequest=shippingRateRequestRepository.saveAndFlush(shippingRateRequest);
			
    	
    	ShippingRateResponse shippingRateResponse =new ShippingRateResponse();	    	
    	shippingRateResponse.setDetailResponse("{\"data\":[{\"courier\":\"citylink\",\"rate\":539.6},{\"courier\":\"jtexpress\",\"rate\":7.42}]}");
    	shippingRateResponse.setShippingRateRequest(shippingRateRequest);
    	
    	shippingRateResponse=shippingRateResponseRepository.saveAndFlush(shippingRateResponse);
        Assertions.assertThat(shippingRateResponse.getId()).isPositive();
    }
    

}
