package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Province;


public interface ProvinceRepository extends
		PagingAndSortingRepository<Province, Long>,
		JpaSpecificationExecutor<Province> {
	public Province findByIdAndStatusNot(Long id,Integer status);
}
