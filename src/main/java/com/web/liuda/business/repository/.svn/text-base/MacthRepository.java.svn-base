package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Macth;


public interface MacthRepository extends
		PagingAndSortingRepository<Macth, Long>,
		JpaSpecificationExecutor<Macth> {
	public Macth findByIdAndStatusNot(Long id,Integer status);
}
