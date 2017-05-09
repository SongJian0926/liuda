package com.web.liuda.remote.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.MatchComment;
import com.web.liuda.remote.service.ApiMatchCommentService;
import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.service.ApiUserService;
import com.web.liuda.remote.vo.MatchCommentVo;
import com.web.liuda.remote.vo.UserVo;
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
 * @Title: ApiMatchCommentController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 活动/赛事评论表信息接口
 * @author flora.li
 * @date 2016年4月20日 下午13:00:00
 * @version V1.0
 */
@Api(value = "MatchComment", description = "活动/赛事评论表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/matchComment")
public class ApiMatchCommentController extends BaseController {
	
	@Autowired
	private ApiMatchCommentService matchCommentService;
	@Autowired
	private ApiMatchService matchService;
	@Autowired
	private ApiUserService userService;

	/**
	 * @Title: findMatchCommentList
	 * @Description: 活动/赛事评论列表
	 * @return    
	 */
	@ApiOperation(value="活动/赛事评论列表",notes="赛事评论列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMatchCommentList",method=RequestMethod.POST)
	public XaResult<Page<MatchCommentVo>> findMatchCommentList(
			@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
			@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
			@ApiParam("赛事Id,字段名:matchId")  @RequestParam(required=true) Long matchId
	) throws BusinessException{
		XaResult<Page<MatchCommentVo>> xr=new XaResult<Page<MatchCommentVo>> ();
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事Id不能为空！");
			return xr;
		}
		return matchCommentService.findMatchCommentList(XaConstant.Status.valid, matchId, pageable);
	}
	/**
	 * @Title: findMatchCommentNum
	 * author:changlu
	 * time:2016-05-12 11:07:00
	 * @Description: 活动/赛事评论数量
	 * @return    
	 */
	@ApiOperation(value="活动/赛事评论数量",notes="活动/赛事评论数量,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMatchCommentNum",method=RequestMethod.POST)
	public XaResult<MatchCommentVo> findMatchCommentNum(
			@ApiParam("赛事Id,字段名:matchId")  @RequestParam(required=true) Long matchId
	) throws BusinessException{
		XaResult<MatchCommentVo> xr=new XaResult<MatchCommentVo> ();
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事Id不能为空！");
			return xr;
		}
		return matchCommentService.findMatchCommentNum(XaConstant.Status.valid, matchId);
	}
	/**
	 * @Title: save
	 * @Description: 创建赛事评论/回复
	 * @return    
	 */
	@ApiOperation(value="创建赛事评论/回复",notes="创建赛事评论,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveMatchComment",method=RequestMethod.POST)
	public XaResult<MatchCommentVo> saveMatchComment(
		@ApiParam("用户Id,字段名:userId") @RequestParam(required=true,value = "userId") Long userId,
		@ApiParam("赛事Id,字段名:matchId") @RequestParam(required=true,value = "matchId") Long matchId,
		@ApiParam("评论Id,字段名:commentId,回复时必传") @RequestParam(required=false,value = "commentId") Long commentId,
		@ApiParam("内容,字段名:content") @RequestParam(required=true,value = "content") String content,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<MatchCommentVo> xr=new XaResult<MatchCommentVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(matchId)){
			xr.error("赛事Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("内容不能为空！");
			return xr;
		}
		//判断用户ID，判断赛事ID，判断评论ID
		UserVo userVo = userService.findOne(userId).getObject();
		matchService.findOne(matchId).getObject();
		if(XaUtil.isNotEmpty(commentId))
		{
			matchCommentService.findOne(commentId);
		}
		
		MatchComment model = new MatchComment();
		model.setUserId(userId);
		model.setContent(content);
		model.setMatchId(matchId);
		model.setCommentId(commentId);
		model.setPraiseNum(0);
		model.setStatus(XaConstant.Status.valid);
		xr = matchCommentService.createModel(model);
		if(xr.getCode()==XaConstant.Code.success && xr.getObject() instanceof MatchCommentVo)
		{
			xr.getObject().setUserVo(userVo);
		}
		return xr;
	}

	/**
	 * @Title: praise
	 * @Description: 对赛事评论点赞
	 * @return    
	 */
	@ApiOperation(value="对赛事评论点赞",notes="对赛事评论点赞,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="praiseMatchComment",method=RequestMethod.POST)
	public XaResult<MatchCommentVo> praiseMatchComment(
		@ApiParam("评论Id,字段名:commentId") @RequestParam(required=true,value = "commentId") Long commentId,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<MatchCommentVo> xr=new XaResult<MatchCommentVo> ();
		if(XaUtil.isEmpty(commentId)){
			xr.error("评论Id不能为空！");
			return xr;
		}
		return matchCommentService.praiseModel(commentId);
	}
	
}
