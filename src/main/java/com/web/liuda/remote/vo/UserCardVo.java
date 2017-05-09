package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: UserCardVo
 * @Description:用户银行卡 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="用户银行卡Vo对象")
public class UserCardVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="用户id,银行卡所属的用户id")
	private Long userId;
	@ApiModelProperty(value="银行卡号,银行卡号")
	private String bankCardId;
	@ApiModelProperty(value="持卡人,持卡人")
	private String cardHolder;
	@ApiModelProperty(value="身份证号,身份证号身份证号")
	private String idcard;
	@ApiModelProperty(value="开户银行,开户银行开户银行")
	private String openingBank;
	@ApiModelProperty(value="开户区域,开户区域开户区域")
	private String openingArea;
	@ApiModelProperty(value="支行名称,支行名称支行名称")
	private String subBankNam;
		
	public UserCardVo(Long id,Long userId,String bankCardId,String cardHolder,String idcard,String openingBank,String openingArea,String subBankNam) {
		this.id = id;
		this.userId = userId;
		this.bankCardId = bankCardId;
		this.cardHolder = cardHolder;
		this.idcard = idcard;
		this.openingBank = openingBank;
		this.openingArea = openingArea;
		this.subBankNam = subBankNam;
	}
	
	public UserCardVo() {
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
		public void setBankCardId(String bankCardId){
		this.bankCardId=bankCardId;
	}
	
	public String getBankCardId(){
		return bankCardId;
	}
		public void setCardHolder(String cardHolder){
		this.cardHolder=cardHolder;
	}
	
	public String getCardHolder(){
		return cardHolder;
	}
		public void setIdcard(String idcard){
		this.idcard=idcard;
	}
	
	public String getIdcard(){
		return idcard;
	}
		public void setOpeningBank(String openingBank){
		this.openingBank=openingBank;
	}
	
	public String getOpeningBank(){
		return openingBank;
	}
		public void setOpeningArea(String openingArea){
		this.openingArea=openingArea;
	}
	
	public String getOpeningArea(){
		return openingArea;
	}
		public void setSubBankNam(String subBankNam){
		this.subBankNam=subBankNam;
	}
	
	public String getSubBankNam(){
		return subBankNam;
	}
		
}

