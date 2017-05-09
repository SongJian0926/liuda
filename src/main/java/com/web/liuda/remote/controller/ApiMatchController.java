package com.web.liuda.remote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.vo.MacthVo;
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
 * @Title: ApiMatchController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 赛事表信息接口
 * @author flora.li
 * @date 2016年4月20日 下午13:00:00
 * @version V1.0
 */
@Api(value = "Match", description = "活动/赛事表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/match")
public class ApiMatchController extends BaseController {
	
	@Autowired
	private ApiMatchService matchService;

	
	/**
	 * @Title: findMatchList
	 * @Description: 活动/赛事列表
	 * @return    
	 */
	@ApiOperation(value="活动/赛事列表",notes="活动/赛事列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMatchList",method=RequestMethod.POST)
	public XaResult<Page<MacthVo>> findMatchList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("赛事类型,字段名:matchType,1.商家赛事、2.官方活动") @RequestParam(required=true) Integer matchType,
		@ApiParam("兴趣标签,字段名:dictItemIds,例如：1,2,3") @RequestParam(required=false) String dictItemIds,
		@ApiParam("活动开始日期,字段名:startdate,例如：YYYY-MM") @RequestParam(required=false) String startdate
	) throws BusinessException{
		XaResult<Page<MacthVo>> xr=new XaResult<Page<MacthVo>> ();
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		if(XaUtil.isEmpty(matchType))
		{
			xr.error("赛事类型不能为空");
			return xr;
		}
		if(XaUtil.isNotEmpty(startdate))
		{
			if(XaUtil.formatDateString2Date(startdate, "yyyy-MM")==null)
			{
				xr.error("活动开始日期格式错误:YYYY-MM");
				return xr;
			}
		}
		return matchService.findMatchList(XaConstant.Status.publish,matchType,dictItemIds,startdate,pageable);
	}
	
	/**
	 * @Title: findMatchById
	 * @Description: 活动/赛事详情
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="活动/赛事详情",notes="活动/赛事详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findMatchById",method=RequestMethod.POST)
	public XaResult<MacthVo> findMatchById(
		@ApiParam("活动/赛事Id,字段名:id")  @RequestParam(required=true) Long id,
		@ApiParam("用户Id,字段名:userId")  @RequestParam(required=false) Long userId
	) throws BusinessException{
		XaResult<MacthVo> xr=new XaResult<MacthVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("活动/赛事Id不能为空！");
			return xr;
		}
		return matchService.findByMatchIdAndUserId(id,userId);
	}
}
