package com.web.liuda.business.service.impl;

import java.util.HashMap;
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
import com.web.liuda.business.entity.GuessOption;
import com.web.liuda.business.repository.GuessOptionRepository;
import com.web.liuda.business.service.GuessOptionService;

@Service("GuessOptionService")
@Transactional(readOnly = false)
public class GuessOptionServiceImpl extends BaseService<GuessOption> implements GuessOptionService {

	@Autowired
	private GuessOptionRepository guessOptionRepository;
	
	/**
	 * 查询单条GuessOption信息
	 * @param tId
	 * @return 返回单个GuessOption对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<GuessOption> findOne(Long modelId) throws BusinessException {
		GuessOption obj = new GuessOption();
		if(modelId != 0){
			obj = guessOptionRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<GuessOption> xr = new XaResult<GuessOption>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的GuessOption数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuessOption集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuessOption>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<GuessOption> page = guessOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuessOption.class), pageable);
		XaResult<Page<GuessOption>> xr = new XaResult<Page<GuessOption>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的GuessOption数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuessOption集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuessOption>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<GuessOption> page = guessOptionRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuessOption.class), pageable);
		XaResult<Page<GuessOption>> xr = new XaResult<Page<GuessOption>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存GuessOption信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuessOption> saveOrUpdate(GuessOption model) throws BusinessException {
		GuessOption obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guessOptionRepository.findOne(model.getId());
		}else{
			obj = new GuessOption();
		}
		obj.setMatchId(model.getMatchId());
		obj.setOption(model.getOption());
		obj.setIsRight(model.getIsRight());
		obj = guessOptionRepository.save(obj);
		XaResult<GuessOption> xr = new XaResult<GuessOption>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改GuessOption状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回GuessOption对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuessOption> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<GuessOption> xr = new XaResult<GuessOption>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				GuessOption obj = guessOptionRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guessOptionRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<GuessOption> setGuessOption(Long matchId, Long modelId) throws BusinessException {
		XaResult<GuessOption> xr = new XaResult<GuessOption>();
		
		if(XaUtil.isNotEmpty(modelId)){
			
				Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
				filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
				List<GuessOption> lst = guessOptionRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), GuessOption.class));
				for(GuessOption o : lst)
				{
					if(o.getId().equals(modelId))
					{
						o.setIsRight(JConstant.BooleanStatus.TRUE);
						xr.setObject(o);
					}
					else
					{
						o.setIsRight(JConstant.BooleanStatus.FALSE);
					}
					guessOptionRepository.save(o);
				}
			
		}
		else
		{
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
}
