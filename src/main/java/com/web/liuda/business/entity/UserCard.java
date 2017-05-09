package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: UserCard 
* @Description: 用户银行卡定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_usercard")
@ApiModel(value="用户银行卡定义表")
public class UserCard extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id,银行卡所属的用户id")
	private Long userId;
	@ApiModelProperty(value="银行卡号,银行卡号")
	private String bankCardId;
	@ApiModelProperty(value="持卡人,持卡人")
	private String cardHolder;
	@ApiModelProperty(value="身份证号,身份证号")
	private String idcard;
	@ApiModelProperty(value="开户银行,开户银行")
	private String openingBank;
	@ApiModelProperty(value="开户区域,开户区域")
	private String openingArea;
	@ApiModelProperty(value="支行名称,支行名称")
	private String subBankNam;
		
	
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setBankCardId(String bankCardId){
		this.bankCardId=bankCardId;
	}
	
	@Column(nullable=true,length=50)
	public String getBankCardId(){
		return bankCardId;
	}
	public void setCardHolder(String cardHolder){
		this.cardHolder=cardHolder;
	}
	
	@Column(nullable=true,length=50)
	public String getCardHolder(){
		return cardHolder;
	}
	public void setIdcard(String idcard){
		this.idcard=idcard;
	}
	
	@Column(nullable=true,length=50)
	public String getIdcard(){
		return idcard;
	}
	public void setOpeningBank(String openingBank){
		this.openingBank=openingBank;
	}
	
	@Column(nullable=true,length=50)
	public String getOpeningBank(){
		return openingBank;
	}
	public void setOpeningArea(String openingArea){
		this.openingArea=openingArea;
	}
	
	@Column(nullable=true,length=50)
	public String getOpeningArea(){
		return openingArea;
	}
	public void setSubBankNam(String subBankNam){
		this.subBankNam=subBankNam;
	}
	
	@Column(nullable=true,length=50)
	public String getSubBankNam(){
		return subBankNam;
	}
		

}
