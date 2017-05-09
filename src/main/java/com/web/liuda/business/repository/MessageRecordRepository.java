package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.MessageRecord;


public interface MessageRecordRepository extends
		PagingAndSortingRepository<MessageRecord, Long>,
		JpaSpecificationExecutor<MessageRecord> {
	public MessageRecord findByIdAndStatusNot(Long id,Integer status);
	
	public List<MessageRecord> findByUserIdAndMessageIdAndStatus(Long userId,Long messageId,Integer status);
}
