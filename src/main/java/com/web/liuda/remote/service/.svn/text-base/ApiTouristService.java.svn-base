package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;

import com.web.liuda.remote.vo.TouristVo;
import com.web.webstart.base.util.XaResult;

public interface ApiTouristService{
	
	/**
     * 景点列表
     * @param nextPage
     * @param pageSize
     * @param hotelName
     * @param sort
     * @param sortType
     * @param lng
     * @param lat
     * @return
     */
    public XaResult<Page<TouristVo>> getTourists(Integer nextPage,Integer pageSize,String touristName,
    		String sort,String sortType,Double lng,Double lat,Integer groupBuy);
    
    /**
     * 景点详情(登录)
     * @param touristId userId
     * @return
     */
    public XaResult<TouristVo> findTouristDetail(Long touristId,Long userId);
    /**
     * 景点详情(未登录)
     * @param hotelId
     * @return
     */
    public XaResult<TouristVo> findTouristDetail(Long touristId);
}
