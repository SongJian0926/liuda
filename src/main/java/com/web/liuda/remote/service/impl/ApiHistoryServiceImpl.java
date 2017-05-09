package com.web.liuda.remote.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
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
import com.web.liuda.business.constant.position.Location;
import com.web.liuda.business.entity.History;
import com.web.liuda.business.repository.HistoryRepository;
import com.web.liuda.remote.service.ApiHistoryService;
import com.web.liuda.remote.vo.HistoryVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.TouristVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIHistory接口实现类
 **/
@Service("ApiHistoryService")
@Transactional(readOnly = false)
public class ApiHistoryServiceImpl extends BaseService<History> implements ApiHistoryService{

	@Autowired
	HistoryRepository historyRepository;
	
	@Override
	public XaResult<HistoryVo> findOne(Long tId) throws BusinessException {
		History obj = historyRepository.findByIdAndStatusNot(tId,XaConstant.Status.delete);
		XaResult<HistoryVo> xr = new XaResult<HistoryVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj),HistoryVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<List<HistoryVo>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<History> page = historyRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), History.class), pageable);
		XaResult<List<HistoryVo>> xr = new XaResult<List<HistoryVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), HistoryVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<HistoryVo>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<History> page = historyRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), History.class), pageable);
		XaResult<List<HistoryVo>> xr = new XaResult<List<HistoryVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()), HistoryVo.class));
		for(int i=0;i<page.getContent().size();i++){
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<HistoryVo> multiOperate(String modelIds,
			Integer status) throws BusinessException {
		XaResult<HistoryVo> xr = new XaResult<HistoryVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				History obj = historyRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = historyRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), HistoryVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	//取消收藏
	public XaResult<HistoryVo> cancelHistory(String modelIds,
			Integer status) throws BusinessException {
		XaResult<HistoryVo> xr = new XaResult<HistoryVo>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				History obj = historyRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = historyRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj), HistoryVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	@Override
	public XaResult<HistoryVo> createModel(History model)
			throws BusinessException {
		
		XaResult<HistoryVo> xr = new XaResult<HistoryVo>();
		List<History> historys=historyRepository.findByUserIdAndObjectIdAndType(model.getUserId(), model.getObjectId(), model.getType());
		if(XaUtil.isNotEmpty(historys) && historys.size() > 0){
			xr.error("已收藏");
			return xr;
		}
		History obj = historyRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), HistoryVo.class));
		xr.getObject().setId(obj.getId());
		return xr;
	}

	@Override
	public XaResult<HistoryVo> updateModel(History model)
			throws BusinessException {
		History obj = historyRepository.findOne(model.getId());
		XaResult<HistoryVo> xr = new XaResult<HistoryVo>();
		if (XaUtil.isNotEmpty(obj)) {
		obj.setUserId(model.getUserId());
		obj.setObjectId(model.getObjectId());
		obj.setType(model.getType());
			obj = historyRepository.save(obj);
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), HistoryVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<Page<HistoryVo>> findHistorys(Integer nextPage,
			Integer pageSize, Integer type, Long userId,Double lng,Double lat) {
		XaResult<Page<HistoryVo>> xr = new XaResult<Page<HistoryVo>>();
		List<Object> params = new ArrayList<Object>();
		List<Object> paramsCount = new ArrayList<Object>();
		String sql = "CALL pro_historys(?,?,?,?)";
		String countSql = "call pro_historys_count(?,?)";
		params.add(type);
		params.add(userId);
		params.add(nextPage * pageSize);
		params.add(pageSize);
		paramsCount.add(type);
		paramsCount.add(userId);
		List<Object[]> objs = this.queryCall(sql, params);
		List<Object[]> count = this.queryCall(countSql, paramsCount);
		List<HistoryVo> historys = new ArrayList<HistoryVo>();
		for(Object[] obj : objs){
			HistoryVo vo = new HistoryVo();
			
			vo.setId(((BigInteger)obj[1]).longValue());
			vo.setObjectId(((BigInteger)obj[2]).longValue());
			//如果收藏的是酒店存酒店的实体
			if(type == JConstant.ObjectType.HOTEL){
				HotelVo hotel = new HotelVo();
				hotel.setHotelName((String)obj[3]);
				hotel.setPicurl((String)obj[4]);
				hotel.setHotelType((String)obj[5]);
				//根据用户经纬度计算用户到酒店的距离
				Location start = new Location();
				start.setLng(lng);
				start.setLat(lat);
				Location end = new Location();
				end.setLng(XaUtil.isEmpty(obj[6]) ? 0 : (Double)obj[6]);
				end.setLat(XaUtil.isEmpty(obj[7]) ? 0 : (Double)obj[7]);
				double distance = HttpURLConnectionUtil.getDistance(start, end);
				vo.setDistance(new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
				//价格和评分
				DecimalFormat df = new DecimalFormat("#.0"); 
				hotel.setScore(XaUtil.isEmpty(obj[8]) ? 0 : Double.valueOf(df.format((Double)obj[8])));
				hotel.setPrice(XaUtil.isEmpty(obj[9]) ? 0 : (Double)obj[9]);
				//团购信息
				if(((BigInteger)obj[10]).intValue() > 0){
					hotel.setGroupBuy(JConstant.BooleanStatus.TRUE);
					if(XaUtil.isNotEmpty(obj[11])){
						//返回一条团购价格最低的房间信息
						hotel.setPrice(XaUtil.isEmpty(obj[11]) ? 0 : (Double)obj[11]);
					}
					hotel.setGroupPrice(XaUtil.isEmpty(obj[12]) ? null : Double.valueOf(obj[12]+""));
				}else{
					hotel.setGroupBuy(JConstant.BooleanStatus.FALSE);
				}
				vo.setHotel(hotel);
			}else if(type == JConstant.ObjectType.TOURIST){
				//收藏的景点实体
				TouristVo tourist = new TouristVo();
				tourist.setTouristName((String)obj[3]);
				tourist.setImgUrl((String)obj[4]);
				//根据用户经纬度计算用户到酒店的距离
				Location start = new Location();
				start.setLng(lng);
				start.setLat(lat);
				Location end = new Location();
				end.setLng(XaUtil.isEmpty(obj[5]) ? 0 : (Double)obj[5]);
				end.setLat(XaUtil.isEmpty(obj[6]) ? 0 : (Double)obj[6]);
				double distance = HttpURLConnectionUtil.getDistance(start, end);
				vo.setDistance(new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
				//价格和评分
				DecimalFormat df = new DecimalFormat("#.0"); 
				tourist.setScore(XaUtil.isEmpty(obj[7]) ? 0 : Double.valueOf(df.format((Double)obj[7])));
				tourist.setPrice(XaUtil.isEmpty(obj[8]) ? 0 : (Double)obj[8]);
				//团购信息
				if(((BigInteger)obj[9]).intValue() > 0){
					tourist.setGroupBuy(JConstant.BooleanStatus.TRUE);
					if(XaUtil.isNotEmpty(obj[10])){
						//返回一条团购价格最低的房间信息
						tourist.setPrice(XaUtil.isEmpty(obj[10]) ? 0 : (Double)obj[10]);
					}
					tourist.setGroupPrice(XaUtil.isEmpty(obj[11]) ? null : Double.valueOf(obj[11]+""));
				}else{
					tourist.setGroupBuy(JConstant.BooleanStatus.FALSE);
				}
				vo.setTourist(tourist);
			}else{
				//收藏的商品实体
				ShopVo shop = new ShopVo();
				shop.setShopName((String)obj[3]);
				shop.setImgUrl((String)obj[4]);
				shop.setPrice(XaUtil.isEmpty(obj[5]) ? 0 : (Double)obj[5]);
				shop.setShopSales(XaUtil.isEmpty(obj[6]) ? 0 : ((BigDecimal)obj[6]).intValue());
				//团购信息
				if(((BigInteger)obj[7]).intValue() > 0){
					//DecimalFormat df = new DecimalFormat("#.0"); 
					shop.setGroupBuy(JConstant.BooleanStatus.TRUE);
					if(XaUtil.isNotEmpty(obj[8])){
						//返回一条团购价格最低的房间信息
						shop.setPrice(XaUtil.isEmpty(obj[8]) ? 0 : (Double)obj[8]);
					}
					shop.setGroupPrice(XaUtil.isEmpty(obj[9]) ? null : Double.valueOf(obj[9]+""));
				}else{
					shop.setGroupBuy(JConstant.BooleanStatus.FALSE);
				}
				vo.setShop(shop);
			}
			
			vo.setType(type);
			historys.add(vo);
		}
		Page<HistoryVo> page = new MyPage<HistoryVo>(nextPage, pageSize, historys, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}

}
