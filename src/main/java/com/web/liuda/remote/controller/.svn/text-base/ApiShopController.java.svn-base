package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiShopService;
import com.web.liuda.remote.vo.ShopVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


/**
 * @Title: ApiShopController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 商品接口
 * @author yanghui
 * @date 2015年12月30日 下午12:36:00
 * @version V1.0
 */
@Api(value = "Shop", description = "商品接口", position = 10)
@Controller
@RequestMapping("/api/shop")
public class ApiShopController extends BaseController {

	@Autowired
	private ApiShopService shopService;
	
	/**
	 * @Title: findShopList
	 * @Description: 商品列表
	 * @return    
	 */
	@ApiOperation(value="商品列表",notes="商品列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findShopList",method=RequestMethod.POST)
	public XaResult<Page<ShopVo>> findShopList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("排序字段,字段名:sort,值为price或shopSales") @RequestParam(defaultValue = "price") String sort,
		@ApiParam("排序类型,字段名:sortType,值为desc或asc") @RequestParam(defaultValue = "desc") String sortType,
		@ApiParam("是否团购,字段名:groupBuy,0：否、1：是") @RequestParam(defaultValue = "",required = false) Integer groupBuy,
		@ApiParam("商品名称,字段名:shopName") @RequestParam(defaultValue = "",required = false) String shopName
	) throws BusinessException{
		XaResult<Page<ShopVo>> xr = new XaResult<Page<ShopVo>>();
		if(XaUtil.isEmpty(sort)){
			xr.error("排序字段不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(sortType)){
			xr.error("排序类型不能为空！");
			return xr;
		}
		if(!"price".equals(sort) && !"shopSales".equals(sort)){
			xr.error("排序只能是price或shopSales");
			return xr;
		}
		if(!"desc".equals(sortType) && !"asc".equals(sortType)){
			xr.error("排序类型只能是desc或asc");
			return xr;
		}
		if("shopSales".equals(sort)){
			sortType = "desc";
		}
		if(XaUtil.isNotEmpty(groupBuy) && groupBuy != 0 && groupBuy != 1){
			xr.error("是否团购只能是0和1");
			return xr;
		}
		
		return shopService.getShops(nextPage, pageSize, shopName, sort, sortType, groupBuy);
	}
	/**
	 * @Title: findShopDetail
	 * @Description: 酒店详情
	 * @param id
	 * @return    
	 */
	@ApiOperation(value="商品详情",notes="商品详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findShopDetail",method=RequestMethod.POST)
	public XaResult<ShopVo> findShopDetail(
		@ApiParam("id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("userId,字段名:userId,必填") @RequestParam(required=false,value = "userId") Long userId
	) throws BusinessException{
		if(XaUtil.isEmpty(userId)){
			return shopService.findShopDetail(id);
		}else{
			return shopService.findShopDetail(id,userId);
		}
		
	}
}

