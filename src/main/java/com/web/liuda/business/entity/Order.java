package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.liuda.business.constant.JConstant;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Order 
* @Description: 订单定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_order")
@ApiModel(value="订单定义表")
public class Order extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="订单编号,订单编号")
	private String orderNo;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="对象Id,对象Id（房间Id、门票Id）,订单类型为商品，此字段为空")
	private Long objectId;
	@ApiModelProperty(value="订单数量,订单数量")
	private Integer orderNum;
	@ApiModelProperty(value="商品价格,商品价格")
	private Double price;
	@ApiModelProperty(value="订单状态,订单状态（0：待支付；1：已支付(待消费、待发货)；2：待评价；3：已评价；4：待收货；5：已收货；6：失效")
	private Integer orderStatus = JConstant.BooleanStatus.FALSE;
	@ApiModelProperty(value="支付类型,支付类型（0：微信，1：支付宝）")
	private Integer payType;
	@ApiModelProperty(value="支付状态,支付状态（0：成功、1：失败）")
	private Integer payStatus;
	@ApiModelProperty(value="联系人,联系人")
	private String userName;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="入住时间,入住时间（订单为酒店时用）")
	private String checkinTime;
	@ApiModelProperty(value="离开时间,离开时间（订单为酒店时用）")
	private String leaveTime;
	@ApiModelProperty(value="订单类型,订单类型（0：酒店、1：景点、2：商品、3：赛事门票）")
	private Integer orderType;
	@ApiModelProperty(value="订单价格,订单价格")
	private Double orderPrice;
	@ApiModelProperty(value="订单有效期,订单有效期")
	private String orderVilidaty;
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
	 * 前台下单时保存收货地址用（订单类型是商品时用）
	 */
	@ApiModelProperty(value="地址Id,地址Id")
	private Long addressId;
	
	@ApiModelProperty(value="物流公司,物流公司")
	private String expressCompany;
	
	@ApiModelProperty(value="物流运单号,物流运单号")
	private String expressNumber;
	
	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}
	
	@Column(nullable=false,length=50)
	public String getOrderNo(){
		return orderNo;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=false,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=true,length=50)
	public Long getObjectId(){
		return objectId;
	}
	public void setOrderNum(Integer orderNum){
		this.orderNum=orderNum;
	}
	
	@Column(nullable=false,length=50)
	public Integer getOrderNum(){
		return orderNum;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	
	@Column(nullable=false,length=50)
	public Double getPrice(){
		return price;
	}
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	
	@Column(nullable=false,length=50)
	public Integer getOrderStatus(){
		return orderStatus;
	}
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	
	@Column(nullable=false,length=50)
	public Integer getPayType(){
		return payType;
	}
	public void setPayStatus(Integer payStatus){
		this.payStatus=payStatus;
	}
	
	@Column(nullable=true,length=50)
	public Integer getPayStatus(){
		return payStatus;
	}
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	@Column(nullable=true,length=50)
	public String getUserName(){
		return userName;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	@Column(nullable=true,length=11)
	public String getMobile(){
		return mobile;
	}
	public void setCheckinTime(String checkinTime){
		this.checkinTime=checkinTime;
	}
	
	@Column(nullable=true,length=50)
	public String getCheckinTime(){
		return checkinTime;
	}
	public void setLeaveTime(String leaveTime){
		this.leaveTime=leaveTime;
	}
	
	@Column(nullable=true,length=50)
	public String getLeaveTime(){
		return leaveTime;
	}
	public void setOrderType(Integer orderType){
		this.orderType=orderType;
	}
	
	@Column(nullable=false,length=50)
	public Integer getOrderType(){
		return orderType;
	}
	@Column(nullable=true,length=50)
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Column(nullable=true,length=50)
	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
	@Column(nullable=true,length=50)
	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}

	@Column(nullable=true,length=50)
	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	@Column(nullable=true,length=50)
	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	@Column(nullable=true,length=50)
	public String getOrderVilidaty() {
		return orderVilidaty;
	}

	public void setOrderVilidaty(String orderVilidaty) {
		this.orderVilidaty = orderVilidaty;
	}

}
