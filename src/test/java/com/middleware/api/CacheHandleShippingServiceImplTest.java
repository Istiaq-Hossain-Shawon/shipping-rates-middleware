package com.middleware.api;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.gson.Gson;
import com.middleware.api.config.util.Courier;
import com.middleware.api.config.util.GoodTypes;
import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.factory.ShippingFactory;
import com.middleware.api.model.ShippingRateRequest;
import com.middleware.api.model.ShippingRateResponse;
import com.middleware.api.repository.ShippingRateRequestRepository;
import com.middleware.api.repository.ShippingRateResponseRepository;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.ShippingRate;
import com.middleware.api.service.ShippingRateRequestService;
import com.middleware.api.service.ShippingRateResponseService;
import com.middleware.api.service.CacheHandleShippingService;
import com.middleware.api.service.LogisticShippingService;

@SpringBootTest
@EnableTransactionManagement 
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = true)
@ActiveProfiles("test")
public class CacheHandleShippingServiceImplTest {

	@Autowired
	private ShippingRateRequestService shippingRateRequestService;

	@Autowired
	private ShippingRateResponseService shippingRateResponseService;

	@Test
	@Order(1)
	@Rollback(value = true)
	@Transactional
	void saveShippingRateRequestForHandlingCache_When_ValidRequestIsUsed_Then_ShouldSaveShippingRateRequest() {

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

		ShippingRateRequest shippingRateRequest = shippingRateRequestService.saveRequest(shippingRateRequestDto);
		
		Assertions.assertThat(shippingRateRequest.getId()).isPositive();
	}
	
	@Test
	@Order(2)
	@Rollback(value = true)
	@Transactional
	void saveResponseForHandlingCache_When_ValidRequestIsUsed_Then_ShouldSaveResponse() {

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

		ShippingRateRequest shippingRateRequest = shippingRateRequestService.saveRequest(shippingRateRequestDto);
		
		List<ShippingRateDto> data = new ArrayList<>();
		data.add(new ShippingRateDto("citylink",12.0));
		data.add(new ShippingRateDto("jtExpress",10.0));
		MiddlewareResponse responseDTO = ShippingRateUtil.createResponseSuccess();
		responseDTO.setData(data);
		
		ShippingRateResponse response=shippingRateResponseService.saveResponse(shippingRateRequest, responseDTO);
		
		Assertions.assertThat(response.getId()).isPositive();
	}
	
	
// 
//	public ShippingRateRequest saveRequest(ShippingRateRequestDto shippingRateRequestDto) {
//		return shippingRateRequestService.saveRequest(shippingRateRequestDto);
//	}
//
//	public ShippingRateResponse saveResponse(ShippingRateRequest shippingRateRequest, MiddlewareResponse responseDTO) {
//		return shippingRateResponseService.saveResponse(shippingRateRequest, responseDTO);
//	}
//	
//	public MiddlewareResponse requestCacheHandle(ShippingRateRequestDto shippingRequest) {
//		try {
//
//			ShippingRateRequest matchingRequestObject =shippingRateRequestService.filterShippingRateRequest(shippingRequest);
//			if (matchingRequestObject != null) {
//				Gson g = new Gson();
//				MiddlewareResponse existingResponse = g.fromJson(
//						matchingRequestObject.getShippingRateResponse().getDetailResponse(), MiddlewareResponse.class);
//				return existingResponse;
//			}
//
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//		return new MiddlewareResponse();
//	}
//	

}
