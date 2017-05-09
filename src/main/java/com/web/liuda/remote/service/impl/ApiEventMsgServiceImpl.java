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
import com.web.liuda.business.entity.EventMsg;
import com.web.liuda.business.entity.User;
import com.web.liuda.business.repository.EventMsgRepository;
import com.web.liuda.business.repository.UserRepository;
import com.web.liuda.remote.service.ApiEventMsgService;
import com.web.liuda.remote.vo.EventMsgVo;
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
 * 类的说明：前端APIEventMsg接口实现类
 **/
@Service("ApiEventMsgService")
@Transactional(readOnly = false)
public class ApiEventMsgServiceImpl extends BaseService<EventMsg> implements ApiEventMsgService{

	@Autowired
	EventMsgRepository eventMsgRepository;
	@Autowired
	UserRepository userRepository;
	@Override
	public XaResult<EventMsgVo> findOne(Long tId) throws BusinessException {
		EventMsg obj = eventMsgRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<EventMsgVo> xr = new XaResult<EventMsgVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),EventMsgVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<EventMsgVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<EventMsg> page = eventMsgRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventMsg.class), pageable);
		XaResult<List<EventMsgVo>> xr = new XaResult<List<EventMsgVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), EventMsgVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/*
	 * 俱乐部活动留言列表
	 * author:changlu
	 * time:2016-04-18 12:10:00
	 */
	@Override
	public XaResult<Page<EventMsgVo>> findEventMsgList(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<EventMsgVo>> xr=new XaResult<Page<EventMsgVo>>();
		String sql="call pro_clubEvent_msg(?,?,?)";
		List<Object> params=new ArrayList<Object>();
		params.add(Long.valueOf(filterParams.get("EQ_clubEventId")+""));
		params.add(pageable.getPageNumber()*pageable.getPageSize());
		params.add(pageable.getPageSize());
		List<Object[]> objs=this.queryCall(sql, params);
		StringBuffer countsql=new StringBuffer("select count(*) from tb_xa_eventmsg em ");
		countsql.append("LEFT JOIN tb_xa_user u ON em.user_id=u.id ");
		countsql.append("WHERE em.event_id="+Long.valueOf(filterParams.get("EQ_clubEventId")+"")+" and em.status="+XaConstant.Status.valid+" and em.event_msg_id is null ");
		List<Object[]>  count=this.query(countsql.toString(),null,null);
		List<EventMsgVo> vos=new ArrayList<EventMsgVo>();
		for(Object[] obj:objs){
			EventMsgVo vo=new EventMsgVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setContent((String)obj[1]);
			vo.setCreateTime((String)obj[2]);
			vo.setEventId(XaUtil.isEmpty(obj[3])?null:((BigInteger)obj[3]).longValue());
			vo.setMediaPath((String)obj[4]);
			UserVo userVo=new UserVo();
			userVo.setId(XaUtil.isEmpty(obj[5])?null:((BigInteger)obj[5]).longValue());
			userVo.setUserName((String)obj[6]);
			userVo.setPhoto((String)obj[7]);
			vo.setUserVo(userVo);
			//某条评论的回复信息
			vo.setEventMsgVos(getReply(vo.getId()));
			vos.add(vo);
		}
		//分页
		Page<EventMsgVo> page = new MyPage<EventMsgVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/*获取某条评论的回复信息
	 * author:changlu
	 * time:2016-04-18 12:10:00
	 * */
	public List<EventMsgVo> getReply(Long eventMsgId){
		String sql="call pro_clubEvent_reply(?)";
		List<Object> params=new ArrayList<Object>();
		params.add(eventMsgId);
		List<Object[]> objs=this.queryCall(sql, params);
		List<EventMsgVo> vos=new ArrayList<EventMsgVo>();
		for(Object[] obj:objs){
			EventMsgVo vo=new EventMsgVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setContent((String)obj[1]);
			vo.setCreateTime((String)obj[2]);
			vo.setEventId(XaUtil.isEmpty(obj[3])?null:((BigInteger)obj[3]).longValue());
			vo.setMediaPath((String)obj[4]);
			UserVo userVo=new UserVo();
			userVo.setId(XaUtil.isEmpty(obj[5])?null:((BigInteger)obj[5]).longValue());
			userVo.setUserName((String)obj[6]);
			userVo.setPhoto((String)obj[7]);
			vo.setUserVo(userVo);
			vo.setEvent_msg_id(XaUtil.isEmpty(obj[8])?null:((BigInteger)obj[8]).longValue());
			vos.add(vo);
		}
		return vos;
	}
	@Override
	public XaResult<List<EventMsgVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<EventMsg> page = eventMsgRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), EventMsg.class), pageable);
		XaResult<List<EventMsgVo>> xr = new XaResult<List<EventMsgVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), EventMsgVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<EventMsgVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<EventMsgVo> xr = new XaResult<EventMsgVo>();
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
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), EventMsgVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<EventMsgVo> createModel(EventMsg model)
			throws BusinessException {
		XaResult<EventMsgVo> xr = new XaResult<EventMsgVo>();
		EventMsg obj = eventMsgRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), EventMsgVo.class));
		xr.getObject().setId(obj.getId());
		User user=userRepository.findByIdAndStatus(model.getUserId(),XaConstant.Status.valid);
		UserVo userVo=new UserVo();
		userVo.setUserName(user.getUserName());
		userVo.setPhoto(user.getPhoto());
		xr.getObject().setUserVo(userVo);
		return xr;
	}

	@Override
	public XaResult<EventMsgVo> updateModel(EventMsg model)
			throws BusinessException {
		EventMsg obj = eventMsgRepository.findOne(model.getId());
		XaResult<EventMsgVo> xr = new XaResult<EventMsgVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setContent(model.getContent());
		obj.setEventId(model.getEventId());
		obj.setEvent_msg_id(model.getEvent_msg_id());
		obj.setMediaPath(model.getMediaPath());
			obj = eventMsgRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), EventMsgVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
