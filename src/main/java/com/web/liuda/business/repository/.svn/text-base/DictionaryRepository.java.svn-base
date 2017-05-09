package com.web.liuda.business.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Dictionary;

public interface DictionaryRepository  extends
PagingAndSortingRepository<Dictionary, Long>,
JpaSpecificationExecutor<Dictionary> {
public Dictionary findByIdAndStatusNot(Long id,Integer status);
}

