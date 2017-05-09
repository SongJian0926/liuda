package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MatchOrderTemp;


public interface MatchOrderTempRepository extends
		PagingAndSortingRepository<MatchOrderTemp, Long>,
		JpaSpecificationExecutor<MatchOrderTemp> {
	public MatchOrderTemp findByIdAndStatusNot(Long id,Integer status);
	//查询报名临时人员信息
	public List<MatchOrderTemp> findByUserIdAndMatchIdAndTypeAndStatusNot(Long id,Long matchId,Integer type,Integer status);
	
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
	public List<MatchOrderTemp> findByMobileAndMatchIdAndTypeAndStatus(String mobile,Long matchId,Integer type,Integer status);
	
	
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
	public List<MatchOrderTemp> findByIdCardAndMatchIdAndTypeAndStatus(String idCard,Long matchId,Integer type,Integer status);
}
