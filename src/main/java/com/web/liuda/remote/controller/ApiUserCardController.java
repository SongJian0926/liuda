package com.web.liuda.remote.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.UserCard;
import com.web.liuda.remote.service.ApiUserCardService;
import com.web.liuda.remote.vo.UserCardVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiUserCardController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 用户银行卡信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "UserCard", description = "用户银行卡接口(二期)", position = 10)
@Controller
@RequestMapping("/api/userCard")
public class ApiUserCardController extends BaseController {

	@Autowired
	private ApiUserCardService userCardService;
	
	/**
	 * @Title: save
	 * @Description: 绑定银行卡
	 * author:changlu
	 * time:2016-04-26 09:53:00
	 * @return    
	 */
	@ApiOperation(value="绑定银行卡",notes="绑定银行卡,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveUserCard",method=RequestMethod.POST)
	public XaResult<UserCardVo> saveUserCard(
		@ApiParam("用户id,字段名:userId") @RequestParam(value = "userId") Long userId,
		@ApiParam("银行卡号,字段名:bankCardId") @RequestParam(value = "bankCardId") String bankCardId,
		@ApiParam("持卡人,字段名:cardHolder") @RequestParam(value = "cardHolder") String cardHolder,
		@ApiParam("身份证号,字段名:idcard") @RequestParam(value = "idcard") String idcard,
		@ApiParam("开户银行,字段名:openingBank") @RequestParam(value = "openingBank") String openingBank,
		@ApiParam("开户区域,字段名:openingArea") @RequestParam(value = "openingArea") String openingArea,
		@ApiParam("支行名称,字段名:subBankNam") @RequestParam(value = "subBankNam") String subBankNam,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<UserCardVo> xr=new XaResult<UserCardVo>(); 
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(bankCardId)){
			xr.error("银行卡号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(cardHolder)){
			xr.error("持卡人不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(idcard)){
			xr.error("身份证号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(openingBank)){
			xr.error("开户银行不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(openingArea)){
			xr.error("开户区域不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(subBankNam)){
			xr.error("支行名称不能为空！");
			return xr;
		}
		UserCard model = new UserCard();
		model.setUserId(userId);
		model.setBankCardId(bankCardId);
		model.setCardHolder(cardHolder);
		model.setIdcard(idcard);
		model.setOpeningBank(openingBank);
		model.setOpeningArea(openingArea);
		model.setSubBankNam(subBankNam);
		return userCardService.createModel(model);
	}
	/**
	 * @Title: operateUserCardById
	 * @Description: 删除银行卡
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="删除银行卡",notes="删除银行卡,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateUserCardById",method=RequestMethod.POST)
	public XaResult<UserCardVo> operateUserCardById(
		HttpServletRequest request,
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds
	) throws BusinessException{
		XaResult<UserCardVo> xr=new XaResult<UserCardVo>();
		if(XaUtil.isEmpty(modelIds)){
			xr.error("编号不能为空！");
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
		return userCardService.multiOperate(modelIds,XaConstant.Status.delete);
	}
	/**
	 * @Title: findUserCardList
	 * @Description: 银行卡列表
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="银行卡列表",notes="银行卡列表,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findUserCardList",method=RequestMethod.POST)
	public XaResult<List<UserCardVo>> findUserCardList(
		@ApiParam("用户Id,字段名:userId,必填") @RequestParam(value = "userId") Long userId
	) throws BusinessException{
		XaResult<List<UserCardVo>> xr=new XaResult<List<UserCardVo>>(); 
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		return userCardService.findUserCardList(userId);
	}
	/**
	 * @Title: save
	 * @Description: 修改银行卡
	 * author:sj
	 * time:2016-04-26 09:53:00
	 * @return    
	 */
	@ApiOperation(value="修改银行卡",notes="修改银行卡,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updataUserCard",method=RequestMethod.POST)
	public XaResult<String> updataUserCard(
		@ApiParam("用户Id,字段名:userId,必填")@RequestParam(value = "userId") Long userId,
		@ApiParam("银行卡ID,字段名:id,必填") @RequestParam(value = "modelId") Long id,
		@ApiParam("银行卡号,字段名:bankCardId") @RequestParam(value = "bankCardId") String bankCardId,
		@ApiParam("持卡人,字段名:cardHolder") @RequestParam(value = "cardHolder") String cardHolder,
		@ApiParam("身份证号,字段名:idcard") @RequestParam(value = "idcard") String idcard,
		@ApiParam("开户银行,字段名:openingBank") @RequestParam(value = "openingBank") String openingBank,
		@ApiParam("开户区域,字段名:openingArea") @RequestParam(value = "openingArea") String openingArea,
		@ApiParam("支行名称,字段名:subBankNam") @RequestParam(value = "subBankNam") String subBankNam,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<String> xr=new XaResult<String>(); 
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(userId<1){
			xr.error("用户Id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(id)){
			xr.error("银行id不能为空！");
			return xr;
		}
		if(id<1){
			xr.error("银行id不能小于1！");
			return xr;
		}
		if(XaUtil.isEmpty(bankCardId)){
			xr.error("银行卡号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(cardHolder)){
			xr.error("持卡人不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(idcard)){
			xr.error("身份证号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(openingBank)){
			xr.error("开户银行不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(openingArea)){
			xr.error("开户区域不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(subBankNam)){
			xr.error("支行名称不能为空！");
			return xr;
		}
		UserCard model = new UserCard();
		model.setBankCardId(bankCardId);
		model.setCardHolder(cardHolder);
		model.setIdcard(idcard);
		model.setOpeningBank(openingBank);
		model.setOpeningArea(openingArea);
		model.setSubBankNam(subBankNam);
		return userCardService.updataUserCard(userId,id,model);
	}
}

