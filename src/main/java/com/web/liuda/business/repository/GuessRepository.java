package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Guess;


public interface GuessRepository extends
		PagingAndSortingRepository<Guess, Long>,
		JpaSpecificationExecutor<Guess> {
	public Guess findByIdAndStatusNot(Long id,Integer status);
}
