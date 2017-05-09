package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiMatchOrderDetailService;
import com.web.liuda.remote.vo.MatchOrderDetailVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiMatchOrderDetailController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 赛事订单详细表信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "MatchOrderDetail", description = "赛事订单详细表接口(二期)", position = 10)
@Controller
@RequestMapping("/api/matchOrderDetail")
public class ApiMatchOrderDetailController extends BaseController {

	@Autowired
	private ApiMatchOrderDetailService matchOrderDetailService;
	
	
	/**
	 * @Title: findRedeemCode
	 * author：changlu
	 * time:2016-04-26 16:40:00
	 * @Description: web端校验兑换码	
	 * @param businessId 
	 * @param businessType		
	 * @param orderNo
	 * @param orderStatus
	 * @return    
	 */
	@ApiOperation(value="web端校验兑换码——web端使用",notes="web端校验兑换码,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="findRedeemCode",method=RequestMethod.POST)
	public XaResult<MatchOrderDetailVo> findOrder(
		@ApiParam("用户Id,字段名:userId,必传") @RequestParam(value = "userId") Long userId,
		@ApiParam("俱乐部活动Id,字段名:clubEventId,必传") @RequestParam(value = "clubEventId") Long clubEventId,
		@ApiParam("兑换码,字段名:redeemCode,必传") @RequestParam(value = "redeemCode") String redeemCode
	) throws BusinessException{
		XaResult<MatchOrderDetailVo> xr=new XaResult<MatchOrderDetailVo> ();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空!");
			return xr;
		}
		if(XaUtil.isEmpty(clubEventId)){
			xr.error("俱乐部活动Id不能为空!");
			return xr;
		}
		if(XaUtil.isEmpty(redeemCode)){
			xr.error("兑换码不能为空!");
			return xr;
		}
		if(!Validator.isNumber(redeemCode)){
			xr.error("兑换码是数字组成的字符串!");
			return xr;
		}
		return matchOrderDetailService.findOrder(userId,redeemCode,clubEventId);
	}
	
}

