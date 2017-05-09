package com.web.liuda.remote.controller;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.constant.channel.SDKClient;
import com.web.liuda.business.entity.User;
import com.web.liuda.remote.service.ApiUserCardService;
import com.web.liuda.remote.service.ApiUserService;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.entity.MailSenderInfo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.MD5Util;
import com.web.webstart.base.util.SimpleMailSender;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiUserController.java
 * @Package com.web.jnews.remote.controller
 * @Description: 用户信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "User", description = "用户相关", position = 10)
@Controller
@RequestMapping("/api/user")
public class ApiUserController extends BaseController {
	
	private static String SESSION_KEY = "phoneAndode";
	
	private static String SESSION_KEY_MAIL = "MailboxAndode";

	@Autowired
	private ApiUserService userService;
	@Autowired
	ApiUserCardService apiUserCardService;
	/**
	 * @Title: login
	 * @Description: 查询所有用户信息
	 * @return    
	 */
	@ApiOperation(value="登录",notes="登录,当返回的code=1时，登录成功，返回对象实体")
	@ResponseBody
	@RequestMapping(value="login",method=RequestMethod.POST)
	public XaResult<UserVo> login(
		@ApiParam("手机号,字段名:mobile") @RequestParam(value = "mobile") String mobile,
		@ApiParam("密码,字段名:password") @RequestParam(value = "password") String password
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(mobile)){
			xr.error("手机号码不能为空");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("手机号码格式不正确");
			return xr;
		}
		if(XaUtil.isEmpty(password)){
			xr.error("密码不能为空");
			return xr;
		}
		return userService.login(mobile, password);
	}
	/**
	 * @Title: sendMailboxCode
	 * @Description: 获取邮箱验证码
	 * @param mail
	 * @return    
	 */
	@ApiOperation(value="获取邮箱验证码",notes="获取邮箱验证码,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="sendMailboxCode",method=RequestMethod.POST)
	public XaResult<Map<String, Long>> sendMailboxCode(
			@ApiParam("邮箱,字段名:mailbox,必填") @RequestParam(value = "mailbox") String mailbox,
			@ApiParam("是否忘记密码,字段名:forget,如果是忘记密码该字段传“forget”，否则不传") @RequestParam(defaultValue = "",required = false) String forget,
			HttpServletRequest request
		) throws BusinessException{
			XaResult<Map<String, Long>> xr = new XaResult<Map<String, Long>>();
			if(XaUtil.isEmpty(mailbox)){
				xr.error("邮箱不能为空");
				return xr;
			}
			if(!Validator.isEmail(mailbox)){
				xr.error("邮箱格式不正确");
				return xr;
			}
			//验证y邮箱是否注册
			User user = userService.validMailbox(mailbox);
			if(XaUtil.isNotEmpty(forget) && "forget".equals(forget)){
				if(XaUtil.isEmpty(user)){
					xr.error("该邮箱尚未注册");
					return xr;
				}
			}else{
				if(XaUtil.isNotEmpty(user)){
					xr.error("该邮箱已注册");
					return xr;
				}
			}
			//随机生成六位验证码
			Map<String, Long> mailBoxAndCode = new HashMap<String, Long>();
			long code = (long) (Math.random() * 899999) + 100000;
			
			Properties pro = new Properties();
	    	try {
	    		pro.load(ApiUserController.class.getClassLoader()
	    				.getResourceAsStream("email.properties"));
	    	} catch (IOException e) {
	    			e.printStackTrace();
	    	}
	    	MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost(pro.getProperty("email.host"));
			mailInfo.setMailServerPort(pro.getProperty("email.port"));
			mailInfo.setValidate(true);
			mailInfo.setUserName(pro.getProperty("email.fromAddress"));
			mailInfo.setPassword(pro.getProperty("email.password"));// 您的邮箱密码
			mailInfo.setFromAddress(pro.getProperty("email.fromAddress"));
			mailInfo.setToAddress(mailbox);	//415052415@qq.com
			mailInfo.setSubject("【蹓跶蹓跶】绑定邮箱验证");
			String linkurl = "http://www.baidu.com/activeEmail?email=172810573@qq.com&syscode=" + MD5Util.getMD5String("123456");
			/*if(id != null){
				linkurl += "&id=" + id;
			}*/
			String contents = "【蹓跶蹓跶】绑定邮箱验证，验证码为：<a '" + linkurl + "'>"+code+"</a>";
			mailInfo.setContent(contents);
			// 这个类主要来发送邮件
			try{
				SimpleMailSender sms = new SimpleMailSender();
				sms.sendHtmlMail(mailInfo);// 发送文体格式
				//将生成的验证码存到Map集合中返回到前端
				mailBoxAndCode.put(mailbox, code);
				//将生成的验证码保存在session中用来判断验证码是否正确
				HttpSession session = request.getSession();
				session.setAttribute(SESSION_KEY_MAIL, mailBoxAndCode);
				session.setMaxInactiveInterval(5 * 60);	//设置有效时间为5分钟
				xr.success(mailBoxAndCode);
			}catch(Exception e){
				e.printStackTrace();
				xr.error("邮件发送失败，请稍后再试");
			}
			return xr;
		}
	/**
	 * 检查验证码是否正确
	 * @param request
	 * @param phone
	 * @param code
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private boolean checkMailCode(HttpServletRequest request, String mailbox,String code) {
		HashMap<String, Long> mailBoxAndCode = (HashMap<String, Long>) request.getSession().getAttribute(SESSION_KEY_MAIL);
		if (XaUtil.isEmpty(mailBoxAndCode)) {
			return false;
		}
		return mailBoxAndCode.containsKey(mailbox) && mailBoxAndCode.containsValue(Long.valueOf(code));
	}
	/**
	 * 绑定/修改邮箱
	 * @param mailbox
	 * @param userId
	 * @param code
	 * @return
	 */
	@ApiOperation(value="绑定/修改邮箱",notes="绑定邮箱,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="bindMaileBox",method=RequestMethod.POST)
	public XaResult<UserVo> bindMaileBox(
			@ApiParam("用户id,字段名:id") @RequestParam(value = "id") Long id,
			@ApiParam("邮箱,字段名:mailbox,必填") @RequestParam(value = "mailbox") String mailbox,
			@ApiParam("新邮箱,修改邮箱时该内容不可为空") @RequestParam(value = "newMailBox",required = false) String newMailBox,
			@ApiParam("验证码,字段名:code") @RequestParam String code,
			HttpServletRequest request
	)throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(mailbox)){
			xr.error("邮箱不能为空");
			return xr;
		}
		if(!Validator.isEmail(mailbox)){
			xr.error("邮箱格式不正确");
			return xr;
		}
		if(XaUtil.isEmpty(code)){
			xr.error("验证码不能为空");
			return xr;
		}
		if(!Validator.isNumber(code)){
			xr.error("验证码错误");
			return xr;
		}
		if(XaUtil.isEmpty(newMailBox)){
			if(!checkMailCode(request,mailbox,code)){
				xr.error("验证码错误");
				return xr;
			}
		}
		if(XaUtil.isNotEmpty(newMailBox)){
			if(!Validator.isEmail(newMailBox)){
				xr.error("邮箱格式不正确");
				return xr;
			}
		}
		return userService.bindMaileBox(id,mailbox,newMailBox);
	}
	/**
	 * @Title: sendCode
	 * @Description: 发送短信验证码
	 * @param mobile
	 * @return    
	 */
	
	@ApiOperation(value="获取验证码",notes="查询用户详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="sendCode",method=RequestMethod.POST)
	public XaResult<Map<String, Long>> sendCode(
		@ApiParam("手机号码,字段名:mobile,必填") @RequestParam(value = "mobile") String mobile,
		@ApiParam("是否忘记密码,字段名:forget,如果是忘记密码该字段传“forget”，否则不传") @RequestParam(defaultValue = "",required = false) String forget,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Long>> xr = new XaResult<Map<String, Long>>();
		if(XaUtil.isEmpty(mobile)){
			xr.error("手机号码不能为空");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("手机号码格式不正确");
			return xr;
		}
		//验证手机号是否注册
		User user = userService.validMobile(mobile);
		if(XaUtil.isNotEmpty(forget) && "forget".equals(forget)){
			if(XaUtil.isEmpty(user)){
				xr.error("该手机号尚未注册");
				return xr;
			}
		}else{
			if(XaUtil.isNotEmpty(user)){
				xr.error("该手机号已注册");
				return xr;
			}
		}
		//随机生成六位验证码
		Map<String, Long> phoneAndCode = new HashMap<String, Long>();
		long code = (long) (Math.random() * 899999) + 100000;
		//发送验证码
		String msg = SDKClient.StartMenu(mobile, code);
		if(XaUtil.isNotEmpty(msg) && "0".equals(msg)){
			//将生成的验证码存到Map集合中返回到前端
			phoneAndCode.put(mobile, code);
			//将生成的验证码保存在session中用来判断验证码是否正确
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_KEY, phoneAndCode);
			session.setMaxInactiveInterval(5 * 60);	//设置有效时间为5分钟
			xr.success(phoneAndCode);
		}else{
			xr.error("验证码发送失败，请稍后再试");
		}
		return xr;
	}

	/**
	 * @Title: findUserDetail
	 * @Description: 用户详情
	 * @param id
	 * @return    
	 */
	@ApiOperation(value="用户详情",notes="查询用户详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findUserDetail",method=RequestMethod.POST)
	public XaResult<UserVo> findUserById(
		@ApiParam("编号,字段名:id,必填") @RequestParam(value = "id") Long id
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空");
			return xr;
		}
		return userService.findOne(id);
	}
	
	/**
	 * @Title: register
	 * @Description: 用户注册
	 * @param mobile
	 * @param password
	 * @return    
	 */
	@ApiOperation(value="用户注册",notes="用户注册,当返回的code=1时，注册成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="register",method=RequestMethod.POST)
	public XaResult<UserVo> register(
		@ApiParam("手机号码,字段名:mobile") @RequestParam(value = "mobile") String mobile,
		@ApiParam("密码,字段名:password") @RequestParam(value = "password") String password,
		@ApiParam("验证码,字段名:code") @RequestParam String code,
		@ApiParam("邀请码,字段名:inviteCode") @RequestParam(required=false,value = "inviteCode") String inviteCode,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(mobile)){
			xr.error("手机号码不能为空");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("手机号码格式不正确");
			return xr;
		}
		if(XaUtil.isEmpty(password)){
			xr.error("密码不能为空");
			return xr;
		}
		if(XaUtil.isEmpty(code)){
			xr.error("验证码不能为空");
			return xr;
		}
		//判断验证码是否正确
		if (!checkCode(request, mobile, code)) {
			xr.error("验证码不正确");
			return xr;
		}
		User model = new User();
		model.setPassword(MD5Util.getMD5String(password));
		model.setMobile(mobile);
		model.setInviteCode(inviteCode);
		return userService.createModel(model);
	}
	
	/**
	 * @Title: 修改资料
	 * @Description: 修改资料
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改资料",notes="修改资料,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updateUser",method=RequestMethod.POST)
	public XaResult<UserVo> updateUser(
		@ApiParam("用户id,字段名:id") @RequestParam(value = "id") Long id,
		@ApiParam("用户名,字段名:userName") @RequestParam(value = "userName",required = false) String userName,
		@ApiParam("头像,字段名:photo") @RequestParam(value = "photo",required = false) String photo,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空");
			return xr;
		}
		if(XaUtil.isNotEmpty(userName) && XaUtil.isNotEmpty(userService.validUserName(userName))){
			xr.error("用户名已使用");
			return xr;
		}
		User model = new User();
		model.setUserName(userName);
		model.setPhoto(photo);
		model.setId(id);
		return userService.updateModel(model);
	}
	
	/**
	 * @Title: 修改密码
	 * @Description: 修改密码
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="修改密码",notes="修改密码,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="updatePwd",method=RequestMethod.POST)
	public XaResult<UserVo> updatePwd(
		@ApiParam("用户id,字段名:id") @RequestParam(value = "id") Long id,
		@ApiParam("原密码,字段名:oldPassword") @RequestParam String oldPassword,
		@ApiParam("新密码,字段名:newPassword") @RequestParam String newPassword,
		@ApiParam("确认密码,字段名:confirmPassword") @RequestParam String confirmPassword,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空");
			return xr;
		}
		if(XaUtil.isEmpty(oldPassword)){
			xr.error("原密码不能为空");
			return xr;
		}
		if(XaUtil.isEmpty(newPassword)){
			xr.error("新密码不能为空");
			return xr;
		}
		if(XaUtil.isEmpty(confirmPassword)){
			xr.error("确认密码不能为空");
			return xr;
		}
		if(!newPassword.equals(confirmPassword)){
			xr.error("新密码和确认密码不相符");
			return xr;
		}
		return userService.updatePwd(id, oldPassword, newPassword);
	}
	
	/**
	 * @Title: 忘记密码
	 * @Description: 忘记密码
	 * @param mobile
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="忘记密码",notes="忘记密码,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="forgetPwd",method=RequestMethod.POST)
	public XaResult<UserVo> forgetPwd(
		@ApiParam("手机号,字段名:mobile") @RequestParam(required=false,value = "mobile") String mobile,
		@ApiParam("邮箱,字段名:email") @RequestParam(required=false,value = "email") String email,
		@ApiParam("验证码,字段名:code") @RequestParam String code,
		@ApiParam("新密码,字段名:newPassword") @RequestParam String newPassword,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(mobile)&&XaUtil.isEmpty(email)){
			xr.error("手机号码和邮箱不能都为空！");
			return xr;
		}
		if(XaUtil.isNotEmpty(mobile)&&XaUtil.isNotEmpty(email)){
			xr.error("手机号码和邮箱只能传一个！");
			return xr;
		}
		if(XaUtil.isNotEmpty(mobile)){
			if(!Validator.isMobile(mobile)){
				xr.error("手机号码格式不正确");
				return xr;
			}
		}
		if(XaUtil.isNotEmpty(email)){
			if(!Validator.isEmail(email)){
				xr.error("邮箱格式不正确");
				return xr;
			}
		}
		if(XaUtil.isEmpty(code)){
			xr.error("验证码不能为空");
			return xr;
		}
		if(XaUtil.isEmpty(newPassword)){
			xr.error("新密码不能为空");
			return xr;
		}
		//判断手机验证码是否正确
		if(XaUtil.isNotEmpty(mobile)){
			if (!checkCode(request, mobile, code)) {
				xr.error("手机验证码不正确");
				return xr;
			}
		}
		//邮箱验证码是否正确
		if(XaUtil.isNotEmpty(email)){
			if (!checkMailCode(request, email, code)) {
				xr.error("邮箱验证码不正确");
				return xr;
			}
		}
		return userService.forgetPwd(mobile, email,newPassword);
		//return userService.forgetPwd(mobile, newPassword);
	}
	/**
	 * @Title: saveThreeAccount
	 * @Description: 保存第三方账号
	 * @param mobile
	 * @param password
	 * @return    
	 */
	@ApiOperation(value="保存第三方账号",notes="保存第三方账号,当返回的code=1时，注册成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveThreeAccount",method=RequestMethod.POST)
	public XaResult<UserVo> saveThreeAccount(
		@ApiParam("QQ,字段名:thridQq") @RequestParam(value = "thridQq",required = false) String qq,
		@ApiParam("微信,字段名:thridWchat") @RequestParam(value = "thridWchat",required = false) String wechat,
		@ApiParam("微博,字段名:thridWeibo") @RequestParam(value = "thridWeibo",required = false) String weibo,
		@ApiParam("用户名,字段名:userName，第三方账号用户名") @RequestParam(value = "userName",required = false) String userName,
		@ApiParam("头像地址,字段名:photo，第三方账号头像") @RequestParam(value = "photo",required = false) String photo,
		HttpServletRequest request
	) throws BusinessException{
		User model = new User();
		model.setThridQq(qq);
		model.setThridWchat(wechat);
		model.setThridWeibo(weibo);
		model.setUserName(userName);
		model.setPhoto(photo);
		return userService.saveThreeAccount(model);
	}
	/**
	 * @Title: upload
	 * @Description: 图片上传
	 * @param photoFile
	 * @param request
	 * @return    
	 */
	@ApiOperation(value="上传头像",notes="异步上传头像,返回上传图片的地址、宽、高")
	@ResponseBody
	@RequestMapping(value="photoUpload",method=RequestMethod.POST)
	public XaResult<Map<String, Object>> photoUpload(
		@ApiParam("上传的头像,字段名:photoFile,必填") @RequestParam(value = "photoFile") MultipartFile photoFile, 
		HttpServletRequest request
	) throws BusinessException{
		XaResult<Map<String, Object>> result = new XaResult<Map<String, Object>>();
		String root=request.getSession().getServletContext().getRealPath("/");
		String picturePath = "/upload/user";
		String ext =photoFile.getOriginalFilename().substring(photoFile.getOriginalFilename().lastIndexOf("."));
		String newName = new Date().getTime()+ext;
		File filedict = new File(root+picturePath);
		if(!filedict.exists()){
			filedict.mkdir();
		}
		File targetFile=new File(root+picturePath+File.separator+newName);
		try {
			if(StringUtils.equalsIgnoreCase(ext, ".jpg") || StringUtils.equalsIgnoreCase(ext, ".png")|| StringUtils.equalsIgnoreCase(ext, ".jpeg")){
				photoFile.transferTo(targetFile);
				BufferedImage bimg = ImageIO.read(targetFile);
				int width = bimg.getWidth();
				int height = bimg.getHeight();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("pictureHeight",height);
				map.put("pictureWidth",width);
				map.put("picturePath",picturePath+"/"+newName);
				result.setObject(map);
				return result;
			}
			else{
				throw new BusinessException("上传文件类型不允许,请上传jpg/png/jpeg图片");
			}
		} catch (IllegalStateException e) {
			throw new BusinessException("头像上传失败");
		} catch (IOException e) {
			throw new BusinessException("头像上传失败");
		} catch (Exception e) {
			throw new BusinessException("头像上传失败");
		}
	}
	
	
	/**
	 * 检查验证码是否正确
	 * @param request
	 * @param phone
	 * @param code
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	private boolean checkCode(HttpServletRequest request, String phone,String code) {
		HashMap<String, Long> phoneAndCode = (HashMap<String, Long>) request.getSession().getAttribute(SESSION_KEY);
		if (XaUtil.isEmpty(phoneAndCode)) {
			return false;
		}
		return phoneAndCode.containsKey(phone) && phoneAndCode.containsValue(Long.valueOf(code));
	}
	/**
	 * @Title: 申请为大咖
	 * author:changlu
	 * time:2016-05-10 16:02:00
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="申请为大咖",notes="申请为大咖,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="applyBigShot",method=RequestMethod.POST)
	public XaResult<UserVo> applyBigShot(
		@ApiParam("用户id,字段名:id") @RequestParam(value = "id") Long id,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if(XaUtil.isEmpty(id)){
			xr.error("id不能为空");
			return xr;
		}else if(id<1){
			xr.error("用户Id不能少于1");
			return xr;
		}
		
		User model = new User();
		model.setApplyStatus(JConstant.ApplyStatus.CHECK);
		model.setId(id);
		return userService.applyBigShot(model);
	}
}

