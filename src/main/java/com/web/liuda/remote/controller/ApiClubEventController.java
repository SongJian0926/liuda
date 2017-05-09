package com.web.liuda.remote.controller;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ClubEvent;
import com.web.liuda.remote.service.ApiClubEventService;
import com.web.liuda.remote.vo.ClubEventVo;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiClubEventController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 俱乐部活动信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "ClubEvent", description = "俱乐部活动接口(二期)", position = 10)
@Controller
@RequestMapping("/api/clubEvent")
public class ApiClubEventController extends BaseController {

	@Autowired
	private ApiClubEventService clubEventService;
	
	/**
	 * @Title: findClubEventList
	 * @Description: 活动列表
	 * author:changlu
	 * time:2016-04-13 09:40:00
	 */
	
	@ApiOperation(value="活动列表——web端和移动端使用",notes="活动列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findClubEventList",method=RequestMethod.POST)
	public XaResult<Page<ClubEventVo>> findClubEventList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("俱乐部Id,字段名:clubId,必传") @RequestParam(value="clubId") Long clubId
		
	) throws BusinessException{
		XaResult<Page<ClubEventVo>> xr=new XaResult<Page<ClubEventVo>>();
		if(XaUtil.isEmpty(clubId)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}else if(clubId<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_clubId':'"+clubId+"'}", "search_");
		return clubEventService.findClubEventList(XaConstant.Status.valid, filterParams, pageable);
	}
	/**
	 * @Title: findTestClubEventList
	 * @Description: web端活动验证列表——web端使用
	 * author:changlu
	 * time:2016-04-13 09:40:00
	 */
	
	@ApiOperation(value="web端活动验证列表——web端使用",notes="web端活动验证列表——web端使用,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findTestClubEventList",method=RequestMethod.POST)
	public XaResult<Page<ClubEventVo>> findTestClubEventList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		
		@ApiParam("用户Id,字段名:userId,必传") @RequestParam(value="userId") Long userId
		
	) throws BusinessException{
		XaResult<Page<ClubEventVo>> xr=new XaResult<Page<ClubEventVo>>();
		
		if(XaUtil.isEmpty(userId)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_userId':'"+userId+"'}", "search_");
		return clubEventService.findTestClubEventList(XaConstant.Status.valid, filterParams, pageable);
	}
	/**
	 * @Title: findClubEventPersonnelPgae
	 * @Description: 活动人员名单(web端)
	 * author:sj
	 * time:2016-04-13 09:40:00
	 */
	@ApiOperation(value="活动人员名单——web端使用",notes="活动人员名单(web端),当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findClubEventPersonnelPgae",method=RequestMethod.POST)
	public XaResult<Page<MatchOrderDetailVo>> findClubEventPersonnelPgae(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("俱乐部活动Id,字段名:clubEventId,必传") @RequestParam(value="clubEventId") Long clubEventId
		
	) throws BusinessException{
		XaResult<Page<MatchOrderDetailVo>> xr=new XaResult<Page<MatchOrderDetailVo>>();
		if(XaUtil.isEmpty(clubEventId)){
			xr.error("俱乐部活动Id不能为空！");
			return xr;
		}else if(clubEventId<1){
			xr.error("俱乐部活动Id不能小于1！");
			return xr;
		}
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_clubId':'"+clubEventId+"'}", "search_");
		
		return clubEventService.findClubEventPersonnelPgae(XaConstant.Status.valid, filterParams, pageable,clubEventId);
	}
	/**
	 * @Title: upload
	 * @Description: 活动人员名单导出(web端)
	 * @param photoFile
	 * @param request
	 * @return
	 * @throws BusinessException
	 */
	@ApiOperation(value="活动人员名单导出——web端使用",notes="活动人员名单导出(web端),当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="fileDownload",method=RequestMethod.GET)
	public void fileDownload(
			@ApiParam("俱乐部活动Id,字段名:clubEventId,必传") @RequestParam(value="clubEventId") Long clubEventId,
			@RequestParam(defaultValue = "{}") String jsonFilter,
			HttpServletResponse response) throws BusinessException {
		try {
			jsonFilter = URLDecoder.decode(jsonFilter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		clubEventService.exportdata(filterParams, response, clubEventId);
	}
	/**
	 * @Title: findClubEventById
	 * @Description: 活动详情
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="活动详情——web端和移动端使用",notes="活动详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findClubEventById",method=RequestMethod.POST)
	public XaResult<ClubEventVo> findClubEventById(
		@ApiParam("活动Id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=false,value = "userId") Long userId
	) throws BusinessException{
		XaResult<ClubEventVo> xr=new XaResult<ClubEventVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("活动Id不能为空！");
			return xr;
		}else if(id<1){
			xr.error("活动Id不能小于1！");
			return xr;
		}
		//如果用户不为空
		if(XaUtil.isNotEmpty(userId)&&userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		return clubEventService.findClubEventDetail(userId,id,1);
		
	}
	
	/**
	 * @Title: save
	 * @Description: 创建活动
	 * author:changlu
	 * time：2016-04-13 11:12:00
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="创建活动——web端和移动端使用",notes="创建活动,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveClubEvent",method=RequestMethod.POST)
	public XaResult<ClubEventVo> saveClubEvent(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("俱乐部Id,字段名:clubId") @RequestParam(value = "clubId") Long clubId,
		@ApiParam("活动标题,字段名:title") @RequestParam(value = "title") String title,
		@ApiParam("头像,字段名:logo") @RequestParam(value = "logo") String logo,
		@ApiParam("活动开始时间,字段名:starttime") @RequestParam(value = "starttime") String starttime,
		@ApiParam("活动结束时间,字段名:endtime") @RequestParam(value = "endtime") String endtime,
		@ApiParam("活动费用,字段名:price") @RequestParam(value = "price") Double price,
		@ApiParam("报名截止日期,字段名:deadline") @RequestParam(value = "deadline") String deadline,
		@ApiParam("最大人数限制,字段名:maxNum") @RequestParam(value = "maxNum") Integer maxNum,
		@ApiParam("活动说明 ,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("活动区域,字段名:areaCode") @RequestParam(value = "areaCode") String areaCode,
		@ApiParam("详细地址,字段名:address") @RequestParam(value = "address") String address,
		@ApiParam("报名条件,字段名:	requirement") @RequestParam(required=false,value = "requirement") String requirement,
		@ApiParam("定金,字段名:disposit") @RequestParam(required=false,value = "disposit") Double disposit,
		@ApiParam("图片,字段名:mediaPath,最多六张图，图片之间以逗号分隔开") @RequestParam(required=false,value = "mediaPath") String mediaPath,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<ClubEventVo> xr=new XaResult<ClubEventVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(clubId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(clubId<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(title)){
			xr.error("活动标题不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(logo)){
			xr.error("头像不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(starttime)){
			xr.error("活动开始时间不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(endtime)){
			xr.error("活动结束时间不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(price)){
			xr.error("活动费用不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(deadline)){
			xr.error("报名截止日期不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(maxNum)){
			xr.error("最大人数限制不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("活动说明不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(areaCode)){
			xr.error("活动地点区域不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(address)){
			xr.error("详细地址不能为空！");
			return xr;
		}
		if(XaUtil.isNotEmpty(maxNum)&&!Validator.isNumber(maxNum.toString())){
			xr.error("最大限制人数格式不正确！");
			return xr;
		}
		if(XaUtil.isNotEmpty(price)&&!Validator.isDouble(price.toString())){
			xr.error("报名费用格式不正确！");
			return xr;
		}
		if(XaUtil.isNotEmpty(disposit)&&!Validator.isDouble(disposit.toString())){
			xr.error("定金格式不正确！");
			return xr;
		}
		if(XaUtil.isNotEmpty(disposit)&&disposit>price){
			xr.error("定金不能大于活动费用！");
			return xr;
		}
		if(deadline.compareTo(XaUtil.getToDayStr())<0){
			xr.error("报名截止不能小于当前时间！");
			return xr;
		}
		if(starttime.compareTo(deadline)<0){
			xr.error("开始时间不能小于报名截止时间！");
			return xr;
		}
		if(endtime.compareTo(starttime)<0){
			xr.error("结束日期不能小于开始时间！");
			return xr;
		}
		ClubEvent model = new ClubEvent();
		model.setClubId(clubId);
		model.setTitle(title);
		model.setLogo(logo);
		model.setStarttime(starttime);
		model.setEndtime(endtime);
		model.setEventStatus(JConstant.EventStatus.ENROLL);
		model.setPrice(price);
		model.setDeadline(deadline);
		model.setMaxNum(maxNum);
		model.setContent(content);
		model.setAddress(address);
		model.setAreaCode(areaCode);
		model.setDisposit(disposit);
		model.setMediaPath(mediaPath);
		model.setRequirement(requirement);
		//根据地址换为经度和纬度
		model.setLng(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLng());
		model.setLat(HttpURLConnectionUtil.getLocation('"'+model.getAreaCode()+model.getAddress()+'"').getResult().getLocation().getLat());
		return clubEventService.createClubEvent(userId,clubId,model);
	}
	

}

