package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Dict;


public interface DictRepository extends
		PagingAndSortingRepository<Dict, Long>,
		JpaSpecificationExecutor<Dict> {
	public Dict findByIdAndStatusNot(Long id,Integer status);
	/**
	 * 判断这种类型的字典是否已存在
	 * author：changlu
	 * @param type
	 * @param id
	 * @param status
	 * @return
	 */
	public List<Dict> findByTypeAndStatusAndIdNot(Integer type,Integer status,Long id);
}
