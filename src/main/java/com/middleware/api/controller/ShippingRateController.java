package com.middleware.api.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.MiddlewareResponse;
import com.middleware.api.service.ILogisticShippingService;



@RestController
@RequestMapping("/")
public class ShippingRateController {
	 
	private ILogisticShippingService logisticShippingService;
	
	public ShippingRateController(ILogisticShippingService logisticShippingService) {
		this.logisticShippingService = logisticShippingService;
	}
 
	 private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	 
	
	@PostMapping(value = "/shipping-rates")	
	public MiddlewareResponse getShippingRates(@RequestBody  ShippingRateRequestDto shippingRateRequest)
	{		
		try
		{
			if(Boolean.TRUE.equals(ShippingRateUtil.checkIfNull(shippingRateRequest.getOriginPostcode()))) {
				logger.error("Origin Post Code is mendatory.");			
				return ShippingRateUtil.createResponseFalied("Origin Post Code is mendatory.");
			}
			if(Boolean.TRUE.equals(ShippingRateUtil.checkIfNull(shippingRateRequest.getGoodsSelectedType()))) {
				logger.error("Selected type is mendatory.");			
				return ShippingRateUtil.createResponseFalied("Goods Selected type is mendatory.");
			}
			return logisticShippingService.getShippingRate(shippingRateRequest);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			return ShippingRateUtil.createResponseFalied(e.getMessage());
		}
	}
	


}
