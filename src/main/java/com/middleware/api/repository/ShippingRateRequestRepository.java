package com.middleware.api.repository;


import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.middleware.api.model.ShippingRateRequest;


public interface ShippingRateRequestRepository extends JpaRepository<ShippingRateRequest,Integer> {
	@Transactional
	void saveAndFlush(Optional<ShippingRateRequest> shippingRateRequest);	

	@Transactional
	@Query(value ="SELECT pst.* FROM shipping_rate_request_tbl  pst  where  pst.origin_postcode =:origin_postcode  and pst.destination_postcode =:destination_postcode  ",nativeQuery = true)
	List<ShippingRateRequest> getByOriginPostcodeAndDestinationPostcode(
			@Param("origin_postcode") String originPostcode,
			@Param("destination_postcode") String destinationPostcode);
	
	@Transactional
	ShippingRateRequest getById(int id);

	
}
