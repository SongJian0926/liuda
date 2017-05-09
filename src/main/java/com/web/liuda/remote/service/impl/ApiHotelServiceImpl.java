package com.web.liuda.remote.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.constant.position.Location;
import com.web.liuda.business.entity.Hotel;
import com.web.liuda.business.entity.Room;
import com.web.liuda.business.repository.HotelRepository;
import com.web.liuda.business.repository.RoomRepository;
import com.web.liuda.remote.service.ApiHotelService;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.RoomVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIHotel接口实现类
 **/
@Service("ApiHotelService")
@Transactional(readOnly = false)
public class ApiHotelServiceImpl extends BaseService<Hotel> implements ApiHotelService{

	@Autowired
	HotelRepository hotelRepository;
	
	@Autowired
	RoomRepository roomRepository;
	
	@Override
	public XaResult<Page<HotelVo>> getHotels(Integer nextPage,
			Integer pageSize, String hotelName, String sort, String sortType,
			Double lng, Double lat,Integer groupBuy) {
		XaResult<Page<HotelVo>> xr = new XaResult<Page<HotelVo>>();
		StringBuffer sql = new StringBuffer();
		StringBuffer countSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from (SELECT h.id,h.hotel_name,h.picurl,h.hotel_type,h.lng,h.lat,");
		//countSql.append("select count(*) from (SELECT h.id,h.hotel_name,h.picurl,h.hotel_type,h.lng,h.lat,");
		//countSql.append("(select count(*) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(") group_buy ");
		sql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.status=").append(XaConstant.Status.valid).append(" and u.status=").append(XaConstant.Status.valid);
		sql.append(" and c.type=").append(JConstant.ObjectType.HOTEL).append(" and c.object_id=h.id) score,");
		sql.append("(SELECT MIN(r.price) FROM tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id) price,");
		sql.append("(select count(*) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.validity>=CURDATE()) group_buy,");
		sql.append("(select min(r.price) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.group_price=(select min(r.group_price) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.validity>=CURDATE())) group_price1, ");
		sql.append("(select min(r.group_price) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.validity>=CURDATE()) group_price ");
		
		/*sql.append(",case WHEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 "); 
		sql.append("and r.validity>=CURDATE()) is not null ");
		sql.append("THEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 ");
		sql.append("and r.validity>=CURDATE()) ");
		sql.append(" ELSE (SELECT MIN(r.price) FROM tb_xa_room r where r.status=2 and r.hotel_id=h.id) end orderPrice ");
		*/
		sql.append(",case WHEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 "); 
		sql.append("and r.validity>=CURDATE())  is not null ");
		sql.append("THEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 ");
		sql.append("and r.validity>=CURDATE()) ");
		sql.append(" ELSE (SELECT MIN(r.price) FROM tb_xa_room r where r.status=2 and r.hotel_id=h.id) end orderPrice ");
		
		sql.append("FROM tb_xa_hotel h WHERE h.status=").append(XaConstant.Status.valid);
		countSql.append("select count(*) from (SELECT h.id,h.hotel_name,h.picurl,h.hotel_type,h.lng,h.lat,");
		countSql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.status=").append(XaConstant.Status.valid).append(" and u.status=").append(XaConstant.Status.valid);
		countSql.append(" and c.type=").append(JConstant.ObjectType.HOTEL).append(" and c.object_id=h.id) score,");
		countSql.append("(SELECT MIN(r.price) FROM tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id) price,");
		countSql.append("(select count(*) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.validity>=CURDATE()) group_buy,");
		countSql.append("(select min(r.price) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.group_price=(select min(r.group_price) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.validity>=CURDATE())) group_price1, ");
		countSql.append("(select min(r.group_price) from tb_xa_room r where r.status=").append(XaConstant.Status.publish).append(" and r.hotel_id=h.id and r.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and r.validity>=CURDATE()) group_price ");
		/*countSql.append(",case WHEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 "); 
		countSql.append("and r.validity>=CURDATE()) is not null ");
		countSql.append("THEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 ");
		countSql.append("and r.validity>=CURDATE()) ");
		countSql.append(" ELSE (SELECT MIN(r.price) FROM tb_xa_room r where r.status=2 and r.hotel_id=h.id) end orderPrice ");
		*/
		countSql.append(",case WHEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 "); 
		countSql.append("and r.validity>=CURDATE()) is not null ");
		countSql.append("THEN (select min(r.group_price) from tb_xa_room r where r.status=2 and r.hotel_id=h.id and r.group_buy=1 ");
		countSql.append("and r.validity>=CURDATE()) ");
		countSql.append(" ELSE (SELECT MIN(r.price) FROM tb_xa_room r where r.status=2 and r.hotel_id=h.id) end orderPrice ");
		
		countSql.append("FROM tb_xa_hotel h WHERE h.status=").append(XaConstant.Status.valid);
		//countSql.append("FROM tb_xa_hotel h WHERE h.status=").append(XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(hotelName)){
			sql.append(" and h.hotel_name like ?");
			countSql.append(" and h.hotel_name like ?");
			params.add("%" + hotelName + "%");
		}
		if(sort.equals("price")){
			sort="orderPrice";
		}
		sql.append(" order by ").append(sort).append(" ").append(sortType).append(") t");
		countSql.append(") t");
		if(XaUtil.isNotEmpty(groupBuy)){
			if(groupBuy == JConstant.BooleanStatus.FALSE){
				sql.append(" where t.group_buy=").append(groupBuy);
				countSql.append(" where t.group_buy=").append(groupBuy);
			}else{
				sql.append(" where t.group_buy >=").append(groupBuy);
				countSql.append(" where t.group_buy >=").append(groupBuy);
			}
		}
		List<Object[]> objs = this.queryParams(sql.toString(), nextPage * pageSize, pageSize, params);
		List<Object[]> count = this.queryParams(countSql.toString(), null, null, params);
		List<HotelVo> vos = new ArrayList<HotelVo>();
		for(Object[] obj : objs){
			HotelVo vo = new HotelVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setHotelName((String)obj[1]);
			vo.setPicurl((String)obj[2]);
			vo.setHotelType((String)obj[3]);
			DecimalFormat df = new DecimalFormat("#.0"); 
			vo.setScore(XaUtil.isEmpty(obj[6]) ? 0 : Double.valueOf(df.format((Double)obj[6])));
			vo.setPrice(XaUtil.isEmpty(obj[7]) ? 0 : (Double)obj[7]);
			//根据用户经纬度计算用户到酒店的距离
			Location start = new Location();
			start.setLng(lng);
			start.setLat(lat);
			Location end = new Location();
			end.setLng(XaUtil.isEmpty(obj[4]) ? 0 : (Double)obj[4]);
			end.setLat(XaUtil.isEmpty(obj[5]) ? 0 : (Double)obj[5]);
			double distance = HttpURLConnectionUtil.getDistance(start, end);
			vo.setDistance(new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
			//团购
			if(((BigInteger)obj[8]).intValue() > 0){
				
				vo.setGroupBuy(JConstant.BooleanStatus.TRUE);
				if(XaUtil.isNotEmpty(obj[9])){
					//返回一条团购价格最低的房间信息
					vo.setPrice(XaUtil.isEmpty(obj[9]) ? 0 : (Double)obj[9]);
				}
				vo.setGroupPrice(XaUtil.isEmpty(obj[10]) ? null : Double.valueOf(obj[10]+""));
				
			}else{
				vo.setGroupBuy(JConstant.BooleanStatus.FALSE);
			}
			vos.add(vo);
		}
		Page<HotelVo> page = new MyPage<HotelVo>(nextPage, pageSize, vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}
	//酒店详情（用户登录）
	@Override
	public XaResult<HotelVo> findHotelDetail(Long hotelId,Long userId) {
		XaResult<HotelVo> xr = new XaResult<HotelVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT h.id,h.hotel_name,h.picurl,h.hotel_type,h.lng,h.lat,h.address,h.telphone,h.introduce,h.policy,h.prompt,");
		sql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id  where c.status=").append(XaConstant.Status.valid).append(" and c.type=").append(JConstant.ObjectType.HOTEL).append(" and c.object_id=").append(hotelId).append(" and u.status=").append(XaConstant.Status.valid).append(") score,");
		sql.append("(SELECT COUNT(*) FROM tb_xa_comment c1 inner join tb_xa_user u on u.id=c1.user_id where c1.status=").append(XaConstant.Status.valid).append(" and c1.type=").append(JConstant.ObjectType.HOTEL).append(" and c1.object_id=").append(hotelId).append(" and u.status=").append(XaConstant.Status.valid).append(") comments,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.HOTEL).append(" and i.object_id=").append(hotelId).append(") pics,");
		sql.append("(SELECT GROUP_CONCAT(ii.picurl) FROM tb_xa_images ii where ii.status=").append(XaConstant.Status.valid).append(" and ii.type=").append(JConstant.ImageType.HOTEL_INTRO).append(" and ii.object_id=").append(hotelId).append(") intropics ");
		if(XaUtil.isNotEmpty(userId)){
			sql.append(",(select COUNT(*) from tb_xa_history his where his.user_id=").append(userId).append(" and his.status=1 and his.type=0 and his.object_id=").append(hotelId).append(") isLike ");
		}
		
		sql.append("FROM tb_xa_hotel h WHERE h.status=").append(XaConstant.Status.valid).append(" and h.id=").append(hotelId);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		if(XaUtil.isEmpty(objs)){
			xr.error("该商品已下架！");
			return xr;
		}
		HotelVo vo = new HotelVo();
		for(Object[] obj : objs){
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setHotelName((String)obj[1]);
			vo.setPicurl((String)obj[2]);
			vo.setHotelType((String)obj[3]);
			vo.setLng((Double)obj[4]);
			vo.setLat((Double)obj[5]);
			vo.setAddress((String)obj[6]);
			vo.setTelphone((String)obj[7]);
			vo.setIntroduce((String)obj[8]);
			vo.setPolicy((String)obj[9]);
			vo.setPrompt((String)obj[10]);
			DecimalFormat df = new DecimalFormat("#.0");  
			vo.setScore(XaUtil.isEmpty(obj[11]) ? 0 : Double.valueOf(df.format((Double)obj[11])));
			vo.setComments(((BigInteger)obj[12]).intValue());
			vo.setPics(XaUtil.isEmpty(obj[13]) ? null : ((String)obj[13]).split(","));
			vo.setIntropics(XaUtil.isEmpty(obj[14]) ? null : ((String)obj[14]).split(","));
			List<Room> rooms = roomRepository.findByHotelIdAndStatus(hotelId, XaConstant.Status.publish);
			//判断房间是否存在
			/*if(XaUtil.isEmpty(rooms)){
				xr.error("该商品已下架！");
				return xr;
			}*/
			List<RoomVo> roomVos = new ArrayList<RoomVo>();
			for(Room m : rooms){
				RoomVo roomvo = new RoomVo();
				roomvo.setId(m.getId());
				roomvo.setHotelId(m.getHotelId());
				roomvo.setType(m.getType());
				roomvo.setLogo(m.getLogo());
				roomvo.setPrice(m.getPrice());
				roomvo.setBreakfast(m.getBreakfast());
				roomvo.setBeds(m.getBeds());
				/** 参加团购需要的字段 start */
				String aa=XaUtil.getToDayStr().substring(0, 10); 
				if(XaUtil.isNotEmpty(m.getValidity())&&m.getValidity().compareTo(aa)<0){
					roomvo.setGroupBuy(0);
				}else{
					roomvo.setGroupBuy(m.getGroupBuy());
					roomvo.setValidity(m.getValidity());
					roomvo.setGroupPrice(m.getGroupPrice());
				}
				
				/** 参加团购需要的字段 end */
				roomVos.add(roomvo);
			}
			vo.setIsLike(Integer.valueOf(obj[15].toString()));
			vo.setRooms(roomVos);
		}
		xr.success(vo);
		return xr;
	}
	//酒店详情（用户未登录）
	@Override
	public XaResult<HotelVo> findHotelDetail(Long hotelId) {
		XaResult<HotelVo> xr = new XaResult<HotelVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT h.id,h.hotel_name,h.picurl,h.hotel_type,h.lng,h.lat,h.address,h.telphone,h.introduce,h.policy,h.prompt,");
		sql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id  where c.status=").append(XaConstant.Status.valid).append(" and c.type=").append(JConstant.ObjectType.HOTEL).append(" and c.object_id=").append(hotelId).append(" and u.status=").append(XaConstant.Status.valid).append(") score,");
		sql.append("(SELECT COUNT(*) FROM tb_xa_comment c1 inner join tb_xa_user u on u.id=c1.user_id where c1.status=").append(XaConstant.Status.valid).append(" and c1.type=").append(JConstant.ObjectType.HOTEL).append(" and c1.object_id=").append(hotelId).append(" and u.status=").append(XaConstant.Status.valid).append(") comments,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.HOTEL).append(" and i.object_id=").append(hotelId).append(") pics,");
		sql.append("(SELECT GROUP_CONCAT(ii.picurl) FROM tb_xa_images ii where ii.status=").append(XaConstant.Status.valid).append(" and ii.type=").append(JConstant.ImageType.HOTEL_INTRO).append(" and ii.object_id=").append(hotelId).append(") intropics ");
		sql.append("FROM tb_xa_hotel h WHERE h.status=").append(XaConstant.Status.valid).append(" and h.id=").append(hotelId);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		if(XaUtil.isEmpty(objs)){
			xr.error("该商品已下架！");
			return xr;
		}
		HotelVo vo = new HotelVo();
		for(Object[] obj : objs){
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setHotelName((String)obj[1]);
			vo.setPicurl((String)obj[2]);
			vo.setHotelType((String)obj[3]);
			vo.setLng((Double)obj[4]);
			vo.setLat((Double)obj[5]);
			vo.setAddress((String)obj[6]);
			vo.setTelphone((String)obj[7]);
			vo.setIntroduce((String)obj[8]);
			vo.setPolicy((String)obj[9]);
			vo.setPrompt((String)obj[10]);
			DecimalFormat df = new DecimalFormat("#.0");  
			vo.setScore(XaUtil.isEmpty(obj[11]) ? 0 : Double.valueOf(df.format((Double)obj[11])));
			vo.setComments(((BigInteger)obj[12]).intValue());
			vo.setPics(XaUtil.isEmpty(obj[13]) ? null : ((String)obj[13]).split(","));
			vo.setIntropics(XaUtil.isEmpty(obj[14]) ? null : ((String)obj[14]).split(","));
			List<Room> rooms = roomRepository.findByHotelIdAndStatus(hotelId, XaConstant.Status.publish);
			//判断房间是否存在
			/*if(XaUtil.isEmpty(rooms)){
				xr.error("该商品已下架！");
				return xr;
			}*/
			List<RoomVo> roomVos = new ArrayList<RoomVo>();
			for(Room m : rooms){
				RoomVo roomvo = new RoomVo();
				roomvo.setId(m.getId());
				roomvo.setHotelId(m.getHotelId());
				roomvo.setType(m.getType());
				roomvo.setLogo(m.getLogo());
				roomvo.setPrice(m.getPrice());
				roomvo.setBreakfast(m.getBreakfast());
				roomvo.setBeds(m.getBeds());
				
				/** 参加团购需要的字段 start */
				String aa=XaUtil.getToDayStr().substring(0, 10); 
				if(XaUtil.isNotEmpty(m.getValidity())&&m.getValidity().compareTo(aa)<0){
					roomvo.setGroupBuy(0);
				}else{
					roomvo.setGroupBuy(m.getGroupBuy());
					roomvo.setValidity(m.getValidity());
					roomvo.setGroupPrice(m.getGroupPrice());
				}
				
				/** 参加团购需要的字段 end */
			/*	roomvo.setGroupBuy(m.getGroupBuy());
				roomvo.setValidity(m.getValidity());
				roomvo.setGroupPrice(m.getGroupPrice());*/
				roomVos.add(roomvo);
			}
			vo.setRooms(roomVos);
			vo.setIsLike(0);
		}
		xr.success(vo);
		return xr;
	}

}