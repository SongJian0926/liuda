package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: OrderVo
 * @Description:订单 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="订单Vo对象")
public class OrderVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="订单编号,订单编号")
	private String orderNo;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="对象Id,对象Id（房间Id、门票Id、商品Id）")
	private Long objectId;
	@ApiModelProperty(value="订单数量,订单数量")
	private Integer orderNum;
	@ApiModelProperty(value="商品价格,商品价格")
	private Double price;
	@ApiModelProperty(value="订单状态,订单状态（0：待支付；1：已支付(待消费、待发货)；2：待评价；3：已评价；4：待收货；5：已收货；6：失效")
	private Integer orderStatus;
	@ApiModelProperty(value="支付类型,支付类型（0：微信，1：支付宝）")
	private Integer payType;
	@ApiModelProperty(value="支付状态,支付状态（0：成功、1：失败）")
	private Integer payStatus;
	@ApiModelProperty(value="联系人,联系人")
	private String userName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="订单类型,订单类型（0：酒店、1：景点、2：商品）")
	private Integer orderType;
	@ApiModelProperty(value="总额,总额（ 总额  单位：元）")
	private Double total;
	@ApiModelProperty(value="酒店信息,酒店信息")
	private HotelVo hotel;
	@ApiModelProperty(value="订单价格,订单价格（ 总额  单位：分）")
	private Double orderPrice;
	/**
	 * author:常璐
	 * time：2015-01-14 16：45：00
	 * pc端校验兑换码时使用
	 */
	@ApiModelProperty(value="校验时间,校验时间")
	private String testTime;
	/**
	 * author:常璐
	 * time：2015-12-30 16：00：00
	 * 前台下单时保存收获地址用
	 */
	@ApiModelProperty(value="地址Id,地址Id")
	private Long addressId;
	/**
	 * author:常璐
	 * time：2015-12-30 17：00：00
	 * 前台下单时保存规格用（订单类型是商品时用）
	 */
	@ApiModelProperty(value="规格Id,规格Id")
	private Long standardId;
	/**
	 * author:杨惠
	 * time：2015-12-30 18：12：00
	 * 前台查询商品的订单详情时用
	 */
	private List<ShopVo> shopVo;
	private AddressVo addressVo;
	private StandardVo standardVo;
	/**
	 * author:常璐
	 * time：2015-12-30 18：12：00
	 * 前台查询景点的订单列表时用
	 */
	private TouristVo touristVo ;
	private TicketsVo ticketsVo;
	/**
	 * author:常璐
	 * time：2015-01-1 14：12：00
	 * pc端订单管理列表使用
	 */
	private RoomVo roomVo;
	/**
	 * author:常璐
	 * time：2015-01-15 14：12：00
	 * pc端订单详情使用
	 */
	private HotelVo hotelVo;
	/**
	 * author:flora.li
	 * time：2016-04-28 14：28：00
	 * 赛事门票详情
	 */
	private MacthVo macthVo;
	
	@ApiModelProperty(value="物流公司,物流公司")
	private String expressCompany;
	
	@ApiModelProperty(value="物流运单号,物流运单号")
	private String expressNumber;
	
	public OrderVo(Long id,String orderNo,Long userId,Long objectId,Integer orderNum,Double price,Integer orderStatus,Integer payType,Integer payStatus,String userName,String mobile,String checkinTime,String leaveTime,Integer orderType) {
		this.id = id;
		this.orderNo = orderNo;
		this.userId = userId;
		this.objectId = objectId;
		this.orderNum = orderNum;
		this.price = price;
		this.orderStatus = orderStatus;
		this.payType = payType;
		this.payStatus = payStatus;
		this.userName = userName;
		this.mobile = mobile;
		this.orderType = orderType;
	}
	
	public OrderVo() {
		super();
	}
	
	public HotelVo getHotel() {
		return hotel;
	}

	public void setHotel(HotelVo hotel) {
		this.hotel = hotel;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
		public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	
	public String getOrderNo(){
		return orderNo;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public Long getObjectId(){
		return objectId;
	}
		public void setOrderNum(Integer orderNum){
		this.orderNum=orderNum;
	}
	
	public Integer getOrderNum(){
		return orderNum;
	}
		public void setPrice(Double price){
		this.price=price;
	}
	
	public Double getPrice(){
		return price;
	}
		public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	
	public Integer getOrderStatus(){
		return orderStatus;
	}
		public void setPayType(Integer payType){
		this.payType=payType;
	}
	
	public Integer getPayType(){
		return payType;
	}
		public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	
	public Integer getPayStatus(){
		return payStatus;
	}
		public void setUserName(String userName){
		this.userName=userName;
	}
	
	public String getUserName(){
		return userName;
	}
		public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
		public void setOrderType(Integer orderType){
		this.orderType=orderType;
	}
	
	public Integer getOrderType(){
		return orderType;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	
	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public List<ShopVo> getShopVo() {
		return shopVo;
	}

	public void setShopVo(List<ShopVo> shopVo) {
		this.shopVo = shopVo;
	}

	public AddressVo getAddressVo() {
		return addressVo;
	}

	public void setAddressVo(AddressVo addressVo) {
		this.addressVo = addressVo;
	}

	public StandardVo getStandardVo() {
		return standardVo;
	}

	public void setStandardVo(StandardVo standardVo) {
		this.standardVo = standardVo;
	}

	public TouristVo getTouristVo() {
		return touristVo;
	}

	public void setTouristVo(TouristVo touristVo) {
		this.touristVo = touristVo;
	}

	public TicketsVo getTicketsVo() {
		return ticketsVo;
	}

	public void setTicketsVo(TicketsVo ticketsVo) {
		this.ticketsVo = ticketsVo;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public RoomVo getRoomVo() {
		return roomVo;
	}

	public void setRoomVo(RoomVo roomVo) {
		this.roomVo = roomVo;
	}

	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	public HotelVo getHotelVo() {
		return hotelVo;
	}

	public void setHotelVo(HotelVo hotelVo) {
		this.hotelVo = hotelVo;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public MacthVo getMacthVo() {
		return macthVo;
	}

	public void setMacthVo(MacthVo macthVo) {
		this.macthVo = macthVo;
	}

	

}

