package com.web.liuda.remote.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.ClubMember;
import com.web.liuda.business.entity.Fans;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.ClubMemberRepository;
import com.web.liuda.business.repository.FansRepository;
import com.web.liuda.business.repository.FondRecordRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.remote.service.ApiUserService;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.MD5Util;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIUser接口实现类
 **/
@Service("ApiUserService")
@Transactional(readOnly = false)
public class ApiUserServiceImpl extends BaseService<User> implements ApiUserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	FansRepository fansRepository;
	@Autowired
	ClubMemberRepository clubMemberRepository;
	@Autowired
	FondRecordRepository fondRecordRepository;
	@Override
	public XaResult<UserVo> findOne(Long tId) throws BusinessException {
		User obj = userRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if (XaUtil.isNotEmpty(obj)) {
			UserVo user = JSON.parseObject(JSON.toJSONString(obj),UserVo.class);
			//判断是否拥有自己的邀请码
			if(XaUtil.isNotEmpty(user.getExclusiveCode())){
				//计算通过该用户邀请码有多少人注册
				Integer poperNum = userRepository.findNumByInviteCode(user.getExclusiveCode());
				if(XaUtil.isEmpty(poperNum)){poperNum = 0;}
				user.setPoperNum(poperNum);
				//计算该用户获取的收益
				Double earnings = fondRecordRepository.findNumByOriginAndUserIdAndStatus(user.getExclusiveCode(), user.getId());
				if(XaUtil.isEmpty(earnings)){earnings = 0.00;}
				user.setEarnings(earnings);
			}
			xr.setObject(user);
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<UserVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<List<UserVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		return null;
	}

	@Override
	public XaResult<UserVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		return null;
	}

	@Override
	public XaResult<UserVo> createModel(User model)
			throws BusinessException {
		XaResult<UserVo> xr = new XaResult<UserVo>();
		//验证手机是否注册
		User user = userRepository.findByMobileAndStatusNot(model.getMobile(), XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(user)){
			xr.error("该手机号已注册");
			return xr;
		}
		User inviteUser =new User();
		if(XaUtil.isNotEmpty(model.getInviteCode())){
			inviteUser= userRepository.findByExclusiveCodeAndStatusNot(model.getInviteCode(), XaConstant.Status.delete);
			if(XaUtil.isEmpty(inviteUser)){ 
				xr.error("该邀请码不存在！");
				return xr;
			}
			
		}
		model.setJpushKey(XaUtil.getRandomStringByLength(16));
		
		String mobile = model.getMobile();
		String maskNumber = mobile.substring(0,3)+"****"+mobile.substring(7,mobile.length());
		model.setUserName(maskNumber);
		
		User obj = userRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserVo.class));
		xr.getObject().setId(obj.getId());
		//邀请码存在:加入邀请人创建的俱乐部，成为大咖的粉丝
		if(XaUtil.isNotEmpty(inviteUser)){  
			//如果邀请人是大咖，成为大咖的粉丝
			if(inviteUser.getBigShot()==JConstant.BooleanStatus.TRUE){
				Fans fans=new Fans();
				fans.setUserId(inviteUser.getId());
				fans.setFansId(obj.getId());
				fansRepository.save(fans);
			}
			
			//查询邀请人创建的俱乐部
			List<ClubMember> clubMembers=clubMemberRepository.findByUserIdAndApplyStatusAndMemberTypeAndStatusNot(inviteUser.getId(), JConstant.ApplyStatus.CHECKSUCCEED,JConstant.ClubRole.MINISTER, XaConstant.Status.delete);
			if(XaUtil.isNotEmpty(clubMembers)){
				//加入俱乐部
				for(ClubMember cm:clubMembers){
					
					ClubMember clubMember=new ClubMember();
					clubMember.setUserId(obj.getId());
					clubMember.setMemberType(JConstant.ClubRole.MEMBER);
					clubMember.setApplyStatus(JConstant.ApplyStatus.CHECKSUCCEED);
					clubMember.setClubId(cm.getClubId());
					clubMemberRepository.save(clubMember);
				}
			}
			
		}
		
		return xr;
	}

	@Override
	public XaResult<UserVo> updateModel(User model)
			throws BusinessException {
		User obj = userRepository.findOne(model.getId());
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if (XaUtil.isNotEmpty(obj)) {
			if(XaUtil.isNotEmpty(model.getUserName())){
				obj.setUserName(model.getUserName());
			}
			if(XaUtil.isNotEmpty(model.getPhoto())){
				obj.setPhoto(model.getPhoto());
			}
			obj = userRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			xr.error("该用户不存在");
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/**
	 * 申请为大咖
	 * author:changlu
	 * time:2016-05-10 16:42:00
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<UserVo> applyBigShot(User model)
			throws BusinessException {
		User obj = userRepository.findByIdAndStatusNot(model.getId(),XaConstant.Status.delete);
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if (XaUtil.isNotEmpty(obj)) {
			if(obj.getApplyStatus()==JConstant.ApplyStatus.CHECK||obj.getApplyStatus()==JConstant.ApplyStatus.CHECKSUCCEED){
				xr.error("已提交申请,请不要重复申请!");
				return xr;
			}else{
				obj.setId(model.getId());
				obj.setApplyStatus(model.getApplyStatus());
				obj = userRepository.save(obj);
				xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserVo.class));
				xr.getObject().setId(obj.getId());
			}
			
		} else {
			xr.error("该用户不存在");
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	@Override
	public User validMobile(String mobile) {
		return userRepository.findByMobileAndStatusNot(mobile, XaConstant.Status.delete);
	}

	@Override
	public XaResult<UserVo> login(String mobile, String password) {
		XaResult<UserVo> xr = new XaResult<UserVo>();
		User user = userRepository.findByMobileAndPasswordAndStatusNot(mobile, MD5Util.getMD5String(password), XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(user)){
			if(user.getStatus()==XaConstant.Status.locked){
				xr.error("该账户已经停用");
				return xr;
			}
			xr.success(JSON.parseObject(JSON.toJSONString(user),UserVo.class));
		}else{
			xr.error("用户名或密码错误");
		}
		return xr;
	}

	@Override
	public XaResult<UserVo> updatePwd(Long id, String oldpassword,
			String newpassword) {
		User obj = userRepository.findOne(id);
		XaResult<UserVo> xr = new XaResult<UserVo>();
		if (XaUtil.isNotEmpty(obj)) {
			//判断原密码是否相同
			if(!MD5Util.getMD5String(oldpassword).equals(obj.getPassword())){
				xr.error("原密码不正确");
				return xr;
			}
			obj.setPassword(MD5Util.getMD5String(newpassword));
			obj = userRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), UserVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			xr.error("该用户不存在");
		}
		return xr;
	}

	@Override
	public XaResult<UserVo> forgetPwd(String mobile, String email,String newpassword) {
		XaResult<UserVo> xr = new XaResult<UserVo>();
		User user =new User();
		if(XaUtil.isNotEmpty(mobile)){
			user = userRepository.findByMobileAndStatusNot(mobile, XaConstant.Status.delete);
			if(XaUtil.isEmpty(user)){
				xr.error("该手机尚未注册");
				return xr;
			}
		}
		if(XaUtil.isNotEmpty(email)){
			user = userRepository.findByEmailAndStatusNot(email, XaConstant.Status.delete);
			if(XaUtil.isEmpty(user)){
				xr.error("该邮箱尚未绑定");
				return xr;
			}
		}
		user.setPassword(MD5Util.getMD5String(newpassword));
		user = userRepository.save(user);
		xr.setObject(JSON.parseObject(JSON.toJSONString(user), UserVo.class));
		xr.getObject().setId(user.getId());
		return xr;
	}
	@Override
	public XaResult<UserVo> saveThreeAccount(User user) {
		XaResult<UserVo> xr = new XaResult<UserVo>();
		//如果QQ不为空，则验证QQ是否被注册过,如果没有注册过则保存数据、保存过则查询出来
		Long userId = 0L;
		if(XaUtil.isNotEmpty(user.getThridQq())){
			User u = userRepository.findByThridQqAndStatusNot(user.getThridQq(), XaConstant.Status.delete);
			if(XaUtil.isNotEmpty(u)){
				xr.success(JSON.parseObject(JSON.toJSONString(u),UserVo.class));
				userId = u.getId();
			}else{
				User s = new User();
				s.setThridQq(user.getThridQq());
				s.setUserName(user.getUserName());
				s.setPhoto(user.getPhoto());
				s = userRepository.save(s);
				xr.success(JSON.parseObject(JSON.toJSONString(s),UserVo.class));
				userId = s.getId();
			}
			//如果微信不为空，则验证微信是否被注册过,如果没有注册过则保存数据、保存过则查询出来
		}else if(XaUtil.isNotEmpty(user.getThridWchat())){
			User u = userRepository.findByThridWchatAndStatusNot(user.getThridWchat(), XaConstant.Status.delete);
			if(XaUtil.isNotEmpty(u)){
				xr.success(JSON.parseObject(JSON.toJSONString(u),UserVo.class));
				userId = u.getId();
			}else{
				User s = new User();
				s.setThridWchat(user.getThridWchat());
				s.setUserName(user.getUserName());
				s.setPhoto(user.getPhoto());
				s = userRepository.save(s);
				xr.success(JSON.parseObject(JSON.toJSONString(s),UserVo.class));
				userId = s.getId();
			}
			//如果微博不为空，则验证微博是否被注册过,如果没有注册过则保存数据、保存过则查询出来
		}else if(XaUtil.isNotEmpty(user.getThridWeibo())){
			User u = userRepository.findByThridWeiboAndStatusNot(user.getThridWeibo(), XaConstant.Status.delete);
			if(XaUtil.isNotEmpty(u)){
				xr.success(JSON.parseObject(JSON.toJSONString(u),UserVo.class));
				userId = u.getId();
			}else{
				User s = new User();
				s.setThridWeibo(user.getThridWeibo());
				s.setUserName(user.getUserName());
				s.setPhoto(user.getPhoto());
				s = userRepository.save(s);
				xr.success(JSON.parseObject(JSON.toJSONString(s),UserVo.class));
				userId = s.getId();
			}
		}
		return xr;
	}
	@Override
	public User validUserName(String userName) {
		return userRepository.findByUserNameAndStatusNot(userName, XaConstant.Status.delete);
	}
	//验证邮箱
	@Override
	public User validMailbox(String mailbox) {
		return userRepository.findByEmailAndStatusNot(mailbox, XaConstant.Status.delete);
	}
	//绑定邮箱
	@Override
	public XaResult<UserVo> bindMaileBox(Long id, String mailbox,String newMailBox) {
		XaResult<UserVo> xr = new XaResult<UserVo>();
		User user = userRepository.findByIdAndStatus(id,XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(user)){
			if(XaUtil.isEmpty(newMailBox)){
				if(XaUtil.isEmpty(user.getEmail())){
					user.setEmail(mailbox);
					userRepository.save(user);
					xr.setObject(JSON.parseObject(JSON.toJSONString(user),UserVo.class));
				}else{
					xr.error("该用户已绑定邮箱，不可重复绑定");
				}
			}else{
				if(XaUtil.isNotEmpty(user.getEmail())){
					if(user.getEmail().equals(mailbox)){
						user.setEmail(newMailBox);
						userRepository.save(user);
						xr.setObject(JSON.parseObject(JSON.toJSONString(user),UserVo.class));
					}else{
						xr.error("原邮箱错误,请重新输入");
					}
				}else{
					xr.error("该用户未绑定邮箱,请先绑定之后 在修改");
				}
			}
		}else{
			xr.error("用户不存在");
		}
		return xr;
	}

}
