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

import com.web.liuda.business.entity.FeedBack;
import com.web.liuda.remote.service.ApiFeedBackService;
import com.web.liuda.remote.vo.FeedBackVo;
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
 * @Title: ApiFeedBackController.java
 * @Package com.web.jnews.remote.controller
 * @Description: 意见反馈信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "FeedBack", description = "意见反馈", position = 10)
@Controller
@RequestMapping("/api/feedBack")
public class ApiFeedBackController extends BaseController {

	@Autowired
	private ApiFeedBackService feedBackService;
	
	/**
	 * @Title: findFeedBacks
	 * @Description: 查询意见反馈
	 * @return    
	 */
	@ApiOperation(value="查询意见反馈",notes="查询意见反馈,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findFeedBacks",method=RequestMethod.POST)
	public XaResult<Page<FeedBackVo>> findFeedBacks(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<Page<FeedBackVo>> xr=new XaResult<Page<FeedBackVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空!");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_userId':'" + userId + "'}", "search_");
		return feedBackService.findPageNEStatusByFilter(XaConstant.Status.delete, filterParams, pageable);
	}
	
	/**
	 * @Title: save
	 * @Description: 发表意见
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="发表意见",notes="发表意见,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveFeedBack",method=RequestMethod.POST)
	public XaResult<FeedBackVo> saveFeedBack(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("反馈内容,字段名:content") @RequestParam(value = "content") String content,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<FeedBackVo> xr = new XaResult<FeedBackVo>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空");
			return xr;
		}
		if(XaUtil.isEmpty(content)){
			xr.error("反馈内容不能为空");
			return xr;
		}
		FeedBack model = new FeedBack();
		model.setUserId(userId);
		model.setContent(content);
		return feedBackService.createModel(model);
	}
	
}

