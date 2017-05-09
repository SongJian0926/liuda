package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: MyBank 
* @Description: 我的银行卡定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_mybank")
@ApiModel(value="我的银行卡定义表")
public class MyBank extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="商家用户Id,商家用户Id")
	private Long businessUserId;
	@ApiModelProperty(value="用户名,用户名")
	private String realName;
	@ApiModelProperty(value="账号,账号")
	private String account;
	@ApiModelProperty(value="银行名称,银行名称")
	private String bankName;
		
	@ApiModelProperty(value="支行名称,支行名称")
	private String childBankName;
	@ApiModelProperty(value="省市代码,省市代码")
	private String cityCode;
	@ApiModelProperty(value="省市名称,省市名称")
	private String cityName;
	@ApiModelProperty(value="货币代码,货币代码")
	private String currencyCode = "01";
	@ApiModelProperty(value="支付用途,支付用途")
	private String payUse = "商户提现";
	@ApiModelProperty(value="是否他行,值：农行/他行")
	private String isOtherBank;
	@ApiModelProperty(value="业务类型,0：对私、1：对公")
	private Integer type;
	
	public void setBusinessUserId(Long businessUserId){
		this.businessUserId=businessUserId;
	}
	
	@Column(nullable=false,length=50)
	public Long getBusinessUserId(){
		return businessUserId;
	}
	public void setRealName(String realName){
		this.realName=realName;
	}
	
	@Column(nullable=false,length=50)
	public String getRealName(){
		return realName;
	}
	public void setAccount(String account){
		this.account=account;
	}
	
	@Column(nullable=false,length=50)
	public String getAccount(){
		return account;
	}
	public void setBankName(String bankName){
		this.bankName=bankName;
	}
	
	@Column(nullable=false,length=255)
	public String getBankName(){
		return bankName;
	}

	@Column(nullable=true,length=255)
	public String getChildBankName() {
		return childBankName;
	}

	public void setChildBankName(String childBankName) {
		this.childBankName = childBankName;
	}

	@Column(nullable=true,length=255)
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Column(nullable=true,length=255)
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(nullable=true,length=255)
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Column(nullable=true,length=255)
	public String getPayUse() {
		return payUse;
	}

	public void setPayUse(String payUse) {
		this.payUse = payUse;
	}

	@Column(nullable=true,length=255)
	public String getIsOtherBank() {
		return isOtherBank;
	}

	public void setIsOtherBank(String isOtherBank) {
		this.isOtherBank = isOtherBank;
	}

	@Column(nullable=true,length=50)
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
		

}
