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

import com.web.liuda.remote.service.ApiFansService;
import com.web.liuda.remote.vo.FansVo;
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
 * @Title: ApiFansController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 粉丝表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Fans", description = "粉丝表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/fans")
public class ApiFansController extends BaseController {

	@Autowired
	private ApiFansService fansService;
	
	/**
	 * @Title: findFansList
	 * @Description: 关注我的人
	 * author:changlu
	 * @return    
	 */
	@ApiOperation(value="邀请成员列表",notes="邀请成员列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findFansList",method=RequestMethod.POST)
	public XaResult<Page<FansVo>> findFansList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId ,
		@ApiParam("俱乐部Id,字段名:clubId,邀请成员时必填") @RequestParam(required=false,value = "clubId") Long clubId
	) throws BusinessException{
		XaResult<Page<FansVo>> xr=new XaResult<Page<FansVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isNotEmpty(clubId)&&clubId<1){
			xr.error("俱乐部Id不能小于1！");
			return xr;
		}
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return fansService.findMyFansList(XaConstant.Status.valid, userId,clubId,filterParams, pageable);
	}
	/**
	 * @Title: findFansList
	 * @Description: 我的好友
	 * author:songjian
	 * @return    
	 */
	@ApiOperation(value="我的好友，我的好友列表",notes="我的好友，我的好友列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMyFrindList",method=RequestMethod.POST)
	public XaResult<FansVo> findMyFrindList(
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<FansVo> xr=new XaResult<FansVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		
		return fansService.findMyFrindList(XaConstant.Status.valid, userId);
	}
	/**
	 * @Title: findFansList
	 * @Description: 我的好友
	 * author:songjian
	 * @return    
	 */
	@ApiOperation(value="我的粉丝，我的粉丝列表",notes="我的粉丝，我的粉丝列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMyFansList",method=RequestMethod.POST)
	public XaResult<Page<FansVo>> findMyFansList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<Page<FansVo>> xr=new XaResult<Page<FansVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		return fansService.findMyFansList(XaConstant.Status.valid, userId, pageable);
	}
	/**
	 * @Title: addFrinding
	 * @Description: 加为好友/关注
	 * author:songjian
	 * @return    
	 */
	@ApiOperation(value="加为好友/关注，加为我的好友/我的关注",notes="我的好友/关注，我的好友列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="addFrinding",method=RequestMethod.POST)
	public XaResult<FansVo> addFrinding(
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("粉丝Id,字段名:frindId,必填") @RequestParam(value = "frindId") Long frindId
	) throws BusinessException{
		XaResult<FansVo> xr=new XaResult<FansVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(frindId)){
			xr.error("粉丝Id不能为空！");
			return xr;
		}
		if(frindId<1){
			xr.error("粉丝Id不能小于1！");
			return xr;
		}
		if(userId==frindId){
			xr.error("您不能关注自己！");
			return xr;
		}
		return fansService.addFrinding(userId, frindId);
	}
	/**
	 * @Title:deleteFans
	 * @Description: 取消关注（对人）
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="取消关注（对人）",notes="取消关注（对人）,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="cancelFans",method=RequestMethod.POST)
	public XaResult<FansVo> cancelFans(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelIds") String modelIds,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<FansVo> xr=new XaResult<FansVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(modelIds)){
			xr.error("编号不能为空！");
			return xr;
		}
		return fansService.multiOperate(modelIds,XaConstant.Status.delete);
	}
}

