package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.GuideComment;


public interface GuideCommentRepository extends
		PagingAndSortingRepository<GuideComment, Long>,
		JpaSpecificationExecutor<GuideComment> {
	public GuideComment findByIdAndStatusNot(Long id,Integer status);
}
