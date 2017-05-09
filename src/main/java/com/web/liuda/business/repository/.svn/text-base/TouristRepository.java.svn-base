package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Tourist;


public interface TouristRepository extends
		PagingAndSortingRepository<Tourist, Long>,
		JpaSpecificationExecutor<Tourist> {
	public Tourist findByIdAndStatusNot(Long id,Integer status);
	
	public Tourist findByBusinessUserIdAndStatusNot(Long userId,Integer status);
	
	public Tourist findByMobileAndStatusNot(String mobile,Integer status);
}
