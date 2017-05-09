package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Club 
* @Description: 俱乐部定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_club")
@ApiModel(value="俱乐部定义表")
public class Club extends BaseEntity{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="用户Id,用户Id")
	private Long userId;
	@ApiModelProperty(value="名称,俱乐部名称")
	private String title;
	@ApiModelProperty(value="头像,俱乐部头像")
	private String logo;
	@ApiModelProperty(value="类型,俱乐部类型（取至字典表） ")
	private Long type;
	@ApiModelProperty(value="等级,俱乐部等级（取至字典表） ")
	private Long level;
	@ApiModelProperty(value="地址,地址")
	private String address;
	@ApiModelProperty(value="排序值,排序值")
	private Integer sort;
	@ApiModelProperty(value="经度,经度")
	private Double lng;
	@ApiModelProperty(value="纬度,纬度")
	private Double lat;
	@ApiModelProperty(value="简介,俱乐部简介")
	private String content;
	@ApiModelProperty(value="图片,俱乐部图片")
	private String mediaPath;
	@ApiModelProperty(value="所属区域 ,所属区域 ")
	private String areaCode;
	@ApiModelProperty(value="审核状态,1.审核中;2.审核成功;3.审核失败 ")
	private Integer applyStatus;
	@ApiModelProperty(value="是否推荐,1.是;0.否 ")
	private Integer isRecommend;
	@ApiModelProperty(value="兴趣标签,取至字典表 ")
	private Long interest;
	@ApiModelProperty(value="区域Id,区域Id")
	private Long areaId;
	@ApiModelProperty(value="是否用户创建,1：是，0：否")
	private Integer isUserAdd;	
		
	
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=255)
	public String getTitle(){
		return title;
	}
	public void setLogo(String logo){
		this.logo=logo;
	}
	
	@Column(nullable=true,length=255)
	public String getLogo(){
		return logo;
	}
	public void setType(Long type){
		this.type=type;
	}
	
	@Column(nullable=true,length=11)
	public Long getType(){
		return type;
	}
	public void setLevel(Long level){
		this.level=level;
	}
	
	@Column(nullable=true,length=11)
	public Long getLevel(){
		return level;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	@Column(nullable=true,length=255)
	public String getAddress(){
		return address;
	}
	public void setSort(Integer sort){
		this.sort=sort;
	}
	
	@Column(nullable=true,length=11)
	public Integer getSort(){
		return sort;
	}
	public void setLng(Double lng){
		this.lng=lng;
	}
	
	@Column(nullable=true,length=18)
	public Double getLng(){
		return lng;
	}
	public void setLat(Double lat){
		this.lat=lat;
	}
	
	@Column(nullable=true,length=18)
	public Double getLat(){
		return lat;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=1000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=2000)
	public String getMediaPath(){
		return mediaPath;
	}
	public void setAreaCode(String areaCode){
		this.areaCode=areaCode;
	}
	
	@Column(nullable=true,length=50)
	public String getAreaCode(){
		return areaCode;
	}
	public void setApplyStatus(Integer applyStatus){
		this.applyStatus=applyStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getApplyStatus(){
		return applyStatus;
	}
	public void setIsRecommend(Integer isRecommend){
		this.isRecommend=isRecommend;
	}
	
	@Column(nullable=true,length=11)
	public Integer getIsRecommend(){
		return isRecommend;
	}
	@Column(nullable=true,length=20)
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Column(nullable=true,length=20)
	public Long getInterest() {
		return interest;
	}

	public void setInterest(Long interest) {
		this.interest = interest;
	}

	@Column(nullable=true,length=20)
	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(nullable=true,length=11)
	public Integer getIsUserAdd() {
		return isUserAdd;
	}

	public void setIsUserAdd(Integer isUserAdd) {
		this.isUserAdd = isUserAdd;
	}	

}
