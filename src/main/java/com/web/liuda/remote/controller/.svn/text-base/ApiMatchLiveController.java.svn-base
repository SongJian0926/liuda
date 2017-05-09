package com.web.liuda.remote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiMatchLiveService;
import com.web.liuda.remote.vo.MatchLiveVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiMatchLiveController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 活动/赛事直播表信息接口
 * @author flora.li
 * @date 2016年4月20日 下午13:00:00
 * @version V1.0
 */
@Api(value = "MatchLive", description = "活动/赛事直播表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/matchLive")
public class ApiMatchLiveController extends BaseController {
	
	@Autowired
	private ApiMatchLiveService matchLiveService;

	
	/**
	 * @Title: findMatchLiveList
	 * @Description: 活动/赛事直播列表
	 * @return    
	 */
	@ApiOperation(value="活动/赛事直播列表",notes="赛事直播列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMatchLiveList",method=RequestMethod.POST)
	public XaResult<Page<MatchLiveVo>> findMatchLiveList(
			@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
			@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
			@ApiParam("赛事Id,字段名:id")  @RequestParam(required=true) Long id
	) throws BusinessException{
		XaResult<Page<MatchLiveVo>> xr=new XaResult<Page<MatchLiveVo>> ();
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		if(XaUtil.isEmpty(id)){
			xr.error("赛事Id不能为空！");
			return xr;
		}
		return matchLiveService.findMatchLiveList(XaConstant.Status.valid, id, pageable);
	}
	
}
