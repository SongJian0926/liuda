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
import com.web.liuda.business.entity.ClubEvent;
import com.web.liuda.business.repository.ClubEventRepository;
import com.web.liuda.business.service.ClubEventService;

@Service("ClubEventService")
@Transactional(readOnly = false)
public class ClubEventServiceImpl extends BaseService<ClubEvent> implements ClubEventService {

	@Autowired
	private ClubEventRepository clubEventRepository;
	
	/**
	 * 查询单条ClubEvent信息
	 * @param tId
	 * @return 返回单个ClubEvent对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<ClubEvent> findOne(Long modelId) throws BusinessException {
		ClubEvent obj = new ClubEvent();
		if(modelId != 0){
			obj = clubEventRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<ClubEvent> xr = new XaResult<ClubEvent>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的ClubEvent数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ClubEvent集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ClubEvent>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<ClubEvent> page = clubEventRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubEvent.class), pageable);
		XaResult<Page<ClubEvent>> xr = new XaResult<Page<ClubEvent>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的ClubEvent数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象ClubEvent集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<ClubEvent>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<ClubEvent> page = clubEventRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), ClubEvent.class), pageable);
		XaResult<Page<ClubEvent>> xr = new XaResult<Page<ClubEvent>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存ClubEvent信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ClubEvent> saveOrUpdate(ClubEvent model) throws BusinessException {
		ClubEvent obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = clubEventRepository.findOne(model.getId());
		}else{
			obj = new ClubEvent();
		}
		obj.setTitle(model.getTitle());
		obj.setLogo(model.getLogo());
		obj.setStarttime(model.getStarttime());
		obj.setEndtime(model.getEndtime());
		obj.setEventStatus(model.getEventStatus());
		obj.setPrice(model.getPrice());
		obj.setDeadline(model.getDeadline());
		obj.setMaxNum(model.getMaxNum());
		obj.setContent(model.getContent());
		obj.setAddress(model.getAddress());
		obj.setDisposit(model.getDisposit());
		obj.setMediaPath(model.getMediaPath());
		obj = clubEventRepository.save(obj);
		XaResult<ClubEvent> xr = new XaResult<ClubEvent>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改ClubEvent状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回ClubEvent对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<ClubEvent> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<ClubEvent> xr = new XaResult<ClubEvent>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				ClubEvent obj = clubEventRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = clubEventRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<ClubEvent> applyClubEvent(String eventIds, Integer status) throws BusinessException {
		XaResult<ClubEvent> xr = new XaResult<ClubEvent>();
		if(XaUtil.isNotEmpty(eventIds)){
			String[] rids = eventIds.split(",");
			for(String rid : rids){
				ClubEvent obj=clubEventRepository.findByIdAndStatusNot(Long.parseLong(rid), XaConstant.Status.delete);
				if (XaUtil.isNotEmpty(obj)) {
				  //
				  if(status.equals(XaConstant.Status.publish)){
					  if(obj.getStatus()==XaConstant.Status.valid){
						  obj.setStatus(XaConstant.Status.publish);
						  obj = clubEventRepository.save(obj);
					  }
				  //
				  }else if(status.equals(XaConstant.Status.locked)){
					  if(obj.getStatus()==XaConstant.Status.valid){
						  obj.setStatus(XaConstant.Status.locked);
						  obj = clubEventRepository.save(obj);
					  }
				  }
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
		    }
		}
		return xr;
	}
	
}
