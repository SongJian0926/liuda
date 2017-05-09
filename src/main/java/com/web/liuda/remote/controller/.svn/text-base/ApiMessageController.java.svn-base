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

import com.web.liuda.remote.service.ApiMessageService;
import com.web.liuda.remote.vo.MessageVo;
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
 * @Title: ApiMessageController.java
 * @Package com.web.jnews.remote.controller
 * @Description: 消息中心信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Message", description = "消息中心(在一期基础上修改了一部分)", position = 10)
@Controller
@RequestMapping("/api/message")
public class ApiMessageController extends BaseController {

	@Autowired
	private ApiMessageService messageService;
	
	/**
	 * @Title: findMessages
	 * @Description: 查询我的消息列表
	 * @return    
	 */
	@ApiOperation(value="查询我的消息列表",notes="查询我的消息列表,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findMessageList",method=RequestMethod.POST)
	public XaResult<Page<MessageVo>> findMessageList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<Page<MessageVo>> xr=new XaResult<Page<MessageVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_userId':'" + userId + "'}", "search_");
		return messageService.findPageNEStatusByFilter(XaConstant.Status.delete, filterParams, pageable);
	}
	
	/**
	 * @Title: findMessageDetail
	 * @Description: 消息详情
	 * @param id
	 * @return    
	 */
	@ApiOperation(value="消息详情",notes="消息详情,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findMessageDetail",method=RequestMethod.POST)
	public XaResult<MessageVo> findMessageDetail(
		@ApiParam("消息Id,字段名:id,必填") @RequestParam(value = "id") Long id,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<MessageVo> xr=new XaResult<MessageVo>();
		
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空！");
			return xr;
		}
		return messageService.findOneMessage(id,userId);
	}
	/**
	 * @Title: findNewMessage
	 * @Description: 是否有新消息
	 * @param id
	 * @return    
	 */
	@ApiOperation(value="是否有新消息（二期）",notes="是否有新消息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findNewMessage",method=RequestMethod.POST)
	public XaResult<MessageVo> findNewMessage(
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<MessageVo> xr=new XaResult<MessageVo>();
		
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}else if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		return messageService.findNewMessage(userId);
	}
}

