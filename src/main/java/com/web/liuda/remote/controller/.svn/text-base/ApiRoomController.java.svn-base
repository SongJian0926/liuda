package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.remote.service.ApiRoomService;
import com.web.liuda.remote.vo.RoomVo;

/**
 * @Title: ApiRoomController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 房间信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Room", description = "房间接口", position = 10)
@Controller
@RequestMapping("/api/room")
public class ApiRoomController extends BaseController {

	@Autowired
	private ApiRoomService roomService;
	
	/**
	 * @Title: findRoomById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="房间详情",notes="查询房间详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findRoomDetail",method=RequestMethod.POST)
	public XaResult<RoomVo> findRoomDetail(
		@ApiParam("房间id:id,必填") @RequestParam(value = "id") Long id
	) throws BusinessException{
		 XaResult<RoomVo> xr=new  XaResult<RoomVo>();
		 if(XaUtil.isEmpty(id)){
			 xr.error("房间id不能为空");
			 return xr;
		 }
		 if(!Validator.isNumber(id.toString())){
			 xr.error("输入的房间Id是非法的！");
			 return xr;
		 }
		return roomService.findRoomDetail(id);
	}
}

