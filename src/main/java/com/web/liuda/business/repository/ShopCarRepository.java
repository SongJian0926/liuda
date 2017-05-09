package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.ShopCar;


public interface ShopCarRepository extends
		PagingAndSortingRepository<ShopCar, Long>,
		JpaSpecificationExecutor<ShopCar> {
	public ShopCar findByIdAndStatusNot(Long id,Integer status);
	
	/*
	 * 查询该商品是否在购物车中
	 * author:changlu
	 * time:2016-02-04 11:01:00
	 */
	public ShopCar findByShopIdAndStandardIdAndUserIdAndStatusNot(Long shopId,Long standardId,Long userId,Integer status);
}
