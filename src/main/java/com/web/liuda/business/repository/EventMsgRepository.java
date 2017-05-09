package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.EventMsg;


public interface EventMsgRepository extends
		PagingAndSortingRepository<EventMsg, Long>,
		JpaSpecificationExecutor<EventMsg> {
	public EventMsg findByIdAndStatusNot(Long id,Integer status);
}
