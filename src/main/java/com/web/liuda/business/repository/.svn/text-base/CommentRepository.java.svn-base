package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Comment;


public interface CommentRepository extends
		PagingAndSortingRepository<Comment, Long>,
		JpaSpecificationExecutor<Comment> {
	public Comment findByIdAndStatusNot(Long id,Integer status);
	
	@Query(value="select count(c.id) from tb_xa_comment c where c.`status`<>3 ",nativeQuery=true)
	public int getCountPage();
	
	/**
	 * 查询改订单是否已评价
	 * @param orderId
	 * @param userId
	 * @param standardId
	 * @param status
	 * @return
	 */
	public List<Comment> findByOrderIdAndUserIdAndStandardIdAndStatus(Long orderId,Long userId,Long standardId,Integer status);
}
