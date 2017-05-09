package com.web.liuda.business.service.impl;

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
import com.web.liuda.business.entity.UserCard;
import com.web.liuda.business.repository.UserCardRepository;
import com.web.liuda.business.service.UserCardService;

@Service("UserCardService")
@Transactional(readOnly = false)
public class UserCardServiceImpl extends BaseService<UserCard> implements UserCardService {

	@Autowired
	private UserCardRepository userCardRepository;
	
	/**
	 * 查询单条UserCard信息
	 * @param tId
	 * @return 返回单个UserCard对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<UserCard> findOne(Long modelId) throws BusinessException {
		UserCard obj = new UserCard();
		if(modelId != 0){
			obj = userCardRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<UserCard> xr = new XaResult<UserCard>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的UserCard数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象UserCard集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<UserCard>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<UserCard> page = userCardRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), UserCard.class), pageable);
		XaResult<Page<UserCard>> xr = new XaResult<Page<UserCard>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的UserCard数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象UserCard集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<UserCard>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<UserCard> page = userCardRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), UserCard.class), pageable);
		XaResult<Page<UserCard>> xr = new XaResult<Page<UserCard>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存UserCard信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<UserCard> saveOrUpdate(UserCard model) throws BusinessException {
		UserCard obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = userCardRepository.findOne(model.getId());
		}else{
			obj = new UserCard();
		}
		obj.setUserId(model.getUserId());
		obj.setBankCardId(model.getBankCardId());
		obj.setCardHolder(model.getCardHolder());
		obj.setIdcard(model.getIdcard());
		obj.setOpeningBank(model.getOpeningBank());
		obj.setOpeningArea(model.getOpeningArea());
		obj.setSubBankNam(model.getSubBankNam());
		obj = userCardRepository.save(obj);
		XaResult<UserCard> xr = new XaResult<UserCard>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改UserCard状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回UserCard对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<UserCard> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<UserCard> xr = new XaResult<UserCard>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				UserCard obj = userCardRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = userCardRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
