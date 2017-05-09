package com.web.liuda.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.RefundOrder;


public interface RefundOrderRepository extends
		PagingAndSortingRepository<RefundOrder, Long>,
		JpaSpecificationExecutor<RefundOrder> {
	public RefundOrder findByIdAndStatusNot(Long id,Integer status);
	//查询订单的退款信息
	public RefundOrder findByOrderDetailIdAndStatus(Long orderDetailId,Integer status);
	
	
	@Query(value="select r.id,r.refundNo,r.refundAmt,r.refundStatus,r.refundApplyDate,r.createTime,r.reason,o.payType,d.realName,d.mobile,o.type,o.matchId from RefundOrder r,MatchOrder o,MatchOrderDetail d where r.orderId=o.id and r.orderDetailId=d.id and r.createTime>=? and r.createTime<=? and (r.refundStatus=? or ?=0) ",
			countQuery="select count(1) from RefundOrder r,MatchOrder o,MatchOrderDetail d where r.orderId=o.id and r.orderDetailId=d.id and r.createTime>=? and r.createTime<=? and (r.refundStatus=? or ?=0)")
	public Page<Object[]> findRefundByTimeAndRefundStatus(String starttime, String endtime, Integer refundStatus, Integer refundStatus1, Pageable pageable);
}
