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
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.MyBank;
import com.web.liuda.business.repository.CashRecordRepository;
import com.web.liuda.business.repository.MyBankRepository;
import com.web.liuda.business.service.MyBankService;

@Service("MyBankService")
@Transactional(readOnly = false)
public class MyBankServiceImpl extends BaseService<MyBank> implements MyBankService {

	@Autowired
	private MyBankRepository myBankRepository;
	@Autowired
	private CashRecordRepository cashRecordRepository;
	/**
	 * 查询单条MyBank信息
	 * @param tId
	 * @return 返回单个MyBank对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MyBank> findOne(Long modelId) throws BusinessException {
		MyBank obj = new MyBank();
		if(modelId != 0){
			obj = myBankRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MyBank> xr = new XaResult<MyBank>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MyBank数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MyBank集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MyBank>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MyBank> page = myBankRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MyBank.class), pageable);
		XaResult<Page<MyBank>> xr = new XaResult<Page<MyBank>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MyBank数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MyBank集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MyBank>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MyBank> page = myBankRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MyBank.class), pageable);
		XaResult<Page<MyBank>> xr = new XaResult<Page<MyBank>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MyBank信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MyBank> saveOrUpdate(MyBank model) throws BusinessException {
		MyBank obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = myBankRepository.findOne(model.getId());
		}else{
			obj = new MyBank();
		}
		obj.setAccount(model.getAccount());
		obj.setBankName(model.getBankName());
		obj.setChildBankName(model.getChildBankName());
		obj.setBusinessUserId(model.getBusinessUserId());
		obj.setCityCode(model.getCityCode());
		obj.setCityName(model.getCityName());
		obj.setCreateTime(model.getCreateTime());
		obj.setType(model.getType());
		obj.setRealName(model.getRealName());
		obj.setIsOtherBank(model.getIsOtherBank());
		myBankRepository.save(obj);
		XaResult<MyBank> xr = new XaResult<MyBank>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MyBank状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MyBank对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MyBank> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MyBank> xr = new XaResult<MyBank>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MyBank obj = myBankRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = myBankRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//验证是否绑定过银行卡
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MyBank> findByBusinessId(Long id) throws BusinessException {
		XaResult<MyBank> xr=new XaResult<MyBank>();
		MyBank m=myBankRepository.findByBusinessUserIdAndStatusNot(id, XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(m)){
			xr.setObject(m);
		}else{
			xr.error("未绑定");
		}
		return xr;
	}
	//删除银行卡
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MyBank> updataBankCard(Long id) throws BusinessException {
		XaResult<MyBank> xr=new XaResult<MyBank>();
		if(XaUtil.isNotEmpty(cashRecordRepository.findByObjectIdAndCashStatusNot(id, JConstant.BooleanStatus.TRUE))){
			xr.error("删除失败,提现中...");
			return xr;
		}
		MyBank bank=myBankRepository.findByBusinessUserIdAndStatusNot(id,XaConstant.Status.delete);
		if(XaUtil.isNotEmpty(bank)){
			xr.setObject(bank);
			myBankRepository.delete(bank.getId());
		}else{
			xr.error("删除失败");
		}
		return xr;
	}
	
}
