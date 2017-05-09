package com.web.liuda.business.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.web.liuda.business.entity.User;


public interface UserRepository extends
		PagingAndSortingRepository<User, Long>,
		JpaSpecificationExecutor<User> {
	public User findByIdAndStatusNot(Long id,Integer status);
	
	public User findByIdAndStatus(Long id,Integer status);
	
	//验证手机号是否存在
	public User findByMobileAndStatusNot(String mobile,Integer status);
	//验证邮箱是否存在
	public User findByEmailAndStatusNot(String email,Integer status);
	
	//验证用户名是否存在
	public User findByUserNameAndStatusNot(String userName,Integer status);
	
	//登录
	public User findByMobileAndPasswordAndStatusNot(String mobile,String password,Integer status);
	
	//查询有多少人通过指定邀请码注册的
	@Query(value="select COUNT(u.id) from tb_xa_user u where u.invite_code=? and u.`status`=1",nativeQuery=true)
	public Integer findNumByInviteCode(String code);
	
	/**
	 * 查询接收推送消息的用户
	 * @param ispush
	 * @param status
	 * @return
	 */
	public List<User> findByStatusNot(Integer status);
	//查询邀请码是否存在
	public User findByExclusiveCodeAndStatusNot(String inviteCode,Integer status);
	//验证QQ是否存在
	public User findByThridQqAndStatusNot(String userName,Integer status);
	
	//验证微信是否存在
	public User findByThridWchatAndStatusNot(String userName,Integer status);
	
	//验证微博是否存在
	public User findByThridWeiboAndStatusNot(String userName,Integer status);
	
}
