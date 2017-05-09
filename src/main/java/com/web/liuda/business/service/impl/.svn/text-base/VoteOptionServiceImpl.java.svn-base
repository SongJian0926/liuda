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
import com.web.liuda.business.entity.VoteOption;
import com.web.liuda.business.repository.VoteOptionRepository;
import com.web.liuda.business.service.VoteOptionService;

@Service("VoteOptionService")
@Transactional(readOnly = false)
public class VoteOptionServiceImpl extends BaseService<VoteOption> implements VoteOptionService {

	@Autowired
	private VoteOptionRepository voteOptionRepository;
	
	/**
	 * 查询单条VoteOption信息
	 * @param tId
	 * @return 返回单个VoteOption对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<VoteOption> findOne(Long modelId) throws BusinessException {
		VoteOption obj = new VoteOption();
		if(modelId != 0){
			obj = voteOptionRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<VoteOption> xr = new XaResult<VoteOption>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的VoteOption数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象VoteOption集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<VoteOption>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<VoteOption> page = voteOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteOption.class), pageable);
		XaResult<Page<VoteOption>> xr = new XaResult<Page<VoteOption>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的VoteOption数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象VoteOption集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<VoteOption>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<VoteOption> page = voteOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteOption.class), pageable);
		XaResult<Page<VoteOption>> xr = new XaResult<Page<VoteOption>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存VoteOption信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<VoteOption> saveOrUpdate(VoteOption model) throws BusinessException {
		VoteOption obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = voteOptionRepository.findOne(model.getId());
		}else{
			obj = new VoteOption();
		}
		obj.setOptionName(model.getOptionName());
		obj.setVoteId(model.getVoteId());
		obj.setNum(0);
		obj = voteOptionRepository.save(obj);
		XaResult<VoteOption> xr = new XaResult<VoteOption>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改VoteOption状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回VoteOption对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<VoteOption> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<VoteOption> xr = new XaResult<VoteOption>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				VoteOption obj = voteOptionRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = voteOptionRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
