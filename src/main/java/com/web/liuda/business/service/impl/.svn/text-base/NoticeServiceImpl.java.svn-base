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
import com.web.liuda.business.entity.Notice;
import com.web.liuda.business.repository.NoticeRepository;
import com.web.liuda.business.service.NoticeService;

@Service("NoticeService")
@Transactional(readOnly = false)
public class NoticeServiceImpl extends BaseService<Notice> implements NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	/**
	 * 查询单条Notice信息
	 * @param tId
	 * @return 返回单个Notice对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Notice> findOne(Long modelId) throws BusinessException {
		Notice obj = new Notice();
		if(modelId != 0){
			obj = noticeRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Notice> xr = new XaResult<Notice>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Notice数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Notice集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Notice>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Notice> page = noticeRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Notice.class), pageable);
		XaResult<Page<Notice>> xr = new XaResult<Page<Notice>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Notice数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Notice集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Notice>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Notice> page = noticeRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Notice.class), pageable);
		XaResult<Page<Notice>> xr = new XaResult<Page<Notice>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Notice信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Notice> saveOrUpdate(Notice model) throws BusinessException {
		Notice obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = noticeRepository.findOne(model.getId());
		}else{
			obj = new Notice();
		}
		if(model.getType()==3){
			obj.setTitle(model.getTitle());
			obj.setIntroduce(model.getIntroduce());
		}
		
		obj.setContent(model.getContent());
		obj.setImgPath(model.getImgPath());
		obj.setNeedPush(model.getNeedPush());
		obj.setType(model.getType());
		obj = noticeRepository.save(obj);
		XaResult<Notice> xr = new XaResult<Notice>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Notice状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Notice对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Notice> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Notice> xr = new XaResult<Notice>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Notice obj = noticeRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = noticeRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	
}
