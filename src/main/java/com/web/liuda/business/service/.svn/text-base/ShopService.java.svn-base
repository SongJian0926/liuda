package com.web.liuda.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.web.liuda.business.entity.Shop;
import com.web.liuda.business.entity.Standard;
import com.web.liuda.remote.vo.ShopVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.BaseServiceInterFace;
import com.web.webstart.base.util.XaResult;

public interface ShopService extends BaseServiceInterFace<Shop>{
	
	public XaResult<Page<ShopVo>> findShopList(Integer status, Map<String,Object> filterParams, Pageable pageable) throws BusinessException;
	
	public XaResult<ShopVo> findShopById(Long modelId)throws BusinessException;
	
	/**
	 * 商品信息保存
	 * @param shop
	 * @param imgUrls
	 * @param desImgs
	 * @param standards
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Shop> saveOrUpdate(Shop shop,String imgUrls,String desImgs,List<Standard> standards);
}
