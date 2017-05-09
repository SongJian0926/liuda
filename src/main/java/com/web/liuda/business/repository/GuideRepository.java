package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Guide;


public interface GuideRepository extends
		PagingAndSortingRepository<Guide, Long>,
		JpaSpecificationExecutor<Guide> {
	public Guide findByIdAndStatusNot(Long id,Integer status);
	
	public Guide findByIdAndStatus(Long id,Integer status);
}
