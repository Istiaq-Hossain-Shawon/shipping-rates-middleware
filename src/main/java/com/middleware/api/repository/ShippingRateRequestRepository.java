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
	@Query(value ="SELECT pst.* FROM shipping_rate_request_tbl  pst  where pst.origin_country like %:origin_country%   and pst.origin_state like %:origin_state%  and  pst.origin_postcode like %:origin_postcode%  and pst.destination_country like %:destination_country% and pst.destination_state like %:destination_state%  and pst.destination_postcode like %:destination_postcode%  and pst.length = :length  and pst.width = :width and pst.height =:height  and pst.weight = :weight  and pst.goods_selected_type like %:goods_selected_type%     and pst.shipping_rates_type like %:shipping_rates_type%  and pst.item_value like %:item_value%   and pst.shipping_type like %:shipping_type% ",nativeQuery = true)
	List<ShippingRateRequest> getShippingRateRequest(
			@Param("origin_country") String originCountry,
			@Param("origin_state") String originState,
			@Param("origin_postcode") String originPostcode,
			@Param("destination_country") String destinationCountry,
			@Param("destination_state") String destinationState,
			@Param("destination_postcode") String destinationPostcode,
			@Param("length") float length,
			@Param("width") float width,
			@Param("height") float height,
			@Param("weight") float weight,
			@Param("goods_selected_type") int goodsSelectedType,
			@Param("shipping_rates_type") String shippingRatesType,
			@Param("item_value") float itemValue,
			@Param("shipping_type") String shippingType
			);	
	

	
}
