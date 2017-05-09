package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.VersionInfo;


public interface VersionInfoRepository extends
		PagingAndSortingRepository<VersionInfo, Long>,
		JpaSpecificationExecutor<VersionInfo> {
	public VersionInfo findByIdAndStatusNot(Long id,Integer status);
	
}
