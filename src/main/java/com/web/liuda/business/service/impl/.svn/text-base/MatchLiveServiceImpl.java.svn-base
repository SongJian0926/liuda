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
import com.web.liuda.business.entity.MatchLive;
import com.web.liuda.business.repository.MatchLiveRepository;
import com.web.liuda.business.service.MatchLiveService;

@Service("MatchLiveService")
@Transactional(readOnly = false)
public class MatchLiveServiceImpl extends BaseService<MatchLive> implements MatchLiveService {

	@Autowired
	private MatchLiveRepository matchLiveRepository;
	
	/**
	 * 查询单条MatchLive信息
	 * @param tId
	 * @return 返回单个MatchLive对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MatchLive> findOne(Long modelId) throws BusinessException {
		MatchLive obj = new MatchLive();
		if(modelId != 0){
			obj = matchLiveRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MatchLive> xr = new XaResult<MatchLive>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MatchLive数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchLive集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchLive>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MatchLive> page = matchLiveRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchLive.class), pageable);
		XaResult<Page<MatchLive>> xr = new XaResult<Page<MatchLive>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MatchLive数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MatchLive集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MatchLive>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MatchLive> page = matchLiveRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MatchLive.class), pageable);
		XaResult<Page<MatchLive>> xr = new XaResult<Page<MatchLive>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MatchLive信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchLive> saveOrUpdate(MatchLive model) throws BusinessException {
		MatchLive obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = matchLiveRepository.findOne(model.getId());
		}else{
			obj = new MatchLive();
		}
		obj.setMatchId(model.getMatchId());
		obj.setContent(model.getContent());
		obj.setMediaPath(model.getMediaPath());
		obj.setMediaImg(model.getMediaImg());
		obj = matchLiveRepository.save(obj);
		XaResult<MatchLive> xr = new XaResult<MatchLive>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MatchLive状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MatchLive对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MatchLive> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MatchLive> xr = new XaResult<MatchLive>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MatchLive obj = matchLiveRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = matchLiveRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
