package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.City;


public interface CityRepository extends
		PagingAndSortingRepository<City, Long>,
		JpaSpecificationExecutor<City> {
	public City findByIdAndStatusNot(Long id,Integer status);
}
