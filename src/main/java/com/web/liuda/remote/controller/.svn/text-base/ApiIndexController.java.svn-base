package com.web.liuda.remote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiIndexService;
import com.web.liuda.remote.vo.IndexVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiIndexController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 首页接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Index", description = "首页接口", position = 10)
@Controller
@RequestMapping("/api/index")
public class ApiIndexController {
	
	@Autowired
	ApiIndexService indexService;
	
	/**
	 * @Title: indexInfo
	 * @Description: 首页信息
	 * @return    
	 */
	@ApiOperation(value="首页信息",notes="首页信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="indexInfo",method=RequestMethod.POST)
	public XaResult<IndexVo> indexInfo(
		@ApiParam("用户所在位置经度,字段名:lng") @RequestParam Double lng,
		@ApiParam("用户所在位置纬度,字段名:lat") @RequestParam Double lat
	) throws BusinessException{
		XaResult<IndexVo> xr=new XaResult<IndexVo>();
		if(XaUtil.isEmpty(lng)){
			xr.error("经度不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(lat)){
			xr.error("纬度不能为空！");
			return xr;
		}
		return indexService.getIndexInfo(lng, lat);
	}

}
