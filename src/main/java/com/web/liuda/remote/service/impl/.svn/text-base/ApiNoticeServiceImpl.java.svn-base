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
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Notice;
import com.web.liuda.business.repository.NoticeRepository;
import com.web.liuda.remote.service.ApiNoticeService;
import com.web.liuda.remote.vo.NoticeVo;
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
 * 类的说明：前端APINotice接口实现类
 **/
@Service("ApiNoticeService")
@Transactional(readOnly = false)
public class ApiNoticeServiceImpl extends BaseService<Notice> implements ApiNoticeService{

	@Autowired
	NoticeRepository noticeRepository;
	
	/**
	 * @Title: findNoticeById
	 * @Description: 黑板报、活动公告详情
	 * author：changlu
	 * time:2016-4-8 10:12:00
	 * @param modelId
	 * @return    
	 */
	@Override
	public XaResult<NoticeVo> findOne(Long tId) throws BusinessException {
		Notice obj = noticeRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<NoticeVo> xr = new XaResult<NoticeVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),NoticeVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<NoticeVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Notice> page = noticeRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Notice.class), pageable);
		XaResult<List<NoticeVo>> xr = new XaResult<List<NoticeVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), NoticeVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}
	/*
	 * 新闻报道列表
	 * author：changlu
	 * time:2016-04-26 14:43:00
	 */
	@Override
	public XaResult<Page<NoticeVo>> findNoticeListEQStatus(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		XaResult<Page<NoticeVo>> xr = new XaResult<Page<NoticeVo>>();
		StringBuffer sql=new StringBuffer("select n.id,n.title,n.img_path,n.content,n.create_time,n.introduce ");
		sql.append(" from tb_xa_notice n where n.status="+XaConstant.Status.valid+" and n.type="+JConstant.NoticeType.NEWS+" ");
		sql.append(" order by create_time desc ");
		StringBuffer countsql=new StringBuffer("select count(*)");
		countsql.append(" from tb_xa_notice n where n.status="+XaConstant.Status.valid+" and n.type="+JConstant.NoticeType.NEWS+"");
		
		List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
		List<Object[]> count=this.query(countsql.toString(),null,null);
		List<NoticeVo> vos=new ArrayList<NoticeVo>();
		for(Object[] obj:objs){
			NoticeVo vo=new NoticeVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setImgPath((String)obj[2]);
			vo.setContent((String)obj[3]);
			vo.setCreateTime((String)obj[4]);
			vo.setIntroduce((String)obj[5]);
			vos.add(vo);
		}
		//分页
		Page<NoticeVo> page = new MyPage<NoticeVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	/**
	 * @Description: 攻略页面的黑板报、赛事页面活动公告列表
	 * author：changlu
	 * time:2016-4-8 10:12:00
	 */
	@Override
	public XaResult<List<NoticeVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Notice> page = noticeRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Notice.class), pageable);
		XaResult<List<NoticeVo>> xr = new XaResult<List<NoticeVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), NoticeVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<NoticeVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<NoticeVo> xr = new XaResult<NoticeVo>();
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
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), NoticeVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<NoticeVo> createModel(Notice model)
			throws BusinessException {
		XaResult<NoticeVo> xr = new XaResult<NoticeVo>();
		Notice obj = noticeRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), NoticeVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<NoticeVo> updateModel(Notice model)
			throws BusinessException {
		Notice obj = noticeRepository.findOne(model.getId());
		XaResult<NoticeVo> xr = new XaResult<NoticeVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setTitle(model.getTitle());
		obj.setContent(model.getContent());
		obj.setImgPath(model.getImgPath());
		obj.setNeedPush(model.getNeedPush());
		obj.setType(model.getType());
			obj = noticeRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), NoticeVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
