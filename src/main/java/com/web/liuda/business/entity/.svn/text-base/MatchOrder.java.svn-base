package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: MatchOrder 
* @Description: 参赛订单主表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_matchorder")
@ApiModel(value="参赛订单主表定义表")
public class MatchOrder extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="订单数量,订单数量")
	private Integer orderNumber;
	@ApiModelProperty(value="订单状态,1:未支付，2:已支付，3:取消报名,4:订单失效")
	private Integer orderStatus;
	@ApiModelProperty(value="总金额,总金额")
	private Double totalAmt;
	@ApiModelProperty(value="退款金额,退款金额")
	private Double refundAmt;
	@ApiModelProperty(value="支付方式,0.微信,1.支付宝,2.银联 ")
	private Integer payType;
	@ApiModelProperty(value="TN,TN，第三方交易流水号 ")
	private String tradeNum;
	@ApiModelProperty(value="是否全款支付,0.定金支付，1.全款支付 ")
	private Integer isFull;
	@ApiModelProperty(value="线下支付金额 ,线下支付金额 ")
	private Double offlineAmt;
	@ApiModelProperty(value="线上支付金额,线上支付金额")
	private Double onlineAmt;
	@ApiModelProperty(value="订单类型,1.参加赛事订单，2.俱乐部活动订单 ")
	private Integer type;
	@ApiModelProperty(value="赛事订单编号 ")
	private String orderNo;
	@ApiModelProperty(value="支付平台交易号 ")
	private String paymentNo;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMatchId(){
		return matchId;
	}
	public void setOrderNumber(Integer orderNumber){
		this.orderNumber=orderNumber;
	}
	
	@Column(nullable=true,length=11)
	public Integer getOrderNumber(){
		return orderNumber;
	}
	public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getOrderStatus(){
		return orderStatus;
	}
	public void setTotalAmt(Double totalAmt){
		this.totalAmt=totalAmt;
	}
	
	@Column(nullable=true,length=18)
	public Double getTotalAmt(){
		return totalAmt;
	}
	public void setRefundAmt(Double refundAmt){
		this.refundAmt=refundAmt;
	}
	
	@Column(nullable=true,length=18)
	public Double getRefundAmt(){
		return refundAmt;
	}
	public void setPayType(Integer payType){
		this.payType=payType;
	}
	
	@Column(nullable=true,length=11)
	public Integer getPayType(){
		return payType;
	}
	public void setTradeNum(String tradeNum){
		this.tradeNum=tradeNum;
	}
	
	@Column(nullable=true,length=50)
	public String getTradeNum(){
		return tradeNum;
	}
	public void setIsFull(Integer isFull){
		this.isFull=isFull;
	}
	
	@Column(nullable=true,length=11)
	public Integer getIsFull(){
		return isFull;
	}
	public void setOfflineAmt(Double offlineAmt){
		this.offlineAmt=offlineAmt;
	}
	
	@Column(nullable=true,length=18)
	public Double getOfflineAmt(){
		return offlineAmt;
	}
	public void setOnlineAmt(Double onlineAmt){
		this.onlineAmt=onlineAmt;
	}
	
	@Column(nullable=true,length=18)
	public Double getOnlineAmt(){
		return onlineAmt;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=11)
	public Integer getType(){
		return type;
	}
	@Column(nullable=true,length=16)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(nullable=true,length=100)
	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
		

}
