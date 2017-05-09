package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Hotel;


public interface HotelRepository extends
		PagingAndSortingRepository<Hotel, Long>,
		JpaSpecificationExecutor<Hotel> {
	public Hotel findByIdAndStatusNot(Long id,Integer status);
	
	public Hotel findByBusinessUserIdAndStatusNot(Long Userid,Integer status);
	
	public Hotel findByMobileAndStatusNot(String mobile,Integer status);
}
