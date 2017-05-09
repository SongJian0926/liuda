package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.GuideTag;


public interface GuideTagRepository extends
		PagingAndSortingRepository<GuideTag, Long>,
		JpaSpecificationExecutor<GuideTag> {
	public GuideTag findByIdAndStatusNot(Long id,Integer status);
}
