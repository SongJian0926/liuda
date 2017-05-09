package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Order;
import com.web.liuda.business.entity.OrderDetail;


public interface OrderDetailRepository extends
		PagingAndSortingRepository<OrderDetail, Long>,
		JpaSpecificationExecutor<OrderDetail> {
	public Order findByIdAndStatusNot(Long id,Integer status);
	
	
	
}
