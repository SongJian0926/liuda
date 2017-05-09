package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MyBank;


public interface MyBankRepository extends
		PagingAndSortingRepository<MyBank, Long>,
		JpaSpecificationExecutor<MyBank> {
	public MyBank findByIdAndStatusNot(Long id,Integer status);
	//通过商家ID查找银行卡
	public MyBank findByBusinessUserIdAndStatusNot(Long id,Integer status);
	
	//通过银行卡卡号查找银行卡
	//public MyBank findByAccountAndStatusNot(String account,Integer status);
	public MyBank findByAccountAndStatusNotAndBusinessUserId(String account,Integer status,Long businessUserId);
}
