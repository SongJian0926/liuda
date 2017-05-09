package com.web.liuda.remote.vo;

import com.web.liuda.business.constant.JConstant;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: StandardVo
 * @Description:商品规格属性 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="商品规格属性Vo对象")
public class StandardVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
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
		
	public StandardVo(Long id,Long shopId,String porperty,Integer stocks,String imgUrl,Double price) {
		this.id = id;
		this.shopId = shopId;
		this.porperty = porperty;
		this.stocks = stocks;
		this.imgUrl = imgUrl;
		this.price = price;
	}
	
	public StandardVo() {
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
		public void setPorperty(String porperty){
		this.porperty=porperty;
	}
	
	public String getPorperty(){
		return porperty;
	}
		public void setStocks(Integer stocks){
		this.stocks=stocks;
	}
	
	public Integer getStocks(){
		return stocks;
	}
		public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	
	public String getImgUrl(){
		return imgUrl;
	}
		public void setPrice(Double price){
		this.price=price;
	}
	
	public Double getPrice(){
		return price;
	}

	public Integer getGroupBuy() {
		return groupBuy;
	}

	public void setGroupBuy(Integer groupBuy) {
		this.groupBuy = groupBuy;
	}

	public Double getGroupPrice() {
		return groupPrice;
	}

	public void setGroupPrice(Double groupPrice) {
		this.groupPrice = groupPrice;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}
		
}

