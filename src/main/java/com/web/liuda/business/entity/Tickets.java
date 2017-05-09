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
* @ClassName: Tickets 
* @Description: 门票定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_tickets")
@ApiModel(value="门票定义表")
public class Tickets extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
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
	private Integer groupBuy = JConstant.BooleanStatus.FALSE;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
		
	@Column(nullable=false,length=255)
	public Long getTouristId() {
		return touristId;
	}

	public void setTouristId(Long touristId) {
		this.touristId = touristId;
	}

	public void setTicketName(String ticketName){
		this.ticketName=ticketName;
	}
	
	@Column(nullable=false,length=255)
	public String getTicketName(){
		return ticketName;
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
	public void setPredTime(String predTime){
		this.predTime=predTime;
	}
	
	@Column(nullable=true,length=255)
	public String getPredTime(){
		return predTime;
	}
	public void setItems(String items){
		this.items=items;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getItems(){
		return items;
	}
	public void setBuyNotes(String buyNotes){
		this.buyNotes=buyNotes;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getBuyNotes(){
		return buyNotes;
	}
	public void setExchangeNotes(String exchangeNotes){
		this.exchangeNotes=exchangeNotes;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getExchangeNotes(){
		return exchangeNotes;
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
		

}
