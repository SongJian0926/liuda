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
import com.web.liuda.business.entity.Withdraw;
import com.web.liuda.business.repository.WithdrawRepository;
import com.web.liuda.business.service.WithdrawService;

@Service("WithdrawService")
@Transactional(readOnly = false)
public class WithdrawServiceImpl extends BaseService<Withdraw> implements WithdrawService {

	@Autowired
	private WithdrawRepository withdrawRepository;
	
	/**
	 * 查询单条Withdraw信息
	 * @param tId
	 * @return 返回单个Withdraw对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Withdraw> findOne(Long modelId) throws BusinessException {
		Withdraw obj = new Withdraw();
		if(modelId != 0){
			obj = withdrawRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Withdraw> xr = new XaResult<Withdraw>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Withdraw数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Withdraw集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Withdraw>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Withdraw> page = withdrawRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Withdraw.class), pageable);
		XaResult<Page<Withdraw>> xr = new XaResult<Page<Withdraw>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Withdraw数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Withdraw集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Withdraw>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Withdraw> page = withdrawRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Withdraw.class), pageable);
		XaResult<Page<Withdraw>> xr = new XaResult<Page<Withdraw>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Withdraw信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Withdraw> saveOrUpdate(Withdraw model) throws BusinessException {
		Withdraw obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = withdrawRepository.findOne(model.getId());
		}else{
			obj = new Withdraw();
		}
		obj.setUserId(model.getUserId());
		obj.setAccount(model.getAccount());
		obj.setCaedInfoId(model.getCaedInfoId());
		obj = withdrawRepository.save(obj);
		XaResult<Withdraw> xr = new XaResult<Withdraw>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Withdraw状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Withdraw对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Withdraw> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Withdraw> xr = new XaResult<Withdraw>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Withdraw obj = withdrawRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = withdrawRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
