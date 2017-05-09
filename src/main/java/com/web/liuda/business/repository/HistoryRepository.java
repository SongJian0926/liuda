package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.History;


public interface HistoryRepository extends
		PagingAndSortingRepository<History, Long>,
		JpaSpecificationExecutor<History> {
	public History findByIdAndStatusNot(Long id,Integer status);
	public List<History> findByUserIdAndObjectIdAndType(Long userId,Long objectId,Integer type);
}
