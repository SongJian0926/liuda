package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: MatchOrderVo
 * @Description:参赛订单主表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="参赛订单主表Vo对象")
public class MatchOrderVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="赛事Id,赛事Id")
	private Long matchId;
	@ApiModelProperty(value="订单数量,订单数量")
	private Integer orderNumber;
	@ApiModelProperty(value="订单状态,1:未支付，2:已支付，3:取消报名，4:订单失效")
	private Integer orderStatus;
	@ApiModelProperty(value="总金额,总金额")
	private Double totalAmt;
	@ApiModelProperty(value="退款金额,退款金额")
	private Double refundAmt;
	@ApiModelProperty(value="支付方式,0.微信,1.支付宝,2.银联 ")
	private Integer payType;
	@ApiModelProperty(value="TN,TN，第三方交易流水号 ")
	private String tradeNum;
	@ApiModelProperty(value="是否全款支付,0.定金支付,1.全款支付 ")
	private Integer isFull;
	@ApiModelProperty(value="线下支付金额 ,线下支付金额 ")
	private Double offlineAmt;
	@ApiModelProperty(value="线上支付金额,线上支付金额")
	private Double onlineAmt;
	@ApiModelProperty(value="订单类型,1.参加赛事订单,2.俱乐部活动订单 ")
	private Integer type;
	@ApiModelProperty(value="赛事订单编号；赛事订单编号 ")
	private String orderNo;
	//俱乐部活动订单详情使用
	@ApiModelProperty(value="俱乐部活动实体 ")
	private ClubEventVo clubEventVo;
	@ApiModelProperty(value="订单详情集合（报名人员信息集合） ")
	private List<MatchOrderDetailVo> matchOrderDetailVos;
	@ApiModelProperty(value="用户实体 ")
	private UserVo userVo;
	
	
	public MatchOrderVo(Long id,Long userId,Long matchId,Integer orderNumber,Integer orderStatus,Double totalAmt,Double refundAmt,Integer payType,String tradeNum,Integer isFull,Double offlineAmt,Double onlineAmt,Integer type,String orderNo) {
		this.id = id;
		this.userId = userId;
		this.matchId = matchId;
		this.orderNumber = orderNumber;
		this.orderStatus = orderStatus;
		this.totalAmt = totalAmt;
		this.refundAmt = refundAmt;
		this.payType = payType;
		this.tradeNum = tradeNum;
		this.isFull = isFull;
		this.offlineAmt = offlineAmt;
		this.onlineAmt = onlineAmt;
		this.type = type;
		this.orderNo=orderNo;
	}
	
	public MatchOrderVo() {
		super();
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
	
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setMatchId(Long matchId){
		this.matchId=matchId;
	}
	
	public Long getMatchId(){
		return matchId;
	}
		public void setOrderNumber(Integer orderNumber){
		this.orderNumber=orderNumber;
	}
	
	public Integer getOrderNumber(){
		return orderNumber;
	}
		public void setOrderStatus(Integer orderStatus){
		this.orderStatus=orderStatus;
	}
	
	public Integer getOrderStatus(){
		return orderStatus;
	}
		public void setTotalAmt(Double totalAmt){
		this.totalAmt=totalAmt;
	}
	
	public Double getTotalAmt(){
		return totalAmt;
	}
		public void setRefundAmt(Double refundAmt){
		this.refundAmt=refundAmt;
	}
	
	public Double getRefundAmt(){
		return refundAmt;
	}
		public void setPayType(Integer payType){
		this.payType=payType;
	}
	
	public Integer getPayType(){
		return payType;
	}
		public void setTradeNum(String tradeNum){
		this.tradeNum=tradeNum;
	}
	
	public String getTradeNum(){
		return tradeNum;
	}
		public void setIsFull(Integer isFull){
		this.isFull=isFull;
	}
	
	public Integer getIsFull(){
		return isFull;
	}
		public void setOfflineAmt(Double offlineAmt){
		this.offlineAmt=offlineAmt;
	}
	
	public Double getOfflineAmt(){
		return offlineAmt;
	}
		public void setOnlineAmt(Double onlineAmt){
		this.onlineAmt=onlineAmt;
	}
	
	public Double getOnlineAmt(){
		return onlineAmt;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public ClubEventVo getClubEventVo() {
		return clubEventVo;
	}

	public void setClubEventVo(ClubEventVo clubEventVo) {
		this.clubEventVo = clubEventVo;
	}

	public List<MatchOrderDetailVo> getMatchOrderDetailVos() {
		return matchOrderDetailVos;
	}

	public void setMatchOrderDetailVos(List<MatchOrderDetailVo> matchOrderDetailVos) {
		this.matchOrderDetailVos = matchOrderDetailVos;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}

		
}

