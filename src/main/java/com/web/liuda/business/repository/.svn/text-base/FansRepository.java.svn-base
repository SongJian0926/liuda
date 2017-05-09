package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Fans;


public interface FansRepository extends
		PagingAndSortingRepository<Fans, Long>,
		JpaSpecificationExecutor<Fans> {
	public Fans findByIdAndStatusNot(Long id,Integer status);
	
	//查询是否为我的好友
	public Fans findByFansIdAndUserIdAndStatus(Long userId,Long frindId,Integer status);
}
