package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Banner;


public interface BannerRepository extends
		PagingAndSortingRepository<Banner, Long>,
		JpaSpecificationExecutor<Banner> {
	public Banner findByIdAndStatusNot(Long id,Integer status);
	public List<Banner> findByIdNotAndStatus(Long id,Integer status);
	
	public Banner findBySortAndStatusNot(Integer sort,Integer status);
}
