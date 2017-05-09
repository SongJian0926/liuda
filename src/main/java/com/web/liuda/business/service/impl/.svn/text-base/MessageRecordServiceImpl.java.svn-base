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
import com.web.liuda.business.entity.MessageRecord;
import com.web.liuda.business.repository.MessageRecordRepository;
import com.web.liuda.business.service.MessageRecordService;

@Service("MessageRecordService")
@Transactional(readOnly = false)
public class MessageRecordServiceImpl extends BaseService<MessageRecord> implements MessageRecordService {

	@Autowired
	private MessageRecordRepository messageRecordRepository;
	
	/**
	 * 查询单条MessageRecord信息
	 * @param tId
	 * @return 返回单个MessageRecord对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<MessageRecord> findOne(Long modelId) throws BusinessException {
		MessageRecord obj = new MessageRecord();
		if(modelId != 0){
			obj = messageRecordRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<MessageRecord> xr = new XaResult<MessageRecord>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的MessageRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MessageRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MessageRecord>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<MessageRecord> page = messageRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MessageRecord.class), pageable);
		XaResult<Page<MessageRecord>> xr = new XaResult<Page<MessageRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的MessageRecord数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象MessageRecord集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<MessageRecord>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<MessageRecord> page = messageRecordRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), MessageRecord.class), pageable);
		XaResult<Page<MessageRecord>> xr = new XaResult<Page<MessageRecord>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存MessageRecord信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MessageRecord> saveOrUpdate(MessageRecord model) throws BusinessException {
		MessageRecord obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = messageRecordRepository.findOne(model.getId());
		}else{
			obj = new MessageRecord();
		}
		obj.setUserId(model.getUserId());
		obj.setMessageId(model.getMessageId());
		obj = messageRecordRepository.save(obj);
		XaResult<MessageRecord> xr = new XaResult<MessageRecord>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改MessageRecord状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回MessageRecord对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<MessageRecord> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<MessageRecord> xr = new XaResult<MessageRecord>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				MessageRecord obj = messageRecordRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = messageRecordRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
