package com.web.liuda.business.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Message;


public interface MessageRepository extends
		PagingAndSortingRepository<Message, Long>,
		JpaSpecificationExecutor<Message> {
	public Message findByIdAndStatusNot(Long id,Integer status);
	
	//查询系统消息或者个人消息
	//public Page<Message> findByUserIdAndStatusNotOrUserIdIsNullAndStatusNot(Long userId,Integer status,Integer status1,Pageable pageable);
	//查询系统消息或者个人消息(30天以内的消息)
	public Page<Message> findByUserIdAndStatusNotOrUserIdIsNullAndStatusNotAndCreateTimeAfter(Long userId,Integer status,Integer status1,String createTime,Pageable pageable);
	@Query(value="select count(*) from tb_xa_message m where m.create_time>=date_sub(NOW(),interval 30 day) and m.`status`<>3 and m.user_id=? or m.user_id is null and  m.create_time>=date_sub(NOW(),interval 30 day) and m.`status`=1 and m.create_time>=(SELECT u.create_time from tb_xa_user u where u.status=1 and u.id=m.user_id) ",nativeQuery=true)
	public int getCountPage(Long userId);
	@Query(value="select count(*) from tb_xa_message m where m.create_time>=date_sub(NOW(),interval 30 day) and m.`status`<>3 and m.user_id is null ",nativeQuery=true)
	public int getCountPage();
	
	
}
