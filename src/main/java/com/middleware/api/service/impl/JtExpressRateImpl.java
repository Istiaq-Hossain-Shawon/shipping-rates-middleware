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
import com.middleware.api.service.IShippingRate;
import com.middleware.api.service.RateRequestTemplate;

public class JtExpressRateImpl  implements IShippingRate{

	private final Logger logger = LoggerFactory.getLogger(ShippingRateController.class);
	@Override
	public ShippingRateDto getRate(ShippingRateRequestDto shippingRequest) {
		try {
			RateRequestTemplate jtExpressRateRequestImpl = new JtExpressRateRequestImpl();
			return jtExpressRateRequestImpl.makeRateRequest(shippingRequest);	
		}catch (Exception e) {
			logger.info(e.getMessage());
			return new ShippingRateDto(Courier.JTEXPRESS.getName(),0.0);
		}
				
	} 

}
