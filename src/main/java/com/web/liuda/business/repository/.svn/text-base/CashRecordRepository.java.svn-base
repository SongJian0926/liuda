package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.CashRecord;


public interface CashRecordRepository extends
		PagingAndSortingRepository<CashRecord, Long>,
		JpaSpecificationExecutor<CashRecord> {
	public CashRecord findByIdAndStatusNot(Long id,Integer status);
	
	public List<CashRecord> findByObjectIdAndCashStatusNot(Long id,Integer cashStatus);
}
