package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.ClubEvent;


public interface ClubEventRepository extends
		PagingAndSortingRepository<ClubEvent, Long>,
		JpaSpecificationExecutor<ClubEvent> {
	public ClubEvent findByIdAndStatusNot(Long id,Integer status);
}
