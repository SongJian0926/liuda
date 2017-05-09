package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.PrizeResult;


public interface PrizeResultRepository extends
		PagingAndSortingRepository<PrizeResult, Long>,
		JpaSpecificationExecutor<PrizeResult> {
	public PrizeResult findByIdAndStatusNot(Long id,Integer status);
	
	@Query(value="select m.id,m.createTime,m.userId,m.matchId,m.prizeOptionId,u.userName,u.photo " +
    		"from PrizeResult m ,User u where m.userId=u.id and m.status=?1 and m.matchId=?2 order by m.createTime asc")
	public List<Object[]> findByMatchId(Integer status, Long matchId);
	

	@Query(value="select m.id,m.createTime,m.userId,m.matchId,m.prizeOptionId,u.userName,u.photo,m.status,u.mobile " +
    		"from PrizeResult m ,User u where m.userId=u.id and m.status<>?1 and m.matchId=?2 order by m.createTime asc")
	public List<Object[]> findByMatchIdAndStatusNot(Integer status, Long matchId);
}
