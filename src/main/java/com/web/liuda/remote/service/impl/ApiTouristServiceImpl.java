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
import com.web.liuda.business.entity.Tickets;
import com.web.liuda.business.entity.Tourist;
import com.web.liuda.business.repository.TicketsRepository;
import com.web.liuda.business.repository.TouristRepository;
import com.web.liuda.remote.service.ApiTouristService;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.liuda.remote.vo.TouristVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.HttpURLConnectionUtil;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APITourist接口实现类
 **/
@Service("ApiTouristService")
@Transactional(readOnly = false)
public class ApiTouristServiceImpl extends BaseService<Tourist> implements ApiTouristService{

	@Autowired
	TouristRepository touristRepository;
	
	@Autowired
	TicketsRepository ticketsRepository;
	
	@Override
	public XaResult<Page<TouristVo>> getTourists(Integer nextPage,
			Integer pageSize, String touristName, String sort, String sortType,
			Double lng, Double lat,Integer groupBuy) {
		XaResult<Page<TouristVo>> xr = new XaResult<Page<TouristVo>>();
		StringBuffer sql = new StringBuffer();
		StringBuffer countSql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("select * from (SELECT t.id,t.tourist_name,t.img_url,t.lng,t.lat,");
		countSql.append("select count(*) from (SELECT t.id,t.tourist_name,t.img_url,t.lng,t.lat,");
		sql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.status=").append(XaConstant.Status.valid).append(" and u.status=").append(XaConstant.Status.valid);
		sql.append(" and c.type=").append(JConstant.ObjectType.TOURIST).append(" and c.object_id=t.id) score,");
		sql.append("(SELECT MIN(ts.price) FROM tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id) price,");
		sql.append("(select count(*) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE()) groupBuy,");
		sql.append("(SELECT ts.price FROM tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE() and group_price=(select min(ts.group_price) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE())) price1,");
		sql.append("(select min(ts.group_price) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE()) groupPrice ");
		
		

		
		sql.append(",case WHEN (select min(ts.group_price) from tb_xa_tickets ts where ts.status=2 and ts.tourist_id=t.id and ts.group_buy=1 and ts.validity>=CURDATE()) is not null  ");
		sql.append("THEN (select min(ts.group_price) from tb_xa_tickets ts where ts.status=2 and ts.tourist_id=t.id and ts.group_buy=1 and ts.validity>=CURDATE())  "); 
		sql.append("ELSE (SELECT MIN(ts.price) FROM tb_xa_tickets ts where ts.status=2 and ts.tourist_id=t.id) end orderPrice  ");
		
		countSql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.status=").append(XaConstant.Status.valid).append(" and u.status=").append(XaConstant.Status.valid);
		countSql.append(" and c.type=").append(JConstant.ObjectType.TOURIST).append(" and c.object_id=t.id) score,");
		countSql.append("(SELECT MIN(ts.price) FROM tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id) price,");
		countSql.append("(select count(*) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE()) groupBuy,");
		countSql.append("(SELECT ts.price FROM tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE() and group_price=(select min(ts.group_price) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE())) price1,");
		countSql.append("(select min(ts.group_price) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(" and ts.validity>=CURDATE()) groupPrice ");
		countSql.append(",case WHEN (select min(ts.group_price) from tb_xa_tickets ts where ts.status=2 and ts.tourist_id=t.id and ts.group_buy=1 and ts.validity>=CURDATE()) is not null  ");
		countSql.append("THEN (select min(ts.group_price) from tb_xa_tickets ts where ts.status=2 and ts.tourist_id=t.id and ts.group_buy=1 and ts.validity>=CURDATE())  "); 
		countSql.append("ELSE (SELECT MIN(ts.price) FROM tb_xa_tickets ts where ts.status=2 and ts.tourist_id=t.id) end orderPrice  ");
		
		//countSql.append("(select count(*) from tb_xa_tickets ts where ts.status=").append(XaConstant.Status.publish).append(" and ts.tourist_id=t.id and ts.group_buy=").append(JConstant.BooleanStatus.TRUE).append(") groupBuy ");
		sql.append("FROM tb_xa_tourist t WHERE t.status=").append(XaConstant.Status.valid);
		countSql.append("FROM tb_xa_tourist t WHERE t.status=").append(XaConstant.Status.valid);
		//countSql.append("FROM tb_xa_tourist t WHERE t.status=").append(XaConstant.Status.valid);
		if(XaUtil.isNotEmpty(touristName)){
			sql.append(" and t.tourist_name like ?");
			countSql.append(" and t.tourist_name like ?");
			params.add("%" + touristName + "%");
		}
		if(sort.equals("price")){
			sort="orderPrice";
		}
		sql.append(" order by ").append(sort).append(" ").append(sortType).append(") y");
		countSql.append(") y");
		if(XaUtil.isNotEmpty(groupBuy)){
			if(groupBuy == JConstant.BooleanStatus.FALSE){
				sql.append(" where y.groupBuy=").append(groupBuy);
				countSql.append(" where y.groupBuy=").append(groupBuy);
			}else{
				sql.append(" where y.groupBuy >=").append(groupBuy);
				countSql.append(" where y.groupBuy >=").append(groupBuy);
			}
		}
		List<Object[]> objs = this.queryParams(sql.toString(), nextPage * pageSize, pageSize, params);
		List<Object[]> count = this.queryParams(countSql.toString(), null, null, params);
		List<TouristVo> vos = new ArrayList<TouristVo>();
		for(Object[] obj : objs){
			TouristVo vo = new TouristVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setTouristName((String)obj[1]);
			vo.setImgUrl((String)obj[2]);
			DecimalFormat df = new DecimalFormat("#.0"); 
			vo.setScore(XaUtil.isEmpty(obj[5]) ? 0 : Double.valueOf(df.format((Double)obj[5])));
			vo.setPrice(XaUtil.isEmpty(obj[6]) ? 0 : (Double)obj[6]);
			//根据用户经纬度计算用户到酒店的距离
			Location start = new Location();
			start.setLng(lng);
			start.setLat(lat);
			Location end = new Location();
			end.setLng(XaUtil.isEmpty(obj[3]) ? 0 : (Double)obj[3]);
			end.setLat(XaUtil.isEmpty(obj[4]) ? 0 : (Double)obj[4]);
			double distance = HttpURLConnectionUtil.getDistance(start, end);
			vo.setDistance(new BigDecimal(distance).setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
			//团购信息
			if(((BigInteger)obj[7]).intValue() > 0){
				vo.setGroupBuy(JConstant.BooleanStatus.TRUE);
				vo.setGroupPrice(XaUtil.isEmpty(obj[9]) ? null : Double.valueOf(obj[9]+""));
				if(XaUtil.isNotEmpty(obj[8])){
					//返回一条团购价格最低的门票信息
					vo.setPrice(XaUtil.isEmpty(obj[8]) ? 0 : (Double)obj[8]);
				}
			}else{
				vo.setGroupBuy(JConstant.BooleanStatus.FALSE);
			}
			vos.add(vo);
		}
		Page<TouristVo> page = new MyPage<TouristVo>(nextPage, pageSize, vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}

	@Override
	public XaResult<TouristVo> findTouristDetail(Long touristId,Long userId) {
		XaResult<TouristVo> xr = new XaResult<TouristVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.id,t.tourist_name,t.img_url,t.lng,t.lat,t.address,t.telphone,t.tourist_desc,t.business_time,");
		sql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.status=").append(XaConstant.Status.valid).append(" and c.type=").append(JConstant.ObjectType.TOURIST).append(" and c.object_id=").append(touristId).append(" and u.status=").append(XaConstant.Status.valid).append(") score,");
		sql.append("(SELECT COUNT(*) FROM tb_xa_comment c1 inner join tb_xa_user u on u.id=c1.user_id where c1.status=").append(XaConstant.Status.valid).append(" and c1.type=").append(JConstant.ObjectType.TOURIST).append(" and c1.object_id=").append(touristId).append(" and u.status=").append(XaConstant.Status.valid).append(") comments,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.TOURIST).append(" and i.object_id=").append(touristId).append(") pics,");
		sql.append("(SELECT GROUP_CONCAT(ii.picurl) FROM tb_xa_images ii where ii.status=").append(XaConstant.Status.valid).append(" and ii.type=").append(JConstant.ImageType.HOTEL_INTRO).append(" and ii.object_id=").append(touristId).append(") intropics,t.notes ");
		if(XaUtil.isNotEmpty(userId)){
			sql.append(",(select COUNT(*) from tb_xa_history his where his.user_id=").append(userId).append(" and his.status=1 and his.type=1 and his.object_id=").append(touristId).append(") isLike ");
		}
		sql.append("FROM tb_xa_tourist t WHERE t.status=").append(XaConstant.Status.valid).append(" and t.id=").append(touristId);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		if(XaUtil.isEmpty(objs)){
			xr.error("该商品已下架！");
			return xr;
		}
		TouristVo vo = new TouristVo();
		
		for(Object[] obj : objs){
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setTouristName((String)obj[1]);
			vo.setImgUrl((String)obj[2]);
			vo.setLng((Double)obj[3]);
			vo.setLat((Double)obj[4]);
			vo.setAddress((String)obj[5]);
			vo.setTelphone((String)obj[6]);
			vo.setTouristDesc((String)obj[7]);
			vo.setBusinessTime((String)obj[8]);
			DecimalFormat df = new DecimalFormat("#.0");  
			vo.setScore(XaUtil.isEmpty(obj[9]) ? 0 : Double.valueOf(df.format((Double)obj[9])));
			vo.setComments(((BigInteger)obj[10]).intValue());
			vo.setPics(XaUtil.isEmpty(obj[11]) ? null : ((String)obj[11]).split(","));
			vo.setIntropics(XaUtil.isEmpty(obj[12]) ? null : ((String)obj[12]).split(","));
			List<Tickets> tickets = ticketsRepository.findByTouristIdAndStatus(touristId, XaConstant.Status.publish);
			//判断门票是否存在
			/*if(XaUtil.isEmpty(tickets)){
				xr.error("该商品已下架！");
				return xr;
			}
			*/
			List<TicketsVo> ticketsVos = new ArrayList<TicketsVo>();
			for(Tickets t : tickets){
				TicketsVo ticketsvo = new TicketsVo();
				ticketsvo.setId(t.getId());
				ticketsvo.setTouristId(t.getTouristId());
				ticketsvo.setTicketName(t.getTicketName());
				ticketsvo.setImgUrl(t.getImgUrl());
				ticketsvo.setPrice(t.getPrice());
				String aa=XaUtil.getToDayStr().substring(0, 10); 
				if(XaUtil.isNotEmpty(t.getValidity())&&t.getValidity().compareTo(aa)<0){
					ticketsvo.setGroupBuy(0);
				}else{
					/** 参加团购需要的字段 start */
					ticketsvo.setGroupBuy(t.getGroupBuy());
					ticketsvo.setValidity(t.getValidity());
					ticketsvo.setGroupPrice(t.getGroupPrice());
				}
				
				/** 参加团购需要的字段 end */
				ticketsvo.setPredTime(t.getPredTime());
				ticketsVos.add(ticketsvo);
			}
			vo.setTickets(ticketsVos);
			vo.setNotes((String)obj[13]);
			vo.setIsLike(Integer.valueOf(obj[14].toString()));
		}
		xr.success(vo);
		return xr;
	}
	//未登录（景点详情）
	@Override
	public XaResult<TouristVo> findTouristDetail(Long touristId) {
		XaResult<TouristVo> xr = new XaResult<TouristVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT t.id,t.tourist_name,t.img_url,t.lng,t.lat,t.address,t.telphone,t.tourist_desc,t.business_time,");
		sql.append("(SELECT AVG(c.score) FROM tb_xa_comment c inner join tb_xa_user u on u.id=c.user_id where c.status=").append(XaConstant.Status.valid).append(" and c.type=").append(JConstant.ObjectType.TOURIST).append(" and c.object_id=").append(touristId).append(" and u.status=").append(XaConstant.Status.valid).append(") score,");
		sql.append("(SELECT COUNT(*) FROM tb_xa_comment c1 inner join tb_xa_user u on u.id=c1.user_id where c1.status=").append(XaConstant.Status.valid).append(" and c1.type=").append(JConstant.ObjectType.TOURIST).append(" and c1.object_id=").append(touristId).append(" and u.status=").append(XaConstant.Status.valid).append(") comments,");
		sql.append("(SELECT GROUP_CONCAT(i.picurl) FROM tb_xa_images i where i.status=").append(XaConstant.Status.valid).append(" and i.type=").append(JConstant.ImageType.TOURIST).append(" and i.object_id=").append(touristId).append(") pics,");
		sql.append("(SELECT GROUP_CONCAT(ii.picurl) FROM tb_xa_images ii where ii.status=").append(XaConstant.Status.valid).append(" and ii.type=").append(JConstant.ImageType.HOTEL_INTRO).append(" and ii.object_id=").append(touristId).append(") intropics,t.notes ");
		
		sql.append("FROM tb_xa_tourist t WHERE t.status=").append(XaConstant.Status.valid).append(" and t.id=").append(touristId);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		if(XaUtil.isEmpty(objs)){
			xr.error("该商品已下架！");
			return xr;
		}
		TouristVo vo = new TouristVo();
		for(Object[] obj : objs){
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setTouristName((String)obj[1]);
			vo.setImgUrl((String)obj[2]);
			vo.setLng((Double)obj[3]);
			vo.setLat((Double)obj[4]);
			vo.setAddress((String)obj[5]);
			vo.setTelphone((String)obj[6]);
			vo.setTouristDesc((String)obj[7]);
			vo.setBusinessTime((String)obj[8]);
			DecimalFormat df = new DecimalFormat("#.0");  
			vo.setScore(XaUtil.isEmpty(obj[9]) ? 0 : Double.valueOf(df.format((Double)obj[9])));
			vo.setComments(((BigInteger)obj[10]).intValue());
			vo.setPics(XaUtil.isEmpty(obj[11]) ? null : ((String)obj[11]).split(","));
			vo.setIntropics(XaUtil.isEmpty(obj[12]) ? null : ((String)obj[12]).split(","));
			List<Tickets> tickets = ticketsRepository.findByTouristIdAndStatus(touristId, XaConstant.Status.publish);
			//判断门票是否存在
			/*if(XaUtil.isEmpty(tickets)){
				xr.error("该商品已下架！");
				return xr;
			}*/
			List<TicketsVo> ticketsVos = new ArrayList<TicketsVo>();
			for(Tickets t : tickets){
				TicketsVo ticketsvo = new TicketsVo();
				ticketsvo.setId(t.getId());
				ticketsvo.setTouristId(t.getTouristId());
				ticketsvo.setTicketName(t.getTicketName());
				ticketsvo.setImgUrl(t.getImgUrl());
				ticketsvo.setPrice(t.getPrice());
				String aa=XaUtil.getToDayStr().substring(0, 10); 
				if(XaUtil.isNotEmpty(t.getValidity())&&t.getValidity().compareTo(aa)<0){
					ticketsvo.setGroupBuy(0);
				}else{
					/** 参加团购需要的字段 start */
					ticketsvo.setGroupBuy(t.getGroupBuy());
					ticketsvo.setValidity(t.getValidity());
					ticketsvo.setGroupPrice(t.getGroupPrice());
				}
				
				/** 参加团购需要的字段 end */
				ticketsvo.setPredTime(t.getPredTime());
				ticketsVos.add(ticketsvo);
			}
			vo.setTickets(ticketsVos);
			vo.setNotes((String)obj[13]);
			vo.setIsLike(0);
		}
		xr.success(vo);
		return xr;
	}

}
