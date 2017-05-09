package com.web.liuda.business.entity;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.liuda.business.constant.JConstant;
import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: CashRecord 
* @Description: 提现记录定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_cashrecord")
@ApiModel(value="提现记录定义表")
public class CashRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="商家Id,商家Id")
	private Long objectId;
	@ApiModelProperty(value="商家类型,商家类型：0酒店、1景点")
	private Integer type;
	@ApiModelProperty(value="提现金额,提现金额")
	private Double money;
	@ApiModelProperty(value="提现状态,提现状态，0：已提交 1：提现成功2：打款中3：打款失败")
	private Integer cashStatus = JConstant.BooleanStatus.FALSE;
	//后台提现管理使用
	@ApiModelProperty(value="商家名称，后台提现管理使用")
	private String objectName;
	//后台提现管理使用
	@ApiModelProperty(value="账号,账号")
	private String account;
	@ApiModelProperty(value="银行名称,银行名称")
	private String bankName;
	@ApiModelProperty(value="我的银行卡,我的银行卡")
	private MyBank mybank;
	
	public void setObjectId(Long objectId){
		this.objectId=objectId;
	}
	
	@Column(nullable=false,length=50)
	public Long getObjectId(){
		return objectId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=false,length=50)
	public Integer getType(){
		return type;
	}
	public void setMoney(Double money){
		this.money=money;
	}
	
	@Column(nullable=false,length=50)
	public Double getMoney(){
		return money;
	}
	public void setCashStatus(Integer cashStatus){
		this.cashStatus=cashStatus;
	}
	
	@Column(nullable=false,length=50)
	public Integer getCashStatus(){
		return cashStatus;
	}
	@Transient
	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	@Transient
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	@Transient
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public MyBank getMybank() {
		return mybank;
	}

	public void setMybank(MyBank mybank) {
		this.mybank = mybank;
	}

	
		

}
