package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.GuessOption;


public interface GuessOptionRepository extends
		PagingAndSortingRepository<GuessOption, Long>,
		JpaSpecificationExecutor<GuessOption> {
	public GuessOption findByIdAndStatusNot(Long id,Integer status);
}
