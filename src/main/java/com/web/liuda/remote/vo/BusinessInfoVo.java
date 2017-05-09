package com.web.liuda.remote.vo;

import javax.persistence.Column;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: BusinessInfoVo
 * @Description:商家用户 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="商家用户Vo对象")
public class BusinessInfoVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="账号,账号")
	private String account;
	@ApiModelProperty(value="密码,密码")
	private String password;


	private Long goodsId;
	private String businessName;
	private String imgUrl;
	private Integer type;
	private String telphone;
	private String address;
	private Integer status;
	/*
	 * author:changlu
	 * time:2016-01-26 11:22:00
	 * 后台结算管理使用
	 */
	private HotelVo hotelVo;
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
	
	
	public BusinessInfoVo(Long id,String account,String password) {
		this.id = id;
		this.account = account;
		this.password = password;
	}
	
	public BusinessInfoVo() {
		super();
	}
	

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
	
		public void setAccount(String account){
		this.account=account;
	}
	
	public String getAccount(){
		return account;
	}
		public void setPassword(String password){
		this.password=password;
	}
	
	public String getPassword(){
		return password;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public HotelVo getHotelVo() {
		return hotelVo;
	}

	public void setHotelVo(HotelVo hotelVo) {
		this.hotelVo = hotelVo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
		
}

