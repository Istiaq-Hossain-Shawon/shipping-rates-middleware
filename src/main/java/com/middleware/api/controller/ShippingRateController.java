package com.middleware.api.controller;

import java.security.Principal;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.middleware.api.config.util.ShippingRateUtil;
import com.middleware.api.dto.ShippingRateDto;
import com.middleware.api.request.AuthenticationRequest;
import com.middleware.api.request.ShippingRateRequestDto;
import com.middleware.api.response.ResponseDTO;
import com.middleware.api.service.CourierService;



@RestController
public class ShippingRateController {
	 
	private CourierService courierService;
	
		
	@Autowired
	public ShippingRateController(CourierService courierService) {
		this.courierService = courierService;
	}
 
	 private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	
	 
	
	@PostMapping(value = "/get")	
	public ResponseDTO getShippingRates(@RequestBody  ShippingRateRequestDto shippingRateRequest,Principal principal)throws Exception
	{		
		try
		{
			return courierService.GetShippingRate(shippingRateRequest);
		}
		catch (Exception e)
		{
			logger.error(e.getMessage());
			e.printStackTrace();
			return ShippingRateUtil.createResponseFalied(e.getMessage());
		}
	}
	
	
	@GetMapping(value = {"/home"})
	public String  home() {
		return "Api is running.Please follow api documentation for next procedure. ";
	}


}
