package com.web.liuda.business.service.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.business.service.UserService;

@Service("UserService")
@Transactional(readOnly = false)
public class UserServiceImpl extends BaseService<User> implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询单条User信息
	 * @param tId
	 * @return 返回单个User对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<User> findOne(Long modelId) throws BusinessException {
		User obj = new User();
		if(modelId != 0){
			obj = userRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<User> xr = new XaResult<User>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的User数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象User集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<User>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<User> page = userRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), User.class), pageable);
		XaResult<Page<User>> xr = new XaResult<Page<User>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的User数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象User集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<User>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<User> page = userRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), User.class), pageable);
		XaResult<Page<User>> xr = new XaResult<Page<User>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存User信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<User> saveOrUpdate(User model) throws BusinessException {
		User obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = userRepository.findOne(model.getId());
		}else{
			obj = new User();
		}
		obj.setUserName(model.getUserName());
		obj.setPassword(model.getPassword());
		obj.setMobile(model.getMobile());
		obj.setPhoto(model.getPhoto());
		obj = userRepository.save(obj);
		XaResult<User> xr = new XaResult<User>();
		xr.setObject(obj);
		return xr;
	}
	/**
	 * 同意申请为大咖
	 * author:changlu
	 * time:2016-05-10 14:27:00
	 * @param request
	 * @param modelIds
	 * @param bigShot
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<User> checkBigShot(
			String modelIds,Integer bigShot) throws BusinessException {
		XaResult<User> xr = new XaResult<User>();
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				User obj = userRepository.findByIdAndStatusNot(Long.parseLong(id),XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setBigShot(bigShot);
					//设置为大咖之后，分配一个邀请码
					try {
						obj.setExclusiveCode(XaUtil.getExclusiveCode());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					obj.setApplyStatus(JConstant.ApplyStatus.CHECKSUCCEED);
					obj = userRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * 修改User状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回User对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<User> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<User> xr = new XaResult<User>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				User obj = userRepository.findOne(Long.parseLong(id));
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = userRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<List<User>> findUserListStatusNot(Integer status) throws BusinessException {
		if(status == null){
			status = XaConstant.Status.delete;
		}
		List<User> obj = userRepository.findByStatusNot(status);
		XaResult<List<User>> xr = new XaResult<List<User>>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
}
