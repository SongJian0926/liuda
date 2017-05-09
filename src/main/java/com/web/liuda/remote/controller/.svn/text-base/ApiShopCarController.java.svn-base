package com.web.liuda.remote.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.ShopCar;
import com.web.liuda.remote.service.ApiShopCarService;
import com.web.liuda.remote.vo.ShopCarVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiShopCarController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 购物车信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "ShopCar", description = "购物车接口", position = 10)
@Controller
@RequestMapping("/api/shopCar")
public class ApiShopCarController extends BaseController {

	@Autowired
	private ApiShopCarService shopCarService;
	
	/**
	 * @Title: findShopCars
	 * @Description: 购物车列表
	 * @return    
	 */
	@ApiOperation(value="购物车列表",notes="购物车列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findShopCars",method=RequestMethod.POST)
	public XaResult<Page<ShopCarVo>> findShopCars(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<Page<ShopCarVo>> xr=new XaResult<Page<ShopCarVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		return shopCarService.findMyCar(userId, nextPage, pageSize);
	}
	
	/**
	 * @Title: save
	 * @Description: 加入购物车
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="加入购物车",notes="加入购物车,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="addCar",method=RequestMethod.POST)
	public XaResult<ShopCarVo> addCar(
		@ApiParam("商品Id,字段名:shopId") @RequestParam(value = "shopId") Long shopId,
		@ApiParam("规格Id,字段名:standardId") @RequestParam(value = "standardId") Long standardId,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("数量,字段名:shopNumber") @RequestParam(value = "shopNumber",defaultValue="1") Integer shopNumber,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<ShopCarVo> xr = new XaResult<ShopCarVo>();
		if(XaUtil.isEmpty(shopId)){
			xr.error("商品Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(standardId)){
			xr.error("规格Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(shopNumber)){
			xr.error("数量不能为空！");
			return xr;
		}
		if(shopNumber < 1){
			xr.error("数量必须大于0");
			return xr;
		}
		ShopCar shopCar = new ShopCar();
		shopCar.setShopId(shopId);
		shopCar.setStandardId(standardId);
		shopCar.setUserId(userId);
		shopCar.setShopNumber(shopNumber);
		return shopCarService.addCar(shopCar);
	}
	
	/**
	 * @Title: 
	 * @Description: 修改购物车
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改购物车",notes="修改购物车,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateShopCar",method=RequestMethod.POST)
	public XaResult<List<ShopCarVo>> updateShopCar(
		@ApiParam("购物车实体，json格式的字符串，格式：[{\"id\":1,\"shopNumber\":2},{\"id\":2,\"shopNumber\":3}]") @RequestParam(value = "shopCar") String shopCar
	) throws BusinessException{
		XaResult<List<ShopCarVo>> xr=new XaResult<List<ShopCarVo>>();
		if(XaUtil.isEmpty(shopCar)){
			xr.error("购物车实体不能为空!");
			return xr;
		}
		return shopCarService.modifyCar(shopCar);
	}
	/**
	 * @Title: 
	 * @Description: 删除购物车
	 * @param userId
	 * @param ids
	 * @return    
	 */
	@ApiOperation(value="删除购物车",notes="删除购物车,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="deleteShopCars",method=RequestMethod.POST)
	public XaResult<ShopCarVo> deleteShopCars(
			@ApiParam("购物车id,字段名:ids (购物车id,用逗号分割开)") @RequestParam(value = "ids") String ids,
			@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
			HttpServletRequest request
	) throws BusinessException{
		XaResult<ShopCarVo> xr=new XaResult<ShopCarVo>();
		if(XaUtil.isEmpty(ids)){
			xr.error("购物车id不能为空!");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空!");
			return xr;
		}
		/*XaResult<ShopCarVo> xr=new XaResult<ShopCarVo>();
		try{
			return shopCarService.deleteCar(ids,userId);
		}catch(Exception e){
			xr.error(XaConstant.Message.error);
		}
		return xr;*/
		return shopCarService.deleteCar(ids,userId);
	}
	
}

