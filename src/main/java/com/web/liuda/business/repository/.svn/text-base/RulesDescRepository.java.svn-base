package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.RulesDesc;


public interface RulesDescRepository extends
		PagingAndSortingRepository<RulesDesc, Long>,
		JpaSpecificationExecutor<RulesDesc> {
	public RulesDesc findByIdAndStatusNot(Long id,Integer status);
}
