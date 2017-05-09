package com.web.liuda.remote.controller;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.liuda.business.entity.Address;
import com.web.liuda.remote.service.ApiAddressService;
import com.web.liuda.remote.vo.AddressVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiAddressController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 收货地址信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Address", description = "收货地址接口", position = 10)
@Controller
@RequestMapping("/api/address")
public class ApiAddressController extends BaseController {

	@Autowired
	private ApiAddressService addressService;
	
	/**
	 * @Title: findAddressList
	 * @Description: 查询我的收货地址信息
	 * @return    
	 */
	@ApiOperation(value="查询我的收货地址",notes="查询我的收货地址信息,当返回的code=1时，取出object进行显示,存放为数组对象")
	@ResponseBody
	@RequestMapping(value="findAddressList",method=RequestMethod.POST)
	public XaResult<List<AddressVo>> findAddressList(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "100") Integer pageSize,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId 
	) throws BusinessException{
		XaResult<List<AddressVo>> xr=new XaResult<List<AddressVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, "[{property:'createTime',direction:'DESC'}]");
		Map<String , Object> filterParams =  WebUitl.getParametersStartingWith("{'search_EQ_userId':'" + userId + "'}", "search_");
		return addressService.findListEQStatusByFilter(XaConstant.Status.valid, filterParams, pageable);
	}
	
	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="新增收货地址",notes="新增收货地址,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveAddress",method=RequestMethod.POST)
	public XaResult<AddressVo> saveAddress(
		@ApiParam("收货人,字段名:consigneeName") @RequestParam(value = "consigneeName") String consigneeName,
		@ApiParam("手机号,字段名:mobile") @RequestParam(value = "mobile") String mobile,
		@ApiParam("省份,字段名:province") @RequestParam(value = "province") String province,
		@ApiParam("城市,字段名:city") @RequestParam(value = "city") String city,
		@ApiParam("区域,字段名:area") @RequestParam(value = "area") String area,
		@ApiParam("详细地址,字段名:detailAddress") @RequestParam(value = "detailAddress") String detailAddress,
		@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId ,
		@ApiParam("是否默认,字段名:isDefault 0:否，1:是") @RequestParam(value = "isDefault") Integer isDefault,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<AddressVo> xr=new XaResult<AddressVo>();
		if(XaUtil.isEmpty(consigneeName)){
			xr.error("收货人不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(mobile)){
			xr.error("手机号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(province)){
			xr.error("省份不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(city)){
			xr.error("城市不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(area)){
			xr.error("区域不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(detailAddress)){
			xr.error("详细地址不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(isDefault)){
			xr.error("是否默认不能为空！");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("请输入正确的手机号！");
			return xr;
		}
		if(!(isDefault==0||isDefault==1)){
			xr.error("输入的是否默认非法！");
			return xr;
		}
		if(detailAddress.length() > 50){
			xr.error("详细地址不能超过50个字");
			return xr;
		}
		Address model = new Address();
		model.setConsigneeName(consigneeName);
		model.setMobile(mobile);
		model.setProvince(province);
		model.setCity(city);
		model.setArea(area);
		model.setDetailAddress(detailAddress);
		model.setUserId (userId );
		model.setIsDefault(isDefault);
		return addressService.createModel(model);
	}
	
	/**
	 * @Title: 
	 * @Description: 修改一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改收货地址",notes="修改收货地址,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateAddress",method=RequestMethod.POST)
	public XaResult<AddressVo> updateAddress(
		@ApiParam("编号,字段名:id") @RequestParam(value = "id") Long id,
		@ApiParam("收货人,字段名:consigneeName") @RequestParam(value = "consigneeName",required = false) String consigneeName,
		@ApiParam("手机号,字段名:mobile") @RequestParam(value = "mobile",required = false) String mobile,
		@ApiParam("省份,字段名:province") @RequestParam(value = "province",required = false) String province,
		@ApiParam("城市,字段名:city") @RequestParam(value = "city",required = false) String city,
		@ApiParam("区域,字段名:area") @RequestParam(value = "area",required = false) String area,
		@ApiParam("详细地址,字段名:detailAddress") @RequestParam(value = "detailAddress",required = false) String detailAddress,
		//@ApiParam("用户Id,字段名:userId") @RequestParam(value = "userId") Long userId ,
		@ApiParam("是否默认,字段名:isDefault 0:否，1:是") @RequestParam(value = "isDefault",required = false) Integer isDefault,
	HttpServletRequest request
	) throws BusinessException{
		XaResult<AddressVo> xr=new XaResult<AddressVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空！");
			return xr;
		}
		Address model = new Address();
		model.setId(id);
		if(XaUtil.isNotEmpty(mobile)){
			if(!Validator.isMobile(mobile)){
				xr.error("请输入正确的手机号！");
				return xr;
			}
			model.setMobile(mobile);
		}
		if(XaUtil.isNotEmpty(isDefault)){
			if(!(isDefault==0||isDefault==1)){
				xr.error("输入的是否默认非法！");
				return xr;
			}
			model.setIsDefault(isDefault);
		}
		if(XaUtil.isNotEmpty(consigneeName)){
			model.setConsigneeName(consigneeName);
		}
		if(XaUtil.isNotEmpty(province)){
			model.setProvince(province);
		}
		if(XaUtil.isNotEmpty(city)){
			model.setCity(city);
		}
		if(XaUtil.isNotEmpty(area)){
			model.setArea(area);
		}
		if(XaUtil.isNotEmpty(detailAddress)){
			if(detailAddress.length() > 50){
				xr.error("详细地址不能超过50个字");
				return xr;
			}
			model.setDetailAddress(detailAddress);
		}
		return addressService.updateModel(model);
	}
	
	/**
	 * @Title: operateAddressById
	 * @Description: 操作一个实体状态,根据status进行操作
	 * @param modelId
	 * @param status 		操作类型:-1锁定,0无效,1正常,2发布,3删除,默认删除操作
	 * @return    
	 */
	@ApiOperation(value="删除收货地址",notes="删除收货地址,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="operateAddressById",method=RequestMethod.POST)
	public XaResult<AddressVo> operateAddressById(
		HttpServletRequest request,
		@ApiParam("编号,字段名:modelIds,必填") @RequestParam(value = "modelId") String modelIds
		//@ApiParam("操作类型,字段名:status,-1锁定,0无效,1正常,2发布,3删除,选填,默认删除操作") @RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		XaResult<AddressVo> xr=new XaResult<AddressVo>();
		if(XaUtil.isEmpty(modelIds)){
			xr.error("id不能为空！");
			return xr;
		}
		return addressService.multiOperate(modelIds,XaConstant.Status.delete);
	}
}

