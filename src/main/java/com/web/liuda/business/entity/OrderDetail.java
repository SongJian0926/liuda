package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.web.webstart.base.entity.BaseEntity;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: OrderDetail 
 * @Description: 订单详情定义表
 * @author eason
 * @date 2015年3月23日 下午1:00:00 
 *
 */
@Entity
@Table(name = "tb_xa_orderdetail")
@ApiModel(value="订单详情定义表")
public class OrderDetail extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="订单编号,订单编号")
	private String orderNo;
	
	@ApiModelProperty(value="商品Id,商品Id")
	private Long shopId;
	
	@ApiModelProperty(value="规格Id,规格Id")
	private Long standardId;
	
	@ApiModelProperty(value="商品数量,商品数量")
	private Integer shopNumber;
	
	@ApiModelProperty(value="商品价格,商品价格")
	private Double price;
	
	@ApiModelProperty(value="商品团购价格,商品团购价格")
	private Double groupPrice;

	@Column(nullable=false,length=50)
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(nullable=false,length=50)
	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	@Column(nullable=false,length=50)
	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	@Column(nullable=false,length=50)
	public Integer getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(Integer shopNumber) {
		this.shopNumber = shopNumber;
	}
	@Column(nullable=true,length=10)
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(nullable=true,length=10)
	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}

	
}
