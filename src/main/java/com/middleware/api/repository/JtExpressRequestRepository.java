package com.middleware.api.repository;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.middleware.api.model.CityLinkExpressRequest;
import com.middleware.api.model.JtExpressRequest;
 

public interface JtExpressRequestRepository extends JpaRepository<JtExpressRequest,Integer> {
	@Transactional
	void saveAndFlush(Optional<JtExpressRequest> JtExpressRequest);		
}
