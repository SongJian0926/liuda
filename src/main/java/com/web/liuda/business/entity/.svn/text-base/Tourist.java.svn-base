package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Tourist 
* @Description: 景点定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_tourist")
@ApiModel(value="景点定义表")
public class Tourist extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="景点名称,景点名称")
	private String touristName;
	@ApiModelProperty(value="图片,景点图片")
	private String imgUrl;
	@ApiModelProperty(value="电话,电话")
	private String telphone;
	@ApiModelProperty(value="手机号,手机号")
	private String mobile;
	@ApiModelProperty(value="营业时间,营业时间")
	private String businessTime;
	@ApiModelProperty(value="景点地址,景点地址")
	private String address;
	@ApiModelProperty(value="景点介绍,景点介绍")
	private String touristDesc;
	@ApiModelProperty(value="温馨提示,温馨提示")
	private String notes;
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
		
	
	public void setTouristName(String touristName){
		this.touristName=touristName;
	}
	
	@Column(nullable=false,length=255)
	public String getTouristName(){
		return touristName;
	}
	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	
	@Column(nullable=false,length=255)
	public String getImgUrl(){
		return imgUrl;
	}
	public void setTelphone(String telphone){
		this.telphone=telphone;
	}
	
	@Column(nullable=false,length=13)
	public String getTelphone(){
		return telphone;
	}
	public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	@Column(nullable=false,length=11)
	public String getMobile(){
		return mobile;
	}
	public void setBusinessTime(String businessTime){
		this.businessTime=businessTime;
	}
	
	@Column(nullable=true,length=50)
	public String getBusinessTime(){
		return businessTime;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	@Column(nullable=false,length=255)
	public String getAddress(){
		return address;
	}
	public void setTouristDesc(String touristDesc){
		this.touristDesc=touristDesc;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getTouristDesc(){
		return touristDesc;
	}
	public void setNotes(String notes){
		this.notes=notes;
	}
	
	@Column(nullable=true,length=50,columnDefinition="MEDIUMTEXT")
	public String getNotes(){
		return notes;
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
	public void setBusinessUserId(Long businessUserId){
		this.businessUserId=businessUserId;
	}
	
	@Column(nullable=false,length=50)
	public Long getBusinessUserId(){
		return businessUserId;
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
	
}
