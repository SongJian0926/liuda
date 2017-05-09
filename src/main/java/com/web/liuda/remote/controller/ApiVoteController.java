package com.web.liuda.remote.controller;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiVoteService;
import com.web.liuda.remote.vo.VoteVo;
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
 * @Title: ApiVoteController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 投票主表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Vote", description = "投票接口(二期)", position = 10)
@Controller
@RequestMapping("/api/vote")
public class ApiVoteController extends BaseController {

	@Autowired
	private ApiVoteService voteService;
	
	/**
	 * @Title: findVoteList
	 * @Description: 查询所有投票信息
	 * @return    
	 */
	@ApiOperation(value="查询所有投票",notes="查询所有投票信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findVoteList",method=RequestMethod.POST)
	public XaResult<Page<VoteVo>> findVoteList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{}", "search_");
		return voteService.findListEQStatusByFilters(XaConstant.Status.publish, filterParams, pageable);
	}
	
	/**
	 * @Title: findVoteById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="根据tId查询投票",notes="查询投票详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findVoteById",method=RequestMethod.POST)
	public XaResult<VoteVo> findVoteById(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId,
		@ApiParam("用户id,字段名:userId,非必填") @RequestParam(value = "userId",required = false) Long userId
	) throws BusinessException{
		XaResult<VoteVo> xr = new XaResult<VoteVo>();
		if(XaUtil.isEmpty(modelId)){
			xr.error("投票id不可为空");
			return xr;
		}
		
		return voteService.findOneByUserId(modelId,userId);
	}
	/**
	 * @Title: voting
	 * @Description: 投票
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="投票",notes="查询投票详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="voting",method=RequestMethod.POST)
	public XaResult<String> voting(
		@ApiParam("编号,字段名:modelId,必填") @RequestParam(value = "modelId") Long modelId,
		@ApiParam("用户id,字段名:userId,非必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("投票选项id,字段名:voteOptiones,多个投票用,分割 如 1,2,3") @RequestParam(value = "voteOptiones") String voteOptiones
	) throws BusinessException{
		XaResult<String> xr = new XaResult<String>();
		if(XaUtil.isEmpty(modelId)){
			xr.error("投票id不可为空");
			return xr;
		}
		return voteService.voting( modelId, userId, voteOptiones);
	}
}

