package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.GuideAppend;


public interface GuideAppendRepository extends
		PagingAndSortingRepository<GuideAppend, Long>,
		JpaSpecificationExecutor<GuideAppend> {
	public GuideAppend findByIdAndStatusNot(Long id,Integer status);
	public List<GuideAppend> findByGuideIdAndStatusNot(Long id,Integer status);
}
