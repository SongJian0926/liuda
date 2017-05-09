package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Notice;


public interface NoticeRepository extends
		PagingAndSortingRepository<Notice, Long>,
		JpaSpecificationExecutor<Notice> {
	public Notice findByIdAndStatusNot(Long id,Integer status);
}
