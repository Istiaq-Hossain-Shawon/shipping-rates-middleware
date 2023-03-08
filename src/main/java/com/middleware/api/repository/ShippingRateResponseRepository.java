package com.middleware.api.repository;


import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.middleware.api.model.ShippingRateResponse;


public interface ShippingRateResponseRepository extends JpaRepository<ShippingRateResponse,Integer> {
	@Transactional
	public void saveAndFlush(Optional<ShippingRateResponse> shippingRateResponse);		
	
		
}
