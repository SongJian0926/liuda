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
import com.web.liuda.business.entity.PrizeResult;
import com.web.liuda.business.repository.PrizeResultRepository;
import com.web.liuda.business.service.PrizeResultService;

@Service("PrizeResultService")
@Transactional(readOnly = false)
public class PrizeResultServiceImpl extends BaseService<PrizeResult> implements PrizeResultService {

	@Autowired
	private PrizeResultRepository prizeResultRepository;
	
	/**
	 * 查询单条PrizeResult信息
	 * @param tId
	 * @return 返回单个PrizeResult对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<PrizeResult> findOne(Long modelId) throws BusinessException {
		PrizeResult obj = new PrizeResult();
		if(modelId != 0){
			obj = prizeResultRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<PrizeResult> xr = new XaResult<PrizeResult>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的PrizeResult数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象PrizeResult集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<PrizeResult>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<PrizeResult> page = prizeResultRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), PrizeResult.class), pageable);
		XaResult<Page<PrizeResult>> xr = new XaResult<Page<PrizeResult>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的PrizeResult数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象PrizeResult集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<PrizeResult>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<PrizeResult> page = prizeResultRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), PrizeResult.class), pageable);
		XaResult<Page<PrizeResult>> xr = new XaResult<Page<PrizeResult>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存PrizeResult信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<PrizeResult> saveOrUpdate(PrizeResult model) throws BusinessException {
		PrizeResult obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = prizeResultRepository.findOne(model.getId());
		}else{
			obj = new PrizeResult();
		}
		obj.setMatchId(model.getMatchId());
		obj.setUserId(model.getUserId());
		obj.setPrizeOptionId(model.getPrizeOptionId());
		obj = prizeResultRepository.save(obj);
		XaResult<PrizeResult> xr = new XaResult<PrizeResult>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改PrizeResult状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回PrizeResult对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<PrizeResult> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<PrizeResult> xr = new XaResult<PrizeResult>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				PrizeResult obj = prizeResultRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = prizeResultRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
