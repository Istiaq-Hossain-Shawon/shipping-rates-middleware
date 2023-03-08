package com.middleware.api;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.request.ShippingRateRequestDto;

import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ShippingRateRequestRepositoryTest {
	
	
	@Autowired
    private ShippingRateRequestRepository shippingRateRequestRepository;
	
    @Test
    @Order(1)
    @Rollback(value = false)
    void saveShippingRateRequest_When_ValidRequestIsUsed_Then_ShouldSaveShippingRateRequest(){

    	ShippingRateRequest shippingRateRequest = new ShippingRateRequest();

		shippingRateRequest.setDestinationCountry("AW");
		shippingRateRequest.setDestinationPostcode("99999");
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
    @Test
    @Order(2)
    @Rollback(value = false)
    void checkShippingRateRequestAlreadyExistInDatabaseTest(){

    	ShippingRateRequest shippingRateRequest = new ShippingRateRequest();

    	shippingRateRequest.setDestinationCountry("AW");
		shippingRateRequest.setDestinationPostcode("99999");
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
		
		List<ShippingRateRequest> list=shippingRateRequestRepository.getShippingRateRequest(
				shippingRateRequest.getOriginCountry(),
				shippingRateRequest.getOriginState(),
				shippingRateRequest.getOriginPostcode(),
				shippingRateRequest.getDestinationCountry(),
				shippingRateRequest.getDestinationState(),
				shippingRateRequest.getDestinationPostcode(),
				shippingRateRequest.getLength(),
				shippingRateRequest.getWidth(),
				shippingRateRequest.getHeight(),
				shippingRateRequest.getWeight(),
				shippingRateRequest.getGoodsSelectedType(),
				shippingRateRequest.getShippingRatesType(),
				shippingRateRequest.getItemValue(),
				shippingRateRequest.getShippingType()
				);
		
		int minimumExpectedSizeOfData = 1;

		Assert.assertTrue("Check Shipping Rate Request Already Exist In Database ",list.size()>=minimumExpectedSizeOfData);
    	    	
        
    }
    
    
    

}
