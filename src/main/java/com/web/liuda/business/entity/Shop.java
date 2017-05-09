package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.liuda.business.constant.JConstant;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Shop 
* @Description: 商品定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_shop")
@ApiModel(value="商品定义表")
public class Shop extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="商品名称,商品名称")
	private String shopName;
	@ApiModelProperty(value="商品图片,商品图片")
	private String imgUrl;
	@ApiModelProperty(value="商品价格,商品价格")
	private Double price;
	@ApiModelProperty(value="商品介绍,商品介绍")
	private String shopDesc;
	@ApiModelProperty(value="是否团购,是否团购，0、否，1、是")
	private Integer groupBuy = JConstant.BooleanStatus.FALSE;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
	@ApiModelProperty(value="限购个数,限购个数")
	private Integer limitNumber;
		
	
	public void setShopName(String shopName){
		this.shopName=shopName;
	}
	
	@Column(nullable=false,length=255)
	public String getShopName(){
		return shopName;
	}
	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	
	@Column(nullable=false,length=255)
	public String getImgUrl(){
		return imgUrl;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	
	@Column(nullable=true,length=50)
	public Double getPrice(){
		return price;
	}
	public void setShopDesc(String shopDesc){
		this.shopDesc=shopDesc;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getShopDesc(){
		return shopDesc;
	}
	public void setGroupBuy(Integer groupBuy){
		this.groupBuy=groupBuy;
	}
	
	@Column(nullable=false,length=50)
	public Integer getGroupBuy(){
		return groupBuy;
	}
	public void setGroupPrice(Double groupPrice){
		this.groupPrice=groupPrice;
	}
	
	@Column(nullable=true,length=50)
	public Double getGroupPrice(){
		return groupPrice;
	}
	public void setValidity(String validity){
		this.validity=validity;
	}
	
	@Column(nullable=true,length=50)
	public String getValidity(){
		return validity;
	}
	@Column(nullable=true,length=11)
	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	@Override
	public String toString() {
		return "Shop [shopName=" + shopName + ", imgUrl=" + imgUrl + ", price="
				+ price + ", shopDesc=" + shopDesc + ", groupBuy=" + groupBuy
				+ ", groupPrice=" + groupPrice + ", validity=" + validity + "]";
	}
		

}
