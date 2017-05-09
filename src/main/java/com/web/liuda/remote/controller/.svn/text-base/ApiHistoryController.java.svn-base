package com.web.liuda.remote.controller;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.History;
import com.web.liuda.remote.service.ApiHistoryService;
import com.web.liuda.remote.vo.HistoryVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiHistoryController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 收藏信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "History", description = "收藏接口", position = 10)
@Controller
@RequestMapping("/api/history")
public class ApiHistoryController extends BaseController {

	@Autowired
	private ApiHistoryService historyService;
	
	/**
	 * @Title: findHistorys
	 * @Description: 收藏列表
	 * @return    
	 */
	@ApiOperation(value="收藏列表",notes="收藏列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findHistorys",method=RequestMethod.POST)
	public XaResult<Page<HistoryVo>> findHistorys(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("类型,字段名:type,0：酒店、1：景点、2：商品") @RequestParam(defaultValue = "0") Integer type,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("经度,字段名:lng") @RequestParam(value = "lng", required = false) Double lng,
		@ApiParam("纬度,字段名:lat") @RequestParam(value = "lat", required = false) Double lat
	) throws BusinessException{
		XaResult<Page<HistoryVo>> xr = new XaResult<Page<HistoryVo>>();
		if(XaUtil.isEmpty(type)){
			xr.error("类型不能为空");
			return xr;
		}
		if(type != 0 && type != 1 && type !=2){
			xr.error("没有此类型");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空!");
			return xr;
		}
		if(type != 2 && (XaUtil.isEmpty(lng) || XaUtil.isEmpty(lat))){
			xr.error("查询酒店或景点的收藏时经纬度不能为空！");
			return xr;
		}
		return historyService.findHistorys(nextPage, pageSize, type, userId, lng, lat);
	}
	
	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="收藏",notes="收藏,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveHistory",method=RequestMethod.POST)
	public XaResult<HistoryVo> saveHistory(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("对象Id,字段名:objectId") @RequestParam(value = "objectId") Long objectId,
		@ApiParam("收藏类型,字段名:type 0：酒店 1：景点 2：商品") @RequestParam(value = "type") Integer type,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<HistoryVo> xr = new XaResult<HistoryVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(objectId)){
			xr.error("对象Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(type)){
			xr.error("收藏类型不能为空！");
			return xr;
		}
		if(!(type==0||type==1||type==2)){
			xr.error("输入的收藏类型是非法的！");
			return xr;
		}
		if(!Validator.isNumber(objectId.toString())){
			xr.error("输入的对象Id是非法的！");
			return xr;
		}
		if(!Validator.isNumber(userId.toString())){
			xr.error("输入的用户Id是非法的！");
			return xr;
		}
		
		History model = new History();
		model.setUserId(userId);
		model.setObjectId(objectId);
		model.setType(type);
		return historyService.createModel(model);
	}
	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * author:changlu
	 * time:2016-04-25 16:13:00
	 * @return    
	 */
	@ApiOperation(value="取消收藏",notes="取消收藏,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="cancelHistory",method=RequestMethod.POST)
	public XaResult<HistoryVo> cancelHistory(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("操作类型,字段名:status,3删除") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return historyService.cancelHistory(modelIds,status);
	}
}

