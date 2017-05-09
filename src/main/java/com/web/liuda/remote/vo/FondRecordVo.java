package com.web.liuda.remote.vo;

import org.springframework.data.domain.Page;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: FondRecordVo
 * @Description:现金记录 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="现金记录Vo对象")
public class FondRecordVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
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
	@ApiModelProperty(value="余额,余额")
	private Double generalIncome;
	@ApiModelProperty(value="提现记录,提现记录")
	private Page<FondRecordVo> pageFondRecordVo;
	@ApiModelProperty(value="银行卡Id")
	private Long cardId;	
	private UserVo userVo;
	
	public FondRecordVo(Long id,Double price,String origin,Long userId,Integer type) {
		this.id = id;
		this.price = price;
		this.origin = origin;
		this.userId = userId;
		this.type = type;
	}
	
	public FondRecordVo() {
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
	
	public Double getGeneralIncome() {
		return generalIncome;
	}

	public void setGeneralIncome(Double generalIncome) {
		this.generalIncome = generalIncome;
	}

	public Page<FondRecordVo> getPageFondRecordVo() {
		return pageFondRecordVo;
	}

	public void setPageFondRecordVo(Page<FondRecordVo> pageFondRecordVo) {
		this.pageFondRecordVo = pageFondRecordVo;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
		public void setPrice(Double price){
		this.price=price;
	}
	
	public Double getPrice(){
		return price;
	}
		public void setOrigin(String origin){
		this.origin=origin;
	}
	
	public String getOrigin(){
		return origin;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setType(Integer type){
		this.type=type;
	}
	
	public Integer getType(){
		return type;
	}

	public Integer getPresentState() {
		return presentState;
	}

	public void setPresentState(Integer presentState) {
		this.presentState = presentState;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getOpeningBank() {
		return openingBank;
	}

	public void setOpeningBank(String openingBank) {
		this.openingBank = openingBank;
	}

	public String getOpeningArea() {
		return openingArea;
	}

	public void setOpeningArea(String openingArea) {
		this.openingArea = openingArea;
	}

	public String getSubBankNam() {
		return subBankNam;
	}

	public void setSubBankNam(String subBankNam) {
		this.subBankNam = subBankNam;
	}

	public UserVo getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVo userVo) {
		this.userVo = userVo;
	}
		
}

