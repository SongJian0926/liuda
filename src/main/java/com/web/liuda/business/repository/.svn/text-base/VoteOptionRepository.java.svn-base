package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.VoteOption;
import com.web.liuda.remote.vo.VoteOptionVo;


public interface VoteOptionRepository extends
		PagingAndSortingRepository<VoteOption, Long>,
		JpaSpecificationExecutor<VoteOption> {
	public VoteOption findByIdAndStatusNot(Long id,Integer status);
	
	public List<VoteOptionVo> findByVoteIdAndStatus(Long voteId,Integer status);
}
