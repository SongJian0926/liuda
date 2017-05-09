package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Club;


public interface ClubRepository extends
		PagingAndSortingRepository<Club, Long>,
		JpaSpecificationExecutor<Club> {
	public Club findByIdAndStatusNot(Long id,Integer status);
	
	
}
