package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiHotelService;
import com.web.liuda.remote.vo.HotelVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiHotelController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 酒店信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Hotel", description = "酒店接口", position = 10)
@Controller
@RequestMapping("/api/hotel")
public class ApiHotelController extends BaseController {

	@Autowired
	private ApiHotelService hotelService;
	
	/**
	 * @Title: findHotelList
	 * @Description: 酒店列表
	 * @return    
	 */
	@ApiOperation(value="酒店列表",notes="酒店列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findHotels",method=RequestMethod.POST)
	public XaResult<Page<HotelVo>> findHotels(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("排序字段,字段名:sort,值为price或score") @RequestParam(defaultValue = "price") String sort,
		@ApiParam("排序类型,字段名:sortType,值为desc或asc") @RequestParam(defaultValue = "desc") String sortType,
		@ApiParam("用户所在位置经度,字段名:lng") @RequestParam Double lng,
		@ApiParam("用户所在位置纬度,字段名:lat") @RequestParam Double lat,
		@ApiParam("酒店名称,字段名:hotelName") @RequestParam(defaultValue = "",required = false) String hotelName,
		@ApiParam("是否团购,字段名:groupBuy,0:否、1：是") @RequestParam(defaultValue = "",required = false) Integer groupBuy
	) throws BusinessException{
		XaResult<Page<HotelVo>> xr = new XaResult<Page<HotelVo>>();
		if(XaUtil.isEmpty(sort)){
			xr.error("排序字段不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(sortType)){
			xr.error("排序类型不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(lng)){
			xr.error("经度不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(lat)){
			xr.error("纬度不能为空！");
			return xr;
		}
		if(!Validator.isDouble(lng + "")){
			xr.error("用户所在位置经度值不正确");
			return xr;
		}
		if(!Validator.isDouble(lat + "")){
			xr.error("用户所在位置纬度不正确");
			return xr;
		}
		if(!"price".equals(sort) && !"score".equals(sort)){
			xr.error("排序只能是price或score");
			return xr;
		}
		if(!"desc".equals(sortType) && !"asc".equals(sortType)){
			xr.error("排序类型只能是desc或asc");
			return xr;
		}
		if("score".equals(sort)){
			sortType = "desc";
		}
		if(XaUtil.isNotEmpty(groupBuy) && groupBuy != 0 && groupBuy != 1){
			xr.error("是否团购只能是0或1");
			return xr;
		}
		return hotelService.getHotels(nextPage, pageSize, hotelName, sort, sortType, lng, lat, groupBuy);
	}
	
	/**
	 * @Title: findHotelDetail
	 * @Description: 酒店详情
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="酒店详情",notes="酒店详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findHotelDetail",method=RequestMethod.POST)
	public XaResult<HotelVo> findHotelDetail(
		@ApiParam("id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("userId,字段名:userId,必填") @RequestParam(required=false,value = "userId") Long userId
	) throws BusinessException{
		XaResult<HotelVo> xr=new XaResult<HotelVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			return hotelService.findHotelDetail(id);
		}else{
			return hotelService.findHotelDetail(id,userId);
		}
		
	}
	
}

