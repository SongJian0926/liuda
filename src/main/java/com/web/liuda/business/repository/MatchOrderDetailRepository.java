package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MatchOrderDetail;


public interface MatchOrderDetailRepository extends
		PagingAndSortingRepository<MatchOrderDetail, Long>,
		JpaSpecificationExecutor<MatchOrderDetail> {
	public MatchOrderDetail findByIdAndStatusNot(Long id,Integer status);
	
	public List<MatchOrderDetail> findByMatchOrderIdAndStatusNot(Long id,Integer status);
	//查询报名人员信息
	public List<MatchOrderDetail> findByMatchOrderIdAndStatus(Long id,Integer status);
	
	/*
	 * 查询该兑换码是否存在
	 * author：changlu
	 * time:2016-04-26 11:44:00
	 */
	public MatchOrderDetail findByRedeemCodeAndMatchIdAndTypeAndStatus(String redeemCode,Long clubEventId,Integer type,Integer status);
	/**
	 * 查询该手机号是否参加该活动
	 * author:changlu
	 * time:2016-05-05 10:03:00
	 * @param mobile
	 * @param matchId
	 * @param type
	 * @param status
	 * @return
	 */
	@Query(value="select md.mobile from MatchOrderDetail md,MatchOrder mo where mo.id=md.matchOrderId and md.status=1 and mo.status=1 and mo.orderStatus in (1,2) " +
			"and md.mobile=?1 and md.matchId=?2 and md.type=?3 and md.status=?4")
	public List<MatchOrderDetail> findByMobileAndMatchIdAndTypeAndStatus(String mobile,Long matchId,Integer type,Integer status);
	
	
	/**
	 * 查询该身份证号是否参加该活动
	 * author:changlu
	 * time:2016-05-05 10:03:00
	 * @param idCard
	 * @param matchId
	 * @param type
	 * @param status
	 * @return
	 */
	@Query(value="select md.idCard from MatchOrderDetail md,MatchOrder mo where mo.id=md.matchOrderId and md.status=1 and mo.status=1 and " +
			"mo.orderStatus in (1,2) and md.idCard=?1 and md.matchId=?2 and md.type=?3 and md.status=?4")
	public List<MatchOrderDetail> findByIdCardAndMatchIdAndTypeAndStatus(String idCard,Long matchId,Integer type,Integer status);

	/**
	 * 查询实际参加人列表
	 * @param matchId
	 * @param type
	 * @param isRefund
	 * @param pageable
	 * @return
	 */
	@Query(value="select d from MatchOrderDetail d , MatchOrder o where o.id=d.matchOrderId and o.matchId=? and d.status=1 and o.status=1 and o.type=? and o.orderStatus=2 and d.isRefund=? order by d.createTime",
			countQuery="select count(d) from MatchOrderDetail d , MatchOrder o where o.id=d.matchOrderId and o.matchId=? and d.status=1 and o.status=1 and o.type=? and o.orderStatus=2 and d.isRefund=?")
	public Page<MatchOrderDetail> findMatchOrderDetailByMatchIdAndTypePage(Long matchId,Integer type, Integer isRefund, Pageable pageable);
}
