package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.PrizeOption;


public interface PrizeOptionRepository extends
		PagingAndSortingRepository<PrizeOption, Long>,
		JpaSpecificationExecutor<PrizeOption> {
	public PrizeOption findByIdAndStatusNot(Long id,Integer status);
}
