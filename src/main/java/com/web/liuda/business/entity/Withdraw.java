package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Withdraw 
* @Description: 前端提现定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_withdraw")
@ApiModel(value="前端提现定义表（暂时不用）")
public class Withdraw extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="金额,金额")
	private Double account;
	@ApiModelProperty(value="银行卡号Id, 银行卡号Id,银行卡信息确认完成后，替换成银行卡号信息 ")
	private Long caedInfoId;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=20)
	public Long getUserId(){
		return userId;
	}
	public void setAccount(Double account){
		this.account=account;
	}
	
	@Column(nullable=true,length=18)
	public Double getAccount(){
		return account;
	}
	public void setCaedInfoId(Long caedInfoId){
		this.caedInfoId=caedInfoId;
	}
	
	@Column(nullable=true,length=20)
	public Long getCaedInfoId(){
		return caedInfoId;
	}
		

}
