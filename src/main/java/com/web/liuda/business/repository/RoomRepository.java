package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.Room;


public interface RoomRepository extends
		PagingAndSortingRepository<Room, Long>,
		JpaSpecificationExecutor<Room> {
	public Room findByIdAndStatusNot(Long id,Integer status);
	
	/**
	 * 查询酒店下的房间
	 * @param hotelId
	 * @param status
	 * @return
	 */
	public List<Room> findByHotelIdAndStatus(Long hotelId,Integer status);
	
	/**
	 * 查询酒店下的一个房间
	 * @param hotelId
	 * @param status
	 * @return
	 */
	public Room findByHotelIdAndId(Long hotelId,Long id);
	
}
