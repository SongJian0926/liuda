package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Withdraw;


public interface WithdrawRepository extends
		PagingAndSortingRepository<Withdraw, Long>,
		JpaSpecificationExecutor<Withdraw> {
	public Withdraw findByIdAndStatusNot(Long id,Integer status);
}
