package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Tickets;


public interface TicketsRepository extends
		PagingAndSortingRepository<Tickets, Long>,
		JpaSpecificationExecutor<Tickets> {
	/**
	 * 根据Id查询已发布的门票
	 * @param id
	 * @param status
	 * @return Tickets
	 */
	public Tickets findByIdAndStatus(Long id,Integer status);
	
	/**
	 * 查询景点下的门票
	 * @param hotelId
	 * @param status
	 * @return
	 */
	public List<Tickets> findByTouristIdAndStatus(Long touristId,Integer status);
	
	/**
	 * 查询景点下的某门票
	 * @param hotelId
	 * @param status
	 * @return
	 */
	public Tickets findByTouristIdAndId(Long touristId,Long id);

	public Tickets findByIdAndStatusNot(Long modelId, int delete);
}
