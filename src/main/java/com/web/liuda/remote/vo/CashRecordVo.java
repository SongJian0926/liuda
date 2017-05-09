package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: CashRecordVo
 * @Description:提现记录 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="提现记录Vo对象")
public class CashRecordVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="商家Id,商家Id")
	private Long objectId;
	@ApiModelProperty(value="商家类型,商家类型：0酒店、1景点")
	private Integer type;
	@ApiModelProperty(value="提现金额,提现金额")
	private Double money;
	@ApiModelProperty(value="提现状态,提现状态，0：已提交 1：提现成功")
	private Integer cashStatus;
	
	@ApiModelProperty(value="账期时间,几号到几号")
	private String accountPeriod;
	@ApiModelProperty(value="是否可提现,可提现，不可提现")
	private String isCash;
	@ApiModelProperty(value="提现比例,几比几 字符串")
	private String isProportion;
	@ApiModelProperty(value="可提现金额。余额和比例计算出来的值")
	private Double cashMoney;
	
	/*
	 * pc端查询财务管理账户余额使用
	 */
	@ApiModelProperty(value="余额,余额")
	private Double remain;
	/*
	 * pc端查询财务管理列表时用
	 */
	private String orderNo;
	/*
	 * pc端查询财务管理列表时用
	 */
	private String objectName;
	/*
	 * pc端查询财务管理列表时用
	 */
	private OrderVo orderVo;
	
	
	public CashRecordVo(Long id,Long objectId,Integer type,Double money,Integer cashStatus) {
		this.id = id;
		this.objectId = objectId;
		this.type = type;
		this.money = money;
		this.cashStatus = cashStatus;
	}
	
	public CashRecordVo() {
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
	
		public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	public Long getObjectId(){
		return objectId;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}
		public void setMoney(Double money){
		this.money=money;
	}
	
	public Double getMoney(){
		return money;
	}
		public void setCashStatus(Integer cashStatus){
		this.cashStatus=cashStatus;
	}
	
	public Integer getCashStatus(){
		return cashStatus;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public Double getRemain() {
		return remain;
	}

	public void setRemain(Double remain) {
		this.remain = remain;
	}

	public OrderVo getOrderVo() {
		return orderVo;
	}

	public void setOrderVo(OrderVo orderVo) {
		this.orderVo = orderVo;
	}

	public String getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getIsCash() {
		return isCash;
	}

	public void setIsCash(String isCash) {
		this.isCash = isCash;
	}

	public String getIsProportion() {
		return isProportion;
	}

	public void setIsProportion(String isProportion) {
		this.isProportion = isProportion;
	}

	public Double getCashMoney() {
		return cashMoney;
	}

	public void setCashMoney(Double cashMoney) {
		this.cashMoney = cashMoney;
	}
		
}

