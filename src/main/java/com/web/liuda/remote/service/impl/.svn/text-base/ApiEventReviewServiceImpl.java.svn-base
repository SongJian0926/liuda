package com.web.liuda.remote.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.entity.EventReview;
import com.web.liuda.business.repository.EventReviewRepository;
import com.web.liuda.remote.service.ApiEventReviewService;
import com.web.liuda.remote.vo.EventReviewVo;
import com.web.liuda.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIEventReview接口实现类
 **/
@Service("ApiEventReviewService")
@Transactional(readOnly = false)
public class ApiEventReviewServiceImpl extends BaseService<EventReview> implements ApiEventReviewService{

	@Autowired
	EventReviewRepository eventReviewRepository;
	
	@Override
	public XaResult<EventReviewVo> findOne(Long tId) throws BusinessException {
		EventReview obj = eventReviewRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<EventReviewVo> xr = new XaResult<EventReviewVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),EventReviewVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	/*俱乐部活动回顾列表
	 * author:changlu
	 * time:2016-04-18 14:10:00
	 */
	@Override
	public XaResult<Page<EventReviewVo>> findEventReviewList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		StringBuffer sql=new StringBuffer("select er.id,er.title,er.content,er.create_time,er.event_id,er.user_id,er.media_path,u.user_name,u.photo ");
		sql.append("from tb_xa_eventreview er LEFT JOIN tb_xa_user u ON er.user_id=u.id ");
		sql.append("WHERE er.event_id="+Long.valueOf(filterParams.get("EQ_clubEventId")+"")+" and er.status="+XaConstant.Status.valid+" ");
		sql.append(" order by er.create_time desc ");
		List<Object[]> objs=this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize());
		List<EventReviewVo> vos=new ArrayList<EventReviewVo>();
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_eventreview er ");
		countsql.append("LEFT JOIN tb_xa_user u ON er.user_id=u.id ");
		countsql.append("WHERE er.event_id="+Long.valueOf(filterParams.get("EQ_clubEventId")+"")+" and er.status="+XaConstant.Status.valid+" ");
		List<Object[]>  count=this.query(countsql.toString(),null,null);
		for(Object[] obj:objs){
			EventReviewVo vo=new EventReviewVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setContent((String)obj[2]);
			vo.setCreateTime((String)obj[3]);
			vo.setEventId(XaUtil.isEmpty(obj[4])?null:((BigInteger)obj[4]).longValue());
			
			vo.setUserId(XaUtil.isEmpty(obj[5])?null:((BigInteger)obj[5]).longValue());
			vo.setMediaPath((String)obj[6]);
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[7]);
			userVo.setPhoto((String)obj[8]);
			vo.setUserVo(userVo);
			vos.add(vo);
			
		}
		XaResult<Page<EventReviewVo>> xr=new XaResult<Page<EventReviewVo>>();
		//分页
		Page<EventReviewVo> page = new MyPage<EventReviewVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	@Override
	public XaResult<List<EventReviewVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<EventReview> page = eventReviewRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventReview.class), pageable);
		XaResult<List<EventReviewVo>> xr = new XaResult<List<EventReviewVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), EventReviewVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<EventReviewVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<EventReview> page = eventReviewRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventReview.class), pageable);
		XaResult<List<EventReviewVo>> xr = new XaResult<List<EventReviewVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), EventReviewVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<EventReviewVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<EventReviewVo> xr = new XaResult<EventReviewVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				EventReview obj = eventReviewRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = eventReviewRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), EventReviewVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<EventReviewVo> createModel(EventReview model)
			throws BusinessException {
		XaResult<EventReviewVo> xr = new XaResult<EventReviewVo>();
		EventReview obj = eventReviewRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), EventReviewVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<EventReviewVo> updateModel(EventReview model)
			throws BusinessException {
		EventReview obj = eventReviewRepository.findOne(model.getId());
		XaResult<EventReviewVo> xr = new XaResult<EventReviewVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setTitle(model.getTitle());
		obj.setContent(model.getContent());
		obj.setMediaPath(model.getMediaPath());
		obj.setUserId(model.getUserId());
		obj.setEventId(model.getEventId());
			obj = eventReviewRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), EventReviewVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
