package com.middleware.api.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.middleware.api.model.CityLinkExpressRequest;
import com.middleware.api.model.ShippingRateRequest;


public interface ShippingRateRequestRepository extends JpaRepository<ShippingRateRequest,Integer> {
	@Transactional
	void saveAndFlush(Optional<ShippingRateRequest> shippingRateRequest);		
}
