package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Agreement;


public interface AgreementRepository extends
		PagingAndSortingRepository<Agreement, Long>,
		JpaSpecificationExecutor<Agreement> {
	public Agreement findByIdAndStatusNot(Long id,Integer status);
	
	public Agreement findByTypeAndStatus(Integer type,Integer status);
	
	public Agreement findByTypeAndStatusAndIdNot(Integer type,Integer status,Long id);
}
