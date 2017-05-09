package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Collect;


public interface CollectRepository extends
		PagingAndSortingRepository<Collect, Long>,
		JpaSpecificationExecutor<Collect> {
	public Collect findByIdAndStatusNot(Long id,Integer status);
	/*
	 * 查询用户是否关注过
	 * author:changlu
	 * time:2016-04-26 12:04:00
	 */
	public Collect findByObjectIdAndUserIdAndTypeAndStatus(Long objectId,Long userId,Integer type,Integer status);
}
