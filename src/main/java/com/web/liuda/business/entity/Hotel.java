	package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Hotel 
* @Description: 酒店定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_hotel")
@ApiModel(value="酒店定义表")
public class Hotel extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="酒店名称,酒店名称")
	private String hotelName;
	@ApiModelProperty(value="酒店图片,酒店图片")
	private String picurl;
	@ApiModelProperty(value="酒店类型,酒店类型，经济型、快捷型等")
	private String hotelType;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="电话,电话号码")
	private String telphone;
	@ApiModelProperty(value="酒店地址,酒店地址")
	private String address;
	@ApiModelProperty(value="酒店政策,酒店政策")
	private String policy;
	@ApiModelProperty(value="酒店提示,酒店提示")
	private String prompt;
	@ApiModelProperty(value="酒店介绍,酒店介绍")
	private String introduce;
	@ApiModelProperty(value="经度,经度")
	private Double lng;
	@ApiModelProperty(value="纬度,纬度")
	private Double lat;
	@ApiModelProperty(value="商家用户Id,商家用户Id")
	private Long businessUserId;
	@ApiModelProperty(value="省份,省份")
	private String province;
	@ApiModelProperty(value="城市,城市")
	private String city;
	@ApiModelProperty(value="区域,区域")
	private String area;
	
	public void setHotelName(String hotelName){
		this.hotelName=hotelName;
	}
	
	@Column(nullable=false,length=50)
	public String getHotelName(){
		return hotelName;
	}
	public void setPicurl(String picurl){
		this.picurl=picurl;
	}
	
	@Column(nullable=false,length=255)
	public String getPicurl(){
		return picurl;
	}
	public void setHotelType(String hotelType){
		this.hotelType=hotelType;
	}
	
	@Column(nullable=false,length=50)
	public String getHotelType(){
		return hotelType;
	}
	public void setTelphone(String telphone){
		this.telphone=telphone;
	}
	
	@Column(nullable=false,length=13)
	public String getTelphone(){
		return telphone;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	@Column(nullable=false,length=255)
	public String getAddress(){
		return address;
	}
	public void setPolicy(String policy){
		this.policy=policy;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getPolicy(){
		return policy;
	}
	public void setPrompt(String prompt){
		this.prompt=prompt;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getPrompt(){
		return prompt;
	}
	public void setIntroduce(String introduce){
		this.introduce=introduce;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getIntroduce(){
		return introduce;
	}
	public void setLng(Double lng){
		this.lng=lng;
	}
	
	@Column(nullable=false,length=50)
	public Double getLng(){
		return lng;
	}
	public void setLat(Double lat){
		this.lat=lat;
	}
	
	@Column(nullable=false,length=50)
	public Double getLat(){
		return lat;
	}

	@Column(nullable=false,length=11)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(nullable=true,length=50)
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(nullable=true,length=50)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(nullable=true,length=50)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(nullable=true,length=50)
	public Long getBusinessUserId() {
		return businessUserId;
	}

	public void setBusinessUserId(Long businessUserId) {
		this.businessUserId = businessUserId;
	}
		

}
