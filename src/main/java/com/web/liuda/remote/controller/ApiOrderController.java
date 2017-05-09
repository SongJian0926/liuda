package com.web.liuda.remote.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.web.liuda.business.constant.JConstant;
import com.web.liuda.business.entity.Order;
import com.web.liuda.business.entity.OrderDetail;
import com.web.liuda.remote.service.ApiMatchService;
import com.web.liuda.remote.service.ApiOrderService;
import com.web.liuda.remote.vo.MacthVo;
import com.web.liuda.remote.vo.OrderVo;
import com.web.liuda.remote.vo.ShopCarVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.GetRandomOrderno;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiOrderController.java
 * @Package com.web.liuda.remote.controller
 * @Description: 订单信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "Order", description = "订单接口", position = 10)
@Controller
@RequestMapping("/api/order")
public class ApiOrderController extends BaseController {

	@Autowired
	private ApiOrderService orderService;
	@Autowired
	private ApiMatchService matchService;
	
	
	/**
	 * @Title: findOrderById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="订单详情",notes="查询订单详细信息,当返回的code=1时，取出object进行显示,存放为单体对象")
	@ResponseBody
	@RequestMapping(value="findOrderById",method=RequestMethod.POST)
	public XaResult<OrderVo> findOrderById(
		@ApiParam("订单编号:orderNo,必填") @RequestParam(value = "orderNo") String orderNo,
		@ApiParam("订单类型,字段名:orderType ,必填 （0：酒店、1：景点、2：商品、3：赛事门票）") @RequestParam(value = "orderType") Integer orderType
	) throws BusinessException{
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		if(XaUtil.isEmpty(orderNo)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(orderType)){
			xr.error("订单类型不能为空！");
			return xr;
		}
		return orderService.findOrderDetail(orderNo,orderType);
	}
	
	/**
	 * @Title: findOrderList
	 * @Description: 根据用户ID查找订单列表
	 * @param modelId
	 * @return    
	 */
	@ApiOperation(value="查询我的订单",notes="查询我的订单,当返回的code=1时，取出object进行显示,存放为Page对象")
	@ResponseBody
	@RequestMapping(value="findOrderList",method=RequestMethod.POST)
	public XaResult<Page<OrderVo>> findOrderByUserId(
		@ApiParam("页号,字段名:nextPage,默认0,从第0页开始") @RequestParam(defaultValue = "0") Integer nextPage,
		@ApiParam("页长,字段名:pageSize,默认10") @RequestParam(defaultValue = "10") Integer pageSize,
		@ApiParam("用户编号,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("订单类型,字段名:orderType,0：酒店；1：景点；2：商品；3：赛事门票；") @RequestParam(value="orderType") Integer orderType,
		@ApiParam("订单状态,字段名:orderStatus,0：未支付；1：已支付(待消费、待发货)；2：待评价；3：已评价；4：待收货；5：已收货；6：失效") @RequestParam(required=false) Integer orderStatus
	) throws BusinessException{
		XaResult<Page<OrderVo>> xr = new XaResult<Page<OrderVo>>();
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(orderType)){
			xr.error("订单类型不能为空！");
			return xr;
		}
		//判断订单状态是否为空
		if(XaUtil.isNotEmpty(orderStatus)){
			if(!(orderStatus==JConstant.OrderStatus.UN_PAY||orderStatus==JConstant.OrderStatus.UN_CONSIGNEE||orderStatus==JConstant.OrderStatus.UN_COMMENT||orderStatus==JConstant.OrderStatus.PAYED||orderStatus==JConstant.OrderStatus.CONSIGNEED||orderStatus==JConstant.OrderStatus.COMMENTED)){
				xr.error("订单状态只能是0，1，2，3，4，5！");
				return xr;
			}
		}
		//判断订单类型是否为空
		if(XaUtil.isNotEmpty(orderType)){
			if(!(orderType==JConstant.ObjectType.HOTEL||orderType==JConstant.ObjectType.TOURIST||orderType==JConstant.ObjectType.SHOP||orderType==JConstant.ObjectType.MATCHTICKET)){
				xr.error("订单类型只能是0，1，2，3！");
				return xr;
			}
			if(orderType==JConstant.ObjectType.SHOP){
				return orderService.findShopOrderListByUserId(nextPage,pageSize, userId, orderStatus,orderType);	
			}
		}
		return orderService.findListByUserId(nextPage,pageSize, userId, orderStatus,orderType);	
	}
	
	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="预定(酒店和景点下单接口)",notes="预定(酒店和景点下单接口),当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveOrder",method=RequestMethod.POST)
	public XaResult<OrderVo> saveOrder(
		@ApiParam("对象Id,字段名:objectId,必填 （房间Id、门票Id,赛事ID）") @RequestParam(value = "objectId") Long objectId,
		@ApiParam("订单数量,字段名:orderNum,必填") @RequestParam(value = "orderNum") Integer orderNum,
		@ApiParam("联系人,字段名:userName,必填") @RequestParam(value = "userName") String userName,
		@ApiParam("联系人手机号,字段名:mobile,必填") @RequestParam(value = "mobile") String mobile,
		@ApiParam("商品价格(单价),字段名:price,必填 ") @RequestParam(value = "price") Double price,
		@ApiParam("入住时间,字段名:checkinTime,例如：YYYY-MM-dd HH:mm （订单为酒店时用）") @RequestParam(required=false,value = "checkinTime") String checkinTime,
		@ApiParam("离开时间,字段名:leaveTime,例如：YYYY-MM-dd（订单为酒店时用）") @RequestParam(required=false,value = "leaveTime") String leaveTime,
		@ApiParam("用户编号,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("支付类型,字段名:payType,必填 （0：微信、1：支付宝）") @RequestParam(defaultValue ="1") Integer payType,
		@ApiParam("订单类型,字段名:orderType,必填 （0：酒店、1：景点,3：赛事门票）") @RequestParam(value = "orderType") Integer orderType,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		if(XaUtil.isEmpty(objectId)){
			xr.error("对象Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(orderNum)){
			xr.error("订单数量不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(userName)){
			xr.error("联系人不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(mobile)){
			xr.error("联系人手机号不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(price)){
			xr.error("商品价格(单价)不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(userId)){
			xr.error("用户Id不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(payType)){
			xr.error("支付类型不能为空！");
			return xr;
		}
		if(XaUtil.isEmpty(orderType)){
			xr.error("订单类型不能为空！");
			return xr;
		}
		Order model = new Order();
		/*BigDecimal a=new BigDecimal(XaUtil.convertDouble(price));
		price=a.doubleValue();*/
		//price=new BigDecimal(XaUtil.convertDouble(price));
		if(orderType==JConstant.ObjectType.HOTEL){
			if(XaUtil.isEmpty(checkinTime)){
				xr.error("此订单为酒店订单，入住时间不能为空！");
				return xr;
			}
			if(XaUtil.isEmpty(leaveTime)){
				xr.error("此订单为酒店订单，离开时间不能为空！");
				return xr;
			}
			SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd");
			
			if(XaUtil.isNotEmpty(checkinTime)){
				if(!Validator.isDateToMin(checkinTime)){
					xr.error("入住日期是非法的！");
					return xr;
				}
				if((checkinTime.compareTo(matter1.format(new Date())))<0){
					xr.error("入住日期不能小于当天！");
					return xr;
				}
			}
			if(XaUtil.isNotEmpty(leaveTime)){
				if(!Validator.isDate(leaveTime)){
					xr.error("离开日期是非法的！");
					return xr;
				}
				
			}
			if(XaUtil.isNotEmpty(checkinTime)&&XaUtil.isNotEmpty(leaveTime)){
				if((leaveTime.compareTo(checkinTime))<=0){
					xr.error("离开日期必须大于入住日期！");
					return xr;
				}
				model.setCheckinTime(checkinTime);
				model.setLeaveTime(leaveTime);
			}
			model.setOrderPrice(orderNum * price * XaUtil.daysBetween(checkinTime, leaveTime));
		}
		else{
			model.setOrderPrice(orderNum * price);
		}
		if(orderNum <= 0){
			xr.error("订单数量必须大于0！");
			return xr;
		}
		if(!(orderType==0||orderType==1||orderType==JConstant.ObjectType.MATCHTICKET)){
			xr.error("订单类型是非法的！");
			return xr;
		}
		if(!(payType==0||payType==1)){
			xr.error("支付类型是非法的！");
			return xr;
		}
		if(!Validator.isNumber(objectId.toString())){
			xr.error("对象Id是非法的！");
			return xr;
		}
		if(!Validator.isDouble(price.toString())){
			System.out.println(price);
			xr.error("商品价格是非法的！");
			return xr;
		}
		if(!Validator.isMobile(mobile)){
			xr.error("手机号码是非法的！");
			return xr;
		}
		if(orderType==JConstant.ObjectType.MATCHTICKET)
		{
			MacthVo match =  matchService.findOne(objectId).getObject();
			Date ddl = XaUtil.formatDateString2Date(match.getDeadlineView(),"yyyy-MM-dd HH:mm");
			if(ddl==null)
			{
				xr.error("日期格式转换错误{"+match.getDeadlineView()+"}，应为{yyyy-MM-dd HH:mm}");
				return xr;
			}
			if(ddl.compareTo(new Date())<0)
			{
				xr.error("购票时间已过");
				return xr;
			}
			//判断门票数量
			if(match.getTicketStockNum()<orderNum)
			{
				xr.error("目前仅剩余"+String.valueOf(match.getTicketStockNum())+"张票");
				return xr;
			}
			XaResult<Double> xrRealPrice = matchService.getRealTicketPrice(match);
			if(xrRealPrice.getCode()!=XaConstant.Code.success)
			{
				xr.setCode(xr.getCode());
				xr.setMessage(xr.getMessage());
				return xr;
			}
			if(xrRealPrice.getObject().compareTo(price)!=0)
			{
				xr.error("单价不正确，应为"+xrRealPrice.getObject());
				return xr;
			}
		}
		model.setUserId(userId);
		model.setObjectId(objectId);
		model.setOrderNum(orderNum);
		model.setPrice(price);
		model.setUserName(userName);
		model.setMobile(mobile);
		model.setOrderType(orderType);
		model.setOrderNo(GetRandomOrderno.getRandomString(16));
		model.setPayType(payType);
		return orderService.createModel(model);
	}	
	
	/**
	 * @Title: save
	 * @Description: 商品下单
	 * @param userId
	 * @param model
	 * @return    
	 */
	@ApiOperation(value="商品下单",notes="商品下单,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="saveShopOrder",method=RequestMethod.POST)
	public XaResult<OrderVo> saveShopOrder(
		@ApiParam("用户编号,字段名:userId,必填") @RequestParam(value = "userId") Long userId,
		@ApiParam("地址Id,字段名:addressId,必填") @RequestParam(value = "addressId") Long addressId,
		@ApiParam("支付类型,字段名:payType,必填 （0：微信、1：支付宝）") @RequestParam(defaultValue ="1") Integer payType,
		@ApiParam("购物车Id集合,字段名:carIds,如果从购物车下单此字段传购物车Id，以逗号分隔，否则该字段为空") @RequestParam(required = false,defaultValue = "") String carIds,
		@ApiParam("商品信息，json格式的字符串，格式：[{\"shopId\":1,\"price\":1.00,\"standardId\":1,\"shopNumber\":2},{\"shopId\":2,\"price\":1.00,\"standardId\":1,\"shopNumber\":3}]") @RequestParam(value = "shopInfo") String shopInfo,
		HttpServletRequest request
	) throws BusinessException{
		XaResult<OrderVo> xr = new XaResult<OrderVo>();
		//将商品信息字符串转化为对象集合
		List<ShopCarVo> cars = null;
		try{
			cars = JSON.parseArray(shopInfo, ShopCarVo.class);
		}catch(Exception e){
			xr.error("json格式不正确或数据格式不正确");
			return xr;
		}
		if(!(payType==0||payType==1)){
			xr.error("支付类型是非法的！");
			return xr;
		}
		if(addressId<1){
			xr.error("地址不存在！");
			return xr;
		}
		if(userId<1){
			xr.error("用户不存在！");
			return xr;
		}
		
		Order order = new Order();
		order.setUserId(userId);
		order.setAddressId(addressId);
		order.setOrderNum(1);
		order.setOrderType(JConstant.ObjectType.SHOP);
		order.setOrderNo(GetRandomOrderno.getRandomString(16));
		//订单有效期，目前暂设24小时
		order.setOrderVilidaty(XaUtil.calculateDate(XaUtil.getToDayStr(), "hour", 24));
		order.setPayType(payType);
		//封装订单详情集合
		Double totalPrice = 0.0;
		List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
		for(ShopCarVo car : cars){
			OrderDetail detail = new OrderDetail();
			detail.setShopId(car.getShopId());
			detail.setStandardId(car.getStandardId());
			detail.setShopNumber(car.getShopNumber());
			/*detail.setPrice(car.getPrice());
			detail.setGroupPrice(car.getGroupPrice());*/
			orderDetails.add(detail);
			totalPrice += car.getShopNumber() * car.getPrice();
		}
		BigDecimal b1 = new BigDecimal(totalPrice);  
		//保留两位小数
		double f3 = b1.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		order.setPrice(f3);	//商品订单的价格是改订单下所有商品价格的总和
		BigDecimal b2 = new BigDecimal(totalPrice);  
		//保留两位小数
		double f4 = b2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		order.setOrderPrice(f4);
		
		return orderService.shopSaveOrder(order,orderDetails,carIds);
	}
	@ApiOperation(value="确认收货",notes="确认收货,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="receiveConfirmation",method=RequestMethod.POST)
	public XaResult<Boolean> receiveConfirmation(
			@ApiParam("订单号,字段名:orderNo,必填") @RequestParam(value = "orderNo") String orderNo,
			HttpServletRequest request
		) throws BusinessException{
			XaResult<Boolean> xr=new XaResult<Boolean>();
			if(!Validator.isNumber(orderNo)){
				xr.setObject(false);
				return xr;
			}
			return orderService.receiveConfirmation(orderNo);
	}
	
	@ApiOperation(value="获取物流",notes="获取物流,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="getTransport",method=RequestMethod.POST)
	public XaResult<OrderVo> getTransport(
			@ApiParam("订单号,字段名:orderNo,必填") @RequestParam(value = "orderNo") String orderNo,
			HttpServletRequest request
		) throws BusinessException{
			XaResult<OrderVo> xr=new XaResult<OrderVo>();
			if(!Validator.isNumber(orderNo)){
				xr.error("订单号不正确");
				return xr;
			}
			return orderService.getTransport(orderNo);
	}
	/*@ApiOperation(value="test",notes="test,当返回的code=1时，保存成功后object返回对象")
	@ResponseBody
	@RequestMapping(value="test",method=RequestMethod.POST)
	public String test(
			@ApiParam("订单号,字段名:orderNo,必填") @RequestParam(value = "orderNo") String orderNo,
			@ApiParam("金额,字段名:price,必填") @RequestParam(value = "price") String price,
			HttpServletRequest request
		) throws BusinessException{
			
			return orderService.modifyOrder(orderNo, price);
	}		*/
}

