package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: RefundOrderVo
 * @Description:订单退款表 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="订单退款表Vo对象")
public class RefundOrderVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="订单Id,订单Id")
	private Long orderId;
	@ApiModelProperty(value="订单详细Id,订单详细Id为空，表示整单退款")
	private Long orderDetailId;
	@ApiModelProperty(value="退款金额 ,退款金额 ")
	private Double refundAmt;
	@ApiModelProperty(value="退款日期,退款日期")
	private String refundDate;
	@ApiModelProperty(value="退款审核通过日期 ,退款审核通过日期 ")
	private String refundApplyDate;
	@ApiModelProperty(value="退款状态,0.正常状态,1.退款申请中,2.退款审核失败,3.退款中,4.退款成功")
	private Integer refundStatus;
	@ApiModelProperty(value="审核备注,审核备注")
	private String memo;
	@ApiModelProperty(value="取消报名原因,1.报名截止日之前可以取消,2.赛事有取消原因")
	private String reason;
	@ApiModelProperty(value="商户退款单号,商户退款单号")
	private String refundNo;
	@ApiModelProperty(value="赛事名称,赛事名称")
	private String matchTitle;

	private MatchOrderVo matchOrderVo;
	private MatchOrderDetailVo matchOrderDetailVo;
		
	public RefundOrderVo(Long id,Long orderId,Long orderDetailId,Double refundAmt,String refundDate,String refundApplyDate,Integer refundStatus,String memo,String reason) {
		this.id = id;
		this.orderId = orderId;
		this.orderDetailId = orderDetailId;
		this.refundAmt = refundAmt;
		this.refundDate = refundDate;
		this.refundApplyDate = refundApplyDate;
		this.refundStatus = refundStatus;
		this.memo = memo;
		this.reason = reason;
	}
	
	public RefundOrderVo() {
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
	
		public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	
	public Long getOrderId(){
		return orderId;
	}
		public void setOrderDetailId(Long orderDetailId){
		this.orderDetailId=orderDetailId;
	}
	
	public Long getOrderDetailId(){
		return orderDetailId;
	}
		public void setRefundAmt(Double refundAmt){
		this.refundAmt=refundAmt;
	}
	
	public Double getRefundAmt(){
		return refundAmt;
	}
		public void setRefundDate(String refundDate){
		this.refundDate=refundDate;
	}
	
	public String getRefundDate(){
		return refundDate;
	}
		public void setRefundApplyDate(String refundApplyDate){
		this.refundApplyDate=refundApplyDate;
	}
	
	public String getRefundApplyDate(){
		return refundApplyDate;
	}
		public void setRefundStatus(Integer refundStatus){
		this.refundStatus=refundStatus;
	}
	
	public Integer getRefundStatus(){
		return refundStatus;
	}
		public void setMemo(String memo){
		this.memo=memo;
	}
	
	public String getMemo(){
		return memo;
	}
		public void setReason(String reason){
		this.reason=reason;
	}
	
	public String getReason(){
		return reason;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public MatchOrderVo getMatchOrderVo() {
		return matchOrderVo;
	}

	public void setMatchOrderVo(MatchOrderVo matchOrderVo) {
		this.matchOrderVo = matchOrderVo;
	}

	public MatchOrderDetailVo getMatchOrderDetailVo() {
		return matchOrderDetailVo;
	}

	public void setMatchOrderDetailVo(MatchOrderDetailVo matchOrderDetailVo) {
		this.matchOrderDetailVo = matchOrderDetailVo;
	}

	public String getMatchTitle() {
		return matchTitle;
	}

	public void setMatchTitle(String matchTitle) {
		this.matchTitle = matchTitle;
	}
		
}

