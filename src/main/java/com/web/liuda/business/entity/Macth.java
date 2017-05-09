package com.web.liuda.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import com.web.webstart.base.entity.BaseEntity;

/**
 * 
* @ClassName: Macth 
* @Description: 赛事表定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
*
 */
@Entity
@Table(name = "tb_xa_macth")
@ApiModel(value="赛事表定义表")
public class Macth extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="赛事标题,赛事标题")
	private String title;
	@ApiModelProperty(value="开始时间 ,开始时间 ")
	private String startdate;
	@ApiModelProperty(value="结束时间,结束时间")
	private String enddate;
	@ApiModelProperty(value="赛事状态,1.报名中，2.车手报道 ，3.比赛中，4.已结束 ")
	private Integer matchStatus;
	@ApiModelProperty(value="赛事类型,1.商家赛事、2.官方活动")
	private Integer matchType;
	@ApiModelProperty(value="赛事来源,0.表示官方，其他表示：商户id")
	private Long merchantId;
	@ApiModelProperty(value="赛事图片,赛事图片")
	private String mediaPath;
	@ApiModelProperty(value="赛事头像,赛事头像")
	private String logo;
	@ApiModelProperty(value="参赛费用,参赛费用")
	private Double price;
	@ApiModelProperty(value="定金,定金")
	private Double deposit;
	@ApiModelProperty(value="最大参赛人数,最大参赛人数")
	private Integer maxNum;
	@ApiModelProperty(value="活动说明,活动说明")
	private String content;
	@ApiModelProperty(value="门票图片,门票图片")
	private String ticketImages;
	@ApiModelProperty(value="门票名称,门票名称")
	private String ticketName;
	@ApiModelProperty(value="门票数量,门票数量")
	private Integer ticketStockNum;
	@ApiModelProperty(value="门票价格,门票价格")
	private Double ticketPrice;
	@ApiModelProperty(value="优惠价格,优惠价格")
	private Double favourablePrice;
	@ApiModelProperty(value="优惠开始时间,优惠开始时间 ")
	private String favStartTime;
	@ApiModelProperty(value="优惠结束时间 ,优惠结束时间 ")
	private String favEndTime;
	@ApiModelProperty(value="抽奖状态,0表示未抽奖，1，表示已经抽过奖，2表示没有二次抽奖环节 ")
	private Integer luckDraw;
	@ApiModelProperty(value="地址,地址")
	private String address;
	@ApiModelProperty(value="经度 ,经度 ")
	private Double lng;
	@ApiModelProperty(value="纬度 ,纬度 ")
	private Double lat;
	@ApiModelProperty(value="参赛报名截止日,参赛报名截止日")
	private String deadline;
	@ApiModelProperty(value="观赛报名截止日,观赛报名截止日")
	private String deadlineView;
	@ApiModelProperty(value="是否线上核销,1：是，0：否")
	private Integer isOnline;
	@ApiModelProperty(value="报名条件,报名条件")
	private String condition;
	@ApiModelProperty(value="兴趣标签,取至字典表")
	private Long dictItemId;
	@ApiModelProperty(value="门票总数,门票总数")
	private Integer ticketTotleNum;
	@ApiModelProperty(value="商家用户Id,商家用户Id")
	private Long businessUserId;
	@ApiModelProperty(value="区域代码,区域代码")
	private String areaCode;
	@ApiModelProperty(value="区域Id,区域Id")
	private Long areaId;
	@ApiModelProperty(value="url,url")
	private String url;
	
	public void setTitle(String title){
		this.title=title;
	}
	
	@Column(nullable=true,length=100)
	public String getTitle(){
		return title;
	}
	public void setStartdate(String startdate){
		this.startdate=startdate;
	}
	
	@Column(nullable=true,length=50)
	public String getStartdate(){
		return startdate;
	}
	public void setEnddate(String enddate){
		this.enddate=enddate;
	}
	
	@Column(nullable=true,length=50)
	public String getEnddate(){
		return enddate;
	}
	public void setMatchStatus(Integer matchStatus){
		this.matchStatus=matchStatus;
	}
	
	@Column(nullable=true,length=50)
	public Integer getMatchStatus(){
		return matchStatus;
	}
	public void setMatchType(Integer matchType){
		this.matchType=matchType;
	}
	
	@Column(nullable=true,length=50)
	public Integer getMatchType(){
		return matchType;
	}
	public void setMerchantId(Long merchantId){
		this.merchantId=merchantId;
	}
	
	@Column(nullable=true,length=20)
	public Long getMerchantId(){
		return merchantId;
	}
	public void setMediaPath(String mediaPath){
		this.mediaPath=mediaPath;
	}
	
	@Column(nullable=true,length=1000)
	public String getMediaPath(){
		return mediaPath;
	}
	public void setPrice(Double price){
		this.price=price;
	}
	
	@Column(nullable=true,length=18)
	public Double getPrice(){
		return price;
	}
	public void setDeposit(Double deposit){
		this.deposit=deposit;
	}
	
	@Column(nullable=true,length=18)
	public Double getDeposit(){
		return deposit;
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
	
	@Column(nullable=true,length=5000,columnDefinition="MEDIUMTEXT")
	public String getContent(){
		return content;
	}
	public void setTicketImages(String ticketImages){
		this.ticketImages=ticketImages;
	}
	
	@Column(nullable=true,length=255)
	public String getTicketImages(){
		return ticketImages;
	}
	public void setTicketName(String ticketName){
		this.ticketName=ticketName;
	}
	
	@Column(nullable=true,length=50)
	public String getTicketName(){
		return ticketName;
	}
	public void setTicketStockNum(Integer ticketStockNum){
		this.ticketStockNum=ticketStockNum;
	}
	
	@Column(nullable=true,length=11)
	public Integer getTicketStockNum(){
		return ticketStockNum;
	}
	public void setTicketPrice(Double ticketPrice){
		this.ticketPrice=ticketPrice;
	}
	
	@Column(nullable=true,length=18)
	public Double getTicketPrice(){
		return ticketPrice;
	}
	public void setFavourablePrice(Double favourablePrice){
		this.favourablePrice=favourablePrice;
	}
	
	@Column(nullable=true,length=18)
	public Double getFavourablePrice(){
		return favourablePrice;
	}
	public void setFavStartTime(String favStartTime){
		this.favStartTime=favStartTime;
	}
	
	@Column(nullable=true,length=50)
	public String getFavStartTime(){
		return favStartTime;
	}
	public void setFavEndTime(String favEndTime){
		this.favEndTime=favEndTime;
	}
	
	@Column(nullable=true,length=50)
	public String getFavEndTime(){
		return favEndTime;
	}
	public void setLuckDraw(Integer luckDraw){
		this.luckDraw=luckDraw;
	}
	
	@Column(nullable=true,length=11)
	public Integer getLuckDraw(){
		return luckDraw;
	}
	public void setAddress(String address){
		this.address=address;
	}
	
	@Column(nullable=true,length=255)
	public String getAddress(){
		return address;
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
	public void setDeadline(String deadline){
		this.deadline=deadline;
	}
	
	@Column(nullable=true,length=50)
	public String getDeadline(){
		return deadline;
	}
	public void setDeadlineView(String deadlineView){
		this.deadlineView=deadlineView;
	}
	
	@Column(nullable=true,length=50)
	public String getDeadlineView(){
		return deadlineView;
	}
	@Column(nullable=true,length=11)
	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}
	@Column(name="[condition]",nullable=true,length=500)
	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	@Column(nullable=true,length=500)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(nullable=true,length=20)
	public Long getDictItemId() {
		return dictItemId;
	}

	public void setDictItemId(Long dictItemId) {
		this.dictItemId = dictItemId;
	}

	@Column(nullable=true,length=11)
	public Integer getTicketTotleNum() {
		return ticketTotleNum;
	}

	public void setTicketTotleNum(Integer ticketTotleNum) {
		this.ticketTotleNum = ticketTotleNum;
	}

	@Column(nullable=true,length=20)
	public Long getBusinessUserId() {
		return businessUserId;
	}

	public void setBusinessUserId(Long businessUserId) {
		this.businessUserId = businessUserId;
	}

	@Column(nullable=true,length=255)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Column(nullable=true,length=20)
	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	@Column(nullable=true,length=255)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}	

}
