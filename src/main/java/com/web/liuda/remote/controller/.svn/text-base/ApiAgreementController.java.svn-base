package com.web.liuda.remote.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.remote.service.ApiAgreementService;
import com.web.liuda.remote.vo.AgreementVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiAgreementController.java
 * @Package com.web.liuda.remote.controller
 * @Description: agreement信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Agreement", description = "协议/说明 接口(二期)", position = 10)
@Controller
@RequestMapping("/api/agreement")
public class ApiAgreementController extends BaseController {

	@Autowired
	private ApiAgreementService agreementService;
	
	/**
	 * @Title: findByType
	 * @Description: 根据类型查找单条实体
	 * @param type
	 * @return    
	 */
	@ApiOperation(value="协议/说明内容",notes="协议/说明内容,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findByType",method=RequestMethod.POST)
	public XaResult<AgreementVo> findByType(
		@ApiParam("类型,字段名:type,1,投票说明；2，活动报名协议；3，赛事报名协议;4，竞猜说明") @RequestParam(value = "type") Integer type
	) throws BusinessException{
		XaResult<AgreementVo> xr=new XaResult<AgreementVo> ();
		if(XaUtil.isEmpty(type)){
			xr.error("类型不能为空！");
			return xr;
		}
		if(type!=1&&type!=2&&type!=3&&type!=4){
			xr.error("类型只能是1或者2或者3或者4！");
			return xr;
		}
		return agreementService.findByType(type);
	}
}

