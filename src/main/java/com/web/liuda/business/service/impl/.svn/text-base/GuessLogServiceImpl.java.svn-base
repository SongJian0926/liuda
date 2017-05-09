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
import com.web.liuda.business.entity.GuessLog;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.GuessLogRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.business.service.GuessLogService;

@Service("GuessLogService")
@Transactional(readOnly = false)
public class GuessLogServiceImpl extends BaseService<GuessLog> implements GuessLogService {

	@Autowired
	private GuessLogRepository guessLogRepository;
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * 查询单条GuessLog信息
	 * @param tId
	 * @return 返回单个GuessLog对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<GuessLog> findOne(Long modelId) throws BusinessException {
		GuessLog obj = new GuessLog();
		if(modelId != 0){
			obj = guessLogRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<GuessLog> xr = new XaResult<GuessLog>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的GuessLog数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuessLog集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuessLog>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<GuessLog> page = guessLogRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuessLog.class), pageable);
		XaResult<Page<GuessLog>> xr = new XaResult<Page<GuessLog>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的GuessLog数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象GuessLog集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuessLog>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<GuessLog> page = guessLogRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), GuessLog.class), pageable);
		XaResult<Page<GuessLog>> xr = new XaResult<Page<GuessLog>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存GuessLog信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuessLog> saveOrUpdate(GuessLog model) throws BusinessException {
		GuessLog obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guessLogRepository.findOne(model.getId());
		}else{
			obj = new GuessLog();
		}
		obj.setMatchId(model.getMatchId());
		obj.setOptionId(model.getOptionId());
		obj.setBetScore(model.getBetScore());
		obj.setUserId(model.getUserId());
		obj = guessLogRepository.save(obj);
		XaResult<GuessLog> xr = new XaResult<GuessLog>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改GuessLog状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回GuessLog对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<GuessLog> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<GuessLog> xr = new XaResult<GuessLog>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				GuessLog obj = guessLogRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guessLogRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public void computeOdds(Long matchId, Long optionId, Double odds) throws BusinessException {
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("matchId", new SearchFilter("matchId", Operator.EQ, matchId));
		List<GuessLog> lst = guessLogRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), GuessLog.class));
		for(GuessLog o : lst)
		{
			User u = userRepository.findOne(o.getUserId());
			if(u!=null)
			{
				if(o.getOptionId().equals(optionId))
				{
					u.setPoints(u.getPoints()+o.getBetScore()+(int)(o.getBetScore()*odds));
					userRepository.save(u);
				}
			}
		}
		
	}
	
}
