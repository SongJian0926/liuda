package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Room 
* @Description: 房间定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_room")
@ApiModel(value="房间定义表")
public class Room extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="酒店Id,酒店Id")
	private Long hotelId;
	@ApiModelProperty(value="房型,房型")
	private String type;
	@ApiModelProperty(value="包含项目,包含项目")
	private String item;
	@ApiModelProperty(value="购买须知,购买须知")
	private String buyNote;
	@ApiModelProperty(value="房间价格,房间价格")
	private Double price;
	@ApiModelProperty(value="房间logo,房间图片")
	private String logo;
	@ApiModelProperty(value="是否含早,是否含早 0：无早；1：单早，2：双早")
	private Integer breakfast;
	@ApiModelProperty(value="是否团购,是否团购，0、否，1、是")
	private Integer groupBuy;
	@ApiModelProperty(value="团购价格,团购价格")
	private Double groupPrice;
	@ApiModelProperty(value="有效期,有效期")
	private String validity;
	@ApiModelProperty(value="面积,面积")
	private Integer area;
	@ApiModelProperty(value="床位,床位")
	private Integer beds;
	@ApiModelProperty(value="浴室,浴室")
	private String bathRoom;
	@ApiModelProperty(value="通讯,通讯")
	private String communication;
	@ApiModelProperty(value="设施,设施（包含各种空调，宽带，吹风机等）")
	private String establishment;
		
	
	public void setHotelId(Long hotelId){
		this.hotelId=hotelId;
	}
	
	@Column(nullable=false,length=50)
	public Long getHotelId(){
		return hotelId;
	}
	public void setType(String type){
		this.type=type;
	}
	
	@Column(nullable=false,length=50)
	public String getType(){
		return type;
	}
	public void setItem(String item){
		this.item=item;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getItem(){
		return item;
	}
	public void setBuyNote(String buyNote){
		this.buyNote=buyNote;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getBuyNote(){
		return buyNote;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	
	@Column(nullable=false,length=50)
	public Double getPrice(){
		return price;
	}
	public void setLogo(String logo){
		this.logo=logo;
	}
	
	@Column(nullable=false,length=255)
	public String getLogo(){
		return logo;
	}
	public void setBreakfast(Integer breakfast){
		this.breakfast=breakfast;
	}
	
	@Column(nullable=true,length=50)
	public Integer getBreakfast(){
		return breakfast;
	}
	public void setGroupBuy(Integer groupBuy){
		this.groupBuy=groupBuy;
	}
	
	@Column(nullable=true,length=50)
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
	public void setArea(Integer area){
		this.area=area;
	}
	
	@Column(nullable=false,length=50)
	public Integer getArea(){
		return area;
	}
	public void setBeds(Integer beds){
		this.beds=beds;
	}
	
	@Column(nullable=false,length=50)
	public Integer getBeds(){
		return beds;
	}
	public void setBathRoom(String bathRoom){
		this.bathRoom=bathRoom;
	}
	
	@Column(nullable=false,length=255)
	public String getBathRoom(){
		return bathRoom;
	}
	public void setCommunication(String communication){
		this.communication=communication;
	}
	
	@Column(nullable=true,length=255)
	public String getCommunication(){
		return communication;
	}
	public void setEstablishment(String establishment){
		this.establishment=establishment;
	}
	
	@Column(nullable=true,length=255)
	public String getEstablishment(){
		return establishment;
	}
		

}
