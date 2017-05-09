package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;

import com.web.liuda.remote.vo.HotelVo;
import com.web.webstart.base.util.XaResult;

public interface ApiHotelService{
    
    /**
     * 酒店列表
     * @param nextPage
     * @param pageSize
     * @param hotelName
     * @param sort
     * @param sortType
     * @param lng
     * @param lat
     * @return
     */
    public XaResult<Page<HotelVo>> getHotels(Integer nextPage,Integer pageSize,String hotelName,
    		String sort,String sortType,Double lng,Double lat,Integer groupBuy);
    
    /**
     * 酒店详情(用户登录)
     * @param hotelId userId
     * @return
     */
    public XaResult<HotelVo> findHotelDetail(Long hotelId,Long userId) ;
    /**
     * 酒店详情(用户未登录)
     * @param hotelId
     * @return
     */
    public XaResult<HotelVo> findHotelDetail(Long hotelId) ;
}
