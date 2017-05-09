package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.EventReview;


public interface EventReviewRepository extends
		PagingAndSortingRepository<EventReview, Long>,
		JpaSpecificationExecutor<EventReview> {
	public EventReview findByIdAndStatusNot(Long id,Integer status);
}
