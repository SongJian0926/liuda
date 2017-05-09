package com.web.liuda.remote.service;

import org.springframework.data.domain.Page;

import com.web.liuda.business.entity.Shop;
import com.web.liuda.remote.vo.ShopVo;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;


public interface ApiShopService extends ApiBaseService<ShopVo,Shop>{
	 
    /**
     * 商品列表
     * @param nextPage
     * @param pageSize
     * @param shopName
     * @param sort
     * @param sortType
     * @return
     */
    public XaResult<Page<ShopVo>> getShops(Integer nextPage,Integer pageSize,String shopName,
    		String sort,String sortType,Integer groupBuy);
    /**
     * 酒店详情(已登录)
     * @param ShopId userId
     * @return
     */
    public XaResult<ShopVo> findShopDetail(Long ShopId,Long userId);
    /**
     * 酒店详情(未登录)
     * @param ShopId
     * @return
     */
    public XaResult<ShopVo> findShopDetail(Long ShopId);
}
