package com.web.liuda.business.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Order;
import com.web.liuda.business.repository.OrderRepository;
import com.web.liuda.business.service.OrderService;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.MacthVo;
import com.web.liuda.remote.vo.OrderVo;
import com.web.liuda.remote.vo.RoomVo;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.liuda.remote.vo.TouristVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.excelUtil.ExcelColumn;
import com.web.webstart.base.excelUtil.ExcelHead;
import com.web.webstart.base.excelUtil.ExcelHelper;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MyPage;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.SearchFilter.Operator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

@Service("OrderService")
@Transactional(readOnly = false)
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	/**
	 * 查询单条Order信息
	 * @param tId
	 * @return 返回单个Order对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Order> findOne(Long modelId) throws BusinessException {
		XaResult<Order> xr = new XaResult<Order>();
		return xr;
	}
	
	/**
	 * 查询单条Order信息
	 * @param tId
	 * @return 返回单个Order对象
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<List<OrderVo>> findOrderDetailById(Integer businessType,String orderNo) throws BusinessException {
		XaResult<List<OrderVo>> xr = new XaResult<List<OrderVo>>();
		StringBuffer sql=new StringBuffer("select o.order_no,o.create_time,o.order_status,o.order_num,datediff(o.leave_time,o.checkin_time),");
		sql.append("o.checkin_time,o.leave_time,o.order_price,");	
		sql.append("o.user_name,o.mobile,o.test_time ");
		if(businessType==JConstant.ObjectType.HOTEL){
			sql.append(",r.type ");
		}
		if(businessType==JConstant.ObjectType.TOURIST){
			sql.append(",t.ticket_name ");
		}
		sql.append("from tb_xa_order o ");	
		if(businessType==JConstant.ObjectType.HOTEL){
			sql.append("INNER JOIN tb_xa_room r ON o.object_id=r.id ");
		}
		if(businessType==JConstant.ObjectType.TOURIST){
			sql.append("INNER JOIN tb_xa_tickets t ON o.object_id=t.id ");
		}
		sql.append("where o.order_no=? and o.status<>"+XaConstant.Status.delete+" and o.order_type=?");
		
		List<Object> params = new ArrayList<Object>();
		params.add(orderNo);
		params.add(businessType);
		List<Object[]> objs=this.queryParams(sql.toString(), null, null, params);
		List<OrderVo> vos=new ArrayList<OrderVo>();
		for(Object[] obj:objs){
			OrderVo vo=new OrderVo();
			vo.setOrderNo((String)obj[0]);
			vo.setCreateTime((String)obj[1]);
			vo.setOrderStatus((Integer)obj[2]);
			vo.setOrderNum((Integer)obj[3]);
			if(businessType==JConstant.ObjectType.HOTEL){
				HotelVo hotelVo=new HotelVo();
				hotelVo.setDayNumber((Integer.valueOf(obj[4].toString())));
				hotelVo.setCheckinTime((String)obj[5]);
				hotelVo.setLeaveTime((String)obj[6]);
				vo.setHotelVo(hotelVo);
			}
			
			vo.setOrderPrice((Double)obj[7]);
			vo.setUserName((String)obj[8]);
			vo.setMobile((String)obj[9]);
			vo.setTestTime((String)obj[10]);
			
			if(businessType==JConstant.ObjectType.HOTEL){
				RoomVo roomVo=new RoomVo();
				roomVo.setType((String)obj[11]);
				vo.setRoomVo(roomVo);
			}
			if(businessType==JConstant.ObjectType.TOURIST){
				TicketsVo ticketsVo=new TicketsVo();
				ticketsVo.setTicketName((String)obj[11]);
				vo.setTicketsVo(ticketsVo);
			}
			vos.add(vo);
		}
		xr.setObject(vos);
		return xr;
	}
	/**
	 * pc端查订单管理的列表展示
	 * author：changlu
	 * time:2016-01-14 13:40:00
	 * 分页查询Order数据
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Order集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<OrderVo>> findOrderListByFilter(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		XaResult<Page<OrderVo>> xr = new XaResult<Page<OrderVo>>();
		StringBuffer sql=new StringBuffer("");
		//酒店
		if(XaUtil.isNotEmpty(filterParams.get("EQ_hotel_id"))&& Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.HOTEL)){
			sql.append("SELECT o.order_no,o.create_time,o.order_status,r.type ");
			sql.append("from tb_xa_order o ");
			sql.append("INNER JOIN tb_xa_room r ON o.object_id=r.id ");
			sql.append("WHERE o.order_type=0 and o.status<>3 and r.status<>3 and o.object_id in ");
			sql.append("(SELECT r1.id from tb_xa_room r1 where r1.hotel_id=? and r1.status<>3)");
		}
		//景点
		if(XaUtil.isNotEmpty(filterParams.get("EQ_tourist_id"))&& Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.TOURIST)){
			sql.append("SELECT o.order_no,o.create_time,o.order_status,t.ticket_name ");
			sql.append("from tb_xa_order o ");
			sql.append("INNER JOIN tb_xa_tickets t ON o.object_id=t.id ");
			sql.append("WHERE o.order_type=1 and o.status<>3 and t.status<>3 and o.object_id in ");
			sql.append("(SELECT t1.id from tb_xa_tickets t1 where t1.tourist_id=? and t1.status<>3)");
		}
		/*统计数据条数*/
		StringBuffer sql1=new StringBuffer("");
		//酒店
		if(XaUtil.isNotEmpty(filterParams.get("EQ_hotel_id"))&& Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.HOTEL)){
			sql1.append("SELECT count(*) ");
			sql1.append("from tb_xa_order o ");
			sql1.append("INNER JOIN tb_xa_room r ON o.object_id=r.id ");
			sql1.append("WHERE o.order_type=0 and o.status<>3 and r.status<>3 and o.object_id in ");
			sql1.append("(SELECT r1.id from tb_xa_room r1 where r1.hotel_id=? and r1.status<>3)");
		}
		//景点
		if(XaUtil.isNotEmpty(filterParams.get("EQ_tourist_id"))&& Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.TOURIST)){
			sql1.append("SELECT count(*) ");
			sql1.append("from tb_xa_order o ");
			sql1.append("INNER JOIN tb_xa_tickets t ON o.object_id=t.id ");
			sql1.append("WHERE o.order_type=1 and o.status<>3 and t.status<>3 and o.object_id in ");
			sql1.append("(SELECT t1.id from tb_xa_tickets t1 where t1.tourist_id=? and t1.status<>3)");
		}
		//下单时间
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
			sql1.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
				sql1.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
				sql1.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		//订单状态不为空
		if(XaUtil.isNotEmpty(filterParams.get("EQ_order_status"))){
			sql.append(" and o.order_status = ").append(filterParams.get("EQ_order_status"));
			sql1.append(" and o.order_status = ").append(filterParams.get("EQ_order_status"));
		}
		sql.append(" ORDER BY create_time desc");
		List<Object> params = new ArrayList<Object>();
		if(XaUtil.isNotEmpty(filterParams.get("EQ_hotel_id"))&& Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.HOTEL)){
			params.add(filterParams.get("EQ_hotel_id"));
		}
		if(XaUtil.isNotEmpty(filterParams.get("EQ_tourist_id"))&& Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.TOURIST)){
			params.add(filterParams.get("EQ_tourist_id"));
		}
		List<Object[]> objs=this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(), params);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		//遍历objs
		for(Object[] obj : objs){
			OrderVo vo=new OrderVo();
			vo.setOrderNo((String)obj[0]);
			vo.setCreateTime((String)obj[1]);
			vo.setOrderStatus((Integer)obj[2]);
			if(Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.HOTEL)){
				RoomVo roomVo=new RoomVo();
				roomVo.setType((String)obj[3]);
				vo.setRoomVo(roomVo);
			}
			if(Integer.valueOf( (String) filterParams.get("EQ_business_type")).equals(JConstant.ObjectType.TOURIST)){
				TicketsVo ticketsVo=new TicketsVo();
				ticketsVo.setTicketName((String)obj[3]);
				vo.setTicketsVo(ticketsVo);
			}
			vos.add(vo);
		}
		
		//统计数据条数
		List<Object[]> count=this.queryParams(sql1.toString(), null, null, params);
		//调用分页
		Page<OrderVo> page= new MyPage<OrderVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,Integer.parseInt(count.get(0)+""));
		//将page放入xr
		xr.setObject(page);
		return xr;
	}
	/**
	 * 后台财务统计列表
	 * author：changlu
	 * time:2016-01-25 15:23:00
	 * 分页查询财务数据
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Order集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<OrderVo>> findAccountList(
			Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		XaResult<Page<OrderVo>> xr = new XaResult<Page<OrderVo>>();
		/* 查询列表*/
		StringBuffer sql=new StringBuffer("select * from ( ");
		sql.append("select * from ( ");
		sql.append("SELECT count(*),sum(o.order_price),");
		sql.append("(select h.hotel_name from tb_xa_hotel h INNER JOIN tb_xa_room r1 on r1.hotel_id=h.id where r1.status<>3 and r1.id=o.object_id and h.status<>3) hotelName ");
		sql.append(" from tb_xa_order o INNER JOIN tb_xa_room r on r.id=o.object_id ");
		sql.append("where o.status<>3 and o.order_type=0 and o.order_status in(1,2,3,4,5) and r.hotel_id in (SELECT h1.id from tb_xa_hotel h1 where h1.status<>3) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql.append(" GROUP BY hotelName) temp ");
		sql.append(" UNION select * from (");
		sql.append(" SELECT count(*),sum(o.order_price),'平台'  hotelName ");
		sql.append(" from tb_xa_orderdetail od INNER JOIN tb_xa_shop s ON od.shop_id=s.id ");
		sql.append(" INNER JOIN tb_xa_order o ON o.order_no=od.order_no ");
		sql.append(" WHERE s.status<>3 and od.status<>3 and o.order_type=2 and o.status<>3 and o.order_status in(1,2,3,4,5) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql.append("  GROUP BY hotelName) temp1 ");
		sql.append(" UNION select * from (");
		sql.append("SELECT count(*),sum(o.order_price),");
		sql.append("(select tour.tourist_name from tb_xa_tourist tour INNER JOIN tb_xa_tickets t1 on t1.tourist_id=tour.id where t1.status<>3 and t1.id=o.object_id and tour.status<>3) hotelName ");
		sql.append("from tb_xa_order o INNER JOIN tb_xa_tickets t on t.id=o.object_id ");
		sql.append("where o.status<>3 and o.order_type=1 and o.order_status in(1,2,3,4,5) and t.tourist_id in (SELECT tour1.id from tb_xa_tourist tour1 where tour1.status<>3) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql.append(" GROUP BY hotelName) temp2 ");
		sql.append(") t where 1=1 ");
		/*统计数据条数*/
		StringBuffer sql1=new StringBuffer("select count(*) from (");
		sql1.append("select * from ( ");
		sql1.append("select * from ( ");
		sql1.append("SELECT count(*),sum(o.order_price),");
		sql1.append("(select h.hotel_name from tb_xa_hotel h INNER JOIN tb_xa_room r1 on r1.hotel_id=h.id where r1.status<>3 and r1.id=o.object_id and h.status<>3) hotelName ");
		sql1.append(" from tb_xa_order o INNER JOIN tb_xa_room r on r.id=o.object_id ");
		sql1.append(" where o.status<>3 and o.order_type=0 and o.order_status in(1,2,3,4,5) and r.hotel_id in (SELECT h1.id from tb_xa_hotel h1 where h1.status<>3) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql1.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql1.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql1.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql1.append(" GROUP BY hotelName) temp ");
		sql1.append(" UNION select * from (");
		sql1.append(" SELECT count(*),sum(o.order_price),'平台'  hotelName ");
		sql1.append(" from tb_xa_orderdetail od INNER JOIN tb_xa_shop s ON od.shop_id=s.id ");
		sql1.append(" INNER JOIN tb_xa_order o ON o.order_no=od.order_no ");
		sql1.append(" WHERE s.status<>3 and od.status<>3 and o.order_type=2 and o.status<>3 and o.order_status in(1,2,3,4,5) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql1.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql1.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql1.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql1.append("  GROUP BY hotelName) temp1 ");
		sql1.append(" UNION select * from (");
		sql1.append(" SELECT count(*),sum(o.order_price),");
		sql1.append("(select tour.tourist_name from tb_xa_tourist tour INNER JOIN tb_xa_tickets t1 on t1.tourist_id=tour.id where t1.status<>3 and t1.id=o.object_id and tour.status<>3) hotelName ");
		sql1.append(" from tb_xa_order o INNER JOIN tb_xa_tickets t on t.id=o.object_id ");
		sql1.append(" where o.status<>3 and o.order_type=1 and o.order_status in(1,2,3,4,5) and t.tourist_id in (SELECT tour1.id from tb_xa_tourist tour1 where tour1.status<>3)");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql1.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql1.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql1.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql1.append("  GROUP BY hotelName) temp2  ");
		sql1.append(") t where 1=1 ");
		System.out.println(filterParams.get("LIKE_objectname"));
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_objectname"))){
			sql.append(" and hotelName like '%"+filterParams.get("LIKE_objectname")+"%' ");
			sql1.append(" and hotelName like '%"+filterParams.get("LIKE_objectname")+"%' ");
		}
		//下单时间
		sql1.append(") t2 ");
 		List<Object[]> objs=this.queryParams(sql.toString(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize(), new ArrayList<Object>());
		List<OrderVo> vos = new ArrayList<OrderVo>();
		//遍历objs
		for(Object[] obj : objs){
			OrderVo vo=new OrderVo();
			vo.setId(((BigInteger)obj[0]).longValue());
		
			//订单总额不为空
			if(XaUtil.isNotEmpty(obj[1])){
				//保留两位小数
				double f2= Double.parseDouble(obj[1].toString()); 
				BigDecimal b1 = new BigDecimal(f2);  
				double f3 = b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setOrderPrice(f3);
			}else{
				vo.setOrderPrice(0.00);
			}
			
			HotelVo hotelVo=new HotelVo();
			
			if(XaUtil.isNotEmpty(obj[2])){
				hotelVo.setHotelName((String)obj[2]);
			}
			vo.setHotel(hotelVo);
			vos.add(vo);
		}
		//统计数据条数
		List<Object[]> count=this.queryParams(sql1.toString(), null, null, new ArrayList<Object>());
		//调用分页
		Page<OrderVo> page= new MyPage<OrderVo>(pageable.getPageNumber(),pageable.getPageSize(),vos,Integer.parseInt(count.get(0)+""));
		//将page放入xr
		xr.setObject(page);
		return xr;
	}
	/**
	 * pc端校验兑换码
	 * author：changlu
	 * time:2016-01-14 16:40:00
	 * @param businessId 
	 * @param businessType		
	 * @param orderNo
	 * @param orderStatus
	 * @return OrderVo
	 */
	public XaResult<List<OrderVo>> findOrder(Long businessId,Integer businessType,
			String orderNo, Integer orderStatus) throws BusinessException{
		StringBuffer sql=new StringBuffer("");
		List<Object[]> objs2=new ArrayList<Object[]>();
		//酒店
		if(XaUtil.isNotEmpty(businessType)&&businessType.equals(JConstant.ObjectType.HOTEL)){
			sql.append("SELECT o.order_no,o.create_time,o.order_status,r.type,o.user_name,o.mobile,o.id,o.order_num  ");
			sql.append("from tb_xa_order o ");
			sql.append("INNER JOIN tb_xa_room r ON o.object_id=r.id ");
			sql.append("WHERE o.order_type=0 and o.status<>3 and r.status<>3 and o.order_no=? ");
			sql.append( "and o.object_id in ");
			sql.append("(SELECT r1.id from tb_xa_room r1 INNER JOIN tb_xa_hotel h ON h.id=r1.hotel_id ");
			sql.append("WHERE h.status<>3 and r1.status<>3 AND h.id in ");
			sql.append("(SELECT h1.id FROM tb_xa_hotel h1 WHERE h1.business_user_id=?)) ");
			//查询该兑换码是否存在
			List<Object> params2 = new ArrayList<Object>();
			params2.add(orderNo);
			params2.add(businessId);
			objs2= this.queryParams(sql.toString(), null, null, params2);
			sql.append(" and o.order_status=? "); 
		}
		//景点
		if(XaUtil.isNotEmpty(businessType)&&businessType.equals(JConstant.ObjectType.TOURIST)){
			sql.append("SELECT o.order_no,o.create_time,o.order_status,t.ticket_name,o.user_name,o.mobile,o.id,o.order_num   ");
			sql.append("from tb_xa_order o ");
			sql.append("INNER JOIN tb_xa_tickets t ON o.object_id=t.id ");
			sql.append("WHERE o.order_type=1 and o.status<>3 and t.status<>3 and o.order_no=? ");
			sql.append(" and o.object_id in ");
			sql.append("(SELECT t1.id from tb_xa_tickets t1 INNER JOIN tb_xa_tourist tour ON tour.id=t1.tourist_id ");
			sql.append("WHERE tour.status<>3 and t1.status<>3 AND tour.id in ");
			sql.append("(SELECT tour1.id FROM tb_xa_tourist tour1 WHERE tour1.business_user_id=?)) ");
			
			//查询该兑换码是否存在
			List<Object> params2 = new ArrayList<Object>();
			params2.add(orderNo);
			params2.add(businessId);
			objs2= this.queryParams(sql.toString(), null, null, params2);
			sql.append(" and o.order_status=? ");
		}
		List<Object> params = new ArrayList<Object>();
		params.add(orderNo);
		params.add(businessId);
		params.add(orderStatus);
		List<Object[]> objs= this.queryParams(sql.toString(), null, null, params);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		//遍历objs
		for(Object[] obj : objs){
			OrderVo vo=new OrderVo();
			vo.setOrderNo((String)obj[0]);
			vo.setCreateTime((String)obj[1]);
			vo.setOrderStatus((Integer)obj[2]);
			if(businessType.equals(JConstant.ObjectType.HOTEL)){
				RoomVo roomVo=new RoomVo();
				roomVo.setType((String)obj[3]);
				vo.setRoomVo(roomVo);
			}
			if(businessType.equals(JConstant.ObjectType.TOURIST)){
				TicketsVo ticketsVo=new TicketsVo();
				ticketsVo.setTicketName((String)obj[3]);
				vo.setTicketsVo(ticketsVo);
			}
			vo.setUserName((String)obj[4]);
			vo.setMobile((String)obj[5]);
			vo.setOrderNum((Integer)obj[7]);
			//获取当前时间，作为校验时间
			String time=XaUtil.getToDayStr();
			vo.setTestTime(time);
			//修改订单状态
			String upsql="update tb_xa_order set order_status=? , test_time=? where id=?";
			List<Object> updparams = new ArrayList<Object>();
			updparams.add(JConstant.OrderStatus.UN_COMMENT);
			updparams.add(time);
			updparams.add(((BigInteger)obj[6]).longValue());
			this.delete(upsql, updparams);
			vo.setTestTime(time);
			vos.add(vo);
			
		}
		XaResult<List<OrderVo>> xr = new XaResult<List<OrderVo>>();
		
		if(XaUtil.isEmpty(objs)){
			//该兑换码存在，已被兑换
			if(XaUtil.isNotEmpty(objs2)){
				if(objs2.get(0)[2]==JConstant.OrderStatus.UN_PAY){
					xr.setMessage("订单未支付");
					xr.setCode(0);
					return xr;
				}else{
					xr.setMessage("兑换码已兑换");
					xr.setCode(0);
					return xr;
				}
				
			}else{//兑换码不存在
				xr.setCode(0);
				xr.setMessage("兑换码不存在");
				return xr;
			}
			
		}else{//兑换成功
			xr.setObject(vos);
			return xr;
		}
	}
	/**
	 * 分页查询状态非status的Order数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Order集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Order>> findListNEStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		XaResult<Page<Order>> xr = new XaResult<Page<Order>>();
		if(status == null){// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT o.id,o.order_no,o.order_num,o.price,o.order_price,o.order_type,o.user_name,o.mobile,o.order_status,o.create_time,a.consignee_name,a.mobile mobile1,o.test_time ");
		sql.append(" from tb_xa_order o LEFT JOIN tb_xa_address a ON o.address_id=a.id ");
		sql.append(" WHERE o.status<>").append(status);
		
		StringBuffer countSql = new StringBuffer();
		countSql.append("select count(*) from tb_xa_order o LEFT JOIN tb_xa_address a ON o.address_id=a.id ");
		countSql.append("WHERE o.status<>").append(status);
		
		List<Object> params = new ArrayList<Object>();
		//订单编号不为空
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_orderNo"))){
			sql.append(" and o.order_no like ?");
			countSql.append(" and o.order_no like ?");
			params.add("%" + filterParams.get("LIKE_orderNo") + "%");
		}
		//订单状态不为空
		if(XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus"))){
			sql.append(" and o.order_status = ?");
			countSql.append(" and o.order_status = ?");
			params.add(filterParams.get("EQ_orderStatus"));
		}
		//订单类型不为空
		if(XaUtil.isNotEmpty(filterParams.get("EQ_orderType"))){
			sql.append(" and o.order_type = ?");
			countSql.append(" and o.order_type = ?");
			params.add(filterParams.get("EQ_orderType"));
		}
		//下单时间
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
			countSql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
				countSql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
				countSql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}

		//对象Id不为空
		if(XaUtil.isNotEmpty(filterParams.get("EQ_objectId"))){
			sql.append(" and o.object_id = ?");
			countSql.append(" and o.object_id = ?");
			params.add(filterParams.get("EQ_objectId"));
		}
		//订单状态不为空
		if(XaUtil.isNotEmpty(filterParams.get("GT_orderStatus"))){
			sql.append(" and o.order_status > ?");
			countSql.append(" and o.order_status > ?");
			params.add(filterParams.get("GT_orderStatus"));
		}
		//订单状态不为空
		if(XaUtil.isNotEmpty(filterParams.get("LT_orderStatus"))){
			sql.append(" and o.order_status < ?");
			countSql.append(" and o.order_status < ?");
			params.add(filterParams.get("LT_orderStatus"));
		}
//		//订单状态不为空
//		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_orderStatus"))){
//			String[] str = filterParams.get("BETWEEN_orderStatus").toString().split(",");
//			sql.append(" and o.order_status between ? and ?");
//			countSql.append(" and o.order_status between ? and ?");
//			params.add(str[0]);
//			params.add(str[1]);
//		}
		sql.append(" order by o.create_time desc ");
		List<Object[]> objs = this.queryParams(sql.toString(), pageable.getPageSize() * pageable.getPageNumber(), pageable.getPageSize(), params);
		List<Object[]> count = this.queryParams(countSql.toString(), null, null, params);
		List<Order> orders = new ArrayList<Order>();
		for(Object[] obj : objs){
			Order order = new Order();
			order.setId(((BigInteger)obj[0]).longValue());
			order.setOrderNo((String)obj[1]);
			order.setOrderNum((Integer)obj[2]);
			order.setPrice((Double)obj[3]);
			order.setOrderPrice((Double)obj[4]);
			order.setOrderType((Integer)obj[5]);
			order.setUserName(XaUtil.isNotEmpty(obj[6]) ? (String)obj[6] : (String)obj[10]);
			order.setMobile(XaUtil.isNotEmpty(obj[7]) ? (String)obj[7] : (String)obj[11]);
			order.setOrderStatus((Integer)obj[8]);
			order.setCreateTime((String)obj[9]);
			order.setTestTime((String)obj[12]);
			orders.add(order);
		}
		Page<Order> page = new MyPage<Order>(pageable.getPageNumber(),pageable.getPageSize(),orders,Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;
	}

	/**
	 * 分页查询状态status的Order数据
	 * @param status
	 * @param filterParams
	 * @param pageable
	 * @return 返回对象Order集合
	 * @throws BusinessException
	 */
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<Page<Order>> findListEQStatusByFilter(
			Integer status, Map<String, Object> filterParams, Pageable pageable) throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if(status == null){// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Order> page = orderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Order.class), pageable);
		XaResult<Page<Order>> xr = new XaResult<Page<Order>>();
		xr.setObject(page);
		return xr;
	}

	/**
	 * 保存Order信息
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Order> saveOrUpdate(Order model) throws BusinessException {
		Order obj = null;
		if(XaUtil.isNotEmpty(model.getId())){
			obj = orderRepository.findOne(model.getId());
		}else{
			obj = new Order();
		}
		obj.setOrderNo(model.getOrderNo());
		obj.setUserId(model.getUserId());
		obj.setObjectId(model.getObjectId());
		obj.setOrderNum(model.getOrderNum());
		obj.setPrice(model.getPrice());
		obj.setOrderStatus(model.getOrderStatus());
		obj.setPayType(model.getPayType());
		obj.setPayStatus(model.getPayStatus());
		obj.setUserName(model.getUserName());
		obj.setMobile(model.getMobile());
		obj.setCheckinTime(model.getCheckinTime());
		obj.setLeaveTime(model.getLeaveTime());
		obj.setOrderType(model.getOrderType());
		obj = orderRepository.save(obj);
		XaResult<Order> xr = new XaResult<Order>();
		xr.setObject(obj);
		return xr;
	}

	/**
	 * 修改Order状态，可一次修改多条   3删除  -1锁定  1正常
	 * @param userId
	 * @param modelIds
	 * @param status
	 * @return 返回Order对象
	 * @throws BusinessException
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Order> multiOperate(
			String modelIds,Integer status) throws BusinessException {
		XaResult<Order> xr = new XaResult<Order>();
		if(status == null){
			status = XaConstant.Status.delete;
		}
		if(modelIds != null){
			String[] ids = modelIds.split(",");
			for(String id : ids){
				Order obj = orderRepository.findByIdAndStatusNot(Long.parseLong(id),status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = orderRepository.save(obj);
				} else {
					throw new BusinessException(XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}

	@Override
	public XaResult<OrderVo> findOne(Long id, Integer orderType) {
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT o.id,o.order_no,o.order_num,o.price,o.order_price,o.order_type,o.user_name,o.mobile,o.order_status,o.create_time,a.consignee_name,a.mobile mobile1 ");
		//根据订单类型动态拼接sql查询不同的详情信息
		if(orderType == JConstant.ObjectType.HOTEL){
			sql.append(",o.checkin_time,o.leave_time,");
			sql.append("(SELECT r.type FROM tb_xa_room r WHERE o.object_id=r.id) roomName,");
			sql.append("(SELECT h.hotel_name FROM tb_xa_hotel h INNER JOIN tb_xa_room r ON h.id=r.hotel_id WHERE o.object_id=r.id) hotelName ");
		}else if(orderType == JConstant.ObjectType.TOURIST){
			sql.append(",(SELECT t.ticket_name FROM tb_xa_tickets t WHERE o.object_id=t.id) ticketsName,");
			sql.append("(SELECT ts.tourist_name FROM tb_xa_tourist ts INNER JOIN tb_xa_tickets t ON ts.id=t.tourist_id WHERE o.object_id=t.id) touristName ");
		}/*else if(orderType == JConstant.ObjectType.SHOP){
			
			sql.append(",(SELECT GROUP_CONCAT(s.shop_name) FROM tb_xa_orderdetail od INNER JOIN tb_xa_shop s ON od.shop_id=s.id WHERE od.order_no=o.order_no) shopNames ");
			sql.append(",(SELECT GROUP_CONCAT(od.shop_number) FROM tb_xa_orderdetail od INNER JOIN tb_xa_shop s ON od.shop_id=s.id WHERE od.order_no=o.order_no) shopNumbers ");
			sql.append(",(SELECT GROUP_CONCAT(sd.porperty) FROM tb_xa_orderdetail od INNER JOIN tb_xa_standard sd ON od.standard_id=sd.id WHERE od.order_no=o.order_no) porpertys ");
		}*/
		else if(orderType == JConstant.ObjectType.MATCHTICKET){
			sql.append(",(SELECT m.ticket_name FROM tb_xa_macth m WHERE o.object_id=m.id) ticketsName ");
		}
		sql.append("FROM tb_xa_order o LEFT JOIN tb_xa_address a ON o.address_id=a.id ");
		sql.append("WHERE o.id=").append(id);
		List<Object[]> objs = this.query(sql.toString(), null, null);
		OrderVo order = new OrderVo();
		for(Object[] obj : objs){
			order.setId(((BigInteger)obj[0]).longValue());
			order.setOrderNo((String)obj[1]);
			order.setOrderNum((Integer)obj[2]);
			order.setPrice((Double)obj[3]);
			order.setOrderPrice((Double)obj[4]);
			order.setOrderType((Integer)obj[5]);
			order.setUserName(XaUtil.isNotEmpty(obj[6]) ? (String)obj[6] : (String)obj[10]);
			order.setMobile(XaUtil.isNotEmpty(obj[7]) ? (String)obj[7] : (String)obj[11]);
			order.setOrderStatus((Integer)obj[8]);
			order.setCreateTime((String)obj[9]);
			//根据订单类型封装不同详情信息
			if(orderType == JConstant.ObjectType.HOTEL){
				HotelVo hotel = new HotelVo();
				hotel.setCheckinTime((String)obj[12]);
				hotel.setLeaveTime((String)obj[13]);
				hotel.setHotelName((String)obj[15]);
				hotel.setType((String)obj[14]);
				order.setHotel(hotel);
			}else if(orderType == JConstant.ObjectType.TOURIST){
				TouristVo tourist = new TouristVo();
				tourist.setTouristName((String)obj[13]);
				tourist.setTicketName((String)obj[12]);
				order.setTouristVo(tourist);
			}else if(orderType == JConstant.ObjectType.MATCHTICKET){
				MacthVo macthVo = new MacthVo();
				macthVo.setTicketName((String)obj[12]);
				order.setMacthVo(macthVo);
			}else if(orderType == JConstant.ObjectType.SHOP){
				/*List<ShopVo> vos = new ArrayList<ShopVo>();
				if(XaUtil.isNotEmpty(obj[12])){
					
					ShopVo shop = new ShopVo();
					//过滤重复名字
					String[] s = ((String)obj[12]).split(",");
					StringBuffer shopNames = new StringBuffer();
					List<String> list = new ArrayList<String>();
					for(String str : s){
						if(list.contains(str)){
							continue;
						}
						list.add(str);
						shopNames.append(";").append(str);
					}
					shop.setShopName(shopNames.toString().replaceFirst(";", ""));
					
					//过滤重复名字
					String[] s1 = ((String)obj[13]).split(",");
					StringBuffer shopNumbers = new StringBuffer();
					List<String> list1 = new ArrayList<String>();
					for(String str : s1){
						if(list1.contains(str)){
							continue;
						}
						list1.add(str);
						shopNumbers.append(";").append(str);
					}
					shop.setShopNumbers(shopNumbers.toString().replaceFirst(";", ""));
					
					//过滤重复名字
					String[] s2 = ((String)obj[14]).split(",");
					StringBuffer porpertys = new StringBuffer();
					List<String> list2 = new ArrayList<String>();
					for(String str : s2){
						if(list2.contains(str)){
							continue;
						}
						list2.add(str);
						porpertys.append(";").append(str);
					}
					shop.setPorpertys(porpertys.toString().replaceFirst(";", ""));
					
					vos.add(shop);*/
				List<ShopVo> vos = new ArrayList<ShopVo>();
				StringBuffer shopSql=new StringBuffer("select s.shop_name,od.shop_number,sd.porperty FROM tb_xa_orderdetail od ");
				shopSql.append(" INNER JOIN tb_xa_shop s ON od.shop_id=s.id " );
				shopSql.append(" INNER JOIN tb_xa_standard sd ON od.standard_id=sd.id " );
				shopSql.append(" WHERE od.order_no="+(String)obj[1]+" ");
				List<Object[]> shopObjs = this.query(shopSql.toString(), null, null);
				for(Object[] obj1:shopObjs){
					ShopVo shop = new ShopVo();
					shop.setShopName((String)obj1[0]);
					shop.setShopNumber((Integer)obj1[1]);
					shop.setPorperty((String)obj1[2]);
					vos.add(shop);
				}
					order.setShopVo(vos);
				
			}
		}
		xr.setObject(order);
		return xr;
	}

	/**
	 * 查询单条兑换码信息
	 * @param pageable
	 * @return 返回对象吗Page
	 * @throws BusinessException
	 */
	public XaResult<Page<OrderVo>> findExchangeNEStatusPage(Pageable pageable,
			Map<String, Object> filterParams) throws BusinessException {
		XaResult<Page<OrderVo>> xr=new XaResult<Page<OrderVo>>();
		//查询兑换码
		StringBuffer sql=new StringBuffer("");
        if(XaUtil.isNotEmpty(filterParams.get("LIKE_bussniessName")) || XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus")) || XaUtil.isNotEmpty(filterParams.get("BETWEEN_create_time")) || XaUtil.isNotEmpty(filterParams.get("GTE_create_time")) || XaUtil.isNotEmpty(filterParams.get("LTE_create_time"))){
			sql.append("select * from(");
		}
		sql.append("select t.order_no,t.order_status,t.test_time,t.order_type,");
		sql.append("concat(ifnull((select h.hotel_name from tb_xa_room r INNER JOIN tb_xa_hotel h on r.hotel_id=h.id where r.id=t.object_id and t.order_type=0),''),");
		sql.append("ifnull((select l.tourist_name from tb_xa_tickets p INNER JOIN tb_xa_tourist l on p.tourist_id=l.id where p.id=t.object_id and t.order_type=1),'')) hotel_nameTickets_name,");
		sql.append("concat(ifnull((select r.type from tb_xa_room r where r.id=t.object_id and t.order_type=0),''),");
		sql.append("ifnull((select p.ticket_name  from tb_xa_tickets p where p.id=t.object_id and t.order_type=1),'')) as toomTypeticketsType,t.create_time ");
		sql.append("from tb_xa_order t where t.order_status in (1,2) and t.`status`<>3 and t.order_type in (0,1) ");
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_bussniessName")) || XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus")) || XaUtil.isNotEmpty(filterParams.get("BETWEEN_create_time")) || XaUtil.isNotEmpty(filterParams.get("GTE_create_time")) || XaUtil.isNotEmpty(filterParams.get("LTE_create_time"))){
			sql.append(") y where 1=1 ");
		}
		StringBuffer countSql=new StringBuffer("");
		countSql.append("select count(*) from(");
		
		countSql.append("select t.order_no,t.order_status,t.test_time,t.order_type,");
		countSql.append("concat(ifnull((select h.hotel_name from tb_xa_room r INNER JOIN tb_xa_hotel h on r.hotel_id=h.id where r.id=t.object_id and t.order_type=0),''),");
		countSql.append("ifnull((select l.tourist_name from tb_xa_tickets p INNER JOIN tb_xa_tourist l on p.tourist_id=l.id where p.id=t.object_id and t.order_type=1),'')) hotel_nameTickets_name,");
		countSql.append("concat(ifnull((select r.type from tb_xa_room r where r.id=t.object_id and t.order_type=0),''),");
		countSql.append("ifnull((select p.ticket_name  from tb_xa_tickets p where p.id=t.object_id and t.order_type=1),'')) as toomTypeticketsType,t.create_time ");
		countSql.append("from tb_xa_order t where t.order_status in (1,2) and t.`status`<>3 and t.order_type in (0,1) ");
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_bussniessName")) || XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus")) || XaUtil.isNotEmpty(filterParams.get("BETWEEN_create_time")) || XaUtil.isNotEmpty(filterParams.get("GTE_create_time")) || XaUtil.isNotEmpty(filterParams.get("LTE_create_time"))){
			
			countSql.append(") y where 1=1 ");
		}
		//统计总条数
		/*StringBuffer counthotel=new StringBuffer("");
		counthotel.append("select count(t.id) from tb_xa_order t INNER JOIN tb_xa_room h on t.object_id=h.id ");
		counthotel.append("where t.order_status in (1,2) and t.`status`<>3 and t.order_type=0 ");
		StringBuffer counttourist=new StringBuffer("");
		counttourist.append(" select count(t.id) from tb_xa_order t INNER JOIN tb_xa_tickets tt on t.object_id=tt.id ");
		counttourist.append(" where t.order_status in (1,2) and t.`status`<>3 and t.order_type=1 ");*/
		//按商家名字查询
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_bussniessName"))){
			sql.append(" and y.hotel_nameTickets_name ").append(" like '%").append(filterParams.get("LIKE_bussniessName")).append("%'");
			countSql.append(" and y.hotel_nameTickets_name ").append(" like '%").append(filterParams.get("LIKE_bussniessName")).append("%'");
			/*counthotel.append(" and t.hotel_nameTickets_name ").append(" like '%").append(filterParams.get("LIKE_bussniessName")).append("%'");
			counttourist.append(" and t.hotel_nameTickets_name ").append(" like '%").append(filterParams.get("LIKE_bussniessName")).append("%'");*/
		}
		//按状态查询
		if(XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus"))){
				sql.append(" and y.order_status="+filterParams.get("EQ_orderStatus"));
				countSql.append(" and y.order_status="+filterParams.get("EQ_orderStatus"));
				//counthotel.append(" and t.order_status="+filterParams.get("EQ_orderStatus"));
				//counttourist.append(" and t.order_status="+filterParams.get("EQ_orderStatus"));
		}
		//按兑换时间查询
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_create_time"))){
			String[] str = filterParams.get("BETWEEN_create_time").toString().split(",");
			sql.append(" and y.test_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
			countSql.append(" and y.test_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
			//counthotel.append(" and t.test_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
			//counttourist.append(" and t.test_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GTE_create_time"))){
				sql.append(" and y.test_time > '").append(filterParams.get("GTE_create_time") + " 00:00:00'");
				countSql.append(" and y.test_time > '").append(filterParams.get("GTE_create_time") + " 00:00:00'");
				//counthotel.append(" and t.test_time > '").append(filterParams.get("GTE_create_time") + " 00:00:00'");
				//counttourist.append(" and t.test_time > '").append(filterParams.get("GTE_create_time") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LTE_create_time"))){
				sql.append(" and y.test_time < '").append(filterParams.get("LTE_create_time") + " 23:59:59'");
				countSql.append(" and y.test_time < '").append(filterParams.get("LTE_create_time") + " 23:59:59'");
				//counthotel.append(" and t.test_time < '").append(filterParams.get("LTE_create_time") + " 23:59:59'");
				//counttourist.append(" and t.test_time < '").append(filterParams.get("LTE_create_time") + " 23:59:59'");
			}
		}
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_bussniessName")) || XaUtil.isNotEmpty(filterParams.get("EQ_orderStatus")) || XaUtil.isNotEmpty(filterParams.get("BETWEEN_create_time")) || XaUtil.isNotEmpty(filterParams.get("GTE_create_time")) || XaUtil.isNotEmpty(filterParams.get("LTE_create_time"))){
			sql.append(" order by y.create_time desc");
			countSql.append(" order by y.create_time desc");
		}else{
			sql.append(" order by t.create_time desc");
			countSql.append(" order by t.create_time desc");
			countSql.append(") count ");
		}
		/*if(XaUtil.isEmpty(filterParams.get("LIKE_bussniessName")) || XaUtil.isEmpty(filterParams.get("EQ_orderStatus")) || XaUtil.isEmpty(filterParams.get("BETWEEN_create_time")) || XaUtil.isEmpty(filterParams.get("GTE_create_time")) || XaUtil.isEmpty(filterParams.get("LTE_create_time"))){
			
		}*/
		
		//List<Object[]> count1=this.query(counthotel.toString(), 0, 0);
		//List<Object[]> count2=this.query(counttourist.toString(), 0, 0);
		List<Object[]> count2=this.query(countSql.toString(), 0, 0);
		List<Object[]> objs=this.query(sql.toString(),pageable.getPageNumber()*pageable.getPageSize(),pageable.getPageSize());
		List<OrderVo> orderVoList=new ArrayList<OrderVo>();
		for(Object[] obj:objs){
			OrderVo order=new OrderVo();
			order.setOrderNo((String)obj[0]);
			order.setOrderStatus((Integer)obj[1]);
			order.setTestTime((String)obj[2]==null?"":(String)obj[2]);
			order.setOrderType((Integer)obj[3]);
			if((Integer)obj[3]==0){
				RoomVo roomVo=new RoomVo();
				roomVo.setHotelName((String)obj[4]==null?"":(String)obj[4]);
				roomVo.setType((String)obj[5]==null?"":(String)obj[5]);
				order.setRoomVo(roomVo);
			}
			if((Integer)obj[3]==1){
				TicketsVo ticketsVo=new TicketsVo();
				ticketsVo.setTouristName((String)obj[4]==null?"":(String)obj[4]);
				ticketsVo.setTicketName((String)obj[5]==null?"":(String)obj[5]);
				order.setTicketsVo(ticketsVo);
			}
			orderVoList.add(order);
		}
		Page<OrderVo> page=new MyPage<OrderVo>(pageable.getPageNumber(), pageable.getPageSize(), orderVoList, Integer.parseInt(count2.get(0)+""));
		xr.setObject(page);
		return xr;
	}
	

	//财务统计导出数据
	@Transactional(rollbackFor = Exception.class)
	public void exportdata(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException {	
		/* 查询列表*/
		StringBuffer sql=new StringBuffer("select * from ( ");
		sql.append("select * from ( ");
		sql.append("SELECT count(*),sum(o.order_price),");
		sql.append("(select h.hotel_name from tb_xa_hotel h INNER JOIN tb_xa_room r1 on r1.hotel_id=h.id where r1.status<>3 and r1.id=o.object_id and h.status<>3) hotelName ");
		sql.append(" from tb_xa_order o INNER JOIN tb_xa_room r on r.id=o.object_id ");
		sql.append("where o.status<>3 and o.order_type=0 and o.order_status<>0 and r.hotel_id in (SELECT h1.id from tb_xa_hotel h1 where h1.status<>3) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql.append(" GROUP BY hotelName) temp ");
		sql.append(" UNION select * from (");
		sql.append(" SELECT count(*),sum(o.order_price),'平台'  hotelName ");
		sql.append(" from tb_xa_orderdetail od INNER JOIN tb_xa_shop s ON od.shop_id=s.id ");
		sql.append(" INNER JOIN tb_xa_order o ON o.order_no=od.order_no ");
		sql.append(" WHERE s.status<>3 and od.status<>3 and o.order_type=2 and o.status<>3 and o.order_status<>0 ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql.append("  GROUP BY hotelName) temp1 ");
		sql.append(" UNION select * from (");
		sql.append("SELECT count(*),sum(o.order_price),");
		sql.append("(select tour.tourist_name from tb_xa_tourist tour INNER JOIN tb_xa_tickets t1 on t1.tourist_id=tour.id where t1.status<>3 and t1.id=o.object_id and tour.status<>3) hotelName ");
		sql.append("from tb_xa_order o INNER JOIN tb_xa_tickets t on t.id=o.object_id ");
		sql.append("where o.status<>3 and o.order_type=1 and o.order_status<>0 and t.tourist_id in (SELECT tour1.id from tb_xa_tourist tour1 where tour1.status<>3) ");
		if(XaUtil.isNotEmpty(filterParams.get("BETWEEN_createTime"))){
			String[] str = filterParams.get("BETWEEN_createTime").toString().split(",");
			sql.append(" and o.create_time BETWEEN '").append(str[0] + " 00:00:00'").append(" AND '").append(str[1] + " 23:59:59'");
		}else{
			if(XaUtil.isNotEmpty(filterParams.get("GT_createTime"))){
				sql.append(" and o.create_time > '").append(filterParams.get("GT_createTime") + " 00:00:00'");
			}else if(XaUtil.isNotEmpty(filterParams.get("LT_createTime"))){
				sql.append(" and o.create_time < '").append(filterParams.get("LT_createTime") + " 23:59:59'");
			}
		}
		sql.append(" GROUP BY hotelName) temp2 ");
		sql.append(") t where 1=1 ");
		
		if(XaUtil.isNotEmpty(filterParams.get("LIKE_objectname"))){
			sql.append(" and hotelName like '%"+filterParams.get("LIKE_objectname")+"%' ");
		}
		
		List<Object[]> objs = this.query(sql.toString(), null, null);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		//遍历objs
		for(Object[] obj : objs){
			OrderVo vo=new OrderVo();
			vo.setId(((BigInteger)obj[0]).longValue());
		
			//订单总额不为空
			if(XaUtil.isNotEmpty(obj[1])){
				//保留两位小数
				double f2= Double.parseDouble(obj[1].toString()); 
				BigDecimal b1 = new BigDecimal(f2);  
				double f3 = b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
				vo.setOrderPrice(f3);
			}else{
				vo.setOrderPrice(0.00);
			}
			HotelVo hotelVo=new HotelVo();
			if(XaUtil.isNotEmpty(obj[2])){
				hotelVo.setHotelName((String)obj[2]);
			}
			vo.setHotel(hotelVo);
			vos.add(vo);
		}
	    //excel结构
	    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
	    excelColumns.add(new ExcelColumn(0, "hotel.hotelName", "商家名称", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(1, "id", "销量", Cell.CELL_TYPE_STRING));
	    excelColumns.add(new ExcelColumn(2, "orderPrice", "总额", Cell.CELL_TYPE_STRING));
	    //设置头部
	    ExcelHead head = new ExcelHead();
	    head.setRowCount(1); // 模板中头部所占行数
	    head.setColumns(excelColumns);  // 列的定义
	    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
	    ExcelHelper.getInstanse().exportExcelFile(response,head, vos);
	}
	/**
	 * @Title: updateOrder
	 * @Description: 发货之后修改订单
	 * @param model
	 * @return    
	 */
	@Transactional(rollbackFor = Exception.class)
	public XaResult<Boolean> updateOrder(String[] modelId, String address,
			String sentData) throws BusinessException {
		XaResult<Boolean> xr=new XaResult<Boolean>();
		for(String id:modelId){
			String sql="update tb_xa_order o set o.order_status="+JConstant.OrderStatus.UN_CONSIGNEE+",o.express_company="+"'"+address+"'"+",o.express_number="+"'"+sentData+"'"+" where o.id="+Long.parseLong(id)+"";
			this.insert(sql);
		}
		xr.setObject(true);
		return xr;
	}

	//财务统计导出数据
	@Transactional(rollbackFor = Exception.class)
	public void exportdataMatchTickets(Map<String, Object> filterParams,HttpServletResponse response) throws BusinessException {	
		/* 查询列表*/
		
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		
		List<Order> lst = orderRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), Order.class));
		if(lst.size()>0)
		{
			//excel结构
		    List<ExcelColumn> excelColumns = new ArrayList<ExcelColumn>();
		    excelColumns.add(new ExcelColumn(0, "orderNo", "订单编号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(1, "orderNum", "订单数量", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(2, "userName", "联系人", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(3, "mobile", "手机号", Cell.CELL_TYPE_STRING));
		    excelColumns.add(new ExcelColumn(4, "testTime", "兑换时间", Cell.CELL_TYPE_STRING));
		    //设置头部
		    ExcelHead head = new ExcelHead();
		    head.setRowCount(1); // 模板中头部所占行数
		    head.setColumns(excelColumns);  // 列的定义
		    //执行导出,第一个null是response参数，用来输出到浏览器，第二个null是要导出的数据集合
		    ExcelHelper.getInstanse().exportExcelFile(response,head, lst);
		}
	}
	

	@Override
	public XaResult<Order> updateRredeemCode(Long matchId, String redeemCode) throws BusinessException {
		XaResult<Order> xr = new XaResult<Order>();
		Map<String, SearchFilter> filters = new HashMap<String, SearchFilter>();
		filters.put("status", new SearchFilter("status", Operator.NE, XaConstant.Status.delete));
		filters.put("orderNo", new SearchFilter("orderNo", Operator.EQ, redeemCode));
		filters.put("orderType", new SearchFilter("orderType", Operator.EQ, JConstant.ObjectType.MATCHTICKET));
		filters.put("orderStatus", new SearchFilter("orderStatus", Operator.GT, JConstant.OrderStatus.UN_PAY));
		filters.put("orderStatus", new SearchFilter("orderStatus", Operator.LT, JConstant.OrderStatus.CONSIGNEED));
		Order detail = orderRepository.findOne(DynamicSpecifications.bySearchFilter(filters.values(), Order.class));
		if(detail==null)
		{
			xr.error("该兑换码不存在");
			return xr;
		}
		if(detail.getTestTime()!=null && !detail.getTestTime().equals(""))
		{
			xr.error("该兑换码已兑换");
			return xr;
		}
		if(!detail.getObjectId().equals(matchId))
		{
			xr.error("该兑换码不属于该赛事");
			return xr;
		}
		String time=XaUtil.getToDayStr();
		detail.setTestTime(time);
		//核销之后将订单状态改为2（待评价）
		detail.setOrderStatus(JConstant.OrderStatus.UN_COMMENT);
		orderRepository.save(detail);
		xr.setObject(detail);
		return xr;
	}
	
}
