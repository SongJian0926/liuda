package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MatchOrder;


public interface MatchOrderRepository extends
		PagingAndSortingRepository<MatchOrder, Long>,
		JpaSpecificationExecutor<MatchOrder> {
	public MatchOrder findByIdAndStatusNot(Long id,Integer status);
	
	//查询用户是否报名参加该活动
	public List<MatchOrder> findByUserIdAndMatchIdAndTypeAndStatusNot(Long userId,Long clubEventId,Integer type,Integer status);
	//查询该活动/赛事报名人数
	//@Query(value="select count(*) from MatchOrder mo where mo.matchId=?1 and mo.type=?2 and mo.orderStatus=?3 and mo.status=?4 ")
	@Query(value="SELECT count(*) FROM tb_xa_matchorderdetail d INNER JOIN tb_xa_matchorder o on o.id=d.match_order_id where o.match_id=? and d.status=1 and o.status=1 and o.type=? and o.order_status in (1,2) and d.id not in (select r.order_detail_id from tb_xa_refundorder r where r.refund_status in (3,4))",nativeQuery=true)
	public int findByMatchIdAndTypeAndOrderStatusNotAndStatusNot(Long matchId,Integer type);
	
	public MatchOrder findByOrderNoAndStatusNot(String orderNo,Integer status);
	/**
	 * 判断用户是否已报名
	 * author:changlu
	 * time:2016-05-04 13:56:00
	 * @param matchId
	 * @param userId
	 * @param type
	 * @param orderStstus
	 * @param status
	 * @return
	 */
	public List<MatchOrder> findByMatchIdAndUserIdAndTypeAndOrderStatusNotAndStatusNot(Long matchId,Long userId,Integer type,Integer orderStstus,Integer status);

	
	@Query(value="select o.id,o.orderNo,o.payType,o.type,o.orderNumber,o.totalAmt,o.onlineAmt,o.offlineAmt,o.isFull,o.orderStatus,o.createTime,u.userName,u.mobile from MatchOrder o,User u where o.userId=u.id and o.createTime>=? and o.createTime<=? and (o.orderStatus=? or ?=0) ",
			countQuery="select count(1) from MatchOrder o,User u where o.userId=u.id and o.createTime>=? and o.createTime<=? and (o.orderStatus=? or ?=0)")
	public Page<Object[]> findMatchOrderByTimeAndOrderStatus(String starttime, String endtime, Integer orderStatus, Integer orderStatus2, Pageable pageable);
}
