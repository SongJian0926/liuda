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
import com.web.liuda.business.entity.Guide;
import com.web.liuda.business.entity.GuideAppend;
import com.web.liuda.business.repository.GuideAppendRepository;
import com.web.liuda.business.repository.GuideRepository;
import com.web.liuda.remote.service.ApiGuideService;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.GuideAppendVo;
import com.web.liuda.remote.vo.GuideVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.TicketsVo;
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
 * 类的说明：前端APIGuide接口实现类
 **/
@Service("ApiGuideService")
@Transactional(readOnly = false)
public class ApiGuideServiceImpl extends BaseService<Guide> implements ApiGuideService{

	@Autowired
	GuideRepository guideRepository;
	@Autowired
	GuideAppendRepository guideAppendRepository;
	/*
	 * author：changlu
	 * time:2016-04-11 12:00:00
	 * 攻略详情
	 */
	@Override
	public XaResult<GuideVo> findOne(Long tId) throws BusinessException {
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		StringBuffer sql=new StringBuffer("select g.id GuideId,g.title,g.last_update,u.user_name,");
		sql.append("g.type,d.dict_name,g.pageview,g.object_id,g.object_type,u.id ");
		sql.append("from tb_xa_guide g ");
		sql.append("inner join tb_xa_user u on u.id=g.user_id ");
		sql.append("inner join tb_xa_dictitem d on d.id=g.dict_item_id ");
		sql.append("where g.status="+XaConstant.Status.publish+" and g.id="+tId+"");
		List<Object[]> objs = this.query(sql.toString(), null, null);
		GuideVo vo = new GuideVo();
		for(Object[] obj : objs){
			
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLastUpdate((String)obj[2]);
			//作者信息
			UserVo userVo=new UserVo();
			userVo.setId(XaUtil.isEmpty(obj[9])?null:((BigInteger)obj[9]).longValue());
			userVo.setUserName((String)obj[3]);
			vo.setUserVo(userVo);
			vo.setType(XaUtil.isEmpty(obj[4]) ? null:(Integer)obj[4]);
			//标签信息
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[5]);
			vo.setDictItemVo(dictItemVo);
			//浏览量
			vo.setPageview(XaUtil.isEmpty(obj[6])?0 :(Integer)obj[6]);
			//酒店、景点信息
			if(XaUtil.isNotEmpty(obj[8])&&Integer.valueOf(obj[8]+"")==JConstant.ObjectType.HOTEL&&XaUtil.isNotEmpty(obj[7])){
				//调查询酒店、景点信息的方法
				vo.setHotelVo(getObjectInfo(Integer.valueOf(obj[8]+""),((BigInteger)obj[7]).longValue()));
				
			}
			if(XaUtil.isNotEmpty(obj[8])&&Integer.valueOf(obj[8]+"")==JConstant.ObjectType.TOURIST&&XaUtil.isNotEmpty(obj[7])){
				vo.setTicketsVo(getObjectInfos(Integer.valueOf(obj[8]+""),((BigInteger)obj[7]).longValue()));
			}
			//查询追加的内容
			List<GuideAppend> guideAppend = guideAppendRepository.findByGuideIdAndStatusNot(tId,XaConstant.Status.delete);
			List<GuideAppendVo> guideAppendVo = new ArrayList<GuideAppendVo>();
			for(GuideAppend g : guideAppend){
				GuideAppendVo gvo=new GuideAppendVo();
				gvo.setContent(g.getContent());
				gvo.setMediaPath(g.getMediaPath());
				gvo.setCreateTime(g.getCreateTime());
				guideAppendVo.add(gvo);
			}
			//设置追加的内容集合
			vo.setGuideAppendVos(guideAppendVo);
		}
		
		if (XaUtil.isNotEmpty(objs)) {
			xr.setObject(vo);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	
	/*
	 * author：changlu
	 * time:2016-04-11 12:00:00
	 * 返回攻略中推荐的酒店或者景点信息
	 */
	public HotelVo getObjectInfo(Integer type,Long id){
		String sql="call pro_objectInfo(?,?)";
		List<Object> objparams = new ArrayList<Object>();
		objparams.add(type);
		objparams.add(id);
		List<Object[]> aboutobjs = this.queryCall(sql, objparams);
		HotelVo v = new HotelVo();
		for(Object[] b : aboutobjs){
			
			v.setId(XaUtil.isEmpty(b[0])?null:((BigInteger)b[0]).longValue());
			v.setHotelName((String)b[1]);
			v.setPicurl((String)b[2]);
			v.setPrice(XaUtil.isEmpty(b[3]) ? 0 : (Double)b[3]);
			v.setGroupPrice(XaUtil.isEmpty(b[4]) ? null : (Double)b[4]);
			v.setGroupBuy(XaUtil.isEmpty(b[5])?null:Integer.valueOf(b[5]+""));
			
		}
		
		return v;
	}
	/*
	 * author：changlu
	 * time:2016-04-11 12:00:00
	 * 返回攻略中推荐的酒店或者景点信息
	 */
	public TicketsVo getObjectInfos(Integer type,Long id){
		String sql="call pro_objectInfo(?,?)";
		List<Object> objparams = new ArrayList<Object>();
		objparams.add(type);
		objparams.add(id);
		List<Object[]> aboutobjs = this.queryCall(sql, objparams);
		TicketsVo v = new TicketsVo();
		for(Object[] b : aboutobjs){
			v.setId(XaUtil.isEmpty(b[0])?null:((BigInteger)b[0]).longValue());
			v.setTicketName((String)b[1]);
			v.setImgUrl((String)b[2]);
			v.setPrice(XaUtil.isEmpty(b[3]) ? 0 : (Double)b[3]);
			v.setGroupPrice(XaUtil.isEmpty(b[4]) ? null : (Double)b[4]);
			v.setGroupBuy(XaUtil.isEmpty(b[5])?null:Integer.valueOf(b[5]+""));
		}
		return v;
	}
	/**
	 * 查询攻略列表
	 * author:changlu
	 * time:2016-04-08 15:20:00
	 * @param status
	 * @param dictItemId
	 * @param type
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<GuideVo>> findGuideList(
			Integer status, Long dictItemId,Integer type, String title,Pageable pageable)
			throws BusinessException {
		XaResult<Page<GuideVo>> xr=new XaResult<Page<GuideVo>>();
		StringBuffer sql=new StringBuffer("select g.id,g.title,g.last_update,u.user_name,");
		sql.append("g.type,d.dict_name,");
		sql.append("(select ga.content from tb_xa_guideappend ga where ga.guide_id=g.id and status=1  order by create_time limit 1) content,");
		sql.append("(select ga.media_path from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 and ga.media_path<>'' order by create_time limit 1) media_path,g.pageview,g.status ");
		
		sql.append("from tb_xa_guide g ");
		sql.append("inner join tb_xa_user u on u.id=g.user_id ");
		sql.append("inner join tb_xa_dictitem d on d.id=g.dict_item_id ");
		sql.append("where g.status="+XaConstant.Status.publish+"");
		//统计数量
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append("from tb_xa_guide g ");
		countsql.append("inner join tb_xa_user u on u.id=g.user_id ");
		countsql.append("where g.status="+XaConstant.Status.publish+"");
		
		//攻略标签不为空
		if(XaUtil.isNotEmpty(dictItemId)){
			sql.append(" and g.dict_item_id="+dictItemId+" ");
			countsql.append(" and g.dict_item_id="+dictItemId+" ");
		}
		//大咖类型的攻略
		if(XaUtil.isNotEmpty(type)&&type==2){
			sql.append(" and u.big_shot="+JConstant.BooleanStatus.TRUE+" ");
			countsql.append(" and u.big_shot="+JConstant.BooleanStatus.TRUE+" ");
		}
		//精品类型的攻略
		if(XaUtil.isNotEmpty(type)&&type==1){
			sql.append(" and g.type="+JConstant.BooleanStatus.TRUE+" ");
			countsql.append(" and g.type="+JConstant.BooleanStatus.TRUE+" ");
		}
		List<Object> params=new ArrayList<Object>();
		if(XaUtil.isNotEmpty(title)){
			sql.append(" and g.title like ? ");
			countsql.append(" and g.title like ? ");
			params.add("%"+title+"%");
		}
		
		sql.append(" order by g.last_update desc ");
		
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(),params);
		List<Object[]> count = this.queryParams(countsql.toString(),null, null,params);
		List<GuideVo> vos = new ArrayList<GuideVo>();
		for(Object[] obj : objs){
			GuideVo vo = new GuideVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLastUpdate((String)obj[2]);
			//作者信息
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[3]);
			vo.setUserVo(userVo);
			vo.setType(XaUtil.isEmpty(obj[4]) ? null:(Integer)obj[4]);
			//标签信息
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[5]);
			vo.setDictItemVo(dictItemVo);
			//攻略内容信息
			GuideAppendVo guideAppendVo=new GuideAppendVo();
			guideAppendVo.setContent((String)obj[6]);
			
			//有图片
			if(XaUtil.isNotEmpty(obj[7])){
				String[] pic=null;
				pic= ((String)obj[7]).replaceAll("，", ",").split(",");
				guideAppendVo.setMediaPath(pic[0].toString());
			}else{
				guideAppendVo.setMediaPath(null);
			}
			vo.setGuideAppendVo(guideAppendVo);
			vo.setPageview(XaUtil.isEmpty(obj[8])?0 :(Integer)obj[8]);
			vo.setStatus(XaUtil.isEmpty(obj[9])?-1 :(Integer)obj[9]);
			vos.add(vo);
		}
		//分页
		Page<GuideVo> page = new MyPage<GuideVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
		
	}
	/**
	 * 我的攻略列表
	 * author:changlu
	 * time:2016-04-19 11:22:00
	 * @param status
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	public XaResult<Page<GuideVo>> findMyGuideList(
			Long userId, Pageable pageable)
			throws BusinessException {
		XaResult<Page<GuideVo>> xr=new XaResult<Page<GuideVo>>();
		StringBuffer sql=new StringBuffer("select g.id,g.title,g.last_update,u.user_name,");
		sql.append("g.type,d.dict_name,");
		sql.append("(select ga.content from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 order by create_time limit 1) content,");
		sql.append("(select ga.media_path from tb_xa_guideappend ga where ga.guide_id=g.id and status=1 and ga.media_path is not NULL order by create_time limit 1) media_path,g.pageview,g.status ");
		
		sql.append("from tb_xa_guide g ");
		sql.append("inner join tb_xa_user u on u.id=g.user_id ");
		sql.append("inner join tb_xa_dictitem d on d.id=g.dict_item_id ");
		sql.append("where (g.status="+XaConstant.Status.publish+" or g.status="+XaConstant.Status.valid+") and  g.user_id="+userId+" ");
		//统计数量
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append("from tb_xa_guide g ");
		countsql.append("inner join tb_xa_user u on u.id=g.user_id ");
		countsql.append("where (g.status="+XaConstant.Status.publish+" or g.status="+XaConstant.Status.valid+") and g.user_id="+userId+" ");
		sql.append(" order by g.last_update desc ");
		List<Object[]> objs = this.query(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize());
		List<Object[]> count = this.query(countsql.toString(),null, null);
		List<GuideVo> vos = new ArrayList<GuideVo>();
		for(Object[] obj : objs){
			GuideVo vo = new GuideVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setLastUpdate((String)obj[2]);
			//作者信息
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[3]);
			vo.setUserVo(userVo);
			vo.setType(XaUtil.isEmpty(obj[4]) ? null:(Integer)obj[4]);
			//标签信息
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[5]);
			vo.setDictItemVo(dictItemVo);
			//攻略内容信息
			GuideAppendVo guideAppendVo=new GuideAppendVo();
			guideAppendVo.setContent((String)obj[6]);
			
			//有图片
			if(XaUtil.isNotEmpty(obj[7])){
				String[] pic=null;
				pic= ((String)obj[7]).replaceAll("，", ",").split(",");
				guideAppendVo.setMediaPath(pic[0].toString());
			}else{
				guideAppendVo.setMediaPath(null);
			}
			vo.setGuideAppendVo(guideAppendVo);
			vo.setPageview(XaUtil.isEmpty(obj[8])?0 :(Integer)obj[8]);
			vo.setStatus(XaUtil.isEmpty(obj[9])?-1 :(Integer)obj[9]);
			vos.add(vo);
		}
		//分页
		Page<GuideVo> page = new MyPage<GuideVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
		
	}
	@Override
	public XaResult<List<GuideVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Guide> page = guideRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Guide.class), pageable);
		XaResult<List<GuideVo>> xr = new XaResult<List<GuideVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), GuideVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<GuideVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Guide> page = guideRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Guide.class), pageable);
		XaResult<List<GuideVo>> xr = new XaResult<List<GuideVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), GuideVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<GuideVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Guide obj = guideRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = guideRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<GuideVo> createModel(Guide model)
			throws BusinessException {
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		Guide obj = guideRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}
	/*
	 * 增加浏览量
	 * author:changlu
	 * time:2016-04-20 11:52:00
	 */
	@Override
	public XaResult<GuideVo> addPageview(Long id)
			throws BusinessException {
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		String sql="update tb_xa_guide set pageview=pageview+1 where id="+id+"";
		this.insert(sql);
		return xr;
	}
	/**
	 * 创建攻略、追加攻略
	 * author:changlu
	 * time:2016-04-19 11:52:00
	 * @param status
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public XaResult<GuideVo> createGuide(Guide model,GuideAppend guideAppend)
			throws BusinessException {
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		//追加
		if(XaUtil.isNotEmpty(guideAppend.getGuideId())){
			Guide obj = guideRepository.findOne(guideAppend.getGuideId());
			obj.setUserId(model.getUserId());
			obj.setLastUpdate(model.getLastUpdate());
			obj = guideRepository.save(obj);
			//保存攻略追加内容
			guideAppendRepository.save(guideAppend);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideVo.class));
			xr.getObject().setId(obj.getId());
		}else{
			//创建
			Guide obj = guideRepository.save(model);
			guideAppend.setGuideId(obj.getId());
			//保存攻略内容
			guideAppendRepository.save(guideAppend);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideVo.class));
			xr.getObject().setId(obj.getId());
		}
		return xr;
	}
	@Override
	public XaResult<GuideVo> updateModel(Guide model)
			throws BusinessException {
		Guide obj = guideRepository.findOne(model.getId());
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setTitle(model.getTitle());
		obj.setType(model.getType());
		obj.setPageview(model.getPageview());
		obj.setObjectId(model.getObjectId());
		obj.setObjectType(model.getObjectType());
		obj.setApplyStatus(model.getApplyStatus());
		obj.setApplyMemo(model.getApplyMemo());
		obj.setLastUpdate(model.getLastUpdate());
			obj = guideRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), GuideVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}
	//申请加精
	@Transactional
	public XaResult<String> ApplyCompetitiv(Long userId, Long id)
			throws BusinessException {
		XaResult<String> xr = new XaResult<String>();
		Guide guide = guideRepository.findByIdAndStatus(id, XaConstant.Status.publish);
		if(XaUtil.isNotEmpty(guide)){
			if(XaUtil.isEmpty(guide.getApplyStatus()) || guide.getApplyStatus()==3){
				guide.setApplyStatus(JConstant.BooleanStatus.TRUE);
				guideRepository.save(guide);
				xr.setObject("申请完成,正在审核中...");
			}else{
				xr.error("该攻略已申请,不可重复申请");
			}
		}else{
			xr.error("该攻略不存在,选择其他攻略");
		}
		return xr;
	}

}
