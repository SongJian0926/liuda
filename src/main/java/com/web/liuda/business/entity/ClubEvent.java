package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: ClubEvent 
* @Description: 俱乐部活动定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_clubevent")
@ApiModel(value="俱乐部活动定义表")
public class ClubEvent extends BaseEntity{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="俱乐部Id,俱乐部Id")
	private Long clubId;
	@ApiModelProperty(value="活动名称,活动名称")
	private String title;
	@ApiModelProperty(value="活动主图,列表展示用")
	private String logo;
	@ApiModelProperty(value="活动开始时间,开始时间")
	private String starttime;
	@ApiModelProperty(value="活动结束时间,结束时间")
	private String endtime;
	@ApiModelProperty(value="活动状态,1:报名中;2:活动未开始;3:活动进行中;4:已结束")
	private Integer eventStatus;
	@ApiModelProperty(value="活动费用,活动费用/人")
	private Double price;
	@ApiModelProperty(value="报名截止日期,报名截止日期")
	private String deadline;
	@ApiModelProperty(value="最大人数限制,最大人数限制")
	private Integer maxNum;
	@ApiModelProperty(value="活动说明 ,活动说明")
	private String content;
	@ApiModelProperty(value="区域,区域")
	private String areaCode;
	@ApiModelProperty(value="详细地址,详细地址")
	private String address;
	@ApiModelProperty(value="定金,定金")
	private Double disposit;
	@ApiModelProperty(value="图片,图片路径")
	private String mediaPath;
	@ApiModelProperty(value="是否线上核销,1：是，0：否")
	private Integer isOnline;	
	@ApiModelProperty(value="报名条件,报名条件")
	private String requirement;
	@ApiModelProperty(value="经度,经度")
	private Double lng;
	@ApiModelProperty(value="纬度,纬度")
	private Double lat;
	
	@Column(nullable=true,length=20)
	public Long getClubId() {
		return clubId;
	}

	public void setClubId(Long clubId) {
		this.clubId = clubId;
	}

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
	public void setStarttime(String starttime){
		this.starttime=starttime;
	}
	
	@Column(nullable=true,length=50)
	public String getStarttime(){
		return starttime;
	}
	public void setEndtime(String endtime){
		this.endtime=endtime;
	}
	
	@Column(nullable=true,length=50)
	public String getEndtime(){
		return endtime;
	}
	public void setEventStatus(Integer eventStatus){
		this.eventStatus=eventStatus;
	}
	
	@Column(nullable=true,length=11)
	public Integer getEventStatus(){
		return eventStatus;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	
	@Column(nullable=true,length=18)
	public Double getPrice(){
		return price;
	}
	public void setDeadline(String deadline){
		this.deadline=deadline;
	}
	
	@Column(nullable=true,length=50)
	public String getDeadline(){
		return deadline;
	}
	public void setMaxNum(Integer maxNum){
		this.maxNum=maxNum;
	}
	
	@Column(nullable=true,length=11)
	public Integer getMaxNum(){
		return maxNum;
	}
	public void setContent(String content){
		this.content=content;
	}
	
	@Column(nullable=true,length=1000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	@Column(nullable=true,length=255)
	public String getAddress(){
		return address;
	}
	public void setDisposit(Double disposit){
		this.disposit=disposit;
	}
	
	@Column(nullable=true,length=18)
	public Double getDisposit(){
		return disposit;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=1000)
	public String getMediaPath(){
		return mediaPath;
	}
	@Column(nullable=true,length=11)
	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	@Column(nullable=true,length=50)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	
	@Column(nullable=true,length=500)
	public String getRequirement() {
		return requirement;
	}

	public void setRequirement(String requirement) {
		this.requirement = requirement;
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
}
