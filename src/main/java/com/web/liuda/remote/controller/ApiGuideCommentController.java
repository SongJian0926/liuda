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

import com.web.liuda.business.entity.GuideComment;
import com.web.liuda.remote.service.ApiGuideCommentService;
import com.web.liuda.remote.vo.GuideCommentVo;
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
 * @Title: ApiGuideCommentController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 攻略评论表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "GuideComment", description = "攻略评论表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/guideComment")
public class ApiGuideCommentController extends BaseController {

	@Autowired
	private ApiGuideCommentService guideCommentService;
	
	/**
	 * @Title: findGuideCommentList
	 * @Description: 攻略评论列表
	 * @return    
	 */
	@ApiOperation(value="攻略评论列表",notes="攻略评论列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findGuideCommentList",method=RequestMethod.POST)
	public XaResult<Page<GuideCommentVo>> findGuideCommentList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("攻略Id,字段名:guideId,必填") @RequestParam(value = "guideId") Long guideId
	) throws BusinessException{
		XaResult<Page<GuideCommentVo>> xr=new XaResult<Page<GuideCommentVo>>();
		if(XaUtil.isEmpty(guideId)){
			xr.error("攻略Id不能为空！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_guideId':'"+guideId+"'}", "search_");
		return guideCommentService.findGuideContentList(XaConstant.Status.valid, filterParams, pageable);
	}
	
	
	
	/**
	 * @Title: save
	 * @Description: 评论攻略
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="评论攻略",notes="评论攻略,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveGuideComment",method=RequestMethod.POST)
	public XaResult<GuideCommentVo> saveGuideComment(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("评论内容,字段名:content") @RequestParam(value = "content") String content,
		@ApiParam("攻略Id,字段名:guideId") @RequestParam(value = "guideId") Long guideId,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<GuideCommentVo> xr=new XaResult<GuideCommentVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(guideId)){
			xr.error("攻略Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("攻略Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("内容不能为空！");
			return xr;
		}
		GuideComment model = new GuideComment();
		model.setUserId(userId);
		model.setContent(content);
		model.setGuideId(guideId);
		return guideCommentService.createModel(model);
	}
	
	
}

