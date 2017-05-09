package com.web.liuda.remote.vo;


import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: ShopVo
 * @Description:商品 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="商品Vo对象")
public class ShopVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="商品名称,商品名称")
	private String shopName;
	@ApiModelProperty(value="商品图片,商品图片")
	private String imgUrl;
	@ApiModelProperty(value="商品价格,商品价格")
	private Double price;
	@ApiModelProperty(value="商品介绍,商品介绍")
	private String shopDesc;
	@ApiModelProperty(value="是否团购,是否团购，0、否，1、是")
	private Integer groupBuy;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
	@ApiModelProperty(value="商品总格,商品总格")
	private Double totalPrice;
	@ApiModelProperty(value="限购个数,限购个数")
	private Integer limitNumber;
	
	//2015-12-30  yanghui 前端商品销量，商品评论数量
	@ApiModelProperty(value="商品销量,商品销量")
	private Integer shopSales;
	@ApiModelProperty(value="商品评论数量,商品评论数量")
	private Integer shopComments;
	//2015-12-30  yanghui 前端商品状态和轮播图
	@ApiModelProperty(value = "状态，0为无效，1为正常,2为发布,3删除 参看XaConstant.Status")
	private Integer status;
	@ApiModelProperty(value="轮播图,轮播图")
	private String[] pics1;
	//2015-12-30 songjian 
	@ApiModelProperty(value="商品规格,单个、主要用于购物车，订单中商品和规格已经一一对应的时候")
	private StandardVo standard;
	@ApiModelProperty(value="商品规格,列表、主要用户商品详情中的规格列表")
	private List<StandardVo> standards;
	@ApiModelProperty(value="商品介绍图,商品介绍图")
	private String[] imgUrlpics;
	
	/**
	 * autor: zhanglin
	 * 订单列表、订单详情使用
	 */
	@ApiModelProperty(value="商品数量,订单中的商品数量")
	private Integer shopNumber;
	@ApiModelProperty(value="商品规格,订单中的商品规格")
	private String porperty;
	@ApiModelProperty(value="是否收藏（0：否 、1：是）")
	private Integer isLike;
	@ApiModelProperty(value="商品规格Id,订单中的商品规格Id")
	private Long standardId;
	
	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}
	
	public ShopVo(Long id,String shopName,String imgUrl,Double price,String shopDesc) {
		this.id = id;
		this.shopName = shopName;
		this.imgUrl = imgUrl;
		this.price = price;
		this.shopDesc = shopDesc;
	}
	public ShopVo() {
		super();
	}
	
	
	public String[] getImgUrlpics() {
		return imgUrlpics;
	}
	public void setImgUrlpics(String[] imgUrlpics) {
		this.imgUrlpics = imgUrlpics;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public StandardVo getStandard() {
		return standard;
	}
	public void setStandard(StandardVo standard) {
		this.standard = standard;
	}
	public List<StandardVo> getStandards() {
		return standards;
	}
	public void setStandards(List<StandardVo> standards) {
		this.standards = standards;
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
	
		public void setShopName(String shopName){
		this.shopName=shopName;
	}
	
	public String getShopName(){
		return shopName;
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
		public void setShopDesc(String shopDesc){
		this.shopDesc=shopDesc;
	}
	
	public String getShopDesc(){
		return shopDesc;
	}

	public Integer getShopSales() {
		return shopSales;
	}

	public void setShopSales(Integer shopSales) {
		this.shopSales = shopSales;
	}

	public Integer getShopComments() {
		return shopComments;
	}

	public void setShopComments(Integer shopComments) {
		this.shopComments = shopComments;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String[] getPics1() {
		return pics1;
	}

	public void setPics1(String[] pics1) {
		this.pics1 = pics1;
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
	
	public Integer getShopNumber() {
		return shopNumber;
	}
	
	public void setShopNumber(Integer shopNumber) {
		this.shopNumber = shopNumber;
	}
	
	public String getPorperty() {
		return porperty;
	}
	
	public void setPorperty(String porperty) {
		this.porperty = porperty;
	}

	public Integer getLimitNumber() {
		return limitNumber;
	}

	public void setLimitNumber(Integer limitNumber) {
		this.limitNumber = limitNumber;
	}

	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}
		
}

