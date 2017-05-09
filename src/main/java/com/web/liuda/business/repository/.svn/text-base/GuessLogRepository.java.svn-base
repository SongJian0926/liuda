package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.GuessLog;


public interface GuessLogRepository extends
		PagingAndSortingRepository<GuessLog, Long>,
		JpaSpecificationExecutor<GuessLog> {
	public GuessLog findByIdAndStatusNot(Long id,Integer status);
	
	@Query(value="Select count(1) as num, option_id from tb_xa_guesslog where status<>?1 and match_id=?2 group by option_id",nativeQuery=true)
	public List<Object[]> findByMatchIdGroupByOptionId(Integer status, Long match_id);
}
