package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiVersionInfoService;
import com.web.liuda.remote.vo.VersionInfoVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @Title: ApiVersionInfoController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 版本信息信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "VersionInfo", description = "版本更新", position = 10)
@Controller
@RequestMapping("/api/versionInfo")
public class ApiVersionInfoController extends BaseController {

	@Autowired
	private ApiVersionInfoService versionInfoService;
	
	/**
	 * @Title: findVersionInfo
	 * @Description: 版本更新
	 * @return    
	 */
	@ApiOperation(value="版本更新",notes="版本更新,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findVersionInfo",method=RequestMethod.POST)
	public XaResult<VersionInfoVo> findVersionInfo() throws BusinessException{
		return versionInfoService.findVersionInfo();
	}
	
}

