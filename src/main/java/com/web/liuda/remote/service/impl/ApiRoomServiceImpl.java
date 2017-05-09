package com.web.liuda.remote.service.impl;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Room;
import com.web.liuda.remote.vo.RoomVo;

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
import com.web.liuda.business.repository.RoomRepository;
import com.web.liuda.remote.service.ApiRoomService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIRoom接口实现类
 **/
@Service("ApiRoomService")
@Transactional(readOnly = false)
public class ApiRoomServiceImpl extends BaseService<Room> implements ApiRoomService{

	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public XaResult<RoomVo> findRoomDetail(Long tId) throws BusinessException {
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		StringBuffer sql=new StringBuffer("select r.id,r.type,h.address,h.telphone,");
		sql.append("r.logo,r.item,r.area,r.beds,r.bath_room,r.communication,");
		sql.append("r.establishment,r.price,h.hotel_name,r.group_buy,r.group_price,r.validity,r.buy_note from tb_xa_room r INNER JOIN ");
		sql.append("tb_xa_hotel h on r.hotel_id=h.id where r.id=?");
		List<Object> params=new ArrayList<Object>();
		params.add(tId);
		List<Object[]> objs=this.queryParams(sql.toString(), null, null, params);
		RoomVo vo=new RoomVo();
		for (Object[] obj : objs) {	
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setType((String)obj[1]);
			vo.setAddress((String)obj[2]);
			vo.setTelphone((String)obj[3]);
			vo.setLogo((String)obj[4]);
			vo.setItem((String)obj[5]);
			vo.setArea(XaUtil.isEmpty(obj[6]) ? null : ((Integer)obj[6]).intValue());
			vo.setBeds(XaUtil.isEmpty(obj[7]) ? null : ((Integer)obj[7]).intValue());
			vo.setBathRoom((String)obj[8]);
			vo.setCommunication((String)obj[9]);
			vo.setEstablishment((String)obj[10]);
			vo.setPrice(XaUtil.isEmpty(obj[11]) ? null : ((Double)obj[11]).doubleValue());
			vo.setHotelName((String)obj[12]);
			String aa=XaUtil.getToDayStr().substring(0, 10); 
			if(XaUtil.isNotEmpty((String)obj[15])&&((String)obj[15]).compareTo(aa)<0){
				vo.setGroupBuy(0);
			}else{
				//团购信息
				vo.setGroupBuy((Integer)obj[13]);
				if((Integer)obj[13] == JConstant.BooleanStatus.TRUE){
					vo.setGroupPrice(XaUtil.isEmpty(obj[14]) ? null : ((Double)obj[14]).doubleValue());
					vo.setValidity((String)obj[15]);
				}
			}
			
			vo.setBuyNote((String)obj[16]);
		}
		xr.setObject(vo);
		return xr;
	}
	
	@Override
	public XaResult<RoomVo> findOne(Long tId) throws BusinessException {
		Room obj = roomRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),RoomVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<RoomVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Room> page = roomRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Room.class), pageable);
		XaResult<List<RoomVo>> xr = new XaResult<List<RoomVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), RoomVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<RoomVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Room> page = roomRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Room.class), pageable);
		XaResult<List<RoomVo>> xr = new XaResult<List<RoomVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), RoomVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<RoomVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Room obj = roomRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = roomRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), RoomVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<RoomVo> createModel(Room model)
			throws BusinessException {
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		Room obj = roomRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), RoomVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<RoomVo> updateModel(Room model)
			throws BusinessException {
		Room obj = roomRepository.findOne(model.getId());
		XaResult<RoomVo> xr = new XaResult<RoomVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setHotelId(model.getHotelId());
		obj.setType(model.getType());
		obj.setItem(model.getItem());
		obj.setBuyNote(model.getBuyNote());
		obj.setPrice(model.getPrice());
		obj.setLogo(model.getLogo());
		obj.setBreakfast(model.getBreakfast());
		obj.setGroupBuy(model.getGroupBuy());
		obj.setGroupPrice(model.getGroupPrice());
		obj.setValidity(model.getValidity());
		obj.setArea(model.getArea());
		obj.setBeds(model.getBeds());
		obj.setBathRoom(model.getBathRoom());
		obj.setCommunication(model.getCommunication());
		obj.setEstablishment(model.getEstablishment());
			obj = roomRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), RoomVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

}
