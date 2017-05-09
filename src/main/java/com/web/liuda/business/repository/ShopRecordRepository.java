package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.ShopRecord;


public interface ShopRecordRepository extends
		PagingAndSortingRepository<ShopRecord, Long>,
		JpaSpecificationExecutor<ShopRecord> {
	public ShopRecord findByIdAndStatusNot(Long id,Integer status);
}
