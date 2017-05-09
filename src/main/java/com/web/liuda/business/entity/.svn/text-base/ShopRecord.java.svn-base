package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: ShopRecord 
* @Description: 商品记录定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_shoprecord")
@ApiModel(value="商品记录定义表")
public class ShopRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="商品Id,商品Id")
	private Long shopId;
	@ApiModelProperty(value="规格Id,规格Id")
	private Long standardId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="购买数量,购买数量")
	private Integer buyNumber;
		
	
	public void setShopId(Long shopId){
		this.shopId=shopId;
	}
	
	@Column(nullable=false,length=50)
	public Long getShopId(){
		return shopId;
	}
	public void setStandardId(Long standardId){
		this.standardId=standardId;
	}
	
	@Column(nullable=false,length=50)
	public Long getStandardId(){
		return standardId;
	}
	public void setUserId(Long userId){
		this.userId=userId;
	}
	
	@Column(nullable=false,length=50)
	public Long getUserId(){
		return userId;
	}
	public void setBuyNumber(Integer buyNumber){
		this.buyNumber=buyNumber;
	}
	
	@Column(nullable=false,length=50)
	public Integer getBuyNumber(){
		return buyNumber;
	}
		

}
