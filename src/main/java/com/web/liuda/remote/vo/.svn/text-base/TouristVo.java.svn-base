package com.web.liuda.remote.vo;

import java.util.List;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: TouristVo
 * @Description:景点 Vo
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 *
 */
@ApiModel(value="景点Vo对象")
public class TouristVo {

	@ApiModelProperty(value="主键，自动增长")
	private Long id;
	
	@ApiModelProperty(value="创建时间")
	private String createTime;
	
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
	@ApiModelProperty(value="价钱,景点最低价格")
	private Double price;
	@ApiModelProperty(value="评分,景点评价分")
	private Double score;
	@ApiModelProperty(value="距离,当前用户距离景点的距离")
	private Integer distance;
	@ApiModelProperty(value="评论数,评论数")
	private Integer comments;
	@ApiModelProperty(value="轮播图,轮播图")
	private String[] pics;
	@ApiModelProperty(value="酒店介绍图片,酒店介绍图片")
	private String[] intropics;
	@ApiModelProperty(value="门票信息,门票信息")
	private List<TicketsVo> tickets;
	
	/**
	 * autor：zhanglin
	 * time: 2016-01-02 16:08:01
	 * desc: 个人在中心我的评价时用
	 */
	@ApiModelProperty(value="票名,票名")
	private String ticketName;
	@ApiModelProperty(value="总价,总价")
	private Double totalPrice;
	@ApiModelProperty(value="数量,数量")
	private Integer number;
	
	@ApiModelProperty(value="图片,图片")
	private String picurl;
	
	/**
	 * 团购信息
	 */
	@ApiModelProperty(value="是否团购,0：否、1：是")
	private Integer groupBuy;
	@ApiModelProperty(value="团购价格,团购中的最低价格")
	private Double groupPrice;
	
	@ApiModelProperty(value="是否收藏（0：否 、1：是）")
	private Integer isLike;
	
	public Integer getIsLike() {
		return isLike;
	}

	public void setIsLike(Integer isLike) {
		this.isLike = isLike;
	}
		
	public TouristVo(Long id,String touristName,String imgUrl,String telphone,String mobile,String businessTime,String address,String touristDesc,String notes,Double lng,Double lat,Long businessUserId) {
		this.id = id;
		this.touristName = touristName;
		this.imgUrl = imgUrl;
		this.telphone = telphone;
		this.mobile = mobile;
		this.businessTime = businessTime;
		this.address = address;
		this.touristDesc = touristDesc;
		this.notes = notes;
		this.lng = lng;
		this.lat = lat;
		this.businessUserId = businessUserId;
	}
	
	public TouristVo() {
		super();
	}
	
	public Long getId() {
		return id;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
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
	
		public void setTouristName(String touristName){
		this.touristName=touristName;
	}
	
	public String getTouristName(){
		return touristName;
	}
		public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}
	
	public String getImgUrl(){
		return imgUrl;
	}
		public void setTelphone(String telphone){
		this.telphone=telphone;
	}
	
	public String getTelphone(){
		return telphone;
	}
		public void setMobile(String mobile){
		this.mobile=mobile;
	}
	
	public String getMobile(){
		return mobile;
	}
		public void setBusinessTime(String businessTime){
		this.businessTime=businessTime;
	}
	
	public String getBusinessTime(){
		return businessTime;
	}
		public void setAddress(String address){
		this.address=address;
	}
	
	public String getAddress(){
		return address;
	}
		public void setTouristDesc(String touristDesc){
		this.touristDesc=touristDesc;
	}
	
	public String getTouristDesc(){
		return touristDesc;
	}
		public void setNotes(String notes){
		this.notes=notes;
	}
	
	public String getNotes(){
		return notes;
	}
		public void setLng(Double lng){
		this.lng=lng;
	}
	
	public Double getLng(){
		return lng;
	}
		public void setLat(Double lat){
		this.lat=lat;
	}
	
	public Double getLat(){
		return lat;
	}
		public void setBusinessUserId(Long businessUserId){
		this.businessUserId=businessUserId;
	}
	
	public Long getBusinessUserId(){
		return businessUserId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public String[] getPics() {
		return pics;
	}

	public void setPics(String[] pics) {
		this.pics = pics;
	}

	public String[] getIntropics() {
		return intropics;
	}

	public void setIntropics(String[] intropics) {
		this.intropics = intropics;
	}

	public List<TicketsVo> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketsVo> tickets) {
		this.tickets = tickets;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
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
		
}

