package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.UserCard;


public interface UserCardRepository extends
		PagingAndSortingRepository<UserCard, Long>,
		JpaSpecificationExecutor<UserCard> {
	public UserCard findByIdAndStatusNot(Long id,Integer status);
	
	public UserCard findByUserIdAndIdAndStatus(Long userId,Long id,Integer status);
	
	public UserCard findByIdAndStatus(Long id,Integer status);
	
	public List<UserCard> findByUserIdAndStatus(Long userId,Integer status);
}
