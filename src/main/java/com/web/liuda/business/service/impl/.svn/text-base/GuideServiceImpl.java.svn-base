package com.web.liuda.business.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Guide;
import com.web.liuda.business.repository.GuideAppendRepository;
import com.web.liuda.business.repository.GuideRepository;
import com.web.liuda.business.service.GuideService;
import com.web.liuda.remote.vo.DictItemVo;
import com.web.liuda.remote.vo.GuideAppendVo;
import com.web.liuda.remote.vo.GuideVo;
import com.web.liuda.remote.vo.HotelVo;
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

@Service("GuideService")
@Transactional(readOnly = false)
public class GuideServiceImpl extends BaseService<Guide> implements GuideService {

	@Autowired
	private GuideRepository guideRepository;
	@Autowired
	private GuideAppendRepository guideAppendRepository;
	/**
	 * 查询单条Guide信息
	 * @param tId
	 * @return 返回单个Guide对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Guide> findOne(Long modelId) throws BusinessException {
		Guide obj = new Guide();
		if(modelId != 0){
			obj = guideRepository.findByIdAndStatusNot(modelId,XaConstant.Status.delete);
		}
		XaResult<Guide> xr = new XaResult<Guide>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(obj);
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	/**
	 * 分页查询状态非status的Guide数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Guide集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Guide>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Guide> page = guideRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Guide.class), pageable);
		XaResult<Page<Guide>> xr = new XaResult<Page<Guide>>();
		xr.setObject(page);
		return xr;
	}
	/**
	 * 后台攻略列表
	 * author:changlu
	 * time:2016-05-12 16:32:00
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<GuideVo>> findGuideList(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		StringBuffer sql=new StringBuffer("select g.id,g.title,g.type,g.pageview,g.object_id,g.object_type,g.apply_status,");
		sql.append("g.apply_memo,g.last_update,di.dict_name,u.user_name,g.status,g.create_time ");
		sql.append(",(SELECT ga.id from tb_xa_guideappend ga where ga.guide_id=g.id ORDER BY ga.create_time asc limit 1) appendId ");
		//sql.append(",(select h.hotel_name from tb_xa_hotel inner join tb_xa_guide g on g.object_id=h.id where g.object_type="+JConstant.ObjectType.HOTEL+" and g.id)");
		sql.append(" from tb_xa_guide g ");
		sql.append("inner join tb_xa_user u on u.id=g.user_id left join tb_xa_dictitem di on di.id=g.dict_item_id ");
		sql.append(" where g.status<>"+XaConstant.Status.delete+" ");
		StringBuffer countsql=new StringBuffer("select count(*) ");
		countsql.append(" from tb_xa_guide g ");
		countsql.append("inner join tb_xa_user u on u.id=g.user_id left join tb_xa_dictitem di on di.id=g.dict_item_id ");
		countsql.append(" where g.status<>"+XaConstant.Status.delete+" ");
		List<Object> params=new ArrayList<Object>();
		if(XaUtil.isNotEmpty(filterParams.get("EQ_id"))){
			sql.append(" and g.id = ? ");
			countsql.append(" and g.id = ?  ");
			params.add(Long.valueOf(filterParams.get("EQ_id")+""));
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_userId"))){
			sql.append(" and u.user_name like ? ");
			countsql.append(" and u.user_name like ? ");
			params.add("%"+filterParams.get("EQ_userId").toString()+"%");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_title"))){
			sql.append(" and g.title like ? ");
			countsql.append(" and g.title like ? ");
			params.add("%"+filterParams.get("EQ_title").toString()+"%");
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_type"))){
			sql.append(" and g.type = ? ");
			countsql.append(" and g.type = ? ");
			params.add(Integer.valueOf(filterParams.get("EQ_type")+""));
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_applyStatus"))){
			sql.append(" and g.apply_status = ? ");
			countsql.append(" and g.apply_status = ? ");
			params.add(Integer.valueOf(filterParams.get("EQ_applyStatus")+""));
		}
		sql.append(" order by g.last_update desc ");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(),params);
		List<Object[]> count = this.queryParams(countsql.toString(), null, null,params);
		List<GuideVo> vos =new ArrayList<GuideVo>();
		for(Object[] obj : objs){
			GuideVo vo=new GuideVo();
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setType(XaUtil.isEmpty(obj[2]) ? null:(Integer)obj[2]);
			//浏览量
			vo.setPageview(XaUtil.isEmpty(obj[3])?0 :(Integer)obj[3]);
			//酒店、景点信息
			/*if(XaUtil.isNotEmpty(obj[4])&&Integer.valueOf(obj[5]+"")==JConstant.ObjectType.HOTEL&&XaUtil.isNotEmpty(obj[4])){
				//调查询酒店、景点信息的方法
				vo.setHotelVo(getObjectInfo(Integer.valueOf(obj[5]+""),((BigInteger)obj[4]).longValue()));
				vo.setObjectType(Integer.valueOf(obj[5]+""));
			}*/
			vo.setApplyStatus(XaUtil.isEmpty(obj[6])?0 :(Integer)obj[6]);
			vo.setApplyMemo((String)obj[7]);
			
			vo.setLastUpdate((String)obj[8]);
			//标签信息
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[9]);
			vo.setDictItemVo(dictItemVo);
			
			//作者信息
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[10]);
			vo.setUserVo(userVo);
			
			//查询追加的内容
			/*List<GuideAppend> guideAppend = guideAppendRepository.findByGuideIdAndStatusNot(vo.getId(),XaConstant.Status.delete);
			List<GuideAppendVo> guideAppendVo = new ArrayList<GuideAppendVo>();
			for(GuideAppend g : guideAppend){
				GuideAppendVo gvo=new GuideAppendVo();
				gvo.setContent(g.getContent());
				gvo.setMediaPath(g.getMediaPath());
				gvo.setCreateTime(g.getCreateTime());
				guideAppendVo.add(gvo);
			}*/
			//设置追加的内容集合
			//vo.setGuideAppendVos(guideAppendVo);
			vo.setStatus(XaUtil.isEmpty(obj[11])?0 :(Integer)obj[11]);
			vo.setCreateTime(XaUtil.isEmpty(obj[12])? "" :(String)obj[12]);
			vo.setObjectId(XaUtil.isNotEmpty(obj[4]) ? Long.valueOf(obj[4] + "") : null);
			vo.setObjectType(XaUtil.isNotEmpty(obj[5]) ? Integer.valueOf(obj[5] + "") : null);
			GuideAppendVo guideAppendVo =new  GuideAppendVo();
			guideAppendVo.setId(XaUtil.isEmpty(obj[13])?null:((BigInteger)obj[13]).longValue());
			vo.setGuideAppendVo(guideAppendVo);
			vos.add(vo);
		}
		XaResult<Page<GuideVo>> xr = new XaResult<Page<GuideVo>>();
		//分页
		Page<GuideVo> page = new MyPage<GuideVo>(pageable.getPageNumber(), pageable.getPageSize(), vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
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
			v.setGroupPrice(XaUtil.isEmpty(b[4]) ? 0 : (Double)b[4]);
			v.setGroupBuy(XaUtil.isEmpty(b[5])?null:Integer.valueOf(b[5]+""));
			
		}
		
		return v;
	}
	/**
	 * 分页查询状态status的Guide数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Guide集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Guide>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Guide> page = guideRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Guide.class), pageable);
		XaResult<Page<Guide>> xr = new XaResult<Page<Guide>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Guide信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Guide> saveOrUpdate(Guide model) throws BusinessException {
		Guide obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = guideRepository.findOne(model.getId());
		}else{
			obj = new Guide();
		}
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
		XaResult<Guide> xr = new XaResult<Guide>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Guide状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Guide对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Guide> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Guide> xr = new XaResult<Guide>();
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
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<Guide> audit(Long id, Integer applyStatus,String applyMemo) {
		XaResult<Guide> xr = new XaResult<Guide>();
		Guide obj = guideRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		if (XaUtil.isNotEmpty(obj)) {
			if(applyStatus==JConstant.ApplyStatus.CHECKSUCCEED){
				obj.setType(1);	//1精品
			}
			obj.setApplyStatus(applyStatus);
			obj.setApplyMemo(applyMemo);
			obj = guideRepository.save(obj);
		} else {
			xr.error(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<Guide> bandTourist(Long id, Long objectId,
			Integer objectType) {
		XaResult<Guide> xr = new XaResult<Guide>();
		Guide obj = guideRepository.findByIdAndStatusNot(id,XaConstant.Status.delete);
		if (XaUtil.isNotEmpty(obj)) {
			obj.setObjectId(objectId);
			obj.setObjectType(objectType);
			obj = guideRepository.save(obj);
		} else {
			xr.error(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<GuideVo> findDetail(Long id) { 
		XaResult<GuideVo> xr = new XaResult<GuideVo>();
		StringBuffer sql=new StringBuffer("select g.id,g.title,g.type,g.pageview,g.object_id,g.object_type,g.apply_status,");
		sql.append("g.apply_memo,g.last_update,di.dict_name,u.user_name,g.status,g.create_time,t.tourist_name, ");
		sql.append("(SELECT ga.content from tb_xa_guideappend ga where ga.guide_id=" + id + " ORDER BY ga.create_time asc limit 1) content, ");
		sql.append("(SELECT ga.media_path from tb_xa_guideappend ga where ga.guide_id=" + id + " ORDER BY ga.create_time asc limit 1) mediaPath ");
		sql.append(" from tb_xa_guide g ");
		sql.append("left join tb_xa_user u on u.id=g.user_id left join tb_xa_dictitem di on di.id=g.dict_item_id ");
		sql.append("left join tb_xa_tourist t on g.object_id=t.id ");
		sql.append(" where g.status<>"+XaConstant.Status.delete+" and g.id=").append(id);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		GuideVo vo=new GuideVo();
		for(Object[] obj : objs){
			vo.setId(XaUtil.isEmpty(obj[0])?null:((BigInteger)obj[0]).longValue());
			vo.setTitle((String)obj[1]);
			vo.setType(XaUtil.isEmpty(obj[2]) ? null:(Integer)obj[2]);
			//浏览量
			vo.setPageview(XaUtil.isEmpty(obj[3])?0 :(Integer)obj[3]);
			vo.setApplyStatus(XaUtil.isEmpty(obj[6])?0 :(Integer)obj[6]);
			vo.setApplyMemo((String)obj[7]);
			
			vo.setLastUpdate((String)obj[8]);
			//标签信息
			DictItemVo dictItemVo=new DictItemVo();
			dictItemVo.setDictName((String)obj[9]);
			vo.setDictItemVo(dictItemVo);
			
			//作者信息
			UserVo userVo=new UserVo();
			userVo.setUserName((String)obj[10]);
			vo.setUserVo(userVo);
			
			vo.setStatus(XaUtil.isEmpty(obj[11])?0 :(Integer)obj[11]);
			vo.setCreateTime(XaUtil.isEmpty(obj[12])? "" :(String)obj[12]);
			vo.setObjectId(XaUtil.isNotEmpty(obj[4]) ? Long.valueOf(obj[4] + "") : null);
			vo.setObjectType(XaUtil.isNotEmpty(obj[5]) ? Integer.valueOf(obj[5] + "") : null);
			vo.setObjectName(XaUtil.isNotEmpty(obj[13]) ? obj[13] + "" : "");
			vo.setContent(XaUtil.isNotEmpty(obj[14]) ? obj[14] + "" : "");
			GuideAppendVo guideAppendVo =new  GuideAppendVo();
			guideAppendVo.setMediaPath(XaUtil.isNotEmpty(obj[15]) ? obj[15] + "" : "");
			
			/*guideAppendVo.setMediaLastName(XaUtil.isNotEmpty(obj[15]) ? */
			if(XaUtil.isNotEmpty(obj[15])){
				String lastname=((String)obj[15]).substring(((String)obj[15]).indexOf(".")+1) ;
				guideAppendVo.setMediaLastName(lastname);
			}else{
				guideAppendVo.setMediaLastName("");
			}
			
			vo.setGuideAppendVo(guideAppendVo);
		}
		xr.setObject(vo);
		return xr;
	}
	
}
