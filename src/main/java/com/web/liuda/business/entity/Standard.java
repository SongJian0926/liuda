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
* @ClassName: Standard 
* @Description: 商品规格属性定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_standard")
@ApiModel(value="商品规格属性定义表")
public class Standard extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="商品Id,商品Id")
	private Long shopId;
	@ApiModelProperty(value="规格,规格")
	private String porperty;
	@ApiModelProperty(value="库存量,库存量")
	private Integer stocks;
	@ApiModelProperty(value="图片,图片")
	private String imgUrl;
	@ApiModelProperty(value="价格,价格")
	private Double price;
	@ApiModelProperty(value="是否团购,是否团购，0、否，1、是")
	private Integer groupBuy = JConstant.BooleanStatus.FALSE;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
	
	public void setShopId(Long shopId){
		this.shopId=shopId;
	}
	
	@Column(nullable=false,length=50)
	public Long getShopId(){
		return shopId;
	}
	public void setPorperty(String porperty){
		this.porperty=porperty;
	}
	
	@Column(nullable=false,length=255)
	public String getPorperty(){
		return porperty;
	}
	public void setStocks(Integer stocks){
		this.stocks=stocks;
	}
	
	@Column(nullable=false,length=50)
	public Integer getStocks(){
		return stocks;
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
	
	@Column(nullable=false,length=50)
	public Double getPrice(){
		return price;
	}

	@Column(nullable=true,length=50)
	public Integer getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(Integer groupBuy) {
		this.groupBuy = groupBuy;
	}

	@Column(nullable=true,length=50)
	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}

	@Column(nullable=true,length=50)
	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
		

}
