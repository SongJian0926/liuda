package com.web.liuda.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Vote;
import com.web.liuda.remote.vo.VoteVo;


public interface VoteRepository extends
		PagingAndSortingRepository<Vote, Long>,
		JpaSpecificationExecutor<Vote> {
	public Vote findByIdAndStatusNot(Long id,Integer status);
	
	public Vote findByIdAndStatus(Long id,Integer status);

	
	public Page<VoteVo> findByStatus(Integer status,Pageable pageable);
}
