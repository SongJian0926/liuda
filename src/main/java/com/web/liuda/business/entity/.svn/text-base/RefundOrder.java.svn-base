package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: RefundOrder 
* @Description: 订单退款表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_refundorder")
@ApiModel(value="订单退款表定义表")
public class RefundOrder extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
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
	@ApiModelProperty(value="退款状态,0.正常状态1.退款申请中，2.退款审核失败，3.退款中，4.退款成功")
	private Integer refundStatus;
	@ApiModelProperty(value="审核备注,审核备注")
	private String memo;
	@ApiModelProperty(value="取消报名原因,1.报名截止日之前可以取消，2.赛事有取消原因")
	private String reason;
	@ApiModelProperty(value="商户退款单号,商户退款单号")
	private String refundNo;
	@ApiModelProperty(value="批次号,批次号")
	private String batchNo;
	
	public void setOrderId(Long orderId){
		this.orderId=orderId;
	}
	
	@Column(nullable=true,length=20)
	public Long getOrderId(){
		return orderId;
	}
	public void setOrderDetailId(Long orderDetailId){
		this.orderDetailId=orderDetailId;
	}
	
	@Column(nullable=true,length=20)
	public Long getOrderDetailId(){
		return orderDetailId;
	}
	public void setRefundAmt(Double refundAmt){
		this.refundAmt=refundAmt;
	}
	
	@Column(nullable=true,length=18)
	public Double getRefundAmt(){
		return refundAmt;
	}
	public void setRefundDate(String refundDate){
		this.refundDate=refundDate;
	}
	
	@Column(nullable=true,length=50)
	public String getRefundDate(){
		return refundDate;
	}
	public void setRefundApplyDate(String refundApplyDate){
		this.refundApplyDate=refundApplyDate;
	}
	
	@Column(nullable=true,length=50)
	public String getRefundApplyDate(){
		return refundApplyDate;
	}
	public void setRefundStatus(Integer refundStatus){
		this.refundStatus=refundStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getRefundStatus(){
		return refundStatus;
	}
	public void setMemo(String memo){
		this.memo=memo;
	}
	
	@Column(nullable=true,length=200)
	public String getMemo(){
		return memo;
	}
	public void setReason(String reason){
		this.reason=reason;
	}
	
	@Column(nullable=true,length=200)
	public String getReason(){
		return reason;
	}

	@Column(nullable=true,length=50)
	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	@Column(nullable=true,length=100)
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
		

}
