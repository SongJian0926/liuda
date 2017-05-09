package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.FondRecord;


public interface FondRecordRepository extends
		PagingAndSortingRepository<FondRecord, Long>,
		JpaSpecificationExecutor<FondRecord> {
	public FondRecord findByIdAndStatusNot(Long id,Integer status);
	
	//查询用户通过邀请码获得的收益
	@Query(value="select SUM(f.price) from tb_xa_fondrecord f where f.origin=?1 and f.`status`=1 and f.user_id=?2",nativeQuery=true)
	public Double findNumByOriginAndUserIdAndStatus(String origin,Long userId);
}
