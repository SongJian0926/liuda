package com.web.liuda.remote.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.ShopCar;
import com.web.liuda.remote.vo.ShopCarVo;
import com.web.webstart.base.util.XaResult;

public interface ApiShopCarService{
	
	/**
	 * 购物车列表
	 * @param userId
	 * @return
	 */
	public XaResult<Page<ShopCarVo>> findMyCar(Long userId,Integer begin,Integer count);
	
	/**
	 * 加入购物车
	 * @param shopCar
	 * @return
	 */
	public XaResult<ShopCarVo> addCar(ShopCar shopCar);
	
	/**
	 * 修改购物车数量
	 * @param shopCar
	 * @return
	 */
	public XaResult<List<ShopCarVo>> modifyCar(String shopCar);
	/**
	 * 删除购物车
	 * @param ids
	 * @param userId
	 * @return
	 */
	public XaResult<ShopCarVo> deleteCar(String ids,Long userId);
}
