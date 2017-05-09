package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ShopCarVo
 * @Description:购物车 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="购物车Vo对象")
public class ShopCarVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="商品Id,商品Id")
	private Long shopId;
	@ApiModelProperty(value="规格Id,规格Id")
	private Long standardId;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="数量,数量")
	private Integer shopNumber;
	@ApiModelProperty(value="价格,价格")
	private Double price;
	@ApiModelProperty(value="商品信息,商品数量")
	private ShopVo shop;
		
	public ShopCarVo(Long id,Long shopId,Long standardId,Long userId,Integer shopNumber) {
		this.id = id;
		this.shopId = shopId;
		this.standardId = standardId;
		this.userId = userId;
		this.shopNumber = shopNumber;
	}
	
	public ShopCarVo() {
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
	
		public void setShopId(Long shopId){
		this.shopId=shopId;
	}
	
	public Long getShopId(){
		return shopId;
	}
		public void setStandardId(Long standardId){
		this.standardId=standardId;
	}
	
	public Long getStandardId(){
		return standardId;
	}
		public void setUserId(Long userId){
		this.userId=userId;
	}
	
	public Long getUserId(){
		return userId;
	}
		public void setShopNumber(Integer shopNumber){
		this.shopNumber=shopNumber;
	}
	
	public Integer getShopNumber(){
		return shopNumber;
	}

	public ShopVo getShop() {
		return shop;
	}

	public void setShop(ShopVo shop) {
		this.shop = shop;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
		
}

