package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Area;


public interface AreaRepository extends
		PagingAndSortingRepository<Area, Long>,
		JpaSpecificationExecutor<Area> {
	public Area findByIdAndStatusNot(Long id,Integer status);
}
