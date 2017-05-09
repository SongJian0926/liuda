package com.web.liuda.remote.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: TicketsVo
 * @Description:门票 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="门票Vo对象")
public class TicketsVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
 	@ApiModelProperty(value="景点Id,景点Id")
	private Long touristId;
	@ApiModelProperty(value="门票名称,门票名称")
	private String ticketName;
	@ApiModelProperty(value="图片,图片")
	private String imgUrl;
	@ApiModelProperty(value="价格,价格")
	private Double price;
	@ApiModelProperty(value="预定时间,预定时间")
	private String predTime;
	@ApiModelProperty(value="包含项目,包含项目")
	private String items;
	@ApiModelProperty(value="购票须知,购票须知")
	private String buyNotes;
	@ApiModelProperty(value="兑票须知,兑票须知")
	private String exchangeNotes;
	@ApiModelProperty(value="是否团购,是否团购，0、否，1、是")
	private Integer groupBuy;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
	
	/*author:songjian
	 * 2016-01-15
	 * 景点名称*/
	private String touristName;
	
	/*author:yanghui
	 * 2016-01-15
	 * web端显示商品列表(门票或者房间)、图片*/
	private ImagesVo imagesvo;
		
	public TicketsVo(Long id,Long touristId,String ticketName,String imgUrl,Double price,String predTime,String items,String buyNotes,String exchangeNotes,Integer groupBuy,Double groupPrice,String validity) {
		this.id = id;
		this.touristId = touristId;
		this.ticketName = ticketName;
		this.imgUrl = imgUrl;
		this.price = price;
		this.predTime = predTime;
		this.items = items;
		this.buyNotes = buyNotes;
		this.exchangeNotes = exchangeNotes;
		this.groupBuy = groupBuy;
		this.groupPrice = groupPrice;
		this.validity = validity;
	}
	
	public TicketsVo() {
		super();
	}
	
	public String getTouristName() {
		return touristName;
	}

	public void setTouristName(String touristName) {
		this.touristName = touristName;
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
	
		public Long getTouristId() {
		return touristId;
	}

	public void setTouristId(Long touristId) {
		this.touristId = touristId;
	}

		public void setTicketName(String ticketName){
		this.ticketName=ticketName;
	}
	
	public String getTicketName(){
		return ticketName;
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
		public void setPredTime(String predTime){
		this.predTime=predTime;
	}
	
	public String getPredTime(){
		return predTime;
	}
		public void setItems(String items){
		this.items=items;
	}
	
	public String getItems(){
		return items;
	}
		public void setBuyNotes(String buyNotes){
		this.buyNotes=buyNotes;
	}
	
	public String getBuyNotes(){
		return buyNotes;
	}
		public void setExchangeNotes(String exchangeNotes){
		this.exchangeNotes=exchangeNotes;
	}
	
	public String getExchangeNotes(){
		return exchangeNotes;
	}
		public void setGroupBuy(Integer groupBuy){
		this.groupBuy=groupBuy;
	}
	
	public Integer getGroupBuy(){
		return groupBuy;
	}
		public void setGroupPrice(Double groupPrice){
		this.groupPrice=groupPrice;
	}
	
	public Double getGroupPrice(){
		return groupPrice;
	}
		public void setValidity(String validity){
		this.validity=validity;
	}
	
	public String getValidity(){
		return validity;
	}

	public ImagesVo getImagesvo() {
		return imagesvo;
	}

	public void setImagesvo(ImagesVo imagesvo) {
		this.imagesvo = imagesvo;
	}
		
}

