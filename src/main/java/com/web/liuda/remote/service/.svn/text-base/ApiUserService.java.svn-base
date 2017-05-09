package com.web.liuda.remote.service;

import com.web.liuda.business.entity.User;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.ApiBaseService;
import com.web.webstart.base.util.XaResult;

public interface ApiUserService extends ApiBaseService<UserVo,User>{
	
	/**
	 * 验证手机号是否存在
	 * @param mobile
	 * @return
	 */
	public User validMobile(String mobile);
	
	
	/**
	 * 验证邮箱是否存在
	 * @param mobile
	 * @return
	 */
	public User validMailbox(String mailbox);
	
	/**
	 * 验证用户名是否存在
	 * @param mobile
	 * @return
	 */
	public User validUserName(String userName);
	
	/**
	 * 登录
	 * @param mobile
	 * @param password
	 * @return
	 */
	public XaResult<UserVo> login(String mobile,String password);
	
	/**
	 * 修改密码
	 * @param id
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	public XaResult<UserVo> updatePwd(Long id,String oldpassword,String newpassword);
	
	/**
	 * 绑定邮箱
	 * @param id
	 * @param mailbox
	 * @return
	 */
	public XaResult<UserVo> bindMaileBox(Long id,String mailbox,String newMailbox);
	/**
	 * 忘记密码
	 * @param id
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	public XaResult<UserVo> forgetPwd(String mobile, String email,String newpassword);
	/*
	 * 保存第三方账号信息
	 * 
	 */
	public XaResult<UserVo> saveThreeAccount(User user) ;
	/**
	 * 申请为大咖
	 * author:changlu
	 * time:2016-05-10 16:42:00
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<UserVo> applyBigShot(User model)
			throws BusinessException;
}
