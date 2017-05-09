package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: BusinessInfo 
* @Description: 商家用户定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_businessinfo")
@ApiModel(value="商家用户定义表")
public class BusinessInfo extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="账号,账号")
	private String account;
	@ApiModelProperty(value="密码,密码")
	private String password;
	@ApiModelProperty(value="比例,比例")
	private Integer propotion;
	@ApiModelProperty(value="账期,账期")
	private Integer accountPeriod;
	@ApiModelProperty(value="比例,比例")
	private Integer propotions;
	@ApiModelProperty(value="账期,账期")
	private Integer accountPeriods;
	

	@Column(nullable=true,length=11)
	public Integer getPropotion() {
		return propotion;
	}

	public void setPropotion(Integer propotion) {
		this.propotion = propotion;
	}
	@Column(nullable=true,length=11)
	public Integer getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(Integer accountPeriod) {
		this.accountPeriod = accountPeriod;
	}
	@Column(nullable=true,length=11)
	public Integer getPropotions() {
		return propotions;
	}

	public void setPropotions(Integer propotions) {
		this.propotions = propotions;
	}
	@Column(nullable=true,length=11)
	public Integer getAccountPeriods() {
		return accountPeriods;
	}

	public void setAccountPeriods(Integer accountPeriods) {
		this.accountPeriods = accountPeriods;
	}

	public void setAccount(String account){
		this.account=account;
	}
	
	@Column(nullable=false,length=50)
	public String getAccount(){
		return account;
	}
	public void setPassword(String password){
		this.password=password;
	}
	
	@Column(nullable=false,length=50)
	public String getPassword(){
		return password;
	}
		

}
