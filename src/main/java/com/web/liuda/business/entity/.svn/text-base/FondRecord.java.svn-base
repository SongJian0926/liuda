package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: FondRecord 
* @Description: 现金记录定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_fondrecord")
@ApiModel(value="现金记录定义表")
public class FondRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="金额,金额,金额")
	private Double price;
	@ApiModelProperty(value="来源,现金来源,现金来源")
	private String origin;
	@ApiModelProperty(value="用户id,用户id,用户id")
	private Long userId;
	@ApiModelProperty(value="来源类型,1，通过邀请码得到现金。2，现金支出。")
	private Integer type;
	@ApiModelProperty(value="提现状态 ,1.已提交2.打款成功 3.打款失败4.打款中")
	private Integer presentState;
	@ApiModelProperty(value="银行卡号,银行卡号")
	private String bankCardId;
	@ApiModelProperty(value="持卡人,持卡人")
	private String cardHolder;
	@ApiModelProperty(value="身份证号,身份证号")
	private String idcard;
	@ApiModelProperty(value="开户银行,开户银行")
	private String openingBank;
	@ApiModelProperty(value="所属区域,所属区域")
	private String openingArea;
	@ApiModelProperty(value="支行名称,支行名称")
	private String subBankNam;
		
	
	public void setPrice(Double price){
		this.price=price;
	}
	
	@Column(nullable=true,length=50)
	public Double getPrice(){
		return price;
	}
	public void setOrigin(String origin){
		this.origin=origin;
	}
	
	@Column(nullable=true,length=255)
	public String getOrigin(){
		return origin;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=true,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setType(Integer type){
		this.type=type;
	}
	
	@Column(nullable=true,length=50)
	public Integer getType(){
		return type;
	}
	@Column(nullable=true,length=11)
	public Integer getPresentState() {
		return presentState;
	}

	public void setPresentState(Integer presentState) {
		this.presentState = presentState;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
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
