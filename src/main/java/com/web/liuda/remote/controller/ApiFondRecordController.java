package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiFondRecordService;
import com.web.liuda.remote.vo.FondRecordVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiFondRecordController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 现金记录信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "FondRecord", description = "现金记录接口(二期)", position = 10)
@Controller
@RequestMapping("/api/fondRecord")
public class ApiFondRecordController extends BaseController {

	@Autowired
	private ApiFondRecordService fondRecordService;
	
	/**
	 * @Title: findFondRecordList
	 * @Description: 查询用户现金记录
	 * @return    
	 */
	@ApiOperation(value="查询用户现金记录",notes="查询用户现金记录信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findFondRecordListByUserId",method=RequestMethod.POST)
	public XaResult<FondRecordVo> findFondRecordList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户id,字段名:userId") @RequestParam() Long userId
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		return fondRecordService.findFondRecordListByUserId(pageable,userId);
	}
	/**
	 * @Title: withdrawCashIng
	 * @Description: 提现
	 * @return    
	 */
	@ApiOperation(value="用户提现",notes="查询用户现金记录信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="withdrawCashIng",method=RequestMethod.POST)
	public XaResult<String> withdrawCashIng(
		@ApiParam("用户id,字段名:userId") @RequestParam() Long userId,
		@ApiParam("提现金额,字段:cash") @RequestParam() Double cash,
		@ApiParam("银行卡id,字段名:cardId") @RequestParam() Long cardId 
	) throws BusinessException{
		XaResult<String> xr=new XaResult<String> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(cash)){
			xr.error("请输入提现金额！");
			return xr;
		}
		if(XaUtil.isEmpty(cardId)){
			xr.error("银行卡Id不能为空！");
			return xr;
		}
		return fondRecordService.withdrawCashIng(userId,cash,cardId);
	}
}

