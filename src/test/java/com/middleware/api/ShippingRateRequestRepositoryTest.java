package com.middleware.api;


import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.repository.ShippingRateRequestRepository;

import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@EnableTransactionManagement 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = true)
@ActiveProfiles("test")
public class ShippingRateRequestRepositoryTest {
	
	
	@Autowired
    public  ShippingRateRequestRepository shippingRateRequestRepository;
	
	public ShippingRateRequestRepositoryTest() {
		
	}
	
    @Test
    @Order(1)
    @Rollback(value = true)
    @Transactional
    void saveShippingRateRequest_When_ValidRequestIsUsed_Then_ShouldSaveShippingRateRequest(){

    	ShippingRateRequest shippingRateRequest = new ShippingRateRequest();

		shippingRateRequest.setDestinationCountry("AW");
		shippingRateRequest.setDestinationPostcode("999994");
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
		shippingRateRequest.setItemValue(0);
		
		shippingRateRequest=shippingRateRequestRepository.saveAndFlush(shippingRateRequest);
    	
    	
        Assertions.assertThat(shippingRateRequest.getId()).isPositive();
    }

}
