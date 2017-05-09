package com.web.liuda.remote.service.impl;

import java.math.BigDecimal;
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
import com.web.liuda.business.constant.channel.SDKClient;
import com.web.liuda.business.entity.Address;
import com.web.liuda.business.entity.Order;
import com.web.liuda.business.entity.OrderDetail;
import com.web.liuda.business.entity.Shop;
import com.web.liuda.business.entity.Standard;
import com.web.liuda.business.repository.AddressRepository;
import com.web.liuda.business.repository.MatchOrderRepository;
import com.web.liuda.business.repository.OrderRepository;
import com.web.liuda.business.repository.ShopCarRepository;
import com.web.liuda.business.repository.ShopRecordRepository;
import com.web.liuda.business.repository.ShopRepository;
import com.web.liuda.business.repository.StandardRepository;
import com.web.liuda.remote.service.ApiMatchOrderService;
import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.service.ApiOrderService;
import com.web.liuda.remote.vo.AddressVo;
import com.web.liuda.remote.vo.HotelVo;
import com.web.liuda.remote.vo.MacthVo;
import com.web.liuda.remote.vo.OrderVo;
import com.web.liuda.remote.vo.ShopVo;
import com.web.liuda.remote.vo.TicketsVo;
import com.web.liuda.remote.vo.TouristVo;
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
 * @times: 2015-5-15下午06:46:35 类的说明：前端APIOrder接口实现类
 **/
@Service("ApiOrderService")
@Transactional(readOnly = false)
public class ApiOrderServiceImpl extends BaseService<Order> implements
		ApiOrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ShopRecordRepository shopRecordRepository;
	@Autowired
	ShopCarRepository shopCarRepository;
	@Autowired
	ShopRepository shopResitory;
	@Autowired
	StandardRepository standardRepository;
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	ApiMatchService matchService;
	@Autowired
	MatchOrderRepository matchOrderRepository;
	@Autowired
	ApiMatchOrderService matchOrderService;
	
	@Override
	public XaResult<OrderVo> findOne(Long tId) throws BusinessException {
		Order obj = orderRepository.findByIdAndStatusNot(tId,
				XaConstant.Status.delete);
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), OrderVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public XaResult<OrderVo> findOrderDetail(String orderNo, Integer type)
			throws BusinessException {
		String sql = "CALL pro_orderDetail(?,?)";
		List<Object> params = new ArrayList<Object>();
		params.add(orderNo);
		params.add(type);
		List<Object[]> objs = this.queryCall(sql, params);
		OrderVo vo = new OrderVo();
		AddressVo addressVo=new AddressVo();
		/*ShopVo shopVo=new ShopVo();
		StandardVo standardVo=new StandardVo();*/
		// 遍历objs
		for (Object[] obj : objs) {
			if(XaUtil.isEmpty(obj[0])){
				continue;
			}
			vo.setId(((BigInteger) obj[0]).longValue());
			vo.setOrderNo((String) obj[1]);
			//如果是酒店的详情
			if(type == JConstant.ObjectType.HOTEL){
				vo.setOrderNum((Integer) obj[2]);
				vo.setCreateTime((String) obj[3]);
				vo.setUserName((String) obj[4]);
				vo.setMobile((String) obj[5]);
				vo.setOrderStatus((Integer) obj[6]);
				vo.setTotal((Double) obj[9]);
				HotelVo hotel = new HotelVo();
				hotel.setCheckinTime((String) obj[7]);
				hotel.setLeaveTime((String) obj[8]);
				hotel.setHotelName((String) obj[11]);
				hotel.setAddress((String) obj[12]);
				hotel.setTelphone((String) obj[13]);
				hotel.setId(((BigInteger) obj[14]).longValue());
				hotel.setType((String) obj[10]);
				hotel.setGroupBuy((Integer)obj[15]);
				hotel.setGroupPrice(XaUtil.isEmpty(obj[16]) ? null : (Double)obj[16]);
				hotel.setDayNumber(Integer.valueOf(obj[17].toString()));
				hotel.setPrice(XaUtil.isEmpty(obj[18]) ? 0 : (Double)obj[18]);
	    		hotel.setRoomImgForOrder((String)obj[19]);
				vo.setHotel(hotel);
				
			}else if(type == JConstant.ObjectType.TOURIST){
				vo.setOrderNum((Integer)obj[2]);
				vo.setCreateTime((String)obj[3]);
				vo.setUserName((String)obj[4]);
				vo.setMobile((String)obj[5]);
				vo.setOrderStatus((Integer)obj[6]);
				vo.setTotal((Double)obj[7]);
				TouristVo tvo=new TouristVo();
				tvo.setId(XaUtil.isEmpty(obj[8])?null:((BigInteger)obj[8]).longValue());
				tvo.setTouristName((String)obj[9]);
				tvo.setAddress((String)obj[10]);
				tvo.setMobile((String)obj[11]);
				vo.setTouristVo(tvo);
				TicketsVo tkvo=new TicketsVo();
				tkvo.setId(XaUtil.isEmpty(obj[12])?null:((BigInteger) obj[12]).longValue());
				tkvo.setImgUrl((String) obj[13]);
				tkvo.setPrice(XaUtil.isEmpty(obj[14])?0:(Double) obj[14]);
				tkvo.setGroupBuy((Integer) obj[15]);
				tkvo.setTicketName((String) obj[16]);
				tkvo.setGroupPrice(XaUtil.isEmpty(obj[17])?null:(Double) obj[17]);
				vo.setTicketsVo(tkvo);
			}else if(type == JConstant.ObjectType.SHOP){
			    vo.setCreateTime((String) obj[2]);
			    vo.setTotal((Double)obj[3]);
			    vo.setOrderStatus(XaUtil.isEmpty(obj[4]) ? 0 : (Integer)obj[4]);
			    //商品地址
			    addressVo.setProvince((String) obj[5]);
			    addressVo.setCity((String) obj[6]);
			    addressVo.setArea((String) obj[7]);
			    addressVo.setDetailAddress((String) obj[8]);
			    addressVo.setConsigneeName((String) obj[9]);
			    addressVo.setMobile((String) obj[10]);
			    vo.setAddressVo(addressVo);
			    //商品信息集合
			    List<ShopVo> shops = new ArrayList<ShopVo>();
				if(XaUtil.isNotEmpty(obj[11])){
					String[] ids = ((String)obj[11]).split(",");
					for(int i=0;i<ids.length;i++){
						ShopVo shop = new ShopVo();
						shop.setId(Long.valueOf(ids[i]));
						shop.setShopNumber(Integer.valueOf(((String)obj[12]).split(",")[i]));
						shop.setShopName(((String)obj[13]).split(",")[i]);
						shop.setImgUrl(((String)obj[14]).split(",")[i]);
						shop.setPorperty(((String)obj[15]).split(",")[i]);
						//shop.setPrice(Double.valueOf(((String)obj[16]).split(",")[i]));
						/*shop.setGroupBuy(Integer.valueOf(((String)obj[17]).split(",")[i]));
						if(Integer.valueOf(((String)obj[17]).split(",")[i]) == JConstant.BooleanStatus.TRUE){
							String[] gs = ((String)obj[18]).split(",");
							for(int j=0;j<gs.length;j++){
								shop.setGroupPrice(Double.valueOf(gs[j]));
							}
						}*/
						
						//从订单详情中获取的价格
						if(XaUtil.isNotEmpty(obj[16])){
							shop.setPrice(XaUtil.isEmpty(((String)obj[16]).split(",")[i])?null:((obj[16].toString().split(",")[i]).toString().equals("-1") ? null : Double.valueOf(((String)obj[16]).split(",")[i])));
						}else{
							shop.setPrice(null);
						}
						//从订单详情中获取的团购价格
						if(XaUtil.isNotEmpty(obj[18])){
							shop.setGroupPrice(XaUtil.isEmpty(((String)obj[18]).split(",")[i])?null:((obj[18].toString().split(",")[i]).toString().equals("-1") ? null : Double.valueOf(((String)obj[18]).split(",")[i])));
							
						}else{
							shop.setGroupPrice(null);
						}
						//团购价格不为空
						shop.setGroupBuy(XaUtil.isEmpty(shop.getGroupPrice()) ? JConstant.BooleanStatus.FALSE:JConstant.BooleanStatus.TRUE);
						if(XaUtil.isNotEmpty(obj[18])){
							shop.setStandardId(XaUtil.isEmpty(((String)obj[21]).split(",")[i])?null:((obj[21].toString().split(",")[i]).toString().equals("-1") ? null : Long.valueOf(((String)obj[21]).split(",")[i])));
						}else{
							shop.setStandardId(null);
						}
						
						shops.add(shop);
					}
					vo.setShopVo(shops);
				}
			}else if(type == JConstant.ObjectType.MATCHTICKET){//赛事门票
				vo.setId(Long.parseLong(obj[0].toString()));
				vo.setOrderNo((String)obj[1]);
				vo.setOrderNum((Integer)obj[2]);
				vo.setCreateTime((String)obj[3]);
				vo.setUserName((String)obj[4]);
				vo.setMobile((String)obj[5]);
				vo.setOrderStatus((Integer)obj[6]);
				vo.setTotal((Double)obj[7]);
				vo.setPrice((Double)obj[8]);
				//赛事门票明细
				MacthVo mvo=new MacthVo();
				mvo.setId(Long.parseLong(obj[9].toString()));
				mvo.setMatchStatus((Integer)obj[10]);
				mvo.setMatchType((Integer)obj[11]);
				mvo.setTitle((String)obj[12]);
				mvo.setTicketImages((String)obj[13]);
				mvo.setTicketName((String)obj[14]);
				mvo.setTicketPrice(obj[15]==null?null:Double.parseDouble(obj[15].toString()));
				mvo.setTicketStockNum((Integer)obj[16]);
				mvo.setTicketTotleNum((Integer)obj[17]);
				mvo.setFavourablePrice(obj[18]==null?null:Double.parseDouble(obj[18].toString()));
				mvo.setFavStartTime((String)obj[19]);
				mvo.setFavEndTime((String)obj[20]);
				mvo.setAddress((String)obj[21]);
				mvo.setAreaCode((String)obj[22]);
				vo.setMacthVo(mvo);
			}
		}
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		xr.setObject(vo);
		return xr;
	}

	/**
	 * author:常璐
	 * time:2015-12-31 14:00:00
	 * 查询我的订单列表(订单类型为酒店和景点时使用)
	 * param: nextPage 下一页
	 * param: pageSize 页面大小
	 * param: userId 用户ID
	 * param: orderStatus 订单状态（0：未支付；1：已支付(待消费、待发货)；2：待评价；3：已评价；4：待收货；5：已收货）
	 * param: orderType  订单类型 （0：酒店；1：景点；3，赛事门票）
	 * return <Page<OrderVo>
	 */
	@Override
	public XaResult<Page<OrderVo>> findListByUserId(Integer nextPage,
			Integer pageSize, Long userId, Integer orderStatus,Integer orderType)
			throws BusinessException {
		XaResult<Page<OrderVo>> xr = new XaResult<Page<OrderVo>>();
		List<Object> params = new ArrayList<Object>();
		List<Object> paramsCount = new ArrayList<Object>();
		String sql = "CALL pro_orderlist(?,?,?,?,?)";
		String countSql = "call pro_countorderlist(?,?,?)";
		params.add(orderType);
		params.add(orderStatus);
		params.add(userId);
		params.add(nextPage * pageSize);
		params.add(pageSize);
		paramsCount.add(orderStatus);
		paramsCount.add(userId);
		paramsCount.add(orderType);
		List<Object[]> objs = this.queryCall(sql, params);
		List<Object[]> count = this.queryCall(countSql, paramsCount);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		for (Object[] obj : objs) {
			// 创建OrderVo对象vo
			OrderVo vo = new OrderVo();
			// 遍历obj集合，将其放入vo中
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setOrderNum((Integer)obj[1]);
			vo.setCreateTime((String)obj[4]);
			vo.setObjectId(((BigInteger)obj[5]).longValue());
			vo.setUserName((String) obj[6]);
			vo.setMobile((String)obj[7]);
			//订单类型为酒店
			if(orderType==JConstant.ObjectType.HOTEL){
				HotelVo hotel = new HotelVo();
				hotel.setCheckinTime((String)obj[2]);
				hotel.setLeaveTime((String)obj[3]);
				hotel.setHotelName((String)obj[12]);
				hotel.setAddress((String)obj[13]);
				hotel.setTelphone((String)obj[14]);
				hotel.setId(XaUtil.isEmpty(obj[16])? null:((BigInteger)obj[16]).longValue());
				hotel.setType((String)obj[10]);
				hotel.setDayNumber(Integer.valueOf(obj[8].toString()));
				//团购信息
				if((Integer)obj[19] == JConstant.BooleanStatus.TRUE){
					hotel.setGroupBuy(JConstant.BooleanStatus.TRUE);
					hotel.setGroupPrice(XaUtil.isEmpty(obj[20]) ? null : (Double)obj[20]);
				}else{
					hotel.setGroupBuy(JConstant.BooleanStatus.FALSE);
				}
				if(XaUtil.isEmpty(obj[9])){
					vo.setTotal(0.00);
		    	}else{
		    		double f= ((Double)obj[9]).doubleValue();  
		    		BigDecimal b = new BigDecimal(f);  
		    		//保留两位小数
		    		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		    		vo.setTotal(f1);
		    	}
				if(XaUtil.isEmpty(obj[17])){
		    		hotel.setPrice(0.00);
		    	}else{
		    		double a=((Double)obj[17]).doubleValue();
					BigDecimal b = new BigDecimal(a);  
		    		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		    		hotel.setPrice(f1);
		    	}
				hotel.setRoomImgForOrder((String)obj[11]);
				vo.setHotel(hotel);
				vo.setOrderNo((String)obj[15]);
				vo.setOrderStatus((Integer)obj[18]);
			}
			//订单类型为景点
			if(orderType==JConstant.ObjectType.TOURIST){
				TouristVo touristVo = new TouristVo();
				if(XaUtil.isNotEmpty(obj[8])){
					double f= ((Double)obj[8]).doubleValue();  
		    		BigDecimal b = new BigDecimal(f);  
		    		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		    		vo.setTotal(f1);
				}
	    		
	    		touristVo.setTouristName((String)obj[9]);
	    		touristVo.setId(((BigInteger)obj[15]).longValue());
	    		//团购信息
				if((Integer)obj[16] == JConstant.BooleanStatus.TRUE){
					touristVo.setGroupBuy(JConstant.BooleanStatus.TRUE);
					touristVo.setGroupPrice(XaUtil.isEmpty(obj[17]) ? null : (Double)obj[17]);
				}else{
					touristVo.setGroupBuy(JConstant.BooleanStatus.FALSE);
				}
	    		
	    		if(XaUtil.isEmpty(obj[13])){
	    			touristVo.setPrice(0.00);
		    	}else{
		    		double a=((Double)obj[13]).doubleValue();
					BigDecimal b1 = new BigDecimal(a);  
		    		double f2= b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		    		touristVo.setPrice(f2);
		    	}
	    		vo.setTouristVo(touristVo);
	    		TicketsVo ticketsVo=new TicketsVo();
	    		ticketsVo.setTicketName((String)obj[10]);
	    		ticketsVo.setImgUrl((String)obj[11]);
	    		vo.setTicketsVo(ticketsVo);
	    		vo.setOrderNo((String)obj[12]);
	    		vo.setOrderStatus((Integer)obj[14]);
			}
			//订单类型为赛事门票
			if(orderType==JConstant.ObjectType.MATCHTICKET){
				if(XaUtil.isNotEmpty(obj[8])){
					double f= ((Double)obj[8]).doubleValue();  
		    		BigDecimal b = new BigDecimal(f);  
		    		double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue(); 
		    		vo.setTotal(f1);
				}
	    		vo.setOrderNo((String)obj[9]);
	    		vo.setOrderStatus((Integer)obj[10]);
	    		vo.setPrice((Double)obj[11]);
	    		// o.id,//o.order_num,o.checkin_time,o.leave_time,o.create_time,o.object_id,
	    		// o.user_name,o.mobile,o.order_price, o.order_no,o.order_status,o.price,
	    		// m.id,m.title,m.ticket_name,m.ticket_images,m.ticket_price
	    		MacthVo macthVo = new MacthVo();
				macthVo.setId((Long.parseLong(obj[12].toString())));
				macthVo.setTitle((String)obj[13]);
				macthVo.setTicketName((String)obj[14]);
				macthVo.setTicketImages((String)obj[15]);
				macthVo.setTicketPrice(obj[16]==null?null:Double.parseDouble(obj[16].toString()));
	    		vo.setMacthVo(macthVo);
			}
			// 将vo添加到vos集合中
			vos.add(vo);
		}
		Page<OrderVo> page = new MyPage<OrderVo>(nextPage, pageSize, vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;

	}
	/**
	 * author:常璐
	 * time:2015-12-31 14:00:00
	 * 查询我的订单列表(订单类型为商品时使用)
	 * param: nextPage 下一页
	 * param: pageSize 页面大小
	 * param: userId 用户ID
	 * param: orderStatus 订单状态（0：未支付；1：已支付(待消费、待发货)；2：待评价；3：已评价；4：待收货；5：已收货）
	 * param: orderType  订单类型 （2：商品；）
	 * return <Page<OrderVo>
	 */
	@Override
	public XaResult<Page<OrderVo>> findShopOrderListByUserId(Integer nextPage,
			Integer pageSize, Long userId, Integer orderStatus,Integer orderType)
			throws BusinessException {
		XaResult<Page<OrderVo>> xr = new XaResult<Page<OrderVo>>();
		List<Object> params = new ArrayList<Object>();
		List<Object> paramsCount = new ArrayList<Object>();
		String sql = "CALL pro_shop_orderlist(?,?,?,?,?)";
		String countSql = "call pro_countorderlist(?,?,?)";
		params.add(orderType);
		params.add(orderStatus);
		params.add(userId);
		params.add(nextPage * pageSize);
		params.add(pageSize);
		paramsCount.add(orderStatus);
		paramsCount.add(userId);
		paramsCount.add(orderType);
		List<Object[]> objs = this.queryCall(sql, params);
		List<Object[]> count = this.queryCall(countSql, paramsCount);
		List<OrderVo> vos = new ArrayList<OrderVo>();
		for (Object[] obj : objs) {
			// 创建OrderVo对象vo
			OrderVo vo = new OrderVo();
			vo.setId(((BigInteger)obj[0]).longValue());
			vo.setOrderNo((String)obj[1]);
			vo.setOrderStatus((Integer)obj[2]);
			vo.setTotal((Double)obj[3]);
			vo.setCreateTime((String)obj[4]);
			List<ShopVo> shops = new ArrayList<ShopVo>();
			if(XaUtil.isNotEmpty(obj[5])){
				String[] ids = ((String)obj[5]).split(",");
				for(int i=0;i<ids.length;i++){
					ShopVo shop = new ShopVo();
					shop.setId(Long.valueOf(ids[i]));
					if(XaUtil.isNotEmpty(obj[6])){
						shop.setShopNumber(XaUtil.isEmpty(((String)obj[6]).split(",")[i])?null:((obj[6].toString().split(",")[i]).toString().equals("-1") ? null : Integer.valueOf(((String)obj[6]).split(",")[i])));
					}else{
						shop.setShopNumber(null);
					}
					//shop.setShopNumber(XaUtil.isEmpty(obj[6]) ? null : Integer.valueOf(((String)obj[6]).split(",")[i]));
					shop.setShopName(XaUtil.isEmpty(obj[7]) ? null : ((String)obj[7]).split(",")[i]);
					shop.setImgUrl(XaUtil.isEmpty(obj[8]) ? null : ((String)obj[8]).split(",")[i]);
					shop.setPorperty(XaUtil.isEmpty(obj[9]) ? null : ((String)obj[9]).split(",")[i]);
					//shop.setPrice(XaUtil.isEmpty(obj[10]) ? null : Double.valueOf(((String)obj[10]).split(",")[i]));
					//从订单详情中获取的价格
					if(XaUtil.isNotEmpty(obj[11])){
						shop.setPrice(XaUtil.isEmpty(((String)obj[11]).split(",")[i])?null:((obj[11].toString().split(",")[i]).toString().equals("-1") ? null : Double.valueOf(((String)obj[11]).split(",")[i])));
					}else{
						shop.setPrice(null);
					}
					//shop.setPrice(XaUtil.isEmpty(obj[11]) ? null : Double.valueOf(((String)obj[11]).split(",")[i]));
					//从订单详情中获取的团购价格
					if(XaUtil.isNotEmpty(obj[12])){
//						shop.setGroupPrice(XaUtil.isEmpty(((String)obj[12]).split(",")[i])?null:Double.valueOf(((String)obj[12]).split(",")[i]));
						shop.setGroupPrice(XaUtil.isEmpty(((String)obj[12]).split(",")[i])?null:((obj[12].toString().split(",")[i]).toString().equals("-1") ? null : Double.valueOf(((String)obj[12]).split(",")[i])));
						
					}else{
						shop.setGroupPrice(null);
					}
					//团购价格不为空
					shop.setGroupBuy(XaUtil.isEmpty(shop.getGroupPrice()) ? JConstant.BooleanStatus.FALSE:JConstant.BooleanStatus.TRUE);
					//shop.setGroupPrice(XaUtil.isEmpty(obj[12]) ? null : Double.valueOf(((String)obj[12]).split(",")[i]));
					if(XaUtil.isNotEmpty(obj[13])){
						shop.setStandardId(XaUtil.isEmpty(((String)obj[13]).split(",")[i])?null:((obj[13].toString().split(",")[i]).toString().equals("-1") ? null : Long.valueOf(((String)obj[13]).split(",")[i])));
					}else{
						shop.setStandardId(null);
					}
					shops.add(shop);
				}
				vo.setShopVo(shops);
			}
			// 将vo添加到vos集合中
			vos.add(vo);
		}
		Page<OrderVo> page = new MyPage<OrderVo>(nextPage, pageSize, vos, Integer.valueOf(count.get(0) + ""));
		xr.setObject(page);
		return xr;

	}
	@Override
	public XaResult<List<OrderVo>> findListNEStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if (status == null) {// 默认显示非删除的所有数据
			status = XaConstant.Status.delete;
		}
		filters.put("status", new SearchFilter("status", Operator.NE, status));
		Page<Order> page = orderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Order.class), pageable);
		XaResult<List<OrderVo>> xr = new XaResult<List<OrderVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()),
				OrderVo.class));
		for (int i = 0; i < page.getContent().size(); i++) {
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<List<OrderVo>> findListEQStatusByFilter(Integer status,
			Map<String, Object> filterParams, Pageable pageable)
			throws BusinessException {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);
		if (status == null) {// 默认显示正常数据
			status = XaConstant.Status.valid;
		}
		filters.put("status", new SearchFilter("status", Operator.EQ, status));
		Page<Order> page = orderRepository.findAll(DynamicSpecifications
				.bySearchFilter(filters.values(), Order.class), pageable);
		XaResult<List<OrderVo>> xr = new XaResult<List<OrderVo>>();
		xr.setObject(JSON.parseArray(JSON.toJSONString(page.getContent()),
				OrderVo.class));
		for (int i = 0; i < page.getContent().size(); i++) {
			xr.getObject().get(i).setId(page.getContent().get(i).getId());
		}
		return xr;
	}

	@Override
	public XaResult<OrderVo> multiOperate(String modelIds, Integer status)
			throws BusinessException {
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		if (status == null) {
			status = XaConstant.Status.delete;
		}
		if (modelIds != null) {
			String[] ids = modelIds.split(",");
			for (String id : ids) {
				Order obj = orderRepository.findByIdAndStatusNot(
						Long.parseLong(id), status);
				if (XaUtil.isNotEmpty(obj)) {
					obj.setStatus(status);
					obj = orderRepository.save(obj);
					xr.setObject(JSON.parseObject(JSON.toJSONString(obj),
							OrderVo.class));
					xr.getObject().setId(obj.getId());
				} else {
					throw new BusinessException(
							XaConstant.Message.object_not_find);
				}
			}
		}
		return xr;
	}
	/**
	 * author:常璐
	 * time:2015-12-30 14:00:00
	 * 新增一条订单
	 * 
	 */
	@Override
	@Transactional(readOnly = true, rollbackFor = Exception.class)
	public XaResult<OrderVo> createModel(Order model) throws BusinessException {
		XaResult<OrderVo> xr = new XaResult<OrderVo>(); 
		Order obj = orderRepository.save(model);
		xr.setObject(JSON.parseObject(JSON.toJSONString(obj), OrderVo.class));
		if(XaUtil.isNotEmpty(obj)){
			if(obj.getOrderType()==JConstant.ObjectType.MATCHTICKET)
			{
				//赛事修改库存
				matchService.updateTicketStockNum(model.getObjectId(), (-1)*model.getOrderNum());
			}
			xr.getObject().setId(obj.getId());
			xr.getObject().setTotal(obj.getOrderPrice());
			xr.getObject().setOrderPrice(obj.getOrderPrice() * 100);
		}
		return xr;
	}
	
	@Override
	public XaResult<OrderVo> updateModel(Order model) throws BusinessException {
		Order obj = orderRepository.findOne(model.getId());
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		if (XaUtil.isNotEmpty(obj)) {
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
			xr.setObject(JSON.parseObject(JSON.toJSONString(obj), OrderVo.class));
			xr.getObject().setId(obj.getId());
		} else {
			throw new BusinessException(XaConstant.Message.object_not_find);
		}
		return xr;
	}

	@Override
	public String modifyOrder(String orderNo, String price,String paymentNo) {
		try{
			Order order = orderRepository.findByOrderNoAndStatusNot(orderNo,
					XaConstant.Status.delete);
			if (XaUtil.isNotEmpty(order) && order.getOrderStatus() == 1) {
				return "0001"; // 0001代表已经支付过
			}
			if (XaUtil.isNotEmpty(order) && order.getOrderStatus() == 0) {
				order.setOrderStatus(JConstant.OrderStatus.PAYED);
				orderRepository.save(order);
				// 发送短信通知
				if(order.getOrderType() == JConstant.ObjectType.HOTEL){
					List<Object> params = new ArrayList<Object>();
					String sql = "SELECT h.hotel_name,h.address,h.telphone,r.type FROM tb_xa_order o INNER JOIN tb_xa_room r ON o.object_id=r.id INNER JOIN tb_xa_hotel h ON r.hotel_id=h.id WHERE o.order_type=0 AND o.order_no=?";
					params.add(orderNo);
					List<Object[]> objs = this.queryParams(sql.toString(), null, null,
							params);
					if (XaUtil.isNotEmpty(objs) && objs.size() == 1) {
						StringBuffer content = new StringBuffer();
						content.append("【蹓跶蹓跶】您好，您的酒店已预定成功！酒店名：");
						content.append(objs.get(0)[0]).append("，房型：")
						.append(objs.get(0)[3]).append("，入住时间：");
						content.append(order.getCheckinTime());
						content.append("，酒店地址：").append(objs.get(0)[1]);
						content.append("，电话：").append(objs.get(0)[2]).append("，兑换码：").append(order.getOrderNo());
						SDKClient.sendContent(order.getMobile(), content.toString());
					}
				}else if(order.getOrderType() == JConstant.ObjectType.TOURIST){
					List<Object> params = new ArrayList<Object>();
					String sql = "SELECT ts.tourist_name,ts.address,ts.telphone,t.ticket_name FROM tb_xa_order o INNER JOIN tb_xa_tickets t ON o.object_id=t.id INNER JOIN tb_xa_tourist ts ON t.tourist_id=ts.id WHERE o.order_type=1 AND o.order_no=?";
					params.add(orderNo);
					List<Object[]> objs = this.queryParams(sql.toString(), null, null,
							params);
					if (XaUtil.isNotEmpty(objs) && objs.size() == 1) {
						StringBuffer content = new StringBuffer();
						content.append("【蹓跶蹓跶】您好，您的景点已预定成功！景点名：");
						content.append(objs.get(0)[0]).append("，票型：").append(objs.get(0)[3]).append("，");
						content.append("景点地址：").append(objs.get(0)[1]);
						content.append("，电话：").append(objs.get(0)[2]).append("，兑换码：").append(order.getOrderNo());
						SDKClient.sendContent(order.getMobile(), content.toString());
					}
				}else if(order.getOrderType() == JConstant.ObjectType.SHOP){
					//库存量减少
					List<Object[]> standards=standardRepository.findByOrderNo(orderNo);
					StringBuffer insertSql=new StringBuffer("insert into tb_xa_shopRecord (shop_id,standard_id,user_id,buy_number,create_time) values");
					for(Object[] standardObj:standards){
						Standard standard=new Standard();
						int a=((Integer)standardObj[0])-(Integer)standardObj[3];
						standard.setStocks(a);
						standard.setId(((BigInteger)standardObj[1]).longValue());
						//更新库存量
//						String updateSql="update tb_xa_standard set stocks="+a+" where id="+((BigInteger)standardObj[1]).longValue();
//						this.delete(updateSql, null);
						insertSql.append("("+((BigInteger)standardObj[2]).longValue()+","+((BigInteger)standardObj[1]).longValue()+","+((BigInteger)standardObj[4]).longValue()+","+(Integer)standardObj[3]+",'"+XaUtil.getToDayStr()+"'),");
					}
					String s=insertSql.toString();
					int indx = s.lastIndexOf(",");
					if(indx!=-1){
						s = s.substring(0,indx)+s.substring(indx+1,s.length());
					}
					//商品购买记录中加入数据
					this.insert(s);
					
					List<Object> params = new ArrayList<Object>();
					String sql = "SELECT s.shop_name,stan.porperty FROM tb_xa_orderdetail od "
								+"INNER JOIN tb_xa_shop s ON od.shop_id=s.id "
								+"INNER JOIN tb_xa_standard stan ON stan.id=od.standard_id "
								+"WHERE od.order_no=?";
					params.add(orderNo);
					List<Object[]> objs = this.queryParams(sql.toString(), null, null,params);
					if (XaUtil.isNotEmpty(objs)){
						StringBuffer content = new StringBuffer();
						content.append("【蹓跶蹓跶】您好，您的商品已购买成功！商品名：");
						for(Object[] shopObj:objs){
							content.append(shopObj[0]).append("，规格：").append(shopObj[1]).append("，");
						}
						content.append("订单号：").append(order.getOrderNo());
						Address add=addressRepository.findOne(order.getAddressId());
						SDKClient.sendContent(add.getMobile(), content.toString());
					}
				}else if(order.getOrderType() == JConstant.ObjectType.MATCHTICKET){
					MacthVo mvo = matchService.findOne(order.getObjectId()).getObject();
					if (XaUtil.isNotEmpty(mvo)) {
						StringBuffer content = new StringBuffer();
						content.append("【蹓跶蹓跶】您好，您的赛事门票已预定成功！赛事名：");
						content.append(mvo.getTitle()).append("，票型：").append(mvo.getTicketName()).append("，");
						content.append("赛事地址：").append(mvo.getAreaCode()).append(mvo.getAddress());
						content.append("，时间：").append(mvo.getStartdate()+" - "+mvo.getEnddate()).append("，兑换码：").append(order.getOrderNo());
						SDKClient.sendContent(order.getMobile(), content.toString());
					}
				}
				return "0000"; // 支付成功后状态修改成功
			}
			//赛事俱乐部活动订单
			if(XaUtil.isEmpty(order))
			{
				return matchOrderService.modifyOrder1(orderNo, price, paymentNo);
			}
		}catch(Exception e){
			return "0002"; // 支付成功后状态修改失败
		}
		return null;
	}

	@Override
	public XaResult<OrderVo> shopSaveOrder(Order order,List<OrderDetail> details,String carIds) {
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		/*//判断商品库存是否充足
		for(OrderDetail detail : details){
			String cSql = "select s.shop_name,sd.porperty,sd.stocks from tb_xa_shop s inner join tb_xa_standard sd on s.id=sd.shop_id where sd.id=" + detail.getStandardId();
			List<Object[]> objs = this.query(cSql, null, null);
			if(XaUtil.isNotEmpty(objs) && objs.size() > 0){
				if((Integer)objs.get(0)[2] < detail.getShopNumber()){
					xr.error("“" + objs.get(0)[1] + "”的“" + objs.get(0)[0] + "”商品库存量不足！");
					return xr;
				}
			}
		}*/
		/*判断商品库存是否充足
		    判断购买的商品数量是否超过限购个数*/
		for(OrderDetail detail : details){
			String cSql = "select s.shop_name,sd.porperty,sd.stocks,s.limit_number from tb_xa_shop s inner join tb_xa_standard sd on s.id=sd.shop_id where sd.id=" + detail.getStandardId();
			List<Object[]> objs = this.query(cSql, null, null);
			//定义一个变量保存购买的商品数量
			Integer num=0;
			for(OrderDetail detail1 : details){
				//查询订单中该商品的购买数量
				if(detail1.getShopId()==detail.getShopId()){
					//累计商品数量
					num=num+detail1.getShopNumber();
				}
				
			}
			
			if(XaUtil.isNotEmpty(objs) && objs.size() > 0){
				//库存不足
				if((Integer)objs.get(0)[2] < detail.getShopNumber()){
					xr.error("“" + objs.get(0)[1] + "”的“" + objs.get(0)[0] + "”商品库存量不足！");
					return xr;
				}
				//购买的商品数量超过限购个数
				if(XaUtil.isNotEmpty(objs.get(0)[3])&&(Integer)objs.get(0)[3] < num){
					xr.error("“" + objs.get(0)[0] + "”限购“" + objs.get(0)[3] + "”个！");
					return xr;
					
				}
			}
			
		}
		
		Order o = orderRepository.save(order);
		//商品id集合
		List<Long> shopids = new ArrayList<Long>();
		//如果订单保存成功，保存订单明细信息
		if(XaUtil.isNotEmpty(o)){
			//更改库存量
			for(OrderDetail detail : details){
				String uSql = "update tb_xa_standard sd set sd.stocks=sd.stocks-" + detail.getShopNumber() + " where sd.id=" + detail.getStandardId();
				this.insert(uSql);
			}
			StringBuffer sql = new StringBuffer();
			String dateStr = XaUtil.getToDayStr();
			sql.append("insert into tb_xa_orderdetail(create_time,order_no,shop_id,shop_number,standard_id,price,group_price) values ");
			for(int i=0;i<details.size();i++){
				//查询该规格商品的价格
				Standard standard=standardRepository.findByIdAndStatusNot(details.get(i).getStandardId(),XaConstant.Status.delete);
				//查询该规格商品的团购价格
				System.out.println(XaUtil.getToDayStr().substring(0, 10));
				Standard standards=standardRepository.findByIdAndValidityGreaterThanEqualAndStatusNotAndGroupBuy(details.get(i).getStandardId(),XaUtil.getToDayStr().substring(0, 10),XaConstant.Status.delete,JConstant.BooleanStatus.TRUE);
				sql.append("('").append(dateStr).append("','").append(o.getOrderNo()).append("',").append(details.get(i).getShopId()).append(",").append(details.get(i).getShopNumber()).append(",").append(details.get(i).getStandardId()).append(",").append(standard.getPrice()).append(",").append(XaUtil.isEmpty(standards) ? null :XaUtil.isEmpty(standards.getGroupPrice())?null:standards.getGroupPrice()).append(")");
				sql.append("");
				if(i != details.size() - 1){
					sql.append(",");
				}
				shopids.add(details.get(i).getShopId());
			}
			//System.out.println(sql.toString());
			this.insert(sql.toString());
			//查询商品名称
			List<Shop> shops = shopResitory.findByIdIn(shopids);
			List<ShopVo> vos = new ArrayList<ShopVo>();
			for(Shop shop : shops){
				ShopVo vo = new ShopVo();
				vo.setShopName(shop.getShopName());
				vos.add(vo);
			}
			//如果是购物车下单，成功后清除购物车数据
			if(XaUtil.isNotEmpty(carIds)){
				String[] ids = carIds.replaceAll("，", ",").split(",");
				StringBuffer delSql = new StringBuffer("delete from tb_xa_shopcar where id in ");
				for(int i=0;i<ids.length;i++){
					if(i == 0){
						delSql.append("(");
					}
					delSql.append(Long.valueOf(ids[i]));
					if(i != ids.length - 1){
						delSql.append(",");
					}
					if(i == ids.length - 1){
						delSql.append(")");
					}
				}
				this.delete(delSql.toString(), null);
				
			}
			xr.setObject(JSON.parseObject(JSON.toJSONString(o), OrderVo.class));
			xr.getObject().setTotal(order.getPrice());
			xr.getObject().setOrderPrice(order.getPrice() * 100);
			xr.getObject().setShopVo(vos);
		}else{
			xr.error("下单失败");
		}
		return xr;
	}
	/*
	 * 确认收货
	 * author：changlu
	 */
	@Override
	public XaResult<Boolean> receiveConfirmation(String orderNo) throws BusinessException {
		XaResult<Boolean> xr=new XaResult<Boolean>();
		String sql="update tb_xa_order o set o.order_status="+JConstant.OrderStatus.UN_COMMENT+" where o.order_no="+orderNo;
		this.insert(sql);
		xr.setObject(true);
		return xr;
	}
	
	/*
	 * 获取物流
	 * author：changlu
	 */
	@Override
	public XaResult<OrderVo> getTransport(String orderNo) throws BusinessException {
		XaResult<OrderVo> xr=new XaResult<OrderVo>();
		String sql="select o.express_company,o.express_number from tb_xa_order o where o.order_no=? and o.status<>"+XaConstant.Status.delete;
		List<Object> params=new ArrayList<Object>();
		params.add(orderNo);
		List<Object[]> objs=this.queryParams(sql, null, null, params);
		OrderVo vo=new OrderVo();
		vo.setExpressCompany((String)objs.get(0)[0]);
		vo.setExpressNumber((String)objs.get(0)[1]);
		xr.setObject(vo);
		return xr;
	}
	
	public static void main(String[] args) {
		System.out.println(Double.valueOf("-1"));
	}
}
