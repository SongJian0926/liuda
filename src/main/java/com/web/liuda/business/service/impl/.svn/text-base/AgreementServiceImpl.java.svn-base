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
import com.web.liuda.business.entity.Agreement;
import com.web.liuda.business.repository.AgreementRepository;
import com.web.liuda.business.service.AgreementService;

@Service("AgreementService")
@Transactional(readOnly = false)
public class AgreementServiceImpl extends BaseService<Agreement> implements AgreementService {

	@Autowired
	private AgreementRepository agreementRepository;
	
	/**
	 * 查询单条Agreement信息
	 * @param tId
	 * @return 返回单个Agreement对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Agreement> findOne(Long modelId) throws BusinessException {
		Agreement obj = new Agreement();
		if(modelId != 0){
			obj = agreementRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Agreement> xr = new XaResult<Agreement>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Agreement数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Agreement集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Agreement>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Agreement> page = agreementRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Agreement.class), pageable);
		XaResult<Page<Agreement>> xr = new XaResult<Page<Agreement>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Agreement数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Agreement集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Agreement>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Agreement> page = agreementRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Agreement.class), pageable);
		XaResult<Page<Agreement>> xr = new XaResult<Page<Agreement>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Agreement信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Agreement> saveOrUpdate(Agreement model) throws BusinessException {
		Agreement obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = agreementRepository.findOne(model.getId());
		}else{
			obj = new Agreement();
		}
		XaResult<Agreement> xr = new XaResult<Agreement>();
		if(XaUtil.isEmpty(model.getId())){
			Agreement agreement=agreementRepository.findByTypeAndStatus(model.getType(), XaConstant.Status.valid);
			if(XaUtil.isNotEmpty(agreement)){
				xr.error("该类型的说明/协议已存在！");
				return xr;
			}
		}
		if(XaUtil.isNotEmpty(model.getId())){
			Agreement agreement=agreementRepository.findByTypeAndStatusAndIdNot(model.getType(), XaConstant.Status.valid,model.getId());
			if(XaUtil.isNotEmpty(agreement)){
				xr.error("该类型的说明/协议已存在！");
				return xr;
			}
		}
		obj.setContent(model.getContent());
		obj.setType(model.getType());
		obj = agreementRepository.save(obj);
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Agreement状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Agreement对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Agreement> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Agreement> xr = new XaResult<Agreement>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Agreement obj = agreementRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = agreementRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
