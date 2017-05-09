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
import com.web.liuda.business.entity.VoteLog;
import com.web.liuda.business.repository.VoteLogRepository;
import com.web.liuda.business.service.VoteLogService;

@Service("VoteLogService")
@Transactional(readOnly = false)
public class VoteLogServiceImpl extends BaseService<VoteLog> implements VoteLogService {

	@Autowired
	private VoteLogRepository voteLogRepository;
	
	/**
	 * 查询单条VoteLog信息
	 * @param tId
	 * @return 返回单个VoteLog对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<VoteLog> findOne(Long modelId) throws BusinessException {
		VoteLog obj = new VoteLog();
		if(modelId != 0){
			obj = voteLogRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<VoteLog> xr = new XaResult<VoteLog>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的VoteLog数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象VoteLog集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<VoteLog>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<VoteLog> page = voteLogRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteLog.class), pageable);
		XaResult<Page<VoteLog>> xr = new XaResult<Page<VoteLog>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的VoteLog数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象VoteLog集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<VoteLog>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<VoteLog> page = voteLogRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), VoteLog.class), pageable);
		XaResult<Page<VoteLog>> xr = new XaResult<Page<VoteLog>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存VoteLog信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<VoteLog> saveOrUpdate(VoteLog model) throws BusinessException {
		VoteLog obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = voteLogRepository.findOne(model.getId());
		}else{
			obj = new VoteLog();
		}
		obj.setUserId(model.getUserId());
		obj.setVoteId(model.getVoteId());
		obj.setVoteOptionId(model.getVoteOptionId());
		obj = voteLogRepository.save(obj);
		XaResult<VoteLog> xr = new XaResult<VoteLog>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改VoteLog状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回VoteLog对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<VoteLog> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<VoteLog> xr = new XaResult<VoteLog>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				VoteLog obj = voteLogRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = voteLogRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
