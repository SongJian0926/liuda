package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.ReplyComment;


public interface ReplyCommentRepository extends
		PagingAndSortingRepository<ReplyComment, Long>,
		JpaSpecificationExecutor<ReplyComment> {
	public ReplyComment findByIdAndStatusNot(Long id,Integer status);
}
