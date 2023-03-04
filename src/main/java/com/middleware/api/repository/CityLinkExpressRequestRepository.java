package com.middleware.api.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.middleware.api.model.CityLinkExpressRequest;


public interface CityLinkExpressRequestRepository extends JpaRepository<CityLinkExpressRequest,Integer> {
	@Transactional
	void saveAndFlush(Optional<CityLinkExpressRequest> cityLinkExpressRequest);		
}
