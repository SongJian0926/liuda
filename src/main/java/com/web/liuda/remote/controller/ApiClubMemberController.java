package com.web.liuda.remote.controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.remote.service.ApiClubMemberService;
import com.web.liuda.remote.vo.ClubMemberVo;
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
 * @Title: ApiClubMemberController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 俱乐部成员信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "ClubMember", description = "俱乐部成员接口(二期)", position = 10)
@Controller
@RequestMapping("/api/clubMember")
public class ApiClubMemberController extends BaseController {

	@Autowired
	private ApiClubMemberService clubMemberService;
	
	/**
	 * @Title: findClubMemberList
	 * @Description: 俱乐部成员列表
	 * author:changlu
	 * time:2016-04-11 17:41:00
	 * @return    
	 */
	@ApiOperation(value="俱乐部成员列表——web端和移动端使用",notes="俱乐部成员列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findClubMemberList",method=RequestMethod.POST)
	public XaResult<Page<ClubMemberVo>> findClubMemberList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("俱乐部Id,字段名:clubId") @RequestParam(value="clubId") Long clubId
		 
	) throws BusinessException{
		XaResult<Page<ClubMemberVo>> xr=new XaResult<Page<ClubMemberVo>>();
		if(XaUtil.isEmpty(clubId)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}
		
		if(clubId<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_clubId':'"+clubId+"'}", "search_");
		
		return clubMemberService.findClubMemberList(XaConstant.Status.valid, filterParams, pageable);
	}
	/**
	 * @Title: findNewClubMemberList
	 * @Description: 俱乐部新成员列表
	 * author:changlu
	 * time:2016-04-11 17:41:00
	 * @return    
	 */
	@ApiOperation(value="俱乐部新成员列表（部长和管理员才展示此列表）",notes="俱乐部新成员列表，只有部长和管理员才能看见此列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findNewClubMemberList",method=RequestMethod.POST)
	public XaResult<Page<ClubMemberVo>> findNewClubMemberList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("俱乐部Id,字段名:clubId,必填") @RequestParam(value="clubId") Long clubId,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value="userId") Long userId
	) throws BusinessException{
		XaResult<Page<ClubMemberVo>> xr=new XaResult<Page<ClubMemberVo>>();
		Map<String , Object> filterParams=null;
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(clubId)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}
		
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(clubId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		filterParams=WebUitl.getParametersStartingWith("{'search_EQ_clubId':'"+clubId+"','search_EQ_userId':'"+userId+"'}", "search_");
		
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		
		return clubMemberService.findNewClubMemberList(XaConstant.Status.valid, filterParams, pageable);
	}
	
	
	/**
	 * @Title: save
	 * @Description: 申请加入
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="申请加入",notes="申请加入,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveClubMember",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> saveClubMember(
		@ApiParam("俱乐部Id,字段名:clubIds,多个以逗号隔开") @RequestParam(value = "clubIds") String clubIds,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		HttpServletRequest request
	) throws BusinessException{
		ClubMember model = new ClubMember();
		//model.setClubId(clubId);
		model.setUserId(userId);
		model.setMemberType(JConstant.ClubRole.MEMBER);
		model.setApplyStatus(JConstant.ApplyStatus.CHECK);
		return clubMemberService.createClubMember(model,clubIds);
	}
	/**
	 * @Title: findClubMemberById
	 * @Description: 个人主页
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="个人主页/成员详情",notes="个人主页/成员详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findClubMemberById",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> findClubMemberById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId,
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=false) Long userId 
	) throws BusinessException{
		XaResult<ClubMemberVo> xr=new XaResult<ClubMemberVo>();
		if(XaUtil.isEmpty(modelId)){
			xr.error("编号不能为空！");
			return xr;
		}
		if(XaUtil.isNotEmpty(userId)&&userId<1){
			xr.error("编号不能小于1！");
			return xr;
		}
		return clubMemberService.findOneDetail(userId,modelId);
	}
	/**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 *//*
	@ApiOperation(value="修改俱乐部成员",notes="修改俱乐部成员,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateClubMember",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> updateClubMember(
		@ApiParam("俱乐部Id,字段名:clubId") @RequestParam(value = "clubId") Long clubId,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("成员类型,字段名:memberType") @RequestParam(value = "memberType") Integer memberType,
		@ApiParam("申请状态,字段名:applyStatus") @RequestParam(value = "applyStatus") Integer applyStatus,
		@ApiParam("版本编号,字段名:tId") @RequestParam(value = "tId") Long tId,
	HttpServletRequest request
	) throws BusinessException{
		ClubMember model = new ClubMember();
		model.setClubId(clubId);
		model.setUserId(userId);
		model.setMemberType(memberType);
		model.setApplyStatus(applyStatus);
		model.setId(tId);
		return clubMemberService.updateModel(model);
	}
	*/
	/**
	 * @Title: operateClubMemberById
	 * @Description: 同意加入、删除请求
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="同意加入、删除请求",notes="同意加入、删除请求,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateClubMemberById",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> operateClubMemberById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("申请状态,字段名:applyStatus,2.审核通过,同意时传此字段") @RequestParam(required=false) Integer applyStatus,
		@ApiParam("操作类型,字段名:status,3删除；删除请求时传此字段") @RequestParam(required=false) Integer status
	) throws BusinessException{
		XaResult<ClubMemberVo> xr=new XaResult<ClubMemberVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空！");
			return xr;
		}
		//判断Id是否正确
		if(id<1){
			xr.error("id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(applyStatus)&&XaUtil.isEmpty(status)){
			xr.error("申请状态和操作类型不能同时为空！");
			return xr;
		}
		if(XaUtil.isNotEmpty(applyStatus)&&XaUtil.isNotEmpty(status)){
			xr.error("申请状态和操作类型只能传一个！");
			return xr;
		}
		if(XaUtil.isNotEmpty(status)&&status!=3){
			xr.error("操作状态只能是3！");
			return xr;
		}
		if(XaUtil.isNotEmpty(applyStatus)&&applyStatus!=2){
			xr.error("申请状态只能是2！");
			return xr;
		}
		return clubMemberService.multiClubMember(userId,id,applyStatus,status);
	}
	/**
	 * @Title:inviteClubMember
	 * @Description: 邀请成员
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="邀请成员",notes="邀请成员,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="inviteClubMember",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> inviteClubMember(
		HttpServletRequest request,
		@ApiParam("被邀请的用户Id,字段名:modelIds,必填，id以逗号隔开") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("俱乐部Id,字段名:clubId,设置管理时必传") @RequestParam(required=false) Long clubId 
		
	) throws BusinessException{
		XaResult<ClubMemberVo> xr=new XaResult<ClubMemberVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(clubId)){
			xr.error("俱乐部Id不能为空！");
			return xr;
		}
		if(clubId<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(modelIds)){
			xr.error("被邀请的用户Id不能为空！");
			return xr;
		}
	
		return clubMemberService.inviteClubMember(modelIds,userId,clubId);
	}
	/**
	 * @Title:deleteClubMember
	 * @Description: 删除成员、设置管理员
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="删除成员、设置管理员",notes="删除成员、设置管理员,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="deleteClubMember",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> deleteClubMember(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填,多个以逗号隔开") @RequestParam(value = "modelId") String modelIds,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("俱乐部Id,字段名:clubId,设置管理时必传") @RequestParam(required=false) Long clubId,
		@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,删除成员时必传,status和memberType只能传其中一个") @RequestParam(required=false) Integer status,
		@ApiParam("成员类型,字段名:memberType,1.部长，2.管理员，3.普通成员,设置管理员时必传") @RequestParam(required=false) Integer memberType
	) throws BusinessException{
		XaResult<ClubMemberVo> xr=new XaResult<ClubMemberVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isNotEmpty(status)&&XaUtil.isNotEmpty(memberType)){
			xr.error("操作类型和成员类型只能传一个！");
			return xr;
		}
		if(XaUtil.isEmpty(status)&&XaUtil.isEmpty(memberType)){
			xr.error("操作类型和成员类型不能同时为空！");
			return xr;
		}
		//设置管理员时俱乐部Id和成员类型必填
		if(XaUtil.isNotEmpty(memberType)){
			if(XaUtil.isEmpty(clubId)){
				xr.error("设置管理员俱乐部Id不能为空！");
				return xr;
			}
			if(clubId<1){
				xr.error("俱乐部Id不能小于1！");
				return xr;
			}
			
			String[] ids = modelIds.split(",");
			if(ids.length>3){
				xr.error("管理员最多3个！");
				return xr;
			}
			
		}
		return clubMemberService.multiOperate1(modelIds,clubId,status,memberType);
	}
	/**
	 * @Title:deleteClubMember
	 * @Description: 取消加入
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="取消加入",notes="取消加入,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="cancelClubMember",method=RequestMethod.POST)
	public XaResult<ClubMemberVo> cancelClubMember(
		HttpServletRequest request,
		@ApiParam("俱乐部Id,字段名:clubId,必填") @RequestParam(value = "clubId") Long clubId,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("操作类型,字段名:status,3删除") @RequestParam(value="status") Integer status
	) throws BusinessException{
		XaResult<ClubMemberVo> xr=new XaResult<ClubMemberVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(clubId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(clubId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isNotEmpty(status)&&status!=3){
			xr.error("操作状态只能是3！");
			return xr;
		}
		return clubMemberService.cancelJoin(clubId,userId,status);
	}
	
}

