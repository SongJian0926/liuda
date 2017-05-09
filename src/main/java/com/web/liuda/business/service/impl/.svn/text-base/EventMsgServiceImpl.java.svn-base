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
import com.web.liuda.business.entity.EventMsg;
import com.web.liuda.business.repository.EventMsgRepository;
import com.web.liuda.business.service.EventMsgService;

@Service("EventMsgService")
@Transactional(readOnly = false)
public class EventMsgServiceImpl extends BaseService<EventMsg> implements EventMsgService {

	@Autowired
	private EventMsgRepository eventMsgRepository;
	
	/**
	 * 查询单条EventMsg信息
	 * @param tId
	 * @return 返回单个EventMsg对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<EventMsg> findOne(Long modelId) throws BusinessException {
		EventMsg obj = new EventMsg();
		if(modelId != 0){
			obj = eventMsgRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<EventMsg> xr = new XaResult<EventMsg>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的EventMsg数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象EventMsg集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<EventMsg>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<EventMsg> page = eventMsgRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventMsg.class), pageable);
		XaResult<Page<EventMsg>> xr = new XaResult<Page<EventMsg>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的EventMsg数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象EventMsg集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<EventMsg>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<EventMsg> page = eventMsgRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventMsg.class), pageable);
		XaResult<Page<EventMsg>> xr = new XaResult<Page<EventMsg>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存EventMsg信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<EventMsg> saveOrUpdate(EventMsg model) throws BusinessException {
		EventMsg obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = eventMsgRepository.findOne(model.getId());
		}else{
			obj = new EventMsg();
		}
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
		obj.setEventId(model.getEventId());
		obj.setEvent_msg_id(model.getEvent_msg_id());
		obj.setMediaPath(model.getMediaPath());
		obj = eventMsgRepository.save(obj);
		XaResult<EventMsg> xr = new XaResult<EventMsg>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改EventMsg状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回EventMsg对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<EventMsg> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<EventMsg> xr = new XaResult<EventMsg>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				EventMsg obj = eventMsgRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = eventMsgRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
