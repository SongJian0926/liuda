package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Shop;


public interface ShopRepository extends
		PagingAndSortingRepository<Shop, Long>,
		JpaSpecificationExecutor<Shop> {
	public Shop findByIdAndStatusNot(Long id,Integer status);
	
	public List<Shop> findByIdIn(List<Long> id);
	
}
