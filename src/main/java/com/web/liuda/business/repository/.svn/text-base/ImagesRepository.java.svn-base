package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Images;


public interface ImagesRepository extends
		PagingAndSortingRepository<Images, Long>,
		JpaSpecificationExecutor<Images> {
	public Images findByIdAndStatusNot(Long id,Integer status);
}
