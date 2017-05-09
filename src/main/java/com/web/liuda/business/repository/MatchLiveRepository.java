package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MatchLive;


public interface MatchLiveRepository extends
		PagingAndSortingRepository<MatchLive, Long>,
		JpaSpecificationExecutor<MatchLive> {
	public MatchLive findByIdAndStatusNot(Long id,Integer status);
}
