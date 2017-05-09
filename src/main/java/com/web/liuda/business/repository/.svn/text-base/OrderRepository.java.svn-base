package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Order;


public interface OrderRepository extends
		PagingAndSortingRepository<Order, Long>,
		JpaSpecificationExecutor<Order> {
	public Order findByIdAndStatusNot(Long id,Integer status);
	
	/**
	 * 根据订单号查询订单信息
	 * @param orderNo
	 * @param status
	 * @return
	 */
	public Order findByOrderNoAndStatusNot(String orderNo,Integer status);
	
}
