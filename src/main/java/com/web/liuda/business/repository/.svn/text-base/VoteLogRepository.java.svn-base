package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.VoteLog;
import com.web.liuda.remote.vo.VoteLogVo;


public interface VoteLogRepository extends
		PagingAndSortingRepository<VoteLog, Long>,
		JpaSpecificationExecutor<VoteLog> {
	public VoteLog findByIdAndStatusNot(Long id,Integer status);
	
	public List<VoteLogVo> findByUserIdAndVoteIdAndStatus(Long UserId,Long voteId,Integer status);
	//统计投票数
	@Query(value="select COUNT(vl.id) from tb_xa_votelog vl where vl.`status` = ?1 and vl.vote_id = ?2",nativeQuery=true)
	public Integer findByStatusAndVoteId(Integer status,Long voteId);
}
