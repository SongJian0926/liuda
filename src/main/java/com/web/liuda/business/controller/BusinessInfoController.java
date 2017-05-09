package com.web.liuda.business.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.liuda.business.constant.channel.SDKClient;
import com.web.liuda.business.entity.BusinessInfo;
import com.web.liuda.business.service.BusinessInfoService;
import com.web.liuda.remote.vo.BusinessInfoVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wxpay.common.MD5;

/**
 * @Title: BusinessInfoController.java
 * @Package com.web.webstart.business.controller
 * @Description: 商家用户控制器
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Controller
@RequestMapping("/cms/businessInfo")
public class BusinessInfoController extends BaseController {
	
	private static String SESSION_KEY = "phoneAndode";
	
	@Autowired
	private BusinessInfoService businessInfoService;
	
	/**
	 * @Title: sendCode
	 * @Description: 发送短信验证码
	 * @param mobile
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="sendCode",method=RequestMethod.POST)
	public XaResult<Boolean> sendCode(
		@RequestParam String mobile,
		@RequestParam String forget, //如果是忘记密码的验证就传forget，否则传""
		HttpServletRequest request
		)throws BusinessException{
		XaResult<Boolean> xr=new XaResult<Boolean>();
		if(XaUtil.isEmpty(mobile)){
			xr.error("手机号码不能为空");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("手机号码格式不正确");
			return xr;
		}
		//验证手机号是否注册
		Boolean isMobile=(Boolean)businessInfoService.mobileCheck(mobile).getObject();
		if(XaUtil.isNotEmpty(forget) && "forget".equals(forget)){
			if(!isMobile){
				xr.error("该手机号尚未注册");
				return xr;
			}
		}else{
			if(isMobile){
				xr.error("该手机号已注册");
				return xr;
			}
		}
		//随机生成六位验证码
		Map<String, Long> phoneAndCode = new HashMap<String, Long>();
		long code = (long) (Math.random() * 899999) + 100000;
		//发送验证码		
		System.out.println(code+"验证码");
		String msg = SDKClient.StartMenu(mobile, code);
		if(XaUtil.isNotEmpty(msg) && "0".equals(msg)){
			//将生成的验证码存到Map集合
			phoneAndCode.put(mobile, code);
			//将生成的验证码保存在session中用来判断验证码是否正确
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_KEY, phoneAndCode);
			session.setMaxInactiveInterval(60*5);	
			xr.setObject(true);
		}else{
			xr.error("验证码发送失败，请稍后再试");
		}
		return xr;
	}
	/**
	 * @Title: forget
	 * @Description: 忘记密码
	 * @param pageSize
	 * @param status		
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="forget",method=RequestMethod.POST)
	public XaResult<Boolean> forget(
		@RequestParam String code,
		@RequestParam String mobile,
		@RequestParam String password,
		HttpServletRequest request
		)throws BusinessException{
		XaResult<Boolean> xr=new XaResult<Boolean>();
		if(XaUtil.isEmpty(code) || XaUtil.isEmpty(mobile) || XaUtil.isEmpty(password)){
		 	xr.error("内容不可为空");
		 	return xr;
		}
		if(!code.matches("[0-9]+")){
			xr.error("验证码不正确");
		 	return xr;
		}
		if(password.length()<5){
			xr.error("密码至少6位");
		 	return xr;
		}
		//判断验证码是否正确
		if (checkCode(request, mobile, code)!="ok" || !checkCode(request, mobile, code).equals("ok") ) {
			xr.error(checkCode(request, mobile, code));
			return xr;
		}
		return businessInfoService.updataByMobile(mobile, MD5.MD5Encode(password));
	}
	/**
	 * @Title: loginCheck
	 * @Description: 登陆验证
	 * @param pageSize
	 * @param status		
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="loginCheck",method=RequestMethod.POST)
	public XaResult<Object> loginCheck(
		@RequestParam String account,
		@RequestParam String password
		) throws BusinessException{
		return businessInfoService.findBusinessByAccount(account, MD5.MD5Encode(password));
	}
	/**
	 * @Title: updatMobile
	 * @Description: 修改手机号	
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="updatMobile",method=RequestMethod.POST)
	public XaResult<Boolean> updatMobile(
		@RequestParam String code,
		@RequestParam String oldmobile,
		@RequestParam String newmobile,
		@RequestParam String password,
		@RequestParam Integer type,
		@RequestParam Long id,
		HttpServletRequest request
		) throws BusinessException{
		 XaResult<Boolean> xr=new  XaResult<Boolean>();
		//判断验证码是否正确
		 if (checkCode(request, newmobile, code)!="ok" || !checkCode(request, newmobile, code).equals("ok")) {
			xr.error(checkCode(request, newmobile, code));
			return xr;
		}
		if(XaUtil.isEmpty(oldmobile)){
			xr.error("手机号码不能为空");
			return xr;
		}
		if(!Validator.isMobile(oldmobile)){
			xr.error("手机号码格式不正确");
			return xr;
		}
		return businessInfoService.updatMobile(oldmobile, password, newmobile, type, id);
	}
	/**
	 * @Title: updatepwd
	 * @Description: 修改密码 通过原始密码
	 * @param pageSize
	 * @param status		
	 * @return    
	 */
		@ResponseBody
		@RequestMapping(value="updatepwd",method=RequestMethod.POST)
		public XaResult<Boolean> updatepwd(
			@RequestParam String password,	
			@RequestParam Long id,
			@RequestParam String newPassword
			) throws BusinessException{
			return  businessInfoService.oldpwdUpdata(id, MD5.MD5Encode(password), MD5.MD5Encode(newPassword));
		}
	
	/**
	 * @Title: findBusinessInfoNEStatusPage
	 * @Description: 分页查询BusinessInfo	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findBusinessInfoNEStatusPage",method=RequestMethod.POST)
	public XaResult<Page<BusinessInfoVo>> findBusinessInfoNEStatusPage(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "3") Integer status,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return businessInfoService.getBusinessInfos(filterParams, pageable);
	}
	
	/**
	 * @Title: findBusinessInfoById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findBusinessInfoById",method=RequestMethod.POST)
	public XaResult<BusinessInfo> findBusinessInfoById(
		@RequestParam Long modelId
	) throws BusinessException{
		return businessInfoService.findOne(modelId);
	}
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="saveBusinessInfo",method=RequestMethod.POST)
	public XaResult<BusinessInfo> saveBusinessInfo(
		@RequestParam Integer numbers
	) throws BusinessException{
		return businessInfoService.createAccount(numbers);
	}
	
	/**
	 * @Title: multiOperateBusinessInfoByIds
	 * @Description: 批量操作多个实体状态,根据status进行操作
	 * @param modelIds    	多个实体id,中间用逗号隔开
	 * @param status 		操作类型,status类型见XaConstant.Status,默认删除操作
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="operateBusinessInfoByIds",method=RequestMethod.POST)
	public XaResult<BusinessInfo> operateBusinessInfoByIds(
		HttpServletRequest request,
		@RequestParam String modelIds,
		@RequestParam(defaultValue = "3") Integer status
	) throws BusinessException{
		return businessInfoService.multiOperate(modelIds,status);
	}
	
	/**
	 * 检查验证码是否正确
	 * @param request
	 * @param phone
	 * @param code
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static String checkCode(HttpServletRequest request, String phone,String code) {
		if(XaUtil.isEmpty(request.getSession().getAttribute(SESSION_KEY))){
			return "验证码已失效,请重新获取";
		}
		HashMap<String, Long> phoneAndCode = (HashMap<String, Long>) request.getSession().getAttribute(SESSION_KEY);
		if(phoneAndCode.containsKey(phone) && phoneAndCode.containsValue(Long.valueOf(code))){
			return "ok";
		}else{
			return "验证码错误,请重新输入";
		}
		
	}
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<String> photoUpload(
		@RequestParam(value = "photoFile", required = false) MultipartFile photoFile, 
		HttpServletRequest request
	){
		XaResult<String> xr = new XaResult<String>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/images";
		String ext =photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime()+ext;
		File filedict = new File(root+picturePath);
		if(!filedict.exists()){
			filedict.mkdir();
		}
		File targetFile=new File(root+picturePath+File.separator+newName);
		try {
			if(StringUtils.equalsIgnoreCase(ext, ".jpg") || StringUtils.equalsIgnoreCase(ext, ".png")){
				photoFile.transferTo(targetFile);
				BufferedImage bimg = ImageIO.read(targetFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pictureHeight",height);
				map.put("pictureWidth",width);
				xr.setCode(1);
				xr.setObject(XaConstant.Code.success+":"+picturePath+"/"+newName+":"+height+":"+width);
				return xr;
			}
			else{
				xr.setCode(0);
				xr.setObject(XaConstant.Code.error+":上传文件类型不允许,请上传jpg/png图片");
				return xr;
			}
		} catch (IllegalStateException e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片上传失败");
			return xr;
		} catch (IOException e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片上传失败");
			return xr;
		} catch (Exception e) {
			xr.setCode(0);
			xr.setObject(XaConstant.Code.error+":图片上传失败");
			return xr;
		}
	}
	/**
	 * @Title: findBalanceList
	 * author：changlu
	 * time:2016-01-25 10:32:00
	 * @Description: 后台结算管理列表	 * @param nextPage
	 * @param pageSize
	 * @param status		//默认查询非删除3的数据,具体类型参照XaConstant.Status
	 * @param sortDate
	 * @param jsonFilter
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findBalanceList",method=RequestMethod.POST)
	public XaResult<Page<BusinessInfoVo>> findBalanceList(
		@RequestParam(defaultValue = "0") Integer nextPage,
		@RequestParam(defaultValue = "10") Integer pageSize,
		@RequestParam(defaultValue = "[{property:'createTime',direction:'DESC'}]") String sortData,
		//过滤字段,字段名:jsonFilter,选填,默认:{},示例:{'search_EQ_field1':'value1','search_EQ_field2':'value2'},字段名称拼接规则search_为固定查询标识,EQ为等于,filed为字段名
		//EQ等于, IN包含, ISNULL空, LIKE, GT大于, LT小于, GTE大于等于, LTE小于等于, NE不等于,LIKEIGNORE非like 
/*		@RequestParam(value="businessId") Long businessId,
		@RequestParam(value="businessType") Integer businessType,*/
		@RequestParam(defaultValue = "{}") String jsonFilter
	) throws BusinessException{
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortData);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		return businessInfoService.findBalanceList(filterParams, pageable);
	}
	/**
	 * @Title: saveModel
	 * @Description: 保存实体数据
	 * @param model
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="newSaveBalance",method=RequestMethod.POST)
	public XaResult<BusinessInfo> newSaveBalance(
			BusinessInfo model
	) throws BusinessException{
		return businessInfoService.createBalance(model);
	}
	/**
	 * @Title: findBalanceById
	 * author:changlu
	 * time:2016-01-26 12:21:00
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ResponseBody
	@RequestMapping(value="findBalanceById",method=RequestMethod.POST)
	public XaResult<BusinessInfoVo> findOneById(
		@RequestParam Long modelId
	) throws BusinessException{
		return businessInfoService.findBalanceById(modelId);
	}
}

