package com.web.liuda.remote.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiClubEventService;
import com.web.liuda.remote.service.ApiClubMemberService;
import com.web.liuda.remote.service.ApiClubService;
import com.web.liuda.remote.service.ApiGuideService;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.ClubMemberVo;
import com.web.liuda.remote.vo.ClubVo;
import com.web.liuda.remote.vo.GuideVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiClubController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 我的相关
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "AboutMy", description = "我的相关(二期)", position = 10)
@Controller
@RequestMapping("/api/aboutmy")
public class ApiAboutMyController extends BaseController {

	@Autowired
	private ApiClubService clubService;
	
	@Autowired
	private ApiClubMemberService clubMemberService;
	
	@Autowired
	private ApiGuideService guideService;
	@Autowired
	private ApiClubEventService clubEventService;
	/**
	 * @Title: findMyClubList
	 * @Description: 我的俱乐部
	 * author:changlu
	 * time:2016-04-19 10:14:00
	 * @return    
	 */
	@ApiOperation(value="我的俱乐部——移动端使用",notes="我的俱乐部,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMyClubList",method=RequestMethod.POST)
	public XaResult<List<ClubVo>> findMyClubList(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value="userId") Long userId 
	) throws BusinessException{
		XaResult<List<ClubVo>> xr=new XaResult<List<ClubVo>> ();
		
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
			
		return clubService.findMyClubList(userId);
		
	}
	/**
	 * @Title: findMyCreateClubList
	 * @Description: 我创建的俱乐部
	 * author:changlu
	 * time:2016-05-30 10:14:00
	 * @return    
	 */
	@ApiOperation(value="我创建的俱乐部——web端使用",notes="我创建的俱乐部,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMyCreateClubList",method=RequestMethod.POST)
	public XaResult<Page<ClubVo>> findMyCreateClubList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value="userId") Long userId 
	) throws BusinessException{
		XaResult<Page<ClubVo>> xr=new XaResult<Page<ClubVo>> ();
		
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");	
		return clubService.findMyCreateClubList(userId,pageable);
		
	}
	/**
	 * @Title: findMyJoinClubList
	 * @Description: 我加入的俱乐部
	 * author:changlu
	 * time:2016-05-30 10:14:00
	 * @return    
	 */
	@ApiOperation(value="我加入的俱乐部——web端使用",notes="我加入的俱乐部,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMyJoinClubList",method=RequestMethod.POST)
	public XaResult<Page<ClubVo>> findMyJoinClubList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value="userId") Long userId 
	) throws BusinessException{
		XaResult<Page<ClubVo>> xr=new XaResult<Page<ClubVo>> ();
		
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");	
		return clubService.findMyJoinClubList(userId,pageable);
		
	}
	/**
	 * @Title: joinClub
	 * @Description: 加入（同意别人的邀请）
	 * @return    
	 */
	@ApiOperation(value="加入（同意别人的邀请）",notes="加入（同意别人的邀请）,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="joinClub",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> joinClub(
		@ApiParam("俱乐部成员Id,字段名:clubEventId") @RequestParam(value = "clubEventId") Long clubEventId,
		HttpServletRequest request
	) throws BusinessException{
	
		return clubMemberService.joinClub(clubEventId);
	}
	
	/**
	 * @Title: findMyGuide
	 * @Description: 我的攻略
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="我的攻略——web端和移动端使用",notes="我的攻略,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findMyGuide",method=RequestMethod.POST)
	public XaResult<Page<GuideVo>> findMyGuide(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
		
	) throws BusinessException{
		XaResult<Page<GuideVo>> xr=new XaResult<Page<GuideVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		return guideService.findMyGuideList(userId,pageable);
	}
	/**
	 * @Title: findMyEvent
	 * @Description: 我的活动
	 * @param modelId
	 * author:changlu
	 * time:2016-04-19 14:24:00
	 * @return    
	 */
	@ApiOperation(value="我的活动",notes="我的活动,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findMyEvent",method=RequestMethod.POST)
	public XaResult<Page<ClubEventVo>> findMyEvent(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("活动状态,字段名:eventStatus,1:已关注,2:已报名,3:已结束") @RequestParam(value = "eventStatus") Integer eventStatus
		
	) throws BusinessException{
		XaResult<Page<ClubEventVo>> xr=new XaResult<Page<ClubEventVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(eventStatus)){
			xr.error("活动状态不能为空！");
			return xr;
		}
		if(eventStatus!=1&&eventStatus!=2&&eventStatus!=3){
			xr.error("活动状态只能是1或2或3！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		return clubEventService.findMyEventList(userId,eventStatus,pageable);
	}
}

